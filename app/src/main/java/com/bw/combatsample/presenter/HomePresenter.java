package com.bw.combatsample.presenter;

import com.bw.combatsample.base.BasePresenter;
import com.bw.combatsample.contract.IHomeContract;
import com.bw.combatsample.model.HomeModel;
import com.bw.combatsample.model.bean.BannerBean;
import com.bw.combatsample.model.bean.Lawyer;
import com.bw.combatsample.view.activity.HomeActivity;

public class HomePresenter extends BasePresenter<IHomeContract.IView> implements IHomeContract.IPresenter {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }


    @Override
    public void getHomeData(String path) {
        homeModel.getHomeData(path,new IHomeContract.IModel.IModelCallback() {
            @Override
            public void onHomeSuccess(Lawyer lawyer) {
                view.onHomeSuccess(lawyer);
            }

            @Override
            public void onHomeFailure(Throwable throwable) {
                view.onHomeFailure(throwable);
            }

            @Override
            public void onBannerSuccess(BannerBean bannerBean) {
                view.onBannerSuccess(bannerBean);
            }

            @Override
            public void onBannerFailure(Throwable throwable) {
                view.onBannerFailure(throwable);
            }
        });
    }
}
