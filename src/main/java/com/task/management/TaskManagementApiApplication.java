package com.task.management;

import com.task.management.infra.security.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableConfigurationProperties(JwtProperties.class)
@EnableSpringDataWebSupport(
        pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO
)
@SpringBootApplication
public class TaskManagementApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskManagementApiApplication.class, args);
    }
}