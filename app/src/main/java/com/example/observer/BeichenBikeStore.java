package com.example.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 北辰地区的自行车商店     被观察者具体实现
 * @author qch
 * @time 2014/9/15
 */
public class BeichenBikeStore implements IBikeStore {
	private List<ICustomer> list = new ArrayList<ICustomer>();
	private double price;
	private double lastprice;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getLastprice() {
		return lastprice;
	}

	public void setLastprice(double lastprice) {
		this.lastprice = lastprice;
	}

	@Override
	public void addCustomer(ICustomer customer) {
		// TODO Auto-generated method stub
		list.add(customer);
	}

	@Override
	public void removeCustomer(ICustomer customer) {
		// TODO Auto-generated method stub
		if (list.indexOf(customer) != -1) {
			list.remove(customer);
		}
	}

	@Override
	public void notifyCustomer() {
		// TODO Auto-generated method stub
		for (ICustomer cus : list) {
			cus.update(price, lastprice);
		}
	}

}
