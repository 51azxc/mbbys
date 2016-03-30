package com.test1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.test1.util.WebInterceptor;

@Configuration
@EnableWebMvc
@Import({DataConfig.class})
@ComponentScan(basePackages={"com.test1.controller","com.test1.service"})
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public WebInterceptor webInterceptor() {
		return new WebInterceptor();
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/page/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(webInterceptor()).addPathPatterns("/p/*");
	}
}
