package com.bw.combatsample.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.bw.combatsample.R;
import com.bw.combatsample.base.BaseActivity;
import com.bw.combatsample.contract.IHomeContract;
import com.bw.combatsample.model.bean.Lawyer;
import com.bw.combatsample.presenter.HomePresenter;

public class HomeActivity extends BaseActivity implements IHomeContract.IView {

    private GridView gridView;


    @Override
    protected void initData() {
        HomePresenter homePresenter = new HomePresenter();
        // TODO: 2019/11/30 不能直接联网请求，需要调用p层的方法
        homePresenter.getHomeData(this);
    }

    @Override
    protected void initView() {
        gridView = findViewById(R.id.gv);
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
