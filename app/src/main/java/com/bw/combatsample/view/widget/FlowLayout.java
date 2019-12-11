package com.bw.combatsample.view.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FlowLayout extends ViewGroup {

    int screenWid = 0;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        screenWid = displayMetrics.widthPixels;
    }

    // TODO: 2019/12/10 基于的前提：
    //  1、默认当前子view摆放到上一个子view的右侧
    //  2、默认上一个子view，帮我算好了我的left和top，如果我不需要换行，我就可以直接使用 left和top


    //摆放子view ，可以控制子view的位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //两个孩子之间的横向的空隙
        int wSpace = 30;
        //两行之间上下的的空隙      30,20
        int hSapce = 20;

        int left = wSpace;
        int top = hSapce;
        int right = 0;
        int bottom = 0;

        //遍历自己所有的孩子
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            //根据下标获取到对应的孩子
            View child = getChildAt(i);
            //测量孩子的宽高
            child.measure(0, 0);
            //孩子的测量宽度
            int measuredWidth = child.getMeasuredWidth();
            //孩子的测量高度
            int measuredHeight = child.getMeasuredHeight();

            // TODO: 2019/12/10 开始摆放第N个孩子   left和top是上一个子view摆放完毕之后帮我们算好的
            //先计算right值
            right = left + measuredWidth;
            //如果right小于屏幕宽度，说明不用换行，代表left和top不用变
            if (right < screenWid) {
                //不换行就不用重新计算left和top，而right，上边刚算过，只需要算一下自己的bottom
                bottom = top + measuredHeight;
            } else {
                // TODO: 2019/12/10 换行之后，left变成了0+空隙 ，top变成上一个的bottom+空隙
                left = wSpace;
                top = bottom + hSapce;
                //left 变了，right肯定变了
                right = left + measuredWidth;
                bottom = top + measuredHeight;
            }
            //摆放
            child.layout(left, top, right, bottom);
            //替下一个子view计算left和top，而下一个默认和我同一行，所以top不用算
            left = right + wSpace;
        }
    }

    //每搜索一次，添加一次记录
    public void addTag(String tag) {
        final TextView textView = new TextView(getContext());
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTagClickListener != null) {
                    onTagClickListener.onTagClick(textView.getText().toString());
                }
            }
        });

        textView.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //移除view
                removeView(textView);
                if (onTagLongClickListener != null) {
                    onTagLongClickListener.onTagLongClick(textView.getText().toString());
                }
                return true;
            }
        });

        textView.setText(tag);
        textView.setTextColor(Color.GREEN);
        textView.setTextSize(20);
        textView.setBackgroundColor(Color.RED);
        //给流式布局添加子view
        addView(textView);
    }


    onTagClickListener onTagClickListener;

    public void setOnTagClickListener(FlowLayout.onTagClickListener onTagClickListener) {
        this.onTagClickListener = onTagClickListener;
    }

    public interface onTagClickListener {
        void onTagClick(String tag);
    }

    onTagLongClickListener onTagLongClickListener;

    public void setOnTagLongClickListener(FlowLayout.onTagLongClickListener onTagLongClickListener) {
        this.onTagLongClickListener = onTagLongClickListener;
    }

    public interface onTagLongClickListener {
        void onTagLongClick(String tag);
    }
}
