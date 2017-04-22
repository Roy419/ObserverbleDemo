package com.cloud.subjectmode.precenter;



/**
 * Created by Cloud on 2017/2/15.
 * 被观察者接口
 */
public interface Subject {
    void addListener(Observer observer);
    void removeListener(Observer observer);
    void notyfication();
}
