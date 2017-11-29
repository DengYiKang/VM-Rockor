package com.kongqw.kqwrockerdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by wxy on 2017/11/28.
 */

public class MyVideo extends VideoView {

    public MyVideo(Context context, AttributeSet attrs) {
        // TODO Auto-generated constructor stub
        super(context, attrs);
    }

    public MyVideo(Context context) {
        super(context);
// TODO Auto-generated constructor stub
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}