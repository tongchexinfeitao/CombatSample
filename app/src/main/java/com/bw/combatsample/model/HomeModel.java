package com.bw.combatsample.model;

import com.bw.combatsample.contract.IHomeContract;
import com.bw.combatsample.model.bean.Lawyer;
import com.bw.combatsample.util.NetUtil;
import com.google.gson.Gson;

public class HomeModel implements IHomeContract.IModel {

    @Override
    public void getHomeData(final IModelCallback iModelCallback) {
        NetUtil.getInstance().getJson("http://blog.zhaoliang5156.cn/api/news/lawyer.json", new NetUtil.MyCallback() {
            @Override
            public void onGetJson(String json) {
                Lawyer lawyer = new Gson().fromJson(json, Lawyer.class);
                // TODO: 2019/11/30 通知p层成功
                iModelCallback.onHomeSuccess(lawyer);
            }

            @Override
            public void onError(Throwable throwable) {
                // TODO: 2019/11/30 通知p层失败
                iModelCallback.onHomeFailure(throwable);
            }
        });
    }
}
