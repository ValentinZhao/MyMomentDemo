package com.test.zhaoziliang.mymomentdemo.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.test.zhaoziliang.mymomentdemo.R;

/**
 * Created by zhaoziliang on 16/10/26.
 */

public class DotView extends View {
    private Drawable dot_selected;
    private Drawable dot_normal;

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setSelected(boolean selected) {
        isSelected = selected;
        invalidate();
    }

    private boolean isSelected;

    public DotView(Context context) {
        this(context, null);
    }

    public DotView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        dot_normal = getResources().getDrawable(R.drawable.ic_viewpager_dot_indicator_normal);
        dot_selected = getResources().getDrawable(R.drawable.ic_viewpager_dot_indicator_selected);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        if(isSelected){
            dot_selected.setBounds(0, 0, width, height);
            dot_selected.draw(canvas);
        } else {
            dot_normal.setBounds(0, 0, width, height);
            dot_normal.draw(canvas);
        }
    }
}
