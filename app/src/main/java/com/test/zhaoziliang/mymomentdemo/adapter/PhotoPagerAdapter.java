package com.test.zhaoziliang.mymomentdemo.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.zhaoziliang.mymomentdemo.Utils.ImageLoaders;
import com.test.zhaoziliang.mymomentdemo.Views.MyPhotoView;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by zhaoziliang on 16/10/25.
 */

public class PhotoPagerAdapter extends PagerAdapter {
    private static ArrayList<MyPhotoView> sMyPhotoViewPool;
    private static int sMyPhotoViewPoolSize = 10;

    private ArrayList<String> photoAddress;
    private ArrayList<Rect> originViewBounds;
    private Context mContext;
    private LayoutInflater inflater;

    public PhotoPagerAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        photoAddress = new ArrayList<>();
        originViewBounds = new ArrayList<>();
        sMyPhotoViewPool = new ArrayList<>();
        buildSMyPhotoViewPool(context);
    }

    private void buildSMyPhotoViewPool(Context context) {
        for(int i = 0; i < sMyPhotoViewPoolSize; i++){
            MyPhotoView photoView = new MyPhotoView(context);
            photoView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            sMyPhotoViewPool.add(photoView);
        }
    }

    public void resetDatas(ArrayList<String> newAddress, ArrayList<Rect> newOriginViewBounds)
            throws IllegalArgumentException{
        if(newAddress.size() != newOriginViewBounds.size() || newAddress.size() <= 0 ||
                newOriginViewBounds.size() <= 0){
            throw new IllegalArgumentException("图片地址和图片的位置缓存不对等或某一个为空");
        }
        photoAddress.clear();
        originViewBounds.clear();
        photoAddress.addAll(newAddress);
        originViewBounds.addAll(newOriginViewBounds);
    }

    @Override
    public int getCount() {
        return photoAddress.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        MyPhotoView photoView = sMyPhotoViewPool.get(position);
        if(photoView == null){
            photoView = new MyPhotoView(mContext);
            photoView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }
        ImageLoaders.setImage(photoAddress.get(position), photoView);
        container.addView(photoView);
        return photoView;
    }

    /**
     *
     * @param container 承载ViewPager的容器,比如可以是RelativeLayout
     * @param position 在ViewPager中的位置
     * @param object 显示的内容 View之类的
     */

    int[] pos = new int[1];

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        pos[0] = position;
        if(object instanceof MyPhotoView){
            MyPhotoView photoView = (MyPhotoView) object;
            if(photoView.getOnViewTapListener() == null){
                photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                    @Override
                    public void onViewTap(View view, float x, float y) {
                        if(photoViewClickListener != null){
                            photoViewClickListener.onPhotoViewClick(view, originViewBounds.get(pos[0]), pos[0]);
                        }
                    }
                });
            }
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    public void destroy(){
        for (MyPhotoView photoView : sMyPhotoViewPool) {
            photoView.destroy();
        }
        sMyPhotoViewPool.clear();
        sMyPhotoViewPool=null;
    }


    private OnPhotoViewClickListener photoViewClickListener;

    public void setPhotoViewClickListener(OnPhotoViewClickListener photoViewClickListener) {
        this.photoViewClickListener = photoViewClickListener;
    }

    public OnPhotoViewClickListener getPhotoViewClickListener() {
        return photoViewClickListener;
    }

    /**
     * 点击取消预览图片的接口,通过调取接口把业务逻辑抛到接口中执行
     */
    public interface OnPhotoViewClickListener{
        void onPhotoViewClick(View view, Rect originBound, int curPos);
    }

}
