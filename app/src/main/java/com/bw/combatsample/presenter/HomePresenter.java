package com.bw.combatsample.presenter;

import com.bw.combatsample.contract.IHomeContract;
import com.bw.combatsample.model.HomeModel;
import com.bw.combatsample.model.bean.Lawyer;

public class HomePresenter {
    public void getHomeData(final IHomeContract.IView iView) {
        HomeModel homeModel = new HomeModel();
        // TODO: 2019/11/30 不直接联网，要调用 m 层的方法
        homeModel.getHomeData(new IHomeContract.IModelCallback() {
            @Override
            public void onHomeSuccess(Lawyer lawyer) {
                // TODO: 2019/11/30 通知v层成功
                iView.onHomeSuccess(lawyer);
            }

            @Override
            public void onHomeFailure(Throwable throwable) {
                // TODO: 2019/11/30 通知v层失败
                iView.onHomeFailure(throwable);
            }
        });
    }

}
