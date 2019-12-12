package com.bw.combatsample.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.combatsample.R;
import com.bw.combatsample.base.BaseActivity;
import com.bw.combatsample.contract.IHomeContract;
import com.bw.combatsample.model.bean.BannerBean;
import com.bw.combatsample.model.bean.Lawyer;
import com.bw.combatsample.presenter.HomePresenter;
import com.bw.combatsample.util.NetUtil;
import com.bw.combatsample.view.adapter.MyMlssAdapter;
import com.bw.combatsample.view.adapter.MyPzshAdapter;
import com.bw.combatsample.view.adapter.MyRxxpAdapter;
import com.bw.combatsample.view.widget.FlowLayout;
import com.bw.combatsample.view.widget.MyTitleView;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

public class HomeActivity extends BaseActivity<HomePresenter> implements IHomeContract.IView {


    private RecyclerView recyclerViewRxxp;
    private RecyclerView recyclerViewMlss;
    private RecyclerView recyclerViewPzsh;
    private XBanner xBanner;
    private FlowLayout flowLayout;
    private MyTitleView myTitleView;

    @Override
    protected HomePresenter providePresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initData() {
        mPresenter.getHomeData("");
    }

    @Override
    protected void initView() {
        myTitleView = findViewById(R.id.search);
        myTitleView.setOnSearchListener(new MyTitleView.OnSearchListener() {
            @Override
            public void onSearch(String searchContent) {
                Toast.makeText(HomeActivity.this, searchContent, Toast.LENGTH_SHORT).show();
                mPresenter.getHomeData("d");
            }
        });


        flowLayout = findViewById(R.id.flow);
        flowLayout.setOnTagClickListener(new FlowLayout.onTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                Toast.makeText(HomeActivity.this, tag, Toast.LENGTH_SHORT).show();
            }
        });

        flowLayout.setOnTagLongClickListener(new FlowLayout.onTagLongClickListener() {
            @Override
            public void onTagLongClick(String tag) {
                Toast.makeText(HomeActivity.this, "删除了" + tag, Toast.LENGTH_SHORT).show();

            }
        });
        xBanner = findViewById(R.id.xbanner);
        recyclerViewRxxp = findViewById(R.id.recycler_rxxp);
        recyclerViewMlss = findViewById(R.id.recycler_mlss);
        recyclerViewPzsh = findViewById(R.id.recycler_pzsh);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_home2;
    }


    @Override
    public void onHomeSuccess(Lawyer lawyer) {
        Lawyer.ResultBean.PzshBean pzsh = lawyer.getResult().getPzsh();
        List<Lawyer.ResultBean.PzshBean.CommodityListBeanX> commodityList = pzsh.getCommodityList();
        for (int i = 0; i < commodityList.size(); i++) {
            Lawyer.ResultBean.PzshBean.CommodityListBeanX commodityListBeanX = commodityList.get(i);
            String commodityName = commodityListBeanX.getCommodityName();
            flowLayout.addTag(commodityName);
        }
//        ObjectAnimator scaleX = ObjectAnimator.ofFloat(flowLayout, "scaleX", 0.1f, 1, 0.5f);
//        scaleX.setDuration(5000);
//        scaleX.setRepeatCount(3);
//        scaleX.setRepeatMode(ValueAnimator.REVERSE);
//        scaleX.start();

        /**
         *  alpha 是透明度  0-1f
         *  scaleY  scaleX  缩放
         *  rotation  旋转  0- 360顺时针  0- -360逆时针
         *  translationX  translationY   平移
         */
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(flowLayout, "scaleX", 0.1f, 0.2f, 0.8f);
        //持续时间
        scaleXAnimator.setDuration(5000);
        //延迟多长时间后再执行动画
        scaleXAnimator.setStartDelay(2000);
        //重复次数
        scaleXAnimator.setRepeatCount(2);
        //重复模式：   RESTART重新开始执行动画 、REVERSE倒着执行动画
        scaleXAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //设置动画执行的插值器 (AccelerateInterpolator 加速  、DecelerateInterpolator减速 、 AccelerateDecelerateInterpolator先加速后减速 )
        scaleXAnimator.setInterpolator(new AccelerateInterpolator());
        //监听动画的一些事件: 开始、结束
        scaleXAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // TODO: 2019/12/12 动画结束回调
                Toast.makeText(HomeActivity.this, "动画结束了", Toast.LENGTH_SHORT).show();
            }
        });
        //如果不用AnimatorSet的时候，需要单独开始执行
//        scaleXAnimator.start();


        //Y方向上的缩放
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(flowLayout, "scaleY", 0.1f, 0.2f, 0.8f);
        //如果不用AnimatorSet的时候，需要单独开始执行
//        scaleYAnimator.start();

        //属性动画集合
        AnimatorSet animatorSet = new AnimatorSet();
        //持续时间
        animatorSet.setDuration(5000);
        //延迟
        animatorSet.setStartDelay(2000);
        //监听
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // TODO: 2019/12/12 结束监听

            }
        });
        //设置同时执行 将多个动画同时执行
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
        //设置按照参数的 先后顺序执行
//        animatorSet.playSequentially(scaleX, scaleY);
        //开始执行动画
//        animatorSet.start();



        //值动画  也可以实现某个对象的属性变化，  ObjectAnimator extends ValueAnimator
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                Log.e("TAG", "animatedValue ==" + animatedValue);
                flowLayout.setAlpha(animatedValue);
            }
        });
        valueAnimator.start();

//        List<Lawyer.ResultBean.RxxpBean.CommodityListBean> rxxp = lawyer.getResult().getRxxp().getCommodityList();
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerViewRxxp.setLayoutManager(linearLayoutManager);
//        recyclerViewRxxp.setAdapter(new MyRxxpAdapter(rxxp));
//
//
//        List<Lawyer.ResultBean.MlssBean.CommodityListBeanXX> mlss = lawyer.getResult().getMlss().getCommodityList();
//        LinearLayoutManager mlssLinearLayoutManager = new LinearLayoutManager(this);
//        mlssLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerViewMlss.setLayoutManager(mlssLinearLayoutManager);
//        recyclerViewMlss.setAdapter(new MyMlssAdapter(mlss));
//
//
//        List<Lawyer.ResultBean.PzshBean.CommodityListBeanX> pzsh = lawyer.getResult().getPzsh().getCommodityList();
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
//        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
//        recyclerViewPzsh.setLayoutManager(gridLayoutManager);
//        recyclerViewPzsh.setAdapter(new MyPzshAdapter(pzsh));

    }

    @Override
    public void onHomeFailure(Throwable throwable) {
        Toast.makeText(HomeActivity.this, "请求失败" + throwable.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBannerSuccess(BannerBean bannerBean) {
        final List<BannerBean.ResultBean> result = bannerBean.getResult();
        xBanner.setBannerData(result);
        xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                NetUtil.getInstance().getPhoto(result.get(position).getImageUrl(), (ImageView) view);
            }
        });
    }

    @Override
    public void onBannerFailure(Throwable throwable) {
        Toast.makeText(HomeActivity.this, "banner请求失败" + throwable.toString(), Toast.LENGTH_SHORT).show();

    }
}
