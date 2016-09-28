package com.yuan.java.test.guava;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import org.joda.time.LocalTime;

import java.io.Serializable;

public class Time implements Serializable, Comparable<Time> {
	private static final long serialVersionUID = -2294839253099392262L;
	
	private int hour;
	private int minute;
	private int second;
	
	public Time() {
		this(0, 0, 0);
	}
	
	public Time(int hour) {
		this(hour, 0, 0);
	}
	public Time(int hour, int minute) {
		this(hour, minute, 0);
	}
	
	public Time(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.valid();
	}
	
	public Time(String timeStr) {
		if(timeStr == null || timeStr.isEmpty())
			throw new RuntimeException("time string is empty");
		String[] timeArr = timeStr.split(":");
		switch(timeArr.length) {
		case 1:
			resolve(timeArr[0].trim(), "", "");
			break;
		case 2:
			resolve(timeArr[0].trim(), timeArr[1].trim(), "");
			break;
		case 3:
			resolve(timeArr[0].trim(), timeArr[1].trim(), timeArr[2].trim());
			break;
		default:
			throw new RuntimeException("time string format is fault");
		}
		this.valid();
	}
	
	private void resolve(String hs, String ms, String ss) {
		try{
			this.hour = hs.isEmpty() ? 0 : Integer.parseInt(hs);
			this.minute = ms.isEmpty() ? 0 : Integer.parseInt(ms);
			this.second = ss.isEmpty() ? 0 : Integer.parseInt(ss);
		} catch(NumberFormatException e) {
			throw new RuntimeException("time string format is fault");
		}
	}
	
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	public long getMillis() {
		return new LocalTime(this.hour, this.minute, this.second).getMillisOfDay();
	}
	public void valid() {
		if(this.hour < 0 || this.hour >= 24 
			|| this.minute < 0 || this.minute >= 60
			|| this.second < 0 || this.second >= 60) {
			throw new RuntimeException("incorrect time args");
		}
	}
	public String toString() {
		return (this.hour < 10 ? ("0" + this.hour) : this.hour) + ":" 
				+ (this.minute < 10 ? ("0" + this.minute) : this.minute) + ":" 
				+ (this.second < 10 ? ("0" + this.second) : this.second);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Time time = (Time) o;

		if (hour != time.hour) return false;
		if (minute != time.minute) return false;
		return second == time.second;

	}

    @Override
    public int hashCode() {
        return Objects.hashCode(this.hour, this.minute, this.second);
    }

    @Override
    public int compareTo(Time o) {
        return ComparisonChain.start().compare(this.hour, o.hour)
                .compare(this.minute, o.minute)
                .compare(this.second, o.second).result();
    }
}
