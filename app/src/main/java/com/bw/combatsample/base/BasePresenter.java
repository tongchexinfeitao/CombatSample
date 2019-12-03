package com.bw.combatsample.base;

public abstract class BasePresenter<V> {
    protected V view;

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    //attach方法的作用：把IView传给P层
    public void attach(V view) {
        this.view = view;
    }

    // TODO: 2019/12/3 解决内存泄漏 （ps：把activity置空）
    public void detach() {
        view = null;
    }
}
