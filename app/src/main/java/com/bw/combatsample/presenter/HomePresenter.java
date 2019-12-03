package com.bw.combatsample.presenter;

import com.bw.combatsample.base.BasePresenter;
import com.bw.combatsample.contract.IHomeContract;
import com.bw.combatsample.model.HomeModel;
import com.bw.combatsample.model.bean.Lawyer;
import com.bw.combatsample.view.activity.HomeActivity;

public class HomePresenter extends BasePresenter<IHomeContract.IView> implements IHomeContract.IPresenter {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    @Override
    public void getHomeData() {
        // TODO: 2019/11/30 不直接联网，要调用 m 层的方法
        homeModel.getHomeData(new IHomeContract.IModel.IModelCallback() {
            @Override
            public void onHomeSuccess(Lawyer lawyer) {
                // TODO: 2019/11/30 通知v层成功
                view.onHomeSuccess(lawyer);
            }

            @Override
            public void onHomeFailure(Throwable throwable) {
                // TODO: 2019/11/30 通知v层失败
                view.onHomeFailure(throwable);
            }
        });
    }


}
