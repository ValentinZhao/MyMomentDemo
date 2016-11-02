package widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.test.zhaoziliang.mymomentdemo.Utils.ImageLoaders;
import com.test.zhaoziliang.mymomentdemo.Views.RatioImageView;

import java.util.ArrayList;
import java.util.List;

import Impl.InterfaceImpls;

/**
 * Created by zhaoziliang on 16/10/19.
 */

public class MyMomentPicsLayout extends NineGridLayout{
    public static final String TAG = "MyMoment";

    protected static final int MAX_W_H_RATIO = 3;
    private InterfaceImpls interfaceImpls;

    public ArrayList<Rect> mRects = new ArrayList<>();

    public MyMomentPicsLayout(Context context) {
        super(context);
    }

    public MyMomentPicsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setInterfaceImpls(InterfaceImpls interfaceImpls){
        this.interfaceImpls = interfaceImpls;
    }

    @Override
    protected boolean displayOneImage(final RatioImageView imageView, String url, final int parentWidth) {
        ImageLoaders.setImage(url, imageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                int w = bitmap.getWidth();
                int h = bitmap.getHeight();

                int newW;
                int newH;
                if (h > w * MAX_W_H_RATIO) {//h:w = 5:3
                    newW = parentWidth / 2;
                    newH = newW * 5 / 3;
                } else if (h < w) {//h:w = 2:3
                    newW = parentWidth * 2 / 3;
                    newH = newW * 2 / 3;
                } else {//newH:h = newW :w
                    newW = parentWidth / 2;
                    newH = h * newW / w;
                }
                setOneImageLayoutParams(imageView, newW, newH);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {
            }
        });
        return false;
    }

    @Override
    protected void displayImage(RatioImageView imageView, String url) {
        ImageLoaders.setImage(url, imageView);
    }

    @Override
    protected void onClickImage(int position, String url, List<String> urlList, List<RatioImageView> imgList) {
//        Toast.makeText(mContext, "图片数:" + imgList.size(), Toast.LENGTH_SHORT).show();
        final int childCount = imgList.size();
        mRects.clear();
        try{
            if(childCount > 0){
                for(int i = 0; i < childCount; i++){
                    View v = imgList.get(position);
                    Rect bound = new Rect();
                    v.getGlobalVisibleRect(bound);
                    mRects.add(bound);
                }
            }
        } catch (NullPointerException e){
            Log.e(TAG, "这个Item中没有图片啊!");
        }
        interfaceImpls.showPhoto(urlList, mRects, position);
    }

}