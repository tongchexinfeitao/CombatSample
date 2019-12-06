package com.bw.combatsample.util;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.combatsample.App;
import com.bw.combatsample.R;
import com.bw.combatsample.view.activity.HomeActivity;


import java.util.Map;

public class NetUtil {
    private static NetUtil netUtil;
    private final RequestQueue requestQueue;

    private NetUtil() {
        requestQueue = Volley.newRequestQueue(App.app);
    }

    public static NetUtil getInstance() {
        if (netUtil == null) {
            synchronized (NetUtil.class) {
                if (netUtil == null) {
                    netUtil = new NetUtil();
                }
            }

        }
        return netUtil;
    }

    public void getJsonGet(String httpUrl, final MyCallback myCallback) {
        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, httpUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                myCallback.onGetJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                myCallback.onError(error);
            }
        });
        requestQueue.add(stringRequest);
    }

    public void getJsonPost(String httpUrl, final Map<String, String> map, final MyCallback myCallback) {
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, httpUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                myCallback.onGetJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                myCallback.onError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }


    /**
     * 有可能用Glide代替，那么就不用写了
     */
    public void getPhoto(String photoUrl, final ImageView imageView) {
        Glide.with(imageView).load(photoUrl)
                //默认的展位图
                .placeholder(R.mipmap.ic_launcher)
                //请求错误显示的图片
                .error(R.mipmap.ic_launcher_round)
                //磁盘缓存策略
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //设置圆形图片
                //.apply(RequestOptions.bitmapTransform(new CircleCrop()))
                //设置圆角图片
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(80)))
                .into(imageView);
    }


    public interface MyCallback {
        void onGetJson(String json);

        void onError(Throwable throwable);
    }

    //三个方法判断是否有网、wifi、mobile

}
