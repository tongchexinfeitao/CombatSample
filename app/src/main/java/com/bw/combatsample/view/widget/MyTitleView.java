package com.bw.combatsample.view.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.combatsample.R;

/**
 * 自定流程：
 * 1、声明一个自定义view
 * 2、加载一个布局进来
 * 3、设置点击事件
 * 4、在点击中将搜索内容回调到外界
 * 5、外界拿到搜索内容之后，进行数据请求
 * <p>
 * 使用方法：xml中全类名使用
 */

public class MyTitleView extends RelativeLayout {

    private EditText editText;
    private TextView goSearch;
    private ImageButton imageBack;
    private TextView title;

    public MyTitleView(Context context) {
        super(context);
    }

    public MyTitleView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        //将布局加载进来
        inflate(context, R.layout.my_title_layout, this);

        title = findViewById(R.id.tv_title);
        imageBack = findViewById(R.id.img_back);
        editText = findViewById(R.id.edt_content);
        goSearch = findViewById(R.id.tv_go_search);


        imageBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
            }
        });
        goSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSearchListener != null) {
                    String content = editText.getText().toString();
                    onSearchListener.onSearch(content);
                }
            }
        });

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTitleView);

        String string = typedArray.getString(R.styleable.MyTitleView_myHint);
        editText.setHint(string);

        int color = typedArray.getColor(R.styleable.MyTitleView_myTextColor, Color.BLACK);
        editText.setTextColor(color);

        boolean aBoolean = typedArray.getBoolean(R.styleable.MyTitleView_searchEnable, false);
        //如果为true，显示搜索
        if (aBoolean) {
            //标题隐藏
            title.setVisibility(GONE);
            //搜索相关的两个控件显示
            editText.setVisibility(VISIBLE);
            goSearch.setVisibility(VISIBLE);
        } else {
            title.setVisibility(VISIBLE);
            editText.setVisibility(GONE);
            goSearch.setVisibility(GONE);
        }

    }


    OnSearchListener onSearchListener;

    public void setOnSearchListener(OnSearchListener onSearchListener) {
        this.onSearchListener = onSearchListener;
    }

    public interface OnSearchListener {
        void onSearch(String searchContent);
    }


}
