package com.bw.combatsample.contract;


import com.bw.combatsample.model.bean.BannerBean;
import com.bw.combatsample.model.bean.Lawyer;

public interface IHomeContract {


    //p层通知v层用的
    interface IView {
        void onHomeSuccess(Lawyer lawyer);

        void onHomeFailure(Throwable throwable);


        void onBannerSuccess(BannerBean bannerBean);

        void onBannerFailure(Throwable throwable);
    }

    interface IPresenter {
        void getHomeData(String path);
    }

    interface IModel {
        void getHomeData(String path,IModelCallback iModelCallback);


        //m层通知p层结果用的
        interface IModelCallback {
            void onHomeSuccess(Lawyer lawyer);

            void onHomeFailure(Throwable throwable);

            void onBannerSuccess(BannerBean bannerBean);

            void onBannerFailure(Throwable throwable);
        }
    }

}
