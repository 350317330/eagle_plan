package com.example.demo.utils;

import org.apache.commons.codec.binary.Hex;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;

import javax.crypto.KeyGenerator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.NoSuchAlgorithmException;

/**
 * @author shisi
 * @date 2021/06/01 09:16
 **/
public class FFmpegUtils {
    private static byte[] genAesKey ()  {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            return keyGenerator.generateKey().getEncoded();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * 在指定的目录下生成key_info, key文件，返回key_info文件
     * @param folder
     * @throws IOException
     */
    private static Path genKeyInfo(String folder) throws IOException {
        // AES 密钥
        byte[] aesKey = genAesKey();
        // AES 向量
        String iv = Hex.encodeHexString(genAesKey());

        // key 文件写入
        Path keyFile = Paths.get(folder, "key");
        Files.write(keyFile, aesKey, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        // key_info 文件写入
        StringBuilder stringBuilder = new StringBuilder();
        // m3u8加载key文件网络路径
        stringBuilder.append("key").append( System.getProperty("line.separator"));
        // FFmeg加载key_info文件路径
        stringBuilder.append(keyFile.toString()).append( System.getProperty("line.separator"));
        // ASE 向量
        stringBuilder.append(iv);

        Path keyInfo = Paths.get(folder, "key_info");

        Files.write(keyInfo, stringBuilder.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        return keyInfo;
    }

    static String PATH="D:\\personal\\m3u8JavaTest1\\PMP\\0\\";

     static void uploadToM3u8() throws IOException {
         Path path = genKeyInfo(PATH);

         avutil.av_log_set_level(avutil.AV_LOG_INFO);
         FFmpegLogCallback.set();
         FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(new FileInputStream(PATH+"test.mp4"));
         grabber.start();
         FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(PATH+"test.m3u8", grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
         recorder.setFormat("hls");
         recorder.setOption("hls_time", "5");
         recorder.setOption("hls_list_size", "0");
         recorder.setOption("hls_flags", "delete_segments");
         recorder.setOption("hls_delete_threshold", "1");
         recorder.setOption("hls_segment_type", "mpegts");
         recorder.setOption("hls_segment_filename", PATH+"test-%d.ts");
         recorder.setOption("hls_key_info_file", path.toString());
         // http属性
         recorder.setOption("method", "POST");

         recorder.setFrameRate(25);
         recorder.setGopSize(2 * 25);
         recorder.setVideoQuality(1.0);
         recorder.setVideoBitrate(10 * 1024);
         recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
         recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
         recorder.start();
         Frame frame;
         while ((frame = grabber.grabImage()) != null) {
             try {
                 recorder.record(frame);
             } catch (FrameRecorder.Exception e) {
                 e.printStackTrace();
             }
         }
         recorder.setTimestamp(grabber.getTimestamp());
         recorder.close();


         grabber.close();
    }

    public static void main(String[] args)throws Exception {
        uploadToM3u8();
    }


}
