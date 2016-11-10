package com.test.zhaoziliang.mymomentdemo.Views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.test.zhaoziliang.mymomentdemo.R;
import com.test.zhaoziliang.mymomentdemo.Utils.ImageLoaders;

/**
 * Created by zhaoziliang on 16/10/17.
 */

public class MyListView extends ListView {
    private View mHeaderView;
    private ImageView refreshIcon;
    private View momentView;
    private ImageView iv_cover;
    private ImageView iv_headshot;
    private TextView tv_usrId;
    private RotateAnimation upRotateAnim;
    private RotateAnimation downRotateAnim;

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
    }

    private void initRefreshIcon() {
        refreshIcon.measure(0, 0);
        iconHeight = refreshIcon.getMeasuredHeight();
        refreshIcon.setPadding(0, iconHeight, 0, 0);
    }

    public void setRefreshIcon(ImageView refreshIcon){
        this.refreshIcon = refreshIcon;
        initRefreshIcon();
    }

}
