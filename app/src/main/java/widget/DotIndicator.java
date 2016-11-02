package widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.test.zhaoziliang.mymomentdemo.Utils.UIUtils;
import com.test.zhaoziliang.mymomentdemo.Views.DotView;

import java.util.ArrayList;

/**
 * Created by zhaoziliang on 16/10/26.
 */

public class DotIndicator extends LinearLayout {
    public static String TAG_DOT_INDICATOR = "dot_indicator";
    private ArrayList<DotView> mDotViews;
    private int mCurrentSelect;
    private int mDotNum;
    public DotIndicator(Context context) {
        this(context,null);
    }

    public DotIndicator(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DotIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        buildDotIndicator(context);
    }

    private void buildDotIndicator(Context context) {
        mDotViews = new ArrayList<>();
        for(int i = 0; i < 9; i++){
            DotView dotView = new DotView(context);
            dotView.setSelected(false);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(UIUtils.dip2Px(context, 10f),
                    UIUtils.dip2Px(context, 10f));
            if(i == 0){
                params.leftMargin = 0;
            } else {
                params.leftMargin = UIUtils.dip2Px(context, 6f);
            }
            dotView.setLayoutParams(params);
            addView(dotView);
            mDotViews.add(dotView);
        }
    }

    public void setCurrentSelection(int selection){
        this.mCurrentSelect = selection;
        for(DotView dotView : mDotViews){
            dotView.setSelected(false);
        }
        if(selection > 0 || selection < mDotViews.size()){
            mDotViews.get(mCurrentSelect).setSelected(true);
        } else {
            Log.e(TAG_DOT_INDICATOR, "不在选择范围内");
        }
    }

    public int getCurrentSelection(){
        return mCurrentSelect;
    }

    public void setDotViewNum(int num){
        if(num < 0 || num > 9){
            Log.e(TAG_DOT_INDICATOR, "输入数值错误!");
            return;
        }
        for(DotView dotView : mDotViews){
            dotView.setVisibility(VISIBLE);
        }
        mDotNum = num;
        for (int i = num; i < mDotViews.size(); i++){
            DotView dotView = mDotViews.get(i);
            if(dotView != null){
                dotView.setSelected(false);
                dotView.setVisibility(GONE);
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mDotViews.clear();
        mDotViews = null;
        Log.e(TAG_DOT_INDICATOR, "清除dotview引用");
    }
}
