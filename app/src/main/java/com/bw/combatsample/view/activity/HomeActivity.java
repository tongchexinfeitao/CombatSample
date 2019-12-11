package com.bw.combatsample.view.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
        mPresenter.getHomeData();
    }

    @Override
    protected void initView() {
        myTitleView = findViewById(R.id.search);
        //支持搜索
        myTitleView.setSearchEnable(false);

        myTitleView.setOnSearchListener(new MyTitleView.OnSearchListener() {
            @Override
            public void onSearch(String searchContent) {
                Toast.makeText(HomeActivity.this, searchContent, Toast.LENGTH_SHORT).show();
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


        //动态添加一个标签
        flowLayout.addTag("肖申克的救赎");
        flowLayout.addTag("阿甘正传");
        flowLayout.addTag("狂怒");
        flowLayout.addTag("肖申克的救赎");
        flowLayout.addTag("肖申克的救赎");
        flowLayout.addTag("肖申克的救赎");
        flowLayout.addTag("肖申克的救赎");
        flowLayout.addTag("敢死队");


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
        List<Lawyer.ResultBean.RxxpBean.CommodityListBean> rxxp = lawyer.getResult().getRxxp().getCommodityList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewRxxp.setLayoutManager(linearLayoutManager);
        recyclerViewRxxp.setAdapter(new MyRxxpAdapter(rxxp));


        List<Lawyer.ResultBean.MlssBean.CommodityListBeanXX> mlss = lawyer.getResult().getMlss().getCommodityList();
        LinearLayoutManager mlssLinearLayoutManager = new LinearLayoutManager(this);
        mlssLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMlss.setLayoutManager(mlssLinearLayoutManager);
        recyclerViewMlss.setAdapter(new MyMlssAdapter(mlss));


        List<Lawyer.ResultBean.PzshBean.CommodityListBeanX> pzsh = lawyer.getResult().getPzsh().getCommodityList();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerViewPzsh.setLayoutManager(gridLayoutManager);
        recyclerViewPzsh.setAdapter(new MyPzshAdapter(pzsh));

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
