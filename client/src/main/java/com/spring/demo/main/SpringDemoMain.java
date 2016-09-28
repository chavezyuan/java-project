package com.spring.demo.main;

import java.io.IOException;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.demo.controller.DemoController;

public class SpringDemoMain {

	public static void main(String[] args) throws IOException {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring.xml");
		DemoController controller = (DemoController)beanFactory.getBean("demoController");
		
		controller.test();
	}
}
