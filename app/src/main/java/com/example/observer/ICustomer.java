package com.example.observer;
/**
 * 所有的消费者人群接口  作为观察者
 * /**
 * 观察者接口
 * 作用:提供update方法,收到被观察者通知,实现具体业务
 * @author qch
 * @time 2014/9/15
 */
public interface ICustomer {
	void update(double price,double lastPrice);
}
