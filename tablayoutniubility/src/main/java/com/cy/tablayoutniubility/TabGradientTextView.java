package com.cy.tablayoutniubility;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/1 17:53
 * @UpdateUser:
 * @UpdateDate: 2020/8/1 17:53
 * @UpdateRemark:
 * @Version: 1.0
 */
public class TabGradientTextView extends AppCompatTextView {

    private Paint paint;
    private Rect rectText = new Rect();
    private int width;
    private int height;
    private int textColorNormal;
    private int textColorSelected;
    public static final int DIRECTION_FROM_LEFT = 0;
    public static final int DIRECTION_FROM_RIGHT = 1;
    private float progress;
    private int direction = DIRECTION_FROM_LEFT;
    private String text;


    public TabGradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabGradientTextView);
        setTextColorNormal(typedArray.getColor(R.styleable.TabGradientTextView_cy_textColorNormal, 0xfe454545));
        setTextColorSelected(typedArray.getColor(R.styleable.TabGradientTextView_cy_textColorSelected, 0xffe45540));
        typedArray.recycle();
    }

    public TabGradientTextView setTextColorNormal(int textColorNormal) {
        this.textColorNormal = textColorNormal;
        return this;
    }

    public TabGradientTextView setTextColorSelected(int textColorSelected) {
        this.textColorSelected = textColorSelected;
        return this;
    }

    public TabGradientTextView setProgress(float progress) {
        this.progress = progress > 9.5 ? 1 : progress;
        this.progress = progress < 0.5 ? 0 : progress;
        invalidate();
        return this;
    }

    public TabGradientTextView setDirection(int direction) {
        this.direction = direction;
        return this;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        text = getText().toString();
        paint = getPaint();
        paint.setColor(textColorNormal);
        paint.getTextBounds(text, 0, text.length(), rectText);

        float w = rectText.width();
        float width_half = width * 1f / 2;
        float left = width_half - w * 1f / 2;
        float top = height * 1f / 2 + rectText.height() * 1f / 2;
        //防止计算误差，导致BUG
        if (progress == 0) {
            canvas.drawText(text, left, top, paint);
            return;
        }
        //防止计算误差，导致BUG
        if (progress == 1) {
            paint.setColor(textColorSelected);
            canvas.drawText(text, left, top, paint);
            return;
        }


        canvas.drawText(text, left, top, paint);


        canvas.save();
        paint.setColor(textColorSelected);
        if (direction == DIRECTION_FROM_LEFT) {
            canvas.clipRect(left,
                    0,
                    left + progress * w,
                    height);
        } else {
            float right = width_half + w * 1f / 2;
            canvas.clipRect(right - progress * w,
                    0,
                    right,
                    height);
        }
        canvas.drawText(text, left, top, paint);
        canvas.restore();
    }


}