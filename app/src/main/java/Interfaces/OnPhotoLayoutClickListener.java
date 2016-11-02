package Interfaces;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoziliang on 16/10/30.
 */

public interface OnPhotoLayoutClickListener {
//    public void setOnPhotoLayoutClickListener(OnPhotoLayoutClickListener listener){
//        this.listener = listener;
//    }
//
//    public OnPhotoLayoutClickListener getPhotoLayoutClickListener(){
//        return listener;
//    }

    public void onPhotoClick(List<String> urlList, ArrayList<Rect> photoBounds, int curPos);
}
