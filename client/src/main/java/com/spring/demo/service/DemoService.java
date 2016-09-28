package com.spring.demo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.demo.mapper.DemoMapper;
import com.spring.demo.po.Config;

@Service
public class DemoService {

	private static Gson gson = new GsonBuilder().create();

	@Autowired
	private DemoMapper demoMapper;
	int n = 100000;
	public String test() {
		demoMapper.connect();
//		List<Config> config
		long begin = System.currentTimeMillis();
		for(int i= 0 ; i<n ; i++) {
			 demoMapper.select();
		}
		long end = System.currentTimeMillis();
		System.out.println("mybatis:" + (end - begin));
		return null;
	}

	public String test2() {
		try {
			for(int j=0; j<200; j++) {
			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/test";
			// 通过DriverManager获取连接
			Connection conn = DriverManager.getConnection(url, "root", "");
			System.out.println("conn:" + conn);
			// 准备操作数据库
			// Statement:用于执行静态SQL语句并返回它所生产结果的对象
			Statement stm = conn.createStatement();
			String sql = "select * from config where status = 1";
			ResultSet rs = null;
			long begin = System.currentTimeMillis();
			for(int i=0; i<n; i++) {
				rs = stm.executeQuery(sql);
			}
			long end = System.currentTimeMillis();
			System.out.println("jdbc: " + (end-begin));
//			List<Config> configList = new ArrayList<Config>();
//			while(rs.next()){
//		         //Retrieve by column name
//				Config config = new Config();
//				config.setId(rs.getInt("id"));
//				config.setKey(rs.getString("key"));
//				config.setValue(rs.getString("value"));
//				config.setStatus(rs.getInt("status"));
//				config.setCt_time(rs.getDate("ct_time"));
//				config.setUpdate_time(rs.getDate("update_time"));
//				configList.add(config);
//		    }
			System.out.println("link times: " + j + "; result: " + rs.getInt("id"));
			}
//			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
