package com.example.demo.javacv;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;

import java.io.File;

/**
 * @author shisi
 * @date 2021/05/27 10:55
 **/
public class JavacvUtils {


    /**
     * 获取视频时长，单位为秒
     * @param file
     * @return 时长（s）
     */
    public static Long getVideoTime(File file){
        Long times = 0L;
        try {
            FFmpegFrameGrabber ff = new FFmpegFrameGrabber(file);
            ff.start();
            times = ff.getLengthInTime()/(1000*1000);
            ff.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }

    public static void main(String[] args) {
        Long videoTime = getVideoTime(new File("D:\\personal\\m3u8JavaTest1\\0\\金字塔.mp4"));
        System.out.println(videoTime);
    }
}
