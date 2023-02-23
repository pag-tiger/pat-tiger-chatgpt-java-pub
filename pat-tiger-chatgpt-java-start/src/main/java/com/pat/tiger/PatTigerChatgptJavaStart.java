package com.pat.tiger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Description:
 * @Author: Hemon
 * @Date: 2023/2/16
 **/
@SpringBootApplication
@EnableAsync
public class PatTigerChatgptJavaStart {

    public static void main(String[] args) {
        SpringApplication.run(PatTigerChatgptJavaStart.class, args);
    }

}