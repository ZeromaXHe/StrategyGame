package com.zerox.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: zhuxi
 * @Time: 2021/9/28 15:40
 * @Description:
 * @ModifiedBy: zhuxi
 */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        System.exit(
                SpringApplication.exit(
                        SpringApplication.run(MainApplication.class, args)));
    }
}
