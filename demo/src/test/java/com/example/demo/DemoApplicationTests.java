package com.example.demo;


import com.example.demo.entity.User;
import com.example.demo.enums.StatusEnum;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTests {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testJpa(){
        User user = new User();
        user.setDate(new Date());
        user.setStatusEnum(StatusEnum.Active);
        userRepository.save(user);
    }

    @Test
    public void test() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("email", "844072586@qq.com");
        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers, map);
        String s = restTemplate.postForObject("", entity, String.class);
        System.out.println(s);
    }

    @Test
    public void test1() {
        ExecutorService service = new ThreadPoolExecutor(2, 4, 100, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000), new ThreadFactory() {
            final AtomicInteger increment = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "??????:" + increment.incrementAndGet());
            }
        });

        try {
            service.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Future<String> future = service.submit(() -> {
            TimeUnit.SECONDS.sleep(5);
            return Thread.currentThread().getName();
        });

        try {
            future.get(4, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("=================================================================");

        service.shutdown();


        CompletionService<String> csv = new ExecutorCompletionService<>(Executors.newFixedThreadPool(1));

        Future<String> submit = csv.submit(() -> {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("==================================");
            return Thread.currentThread().getName();
        });

    }


    @Test
    public void test2() throws Exception {
//        HikariDataSource source=new HikariDataSource();
//        source.setJdbcUrl("jdbc:mysql://47.115.177.122:3317/ry-config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC");
//        source.setUsername("root");
//        source.setPassword("root");
//        Environment environment = new Environment.Builder("prod")
//                .transactionFactory(new JdbcTransactionFactory())
//                .dataSource(source).build();
//        Configuration config=new Configuration();
//        config.setEnvironment(environment);
//        config.addMapper(ConfigInfoMapper.class);
//        SqlSessionFactory factory =  new SqlSessionFactoryBuilder().build(config);
//        SqlSession session = factory.openSession();
//        ConfigInfoMapper mapper = session.getMapper(ConfigInfoMapper.class);
//        List<Map<String, Object>> maps = mapper.selectList();
//        System.out.println(maps);


    }


//    interface ConfigInfoMapper{
//        @Select("SELECT * FROM config_info")
//        List<Map<String,Object>> selectList();
//    }


    @Test
    public void test3() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
        EntityManager entityManager = emf.createEntityManager();

    }


    @Test
    public void test4() throws Exception {
        avutil.av_log_set_level(avutil.AV_LOG_INFO);
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(new FileInputStream("D:\\test\\1111\\2.mp4"));
        grabber.setOption("re","");
        grabber.setOption("stimeout", "5000000");
        grabber.setFrameRate(30);
         grabber.setVideoBitrate(3000000);
        grabber.start();



        double frameRate = grabber.getVideoFrameRate();
        int bitRate = grabber.getVideoBitrate();
        int width = grabber.getImageWidth();
        int height = grabber.getImageHeight();
        long duraion = grabber.getLengthInTime();// ??????????????????????????????grabber.getFormatContext().duration();??????????????????
        System.err.println("????????????:{??????=" + width + ",??????=" + height + "????????????" + frameRate + "????????????=" + bitRate + "???????????????" + duraion);
        System.out.println("???????????????" + grabber.getFormatContext().duration());


        System.out.println(grabber.getAudioCodec()); //86018 avcodec.AV_CODEC_ID_AAC
        System.out.println(grabber.getVideoCodec()); //27  avcodec.AV_CODEC_ID_H264
        System.out.println(grabber.getImageWidth());
        System.out.println(grabber.getImageHeight());
        System.out.println(grabber.getFormat());
        System.out.println(grabber.getFrameRate()); //29.94537984371224???/???
        System.out.println(grabber.getVideoBitrate());//344088
        System.out.println(grabber.getLengthInTime() / (1000 * 1000));//169


        String s = "rtsp://8.136.234.62:30007/test/" + System.currentTimeMillis();

        System.out.println(s);
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(s, grabber.getImageWidth(), grabber.getImageHeight(),grabber.getAudioChannels());
        recorder.setOption("rtsp_transport", "tcp");
        recorder.setFrameRate(30);
        recorder.setVideoBitrate(3000000);
        recorder.start(grabber.getFormatContext());


        long err_index = 0;//????????????????????????????????????
        // ????????????????????????????????????????????????pts???????????????0??????????????????
        grabber.flush();

        for(int no_frame_index = 0; no_frame_index < 10 || err_index > 1;) {
            AVPacket pkt;
            try {
                pkt = grabber.grabPacket();
                if(pkt == null || pkt.size() <= 0 || pkt.data() == null) {
                    //????????????????????????
                    no_frame_index ++;
                    continue;
                }
                //??????????????????????????????
                recorder.recordPacket(pkt);
            } catch (IOException e) {//????????????
                log.error(e.getMessage(),e);
                err_index++;
            }
        }
        System.out.println(err_index);
        recorder.close();
        grabber.close();

    }

    @Test
    public void test5()throws Exception{
        avutil.av_log_set_level(avutil.AV_LOG_INFO);
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("desktop");
        grabber.setFormat("gdigrab");
        grabber.setOption("offset_x", "0");
        grabber.setOption("offset_y", "0");
        grabber.setOption("framerate", "25");
        grabber.setOption("draw_mouse", "0");
        grabber.setOption("video_size", "1920x1080");



        grabber.start();
        CanvasFrame canvas = new CanvasFrame("?????????");
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setAlwaysOnTop(true);

        while (canvas.isDisplayable()) {
            canvas.showImage(grabber.grab());
            TimeUnit.MILLISECONDS.sleep(40);
        }

        grabber.stop();
    }

    private static void playVideo(String url, double videoPlaySpeed) throws Exception, InterruptedException {

        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(url);
        grabber.setFormat("mp4");// ??????gdigrab???????????????
        grabber.start();

        double frameRate = grabber.getVideoFrameRate();
        int bitRate = grabber.getVideoBitrate();
        int width = grabber.getImageWidth();
        int height = grabber.getImageHeight();
        long duraion = grabber.getLengthInTime();// ??????????????????????????????grabber.getFormatContext().duration();??????????????????
        System.err.println(
                "????????????:{??????=" + width + ",??????=" + height + "????????????" + frameRate + "????????????=" + bitRate + "???????????????" + duraion);
        int videoStreamIndex = grabber.getVideoStream();
        CanvasFrame canvas = new CanvasFrame("????????????", CanvasFrame.getDefaultGamma());// ??????????????????
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setAlwaysOnTop(true);
        Frame frame = null;

        long startRealTime;// ??????????????????
        long startViewTime = 0;
        long consumTime = 0;
        // ??????????????????
        int i = 0;
        long lastTime = 0;
        for (startRealTime = System.nanoTime(); (frame = grabber.grabFrame()) != null;) {
            // ????????????
            if (frame.streamIndex == videoStreamIndex) {
                // ????????????????????????????????????????????????sleep(1000/??????)??????????????????????????????????????????????????????????????????(1000/??????)???????????????????????????
                if (lastTime > 0) {
                    long time = frame.timestamp - lastTime;
                    if (videoPlaySpeed > 0 && videoPlaySpeed != 1) {
                        time /= videoPlaySpeed;
                    }
                    consumTime = (System.nanoTime() - startViewTime) / 1000;
                    if (consumTime < time) {
                        long interval = (time - consumTime) / 1000;
                        int nano = (int) (time % (interval * 1000));
                        Thread.sleep(interval, nano);
                    }
                }
                startViewTime = System.nanoTime();
                canvas.showImage(frame);// ????????????
                i++;
                lastTime = frame.timestamp;
            }
        }
        double inteval = (System.nanoTime() - startRealTime) / 1000000000.0;
        System.out.println("????????????" + inteval + "??????????????????" + i + ",???????????????" + i / inteval);
        grabber.stop();
        canvas.dispose();
    }
}
