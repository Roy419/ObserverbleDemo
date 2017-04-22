package com.cloud.subjectmode.precenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cloud on 2017/2/15.
 * 具体被观察者实现
 */
public class ContentSubject implements Subject {
    private String s;
    public  ContentSubject(String s){
        this.s = s;
    }

    List<Observer> list = new ArrayList<>();
    @Override
    public void addListener(Observer observer) {
     list.add(observer);
    }

    @Override
    public void removeListener(Observer observer) {
      list.remove(observer);
    }

    @Override
    public void notyfication() {
        if(list != null && list.size()>0){
            //遍历所有观察者 发布通知  实现具体业务逻辑
            for (Observer observer :
                    list) {
                observer.updata(s);
            }
        }

    }
}
