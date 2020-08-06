package com.cy.tablayoutsimple_;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/3 15:16
 * @UpdateUser:
 * @UpdateDate: 2020/8/3 15:16
 * @UpdateRemark:
 * @Version:
 */
public class CircleView extends View {
    private int  width;
    private int height;

    private Paint paint;

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width=getWidth();
        height=getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(width*1f/2,height*1f/2, width*1f/2,paint);
    }
}
