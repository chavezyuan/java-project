package com.spring.demo.po;

import java.util.Date;

public class Config {
	private int id;
	private String key;
	private String value;
	private Date ct_time;
	private Date update_time;
	private int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Date getCt_time() {
		return ct_time;
	}
	public void setCt_time(Date ct_time) {
		this.ct_time = ct_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
