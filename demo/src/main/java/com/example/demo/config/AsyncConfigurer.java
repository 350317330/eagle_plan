package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author shisi
 * @date 2021/06/11 09:55
 **/
@Configuration
@EnableAsync
public class AsyncConfigurer implements org.springframework.scheduling.annotation.AsyncConfigurer{
}
