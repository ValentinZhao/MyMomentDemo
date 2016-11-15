package com.test.zhaoziliang.mymomentdemo.Views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.test.zhaoziliang.mymomentdemo.R;
import com.test.zhaoziliang.mymomentdemo.Utils.ImageLoaders;

/**
 * Created by zhaoziliang on 16/10/17.
 */

public class MyListView extends ListView{
    private View mHeaderView;
    private ImageView refreshIcon;
    private View momentView;
    private ImageView iv_cover;
    private ImageView iv_headshot;
    private TextView tv_usrId;
    private RotateAnimation mRotateAnim;
    private int refreshState;

    private final int PULL_REFRESH = 0;
    private final int RELEASE_REFRESH = 1;
    private final int REFRESHING = 2;

    private int iconHeight;
    private int downY;

    public MyListView(Context context) {
        super(context);
        init(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context){
        mHeaderView = View.inflate(context, R.layout.layout_header_view, null);
        iv_cover = (ImageView) mHeaderView.findViewById(R.id.iv_cover);
        iv_headshot = (ImageView) mHeaderView.findViewById(R.id.iv_headshot);
        tv_usrId = (TextView) mHeaderView.findViewById(R.id.tv_usr_id);
        ImageLoaders.setImage("http://cdn.duitang.com/uploads/blog/201507/31/20150731133335_KdymU.thumb.700_0.jpeg", iv_cover);
        ImageLoaders.setImage("http://img4.duitang.com/uploads/item/201405/02/20140502170056_ik2tV.jpeg", iv_headshot);
        tv_usrId.setText("Valentin");
        addHeaderView(mHeaderView);
        initRotateAnims();
    }

    private void initRotateAnims() {
        mRotateAnim = new RotateAnimation(0, 359,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnim.setDuration(300);
        mRotateAnim.setFillAfter(true);
        mRotateAnim.setInterpolator(new LinearInterpolator());
    }

    private void initRefreshIcon() {
        refreshIcon.measure(0, 0);
        iconHeight = refreshIcon.getMeasuredHeight();
        refreshIcon.setPadding(0, -iconHeight, 0, 0);
    }

    public void setRefreshIcon(ImageView refreshIcon){
        this.refreshIcon = refreshIcon;
        initRefreshIcon();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = (int) (ev.getRawY() - downY);
                int paddingTop = deltaY - iconHeight;
                if(paddingTop > -iconHeight && getFirstVisiblePosition() == 0){//避免在listview向下滑时无论什么位置都会把下拉刷新触发
                    refreshIcon.setPadding(0, paddingTop, 0, 0);
                    if(paddingTop > 300){
                        refreshIcon.setPadding(0, 300, 0, 0);
                    }
                    if(deltaY > 0){
                        refreshIcon.setPivotX(20);
                        refreshIcon.setPivotY(20);
                        refreshIcon.setRotation(deltaY * 5);
                    } else {
                        refreshIcon.setPivotX(20);
                        refreshIcon.setPivotY(20);
                        refreshIcon.setRotation(-deltaY * 5);
                    }
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                refreshIcon.setPadding(0, 300, 0, 0);
//                refreshIcon.startAnimation(mRotateAnim);
                break;
        }
        return super.onTouchEvent(ev);
    }

}
