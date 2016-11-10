package com.test.zhaoziliang.mymomentdemo;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.test.zhaoziliang.mymomentdemo.Views.MyListView;
import com.test.zhaoziliang.mymomentdemo.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import Impl.InterfaceImpls;
import Interfaces.OnPhotoLayoutClickListener;
import Manager.PhotoManager;
import bean.UserInfo;
import widget.DotIndicator;
import widget.HackyViewPager;
import widget.MyMomentPicsLayout;

/**
 * Created by zhaoziliang on 16/10/17.
 */

public class MomentActivity extends Activity implements OnPhotoLayoutClickListener, MyAdapter.MyPresenterTransacator{
    private MyListView listView;
    private MyAdapter adapter;
    private ArrayList<UserInfo> data;
    private ArrayList<String> contentImgs;
    private PhotoManager manager;
    private InterfaceImpls interfaceImpls;
    private ImageView refresh_icon;
    private View momentItemView;
    private MyMomentPicsLayout picsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moment);
        manager = PhotoManager.create(MomentActivity.this, (HackyViewPager) findViewById(R.id.photo_pager), findViewById(R.id.photo_container),
                (DotIndicator) findViewById(R.id.dot_indicator));
        refresh_icon = (ImageView) findViewById(R.id.refresh_icon);
        interfaceImpls = new InterfaceImpls(this);
        initUserDatas();
    }

    private ArrayList<String> initImgs() {
        contentImgs = new ArrayList<String>();
        contentImgs.add("http://img3.imgtn.bdimg.com/it/u=2154968291,1719718546&fm=21&gp=0.jpg");
        contentImgs.add("http://img2.imgtn.bdimg.com/it/u=3493660548,2393773218&fm=21&gp=0.jpg");
        contentImgs.add("http://img5.imgtn.bdimg.com/it/u=3904924745,134157930&fm=21&gp=0.jpg");
        contentImgs.add("http://img5.imgtn.bdimg.com/it/u=2373447415,467029216&fm=21&gp=0.jpg");
        contentImgs.add("http://img0.imgtn.bdimg.com/it/u=527035050,2809012686&fm=11&gp=0.jpg");
        contentImgs.add("http://img5.imgtn.bdimg.com/it/u=1970447107,3310066568&fm=21&gp=0.jpg");
        contentImgs.add("http://img0.imgtn.bdimg.com/it/u=1730042639,1953950647&fm=11&gp=0.jpg");
        contentImgs.add("http://img1.imgtn.bdimg.com/it/u=4033799331,457854326&fm=11&gp=0.jpg");
        contentImgs.add("http://img2.imgtn.bdimg.com/it/u=564284162,2883323748&fm=21&gp=0.jpg");
        return contentImgs;
    }

    private void initUserDatas() {
        data = new ArrayList<UserInfo>();
        contentImgs = initImgs();
        listView = (MyListView) findViewById(R.id.my_list_view);
        listView.setRefreshIcon(refresh_icon);
        UserInfo user_1 = new UserInfo();
        initUser(user_1, "Ken", "http://img0.imgtn.bdimg.com/it/u=1907522267,200908207&fm=21&gp=0.jpg",
                "天气不错!", 4);

        UserInfo user_2 = new UserInfo();
        initUser(user_2, "Juliet", "http://imgsrc.baidu.com/forum/w=580/sign=76af3404b6fd5266a72b3c1c9b199799/cffc1e178a82b901bdf4fbdc738da9773812ef80.jpg",
                "庄生晓梦迷蝴蝶", 6);

        UserInfo user_3 = new UserInfo();
        initUser(user_3, "大拿", "http://p2.gexing.com/G1/M00/B5/F8/rBACE1OeYpvgbwMDAAAj5Bi02iU175_200x200_3.jpg?recache=20131108",
                "一起运动吧!", 2);

        UserInfo user_4 = new UserInfo();
        initUser(user_4, "阿权", "http://img4.imgtn.bdimg.com/it/u=1395310503,1917993133&fm=21&gp=0.jpg",
                "这种事怎么会发生在我身上!", 9);

        UserInfo user_5 = new UserInfo();
        initUser(user_5, "Valentin", "http://img0.imgtn.bdimg.com/it/u=373074720,1296745380&fm=21&gp=0.jpg",
                "好大的一场雨.....", 4);

        UserInfo user_6 = new UserInfo();
        initUser(user_6, "小圆", "http://img0.imgtn.bdimg.com/it/u=2723891211,2180059777&fm=21&gp=0.jpg",
                "大家注意明早交作业了!", 1);

        for(int i = 0; i < 5; i++){
            UserInfo usr = new UserInfo();
            initUser(usr, "用户" + i, "http://img1.imgtn.bdimg.com/it/u=563207053,759860262&fm=21&gp=0.jpg",
                    "这里是机器人的第" + i + "条动态", 4);
        }
        adapter = new MyAdapter(MomentActivity.this, data);
        adapter.setMyPresenterTransacator(this);
        listView.setAdapter(adapter);
    }

    /**
     * picCount是该用户要加载的图片数
     */
    private void initUser(UserInfo usr, String usrId, String headShotUrl, String content, int picCount){
        ArrayList<String> temp_img_holder = new ArrayList<String>();
        String temp_img_url;
        usr.userId = usrId;
        usr.headshotUrl = headShotUrl;
        usr.content = content;
        for(int i = 0; i < picCount; i++){
            temp_img_url = contentImgs.get(i);
            temp_img_holder.add(temp_img_url);
        }
        usr.content_imgs = temp_img_holder;
        data.add(usr);
    }

    @Override
    public void onPhotoClick(List<String> urlList, ArrayList<Rect> photoBounds, int curPos) {
        manager.showPhoto((ArrayList<String>) urlList, photoBounds, curPos);
//        Log.e("InterfaceImpl", "在Activity中调用成功!");
    }

    @Override
    public InterfaceImpls Transit() {
        return interfaceImpls;
    }

}
