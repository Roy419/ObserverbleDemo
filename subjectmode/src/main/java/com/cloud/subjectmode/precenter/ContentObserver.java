package com.cloud.subjectmode.precenter;



/**
 * Created by Cloud on 2017/2/15.
 * 具体观察者实现
 */
public class ContentObserver implements Observer {
     private CallBack callBack;
     private String content;
    public ContentObserver(String content , CallBack callback){
        this.content = content;
        this.callBack = callback;
    }

    @Override
    public void updata(String s) {
        callBack.setText(s+"hahahhah++"+content);
    }
}
