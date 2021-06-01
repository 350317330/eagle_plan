package com.example.demo.javacv;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shisi
 * @date 2021/05/27 16:28
 **/
@Slf4j
public class FfmpegUtil implements Closeable {
    /**
     * 每个ts时间
     * 单位: 秒
     */
    private final static int RANGE = 60;
    private FFmpegFrameRecorder recorder;
    private FFmpegFrameGrabber grabber;

    public FfmpegUtil from(String path) throws Exception {
        log.info("from-{}", DateUtil.now());
        grabber = FFmpegFrameGrabber.createDefault(path);
        grabber.setOption("safe", "0");
        grabber.setFormat("concat");
        grabber.start();

        log.info("from-{}", DateUtil.now());
        return this;
    }

    public FfmpegUtil to(String mp4SavePath) throws Exception {
        log.info("to-{}", DateUtil.now());
        recorder = FFmpegFrameRecorder.createDefault(mp4SavePath, grabber.getImageWidth(), grabber.getImageHeight());
        recorder.setFormat("hls");
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        recorder.setVideoBitrate(10 * 1024 * 1024);
        recorder.setFrameRate(20);
        recorder.setGopSize(RANGE);
        recorder.setOption("hls_time", String.valueOf(RANGE));
        recorder.setOption("hls_list_size", "0");
//        recorder.setOption("hls_allow_cache", "1");
//        recorder.setOption("hls_segment_filename", "/Users/kuoxin/Movies/56K/'TI2014_56k_%05d.ts'");
        recorder.setOption("preset", "fast");
//        recorder.setOption("force_key_frames", StrUtil.format("\"expr:gte(t,n_forced*{})\"", RANGE));
        recorder.start();
        log.info("to-{}", DateUtil.now());
        return this;
    }

    public FfmpegUtil go() throws Exception {
        log.info("go-{}", DateUtil.now());
        Frame imageFrame = null;
        while ((imageFrame = grabber.grabImage()) != null) {
            recorder.record(imageFrame);
        }
        log.info("go-{}", DateUtil.now());
        return this;
    }

    @Override
    public void close() {
        try {
            this.recorder.release();
            this.grabber.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void toHls(String mvPath) {
        FfmpegUtil ffmpegUtil = new FfmpegUtil();
        try {
            //生成转换规则
            String img2VideoProp = createImg2VideoProp(mvPath);
            String parent = StrUtil.subBefore(mvPath, CharUtil.DOT, true);
            //ts文件默认将生成在同级目录下
            String m3u8 = StrUtil.format("{}_hls/hls.m3u8", parent);
            FileUtil.touch(m3u8);
            //调用转换
            ffmpegUtil.from(img2VideoProp).to(m3u8).go();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            ffmpegUtil.close();
        }
    }

    private static String createImg2VideoProp(String mvPath) {
        List<File> files = findHlsImgByPath(mvPath);
        Long tempTimestamp = null;
        List<String> lines = new ArrayList<>();
        for (File file : files) {
            String mainName = FileUtil.mainName(file);
            if (!NumberUtil.isLong(mainName)) {
                continue;
            }
            long timestamp = NumberUtil.parseLong(mainName);
            if (tempTimestamp != null) {
                double div = NumberUtil.div(timestamp - tempTimestamp, 1000);
                lines.add(StrUtil.format("duration {}", div));
            }
            tempTimestamp = timestamp;
            lines.add(StrUtil.format("file '{}'", file.getAbsolutePath()));
        }
        File file = FileUtil.writeLines(lines, mvPath + ".hls", Charset.defaultCharset());
        return file.getPath();
    }

    private static List<File> findHlsImgByPath(String mvPath) {
        String parent = StrUtil.subBefore(mvPath, CharUtil.DOT, true);
        List<File> files = FileUtil.loopFiles(parent);
        Assert.notEmpty(files, "[{}]未找到图片文件", parent);
        return files.stream().sorted(Comparator.comparing(File::getName)).collect(Collectors.toList());
    }

    public static void main(String[] args)throws Exception{

    }



}
