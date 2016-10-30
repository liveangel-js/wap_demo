package com.example.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("base/index");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/inherit").setViewName("inherit");
        registry.addViewController("/sale_report").setViewName("sale/report");
        registry.addViewController("/report_cus").setViewName("sale/report_cus");
        registry.addViewController("/report_unsalable").setViewName("sale/report_unsalable");
//        registry.addViewController("/footer").setViewName("footer");
//        registry.addViewController("/login").setViewName("login");
    }

}