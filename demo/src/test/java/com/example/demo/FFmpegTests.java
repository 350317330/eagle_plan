package com.example.demo;

import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.bytedeco.ffmpeg.global.avcodec.av_packet_unref;

@Slf4j
@SpringBootTest
//@RunWith(SpringRunner.class)
public class FFmpegTests {
    @Test
    public void test()throws Exception{
        avutil.av_log_set_level(avutil.AV_LOG_INFO);
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("D:\\test\\1111\\2.mp4");
        grabber.start();
        double frameRate = grabber.getVideoFrameRate();
        int bitRate = grabber.getVideoBitrate();
        int width = grabber.getImageWidth();
        int height = grabber.getImageHeight();
        long duration = grabber.getLengthInTime();// 视频时长，也可以通过grabber.getFormatContext().duration();获取视频时长
        log.info( "视频信息:【宽度={},高度={}，帧率：{}，比特率={},视频时长：{}】",width,height,frameRate,bitRate,duration/(1000*1000));

        FFmpegFrameRecorder recorder = FFmpegFrameRecorder.createDefault("rtsp://8.136.234.62:30007", grabber.getImageWidth(), grabber.getImageHeight());
        recorder.setOption("rtsp_transport", "tcp");
        recorder.start(grabber.getFormatContext());

        while(true){
            AVPacket packet = grabber.grabPacket();
            if(packet==null){
                break;
            }
            recorder.recordPacket(packet);
            av_packet_unref(packet);
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
