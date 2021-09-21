package com.dream.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@Component
@MapperScan("com.dream.mapper")
public class MybatisConfig {
}
