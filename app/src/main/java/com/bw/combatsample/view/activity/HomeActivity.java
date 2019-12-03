package com.bw.combatsample.view.activity;

import android.widget.GridView;
import android.widget.Toast;

import com.bw.combatsample.R;
import com.bw.combatsample.base.BaseActivity;
import com.bw.combatsample.contract.IHomeContract;
import com.bw.combatsample.model.bean.Lawyer;
import com.bw.combatsample.presenter.HomePresenter;

public class HomeActivity extends BaseActivity<HomePresenter> implements IHomeContract.IView {

    private GridView gridView;


    @Override
    protected HomePresenter providePresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initData() {
        mPresenter.getHomeData();
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
