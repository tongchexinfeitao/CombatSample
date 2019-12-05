package com.bw.combatsample.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.combatsample.R;
import com.bw.combatsample.base.BaseActivity;
import com.bw.combatsample.contract.IHomeContract;
import com.bw.combatsample.model.bean.Lawyer;
import com.bw.combatsample.presenter.HomePresenter;

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
                String photoUrl = "http://blog.zhaoliang5156.cn/api/images/01.jpg";
                Glide.with(HomeActivity.this).load(photoUrl)
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
