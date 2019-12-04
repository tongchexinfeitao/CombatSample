package com.bw.combatsample.view.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bw.combatsample.App;
import com.bw.combatsample.R;
import com.bw.combatsample.base.BaseActivity;
import com.bw.combatsample.contract.IHomeContract;
import com.bw.combatsample.model.bean.Lawyer;
import com.bw.combatsample.presenter.HomePresenter;
import com.bw.combatsample.util.NetUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends BaseActivity<HomePresenter> implements IHomeContract.IView {


    private View button;
    private ImageView imageView;

    @Override
    protected HomePresenter providePresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        button = findViewById(R.id.btn_request);
        imageView = findViewById(R.id.img);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_home2;
    }


    @Override
    public void onHomeSuccess(Lawyer lawyer) {
        Toast.makeText(HomeActivity.this, "请求到了数据" + lawyer.getCode(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHomeFailure(Throwable throwable) {
        Toast.makeText(HomeActivity.this, "请求失败" + throwable.toString(), Toast.LENGTH_SHORT).show();
    }
}
