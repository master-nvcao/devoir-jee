package com.my.devoir.miscroservicecommandes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MiscroserviceCommandesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiscroserviceCommandesApplication.class, args);
    }

}
