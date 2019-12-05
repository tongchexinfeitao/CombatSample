package com.bw.combatsample.util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;


//代替配置功能清单
@GlideModule
public class MyGlideModel extends AppGlideModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
        int maxSize = 1024 * 1024 * 20;
        //自定义内存缓存
        builder.setMemoryCache(new LruResourceCache(maxSize));
        //自定义磁盘缓存
        builder.setDiskCache(new DiskLruCacheFactory(context.getExternalCacheDir().getAbsolutePath() + "/glide", maxSize));
    }
}
