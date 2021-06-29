package com.example.demo;


import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void test() {
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("email", "844072586@qq.com");
        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers, map);
        String s = restTemplate.postForObject("", entity, String.class);
        System.out.println(s);
    }

    @Test
    public void test1(){
        ExecutorService service=new ThreadPoolExecutor(1, 1, 100, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), new ThreadFactory() {
            final AtomicInteger increment=new AtomicInteger();
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"测试:"+increment.incrementAndGet());
            }
        });

        try {
            service.awaitTermination(1,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Future<String> future = service.submit(() -> {
            TimeUnit.SECONDS.sleep(5);
            return Thread.currentThread().getName();
        });

        try {
            future.get(4,TimeUnit.SECONDS);
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
    public void test2()throws Exception{
        HikariDataSource source=new HikariDataSource();
        source.setJdbcUrl("jdbc:mysql://47.115.177.122:3317/ry-config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC");
        source.setUsername("root");
        source.setPassword("root");
        Environment environment = new Environment.Builder("prod")
                .transactionFactory(new JdbcTransactionFactory())
                .dataSource(source).build();
        Configuration config=new Configuration();
        config.setEnvironment(environment);
        config.addMapper(ConfigInfoMapper.class);
        SqlSessionFactory factory =  new SqlSessionFactoryBuilder().build(config);
        SqlSession session = factory.openSession();
        ConfigInfoMapper mapper = session.getMapper(ConfigInfoMapper.class);
        List<Map<String, Object>> maps = mapper.selectList();
        System.out.println(maps);


    }


    interface ConfigInfoMapper{
        @Select("SELECT * FROM config_info")
        List<Map<String,Object>> selectList();
    }



}
