package com.star;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
* @Description: 
* @Param: 
* @return: 
* @Author: duxing
* @Date: 2020/04/01
*/
@SpringBootApplication
@EnableCaching //缓存 20200409
public class StarSpringbootJdbcTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarSpringbootJdbcTemplateApplication.class, args);
    }

}
