package com.bw.combatsample.view.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

    private Paint paint;

    //直接 new MyView的时候调用
    public MyView(Context context) {
        super(context);
    }

    //在xml中使用  MyView 的时候
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        //设置画笔颜色
        paint.setColor(Color.GREEN);
        //设置画笔的粗细
        paint.setStrokeWidth(10);
        //设置是否填充
        paint.setStyle(Paint.Style.STROKE);
        //抗锯齿
        paint.setAntiAlias(true);

    }

    //专门用来画东西， 绘制
    @SuppressLint("DrawAllocation")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画线
        canvas.drawLine(10, 10, 150, 150, paint);

        //画圆
        canvas.drawCircle(300, 300, 200, paint);

        //画矩形
        paint.setColor(Color.BLACK);
        canvas.drawRect(150, 150, 550, 450, paint);

        //画弧 false, 画扇形是true ,
        paint.setColor(Color.RED);
        //STROKE只画轮廓、 FILL填充
        paint.setStyle(Paint.Style.FILL);
        RectF rect = new RectF(150, 150, 450, 450);
        canvas.drawArc(rect, 0, 90, true, paint);
        paint.setColor(Color.BLACK);
        canvas.drawArc(rect, 90, 90, true, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawArc(rect, 180, 90, true, paint);
        paint.setColor(Color.WHITE);
        canvas.drawArc(rect, 270, 90, true, paint);
    }
}
