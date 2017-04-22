package com.example.observer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		content = (TextView) findViewById(R.id.content);
		final StringBuffer sb = new StringBuffer();
		CallBack cb = new CallBack() {

			@Override
			public void setTextContent(String str) {
				// TODO Auto-generated method stub
				sb.append(str + "\n");
				content.setText(sb);
			}
		};
		BeichenBikeStore bs = new BeichenBikeStore();
		bs.addCustomer(new Student("张女士", "19", "中国", cb));
		bs.addCustomer(new Student("姜先生", "25", "中国", cb));
		bs.setPrice(2000);
		bs.setLastprice(10000);
		bs.notifyCustomer();

		bs.setPrice(1000);
		bs.setLastprice(2000);
		bs.notifyCustomer();

		Student xb = new Student("薛先生", "28", "中国", cb);
		bs.addCustomer(xb);
		sb.append("薛先生加入!" + "\n");

		bs.setPrice(500);
		bs.setLastprice(1000);
		bs.notifyCustomer();

		sb.append("薛先生买车成功,正赶往回家的路上..." + "\n");
		bs.removeCustomer(xb);
		bs.notifyCustomer();

		bs.setPrice(5000);
		bs.setLastprice(500);
		bs.notifyCustomer();
	}

}
