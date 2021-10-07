package com.example.demo;

import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FFmpegLogCallback;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.bytedeco.ffmpeg.global.avcodec.av_packet_unref;

@Slf4j
@SpringBootTest
//@RunWith(SpringRunner.class)
public class FFmpegTests {
    @Test
    public void test()throws Exception{
        double framerate;

        avutil.av_log_set_level(avutil.AV_LOG_INFO);
        FFmpegLogCallback.set();

        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("D:\\test\\1111\\2.mp4");
        grabber.start();
        // 部分监控设备流信息里携带的帧率为9000，如出现此问题，会导致dts、pts时间戳计算失败，播放器无法播放，故出现错误的帧率时，默认为25帧
        if (grabber.getFrameRate() > 0 && grabber.getFrameRate() < 100) {
            framerate = grabber.getFrameRate();
        } else {
            framerate = 25.0;
        }

        FFmpegFrameRecorder recorder = FFmpegFrameRecorder.createDefault("rtsp://8.136.234.62:30007", grabber.getImageWidth(), grabber.getImageHeight());
        recorder.setInterleaved(true);
        // 关键帧间隔，一般与帧率相同或者是视频帧率的两倍
        recorder.setGopSize((int) framerate * 2);
        // 视频帧率(保证视频质量的情况下最低25，低于25会出现闪屏)
        recorder.setFrameRate(framerate);
        // 设置比特率
        recorder.setVideoBitrate(grabber.getVideoBitrate());
        // 封装flv格式
        recorder.setFormat("flv");
        // h264编/解码器
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
        Map<String, String> videoOption = new HashMap<>();

        // 该参数用于降低延迟
        videoOption.put("tune", "zerolatency");
        /**
         ** 权衡quality(视频质量)和encode speed(编码速度) values(值)： *
         * ultrafast(终极快),superfast(超级快), veryfast(非常快), faster(很快), fast(快), *
         * medium(中等), slow(慢), slower(很慢), veryslow(非常慢) *
         * ultrafast(终极快)提供最少的压缩（低编码器CPU）和最大的视频流大小；而veryslow(非常慢)提供最佳的压缩（高编码器CPU）的同时降低视频流的大小
         */
        videoOption.put("preset", "ultrafast");
        // 画面质量参数，0~51；18~28是一个合理范围
        videoOption.put("crf", "28");
        recorder.setOptions(videoOption);
        AVFormatContext fc = grabber.getFormatContext();
        recorder.start(fc);
        // 清空探测时留下的缓存
        grabber.flush();

        AVPacket pkt;
        long dts = 0;
        long pts = 0;
        int timebase;
        int err_index=0;
        int exitcode=0;


        for (int no_frame_index = 0; no_frame_index < 5 && err_index < 5;) {
            long time1 = System.currentTimeMillis();
            if (exitcode == 1) {
                break;
            }
            pkt = grabber.grabPacket();
            if (pkt == null || pkt.size() == 0 || pkt.data() == null) {
                // 空包记录次数跳过
                no_frame_index++;
                continue;
            }
            // 过滤音频
            if (pkt.stream_index() == 1) {
                av_packet_unref(pkt);
                continue;
            }

            // 矫正sdk回调数据的dts，pts每次不从0开始累加所导致的播放器无法续播问题
            pkt.pts(pts);
            pkt.dts(dts);
            err_index += (recorder.recordPacket(pkt) ? 0 : 1);
            // pts,dts累加
            timebase = grabber.getFormatContext().streams(pkt.stream_index()).time_base().den();
            pts += timebase / (int) framerate;
            dts += timebase / (int) framerate;
            // 将缓存空间的引用计数-1，并将Packet中的其他字段设为初始值。如果引用计数为0，自动的释放缓存空间。
            av_packet_unref(pkt);

            long endtime = System.currentTimeMillis();
            if ((long) (1000 /framerate) - (endtime - time1) > 0) {
                Thread.sleep((long) (1000 / framerate) - (endtime - time1));
            }
        }

        recorder.stop();
        grabber.stop();

    }

    @Test
    public void test1()throws Exception{
        int a=0;
        Integer b=0;


        Class<Integer> integerClass = int.class;
        Class<Integer> integerClass1 = Integer.class;
        System.out.println(integerClass.equals(integerClass1));



        Class<User> userClass = User.class;
        Field[] fields = userClass.getDeclaredFields();
        Method[] methods = userClass.getDeclaredMethods();
        Arrays.stream(methods).forEach(method -> {
            System.out.println(method.getName());
            System.out.println(method.getReturnType());
            if(int.class==method.getReturnType()){
                System.out.println("====================================================");
            }

        });

        Arrays.stream(fields).forEach(field -> {
            System.out.println(field.getName());
            System.out.println(field.getType());
        });
    }
}
