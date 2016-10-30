package com.example.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/hide").setViewName("base/index");
        registry.addViewController("/").setViewName("sale/report_cus");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/inherit").setViewName("inherit");
        registry.addViewController("/report_sale").setViewName("sale/report");
        registry.addViewController("/report_cus").setViewName("sale/report_cus");
        registry.addViewController("/report_unsalable").setViewName("sale/report_unsalable");
        registry.addViewController("/report_promote").setViewName("sale/report_promote");
//        registry.addViewController("/footer").setViewName("footer");
//        registry.addViewController("/login").setViewName("login");
    }

}