package com.test.zhaoziliang.mymomentdemo.Utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by zhaoziliang on 16/10/17.
 */

public class ImageLoaders {
    private static DisplayImageOptions OPTION = null;

    public static void setImage(String url, ImageView ivPic){
        if(OPTION == null){
            OPTION = new DisplayImageOptions.Builder()
                    .showImageOnLoading(0)
                    .showImageForEmptyUri(0)
                    .showImageOnFail(0)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                    .build();
        }
        ImageLoader.getInstance().displayImage(url, ivPic, OPTION);
    }

    public static void setImage(String url, ImageView ivPic, ImageLoadingListener listener){
        if(OPTION == null){
            OPTION = new DisplayImageOptions.Builder()
                    .showImageOnLoading(0)
                    .showImageForEmptyUri(0)
                    .showImageOnFail(0)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                    .build();
        }
        ImageLoader.getInstance().displayImage(url, ivPic, OPTION, listener);
    }
}
