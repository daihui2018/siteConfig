package com.johnjadd;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//Enabling CORS
//必须添加CROS，否则JavaScript在访问API时会出现 Access-Control-Allow-Origin 错误！！
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("*");
		//TODO Auto-generated method stub
		// super.addCorsMappings(registry);
	}

}
