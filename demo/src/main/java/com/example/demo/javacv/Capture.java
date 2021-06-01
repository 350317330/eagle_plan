package com.example.demo.javacv;


import org.springframework.util.StopWatch;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * @author shisi
 * @date 2021/05/27 14:00
 **/
public class Capture {
    public static void main(String[] args) throws Exception{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("=============================");
        Thread.sleep(6000);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dimension = tk.getScreenSize();
        //1920.0
        //1080

        //1536.0
        //864

        Rectangle rec = new Rectangle(0, 0, (int)dimension.getWidth(), (int)dimension.getHeight());
        Robot robot = new Robot();
        int i=0;
        System.out.println(dimension.getWidth());
        System.out.println((int)dimension.getHeight());
        /*while (true){
            BufferedImage bi = robot.createScreenCapture(rec);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            byte[] bytes = baos.toByteArray();
            FileUtils.writeByteArrayToFile(new File("D:\\personal\\m3u8JavaTest1\\0\\"+i+".jpg"),bytes);
            i++;

            Thread.sleep(50);
        }*/

    }
}
