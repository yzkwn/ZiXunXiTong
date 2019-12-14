package com.hhlt.konsultado.config;


import com.hhlt.konsultado.interceptor.MyInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


public class WebmvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/api/**").excludePathPatterns("/api/landing","/api/login","/api/registered");
        WebMvcConfigurer.super.addInterceptors(registry);
    }




//    /**
//     * 添加静态资源文件，外部可以直接访问地址
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        super.addResourceHandlers(registry);
//    }

}
