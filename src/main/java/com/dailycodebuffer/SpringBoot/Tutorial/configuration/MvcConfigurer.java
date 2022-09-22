package com.dailycodebuffer.SpringBoot.Tutorial.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigurer  implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/home").setViewName("home");
	}
}