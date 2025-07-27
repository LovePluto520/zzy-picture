package com.zzy.zzypicturebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.zzy.zzypicturebackend.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class ZzyPictureBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzyPictureBackendApplication.class, args);
    }

}
