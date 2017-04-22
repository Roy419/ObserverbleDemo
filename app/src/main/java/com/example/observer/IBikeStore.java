package com.example.observer;

/**
 * 自行车商店接口  作为通知者
 * * 被观察者 接口
 * 作用: 提供增删,通知观察者的方法
 * @author qch
 * @time 2014/9/15
 */
public interface IBikeStore {
	void addCustomer(ICustomer customer);
	void removeCustomer(ICustomer customer);
	void notifyCustomer();
}
