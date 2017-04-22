package com.example.observer;

import android.content.Context;
import android.util.Log;

/**
 * 具体观察者,实现业务逻辑
 * */
public class Student implements ICustomer {
	private String name;
	private String age;
	private String nationnality;
	private Context context;
	private CallBack cb;

	public Student(String name, String age, String nationnality, CallBack cb) {
		super();
		this.name = name;
		this.age = age;
		this.nationnality = nationnality;
		this.cb = cb;
	}

	public String getName() {
		return name;
	}

	@Override
	public void update(double price, double lastPrice) {
		// TODO Auto-generated method stub
		if (price < lastPrice) {
			cb.setTextContent("降价了，" + price + "元，" + name + "收到了通知！");
			Log.i("qqq", price + "降价了，" + price + "元，" + name + "收到了通知！");
		}

	}

}
