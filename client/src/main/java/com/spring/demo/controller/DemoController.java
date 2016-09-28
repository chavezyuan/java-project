package com.spring.demo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.demo.po.Balance;

@Controller("demoController")
@RequestMapping("/demo")
public class DemoController {
	private static Gson gson = new GsonBuilder().create();
//	@Autowired
//	private DemoService demoService;
	
	@RequestMapping("/hello")//(method = RequestMethod.POST)
	@ResponseBody
	public String test() throws IOException{
		
		
		
		Balance balance = new Balance();
		balance.setBalance(100);
		balance.setId(10);
		balance.setUpdateTime(new Date());
		System.out.println(gson.toJson(balance));
		return gson.toJson(balance);
	}
	
	public static void main(String [] args) {
		if(args != null && args.length != 0) {
			for(String arg : args) {
				System.out.println(arg);
			}
		}
		
		Properties ps = System.getProperties();
		
		Set<Object> keys = ps.keySet();
		for(Object key : keys) {
//			System.out.println(key.toString() + " : " +  ps.getProperty(key.toString()));
			if(key.toString().equals("host")) {
				System.out.println(key.toString() + " : " +  ps.getProperty(key.toString()));
			}
		}
		
	}
}
