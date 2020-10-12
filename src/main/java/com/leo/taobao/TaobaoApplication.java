package com.leo.taobao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement

public class TaobaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaobaoApplication.class, args);
    }

}
