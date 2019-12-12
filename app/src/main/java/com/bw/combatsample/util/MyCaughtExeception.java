package com.bw.combatsample.util;

import android.util.Log;

public class MyCaughtExeception implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.e("TAG", "捕获到了异常" + e.getMessage());
        // TODO: 2019/12/12 正常来说这里是不是联网请求，把异常信息上传
    }
}
