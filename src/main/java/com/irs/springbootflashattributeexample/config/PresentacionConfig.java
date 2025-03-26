package com.irs.springbootflashattributeexample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PresentacionConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Añadimos los path que no tienen la necesidad de tener un controlador
        registry.addViewController("/").setViewName("home");
    }
}
