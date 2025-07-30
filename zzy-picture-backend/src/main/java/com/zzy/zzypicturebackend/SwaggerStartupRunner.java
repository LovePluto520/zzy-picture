package com.zzy.zzypicturebackend;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SwaggerStartupRunner implements ApplicationRunner {

    @Value("${server.port:8080}")
    private String port;

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String swaggerUrl = "http://localhost:" + port + contextPath + "/doc.html";
        System.out.println("\n============================================");
        System.out.println("Swagger UI 已启动，访问地址:");
        System.out.println(swaggerUrl);
        System.out.println("============================================\n");
    }
}