package com.hhlt.konsultado.config;


import com.hhlt.konsultado.table.PageTableArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	/**
	 * 跨域支持
	 * 
	 * @return
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*");
			}
		};
	}

	/**
	 * datatable分页解析
	 * 
	 * @return
	 */
	@Bean
	public PageTableArgumentResolver tableHandlerMethodArgumentResolver() {
		return new PageTableArgumentResolver();
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(tableHandlerMethodArgumentResolver());
	}

	/**
	 * 上传文件根路径
	 */
//	@Value("${files.path}")
//	private String filesPath;
//	@Value("${localGoodsUrl}")
//	private String localGoodsUrl;
//	@Value("${localFeedbackUrl}")
//	private String localFeedbackUrl;


//	@Value("${goodsUrl}")
//	private String goodsUrl;
//	@Value("${feedbackUrl}")
//	private String feedbackUrl;
	/**
	 * 外部文件访问
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/statics/**");
//				.addResourceLocations(ResourceUtils.FILE_URL_PREFIX + filesPath + File.separator)
//				.addResourceLocations(ResourceUtils.FILE_URL_PREFIX + goodsUrl + File.separator)
//		        .addResourceLocations(ResourceUtils.FILE_URL_PREFIX + feedbackUrl + File.separator);
	}

}
