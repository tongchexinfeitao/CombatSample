package com.bw.combatsample.util;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bw.combatsample.App;

import java.util.HashMap;
import java.util.Map;

public class NetUtil {


    private final RequestQueue requestQueue;

    private NetUtil() {
        requestQueue = Volley.newRequestQueue(App.app);
    }

    private static NetUtil netUtil;

    // TODO: 2019/12/4 双重校验锁       线程安全的懒汉式
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
        StringRequest stringRequest = new StringRequest(httpUrl, new Response.Listener<String>() {
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
        // TODO: 2019/12/4 必须添加队列，才会执行
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
        // TODO: 2019/12/4 必须添加
        requestQueue.add(stringRequest);
    }

    // TODO: 2019/12/4    new   new  添加
    public void getPhoto(String photoUrl, final ImageView imageView) {
        ImageRequest imageRequest = new ImageRequest(photoUrl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "请求图片失败");
            }
        });
        requestQueue.add(imageRequest);
    }

   public interface MyCallback {
        void onGetJson(String json);

        void onError(Throwable throwable);
    }
}
