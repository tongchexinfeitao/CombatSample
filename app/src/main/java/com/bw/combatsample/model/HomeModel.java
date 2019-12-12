package com.bw.combatsample.model;

import com.bw.combatsample.contract.IHomeContract;
import com.bw.combatsample.model.bean.BannerBean;
import com.bw.combatsample.model.bean.Lawyer;
import com.bw.combatsample.util.NetUtil;
import com.google.gson.Gson;

public class HomeModel implements IHomeContract.IModel {

    @Override
    public void getHomeData(String path, final IModelCallback iModelCallback) {
        String httpUrl = "http://172.17.8.100/small/commodity/v1/commodityList";
        NetUtil.getInstance().getJsonGet(httpUrl, new NetUtil.MyCallback() {
            @Override
            public void onGetJson(String json) {
                Lawyer lawyer = new Gson().fromJson(json, Lawyer.class);
                iModelCallback.onHomeSuccess(lawyer);
            }

            @Override
            public void onError(Throwable throwable) {
                iModelCallback.onHomeFailure(throwable);
            }
        });


        String bannerUrl = "http://172.17.8.100/small/commodity/v1/bannerShow";
        NetUtil.getInstance().getJsonGet(bannerUrl, new NetUtil.MyCallback() {
            @Override
            public void onGetJson(String json) {
                BannerBean bannerBean = new Gson().fromJson(json, BannerBean.class);
                iModelCallback.onBannerSuccess(bannerBean);
            }

            @Override
            public void onError(Throwable throwable) {
                iModelCallback.onBannerFailure(throwable);
            }
        });


    }
}
