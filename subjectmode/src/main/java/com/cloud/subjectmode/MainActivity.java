package com.cloud.subjectmode;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.cloud.subjectmode.precenter.CallBack;
import com.cloud.subjectmode.precenter.ContentObserver;
import com.cloud.subjectmode.precenter.ContentSubject;
import com.squareup.leakcanary.RefWatcher;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = (TextView) findViewById(R.id.tvcontent);
        iv = (ImageView) findViewById(R.id.iv);
        CallBack callBack = new CallBack() {
            @Override
            public void setText(String s) {
                tv.setText(s);
            }
        };
        String y = "ss";
        ContentSubject contentSubject = new ContentSubject(y);   //被观察者  获取里面y的变化
        ContentObserver contentObserver = new ContentObserver("哈哈", callBack);  //观察者   //观察者通过被观察者的数据相应的做一些改动
        contentSubject.addListener(contentObserver);  //被观察者的一举一动都需要让观察者知道  所以需要在被观察者中添加一个观察者的接口作为通知
        //  contentSubject.removeListener(contentObserver);
        contentSubject.notyfication();

        setImage();
        setLeaks();   //制作内存泄露

    }

    private void setLeaks() {
        Vector vector = new Vector(10);
        for (int i = 0; i < 100; i++) {
            Object o = new Object();
            o=null;
            vector.add(o);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
        refWatcher.watch(this);//内存泄露检测
    }




    private void setImage() {

        // String url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487239819106&di=ab44f1d3cbcf9084e1fa639f9dd9db26&imgtype=0&src=http%3A%2F%2Fuserimage7.360doc.com%2F16%2F0316%2F16%2F31705301_201603161601230500934437.gif";
        Glide.with(this)
                .load(R.drawable.ab)

                .transform(new CircleTransform(this))
                .error(R.drawable.ab)
                .into(iv);
    }


    public void tets(String uri) throws IOException {
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream is = connection.getInputStream();
        BufferedInputStream bi = new BufferedInputStream(is);
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
        bi.close();
        is.close();
        Bitmap bm = BitmapFactory.decodeStream(is);
    }


    public static class CircleTransform extends BitmapTransformation {

     /*   public CircleTransform(Context context) {
            super(context);
        }

        @Override protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override public String getId() {
            return getClass().getName();
        }*/
        private static float radius = 0f;

        public CircleTransform(Context context) {
            this(context, 4);
        }

        public CircleTransform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override public String getId() {
            return getClass().getName() + Math.round(radius);
        }
    }



}