package Impl;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

import Interfaces.OnPhotoLayoutClickListener;

/**
 * Created by zhaoziliang on 16/10/30.
 */

public class InterfaceImpls {
    private OnPhotoLayoutClickListener listener;

    public InterfaceImpls(OnPhotoLayoutClickListener listener){
        this.listener = listener;
    }

    public void showPhoto(List<String> urlList, ArrayList<Rect> photoBounds, int curPos){
//        Log.e("InterfaceImpl", "调用成功!");
        listener.onPhotoClick(urlList, photoBounds, curPos);
    }
}
