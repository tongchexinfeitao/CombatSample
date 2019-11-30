package com.bw.combatsample.contract;


import com.bw.combatsample.model.bean.Lawyer;

public interface IHomeContract {

    //m层通知p层结果用的
    interface IModelCallback {
        void onHomeSuccess(Lawyer lawyer);

        void onHomeFailure(Throwable throwable);
    }

    //p层通知v层用的
    interface IView {
        void onHomeSuccess(Lawyer lawyer);

        void onHomeFailure(Throwable throwable);
    }


}
