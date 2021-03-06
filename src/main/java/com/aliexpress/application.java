package com.aliexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@Configuration
public class application {
    public static void main(String[] args){
    SpringApplication.run(application.class,args);
}
/**
 * 文件上传配置
 * @return
 */
@Bean
public MultipartConfigElement multipartConfigElement() {
    MultipartConfigFactory factory = new MultipartConfigFactory();
    //单个文件最大
    factory.setMaxFileSize("10240MB"); //KB,MB
    /// 设置总上传数据总大小
    factory.setMaxRequestSize("102400MB");
    return factory.createMultipartConfig();
}
}
