package com.syy.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * ShrioApplication
 *
 * @author: shiyan
 * @version: 2019-12-06 15:23
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
@SpringBootApplication
@MapperScan("com.syy.springboot.mapper")
@ServletComponentScan
public class ShrioApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShrioApplication.class, args);
    }
}
