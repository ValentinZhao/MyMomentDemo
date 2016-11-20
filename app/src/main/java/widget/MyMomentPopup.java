package widget;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.test.zhaoziliang.mymomentdemo.R;

import java.lang.ref.WeakReference;

import Configuration.ConfigurationValues;
import bean.DynamicInfo;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by zhaoziliang on 16/11/14.
 */

public class MyMomentPopup extends BasePopupWindow implements View.OnClickListener{
    private Context context = getContext();
    private View mPopupView = getPopupWindowView();
    private int[] viewLocation;

    private ImageView mLikeView;
    private TextView mLikeText;

    private RelativeLayout mLikeClikcLayout;
    private RelativeLayout mCommentClickLayout;
    private ScaleAnimation mScaleAnimation;
    private WeakHandler handler;
    private DynamicInfo mDynamicInfo;

    public MyMomentPopup(Activity context) {
        super(context);
        this.context = context;
        viewLocation = new int[2];
        buildValues();
    }

    private void buildValues() {
        mLikeView = (ImageView) mPopupView.findViewById(R.id.iv_like);
        mLikeText = (TextView) mPopupView.findViewById(R.id.tv_like);

        mLikeClikcLayout = (RelativeLayout) mPopupView.findViewById(R.id.item_like);
        mCommentClickLayout = (RelativeLayout) mPopupView.findViewById(R.id.item_comment);

        mLikeView.setOnClickListener(this);
        mCommentClickLayout.setOnClickListener(this);
        mLikeClikcLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(mPopupWindow != null && mPopupWindow.isShowing()){
//                    Toast.makeText(context, "Click Popupwindow!", Toast.LENGTH_SHORT).show();
                    mLikeText.setText("取消");
                    mLikeView.clearAnimation();
                    mLikeView.startAnimation(mScaleAnimation);
//                    mPopupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });

        mCommentClickLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(mPopupWindow != null && mPopupWindow.isShowing()){
                    Toast.makeText(context, "点击了评论!", Toast.LENGTH_SHORT).show();
//                    mLikeText.setText("取消");
//                    mLikeView.clearAnimation();
//                    mLikeView.startAnimation(mScaleAnimation);
                    mPopupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });

        mScaleAnimation = new ScaleAnimation(1f, 2.5f, 1f, 2.5f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mScaleAnimation.setDuration(300);
        mScaleAnimation.setInterpolator(new SpringInterPolator());
        mScaleAnimation.setFillAfter(false);

        mScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        dismiss();
//                    }
//                }, 150);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void showPopupWindow(View v) {
        v.getLocationOnScreen(viewLocation);
//        mPopupWindow.showAtLocation(v, Gravity.RIGHT | Gravity.TOP, (int) (v.getWidth() * 1.8),
//                viewLocation[1] - UIUtils.dip2Px(context, 10f));
        mPopupWindow.showAsDropDown(v);

        if(initShowAnimation() != null && initAnimaView() != null){
            initAnimaView().startAnimation(initShowAnimation());
        }
    }

    @Override
    protected Animation initShowAnimation() {
        return getScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
                0.0f);
    }

    @Override
    protected Animation initExitAnimation() {
        return getScaleAnimation(1.0f, 0.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
                0.0f);
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popup_comment);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.comment_popup_contianer);
    }

    @Override
    public void onClick(View v) {
    }

    private OnCommentPopupClickListener listener;

    public void setOnCommentPopupClickListener(OnCommentPopupClickListener onCommentPopupClickListener){
        this.listener = onCommentPopupClickListener;
    }

    public OnCommentPopupClickListener getOnCommentPopupClickListener(){
        return listener;
    }

    public void setDynmaicInfo(DynamicInfo info){
        if(info == null){
            return;
        }
        mDynamicInfo = info;
        if(mDynamicInfo.likeState == ConfigurationValues.NOT_LIKED){
            mLikeText.setText("赞  ");
        } else if(mDynamicInfo.likeState == ConfigurationValues.HAS_LIKED){
            mLikeText.setText("取消");
        }
    }

    public interface OnCommentPopupClickListener{
        void onLikeClick(View v, DynamicInfo info);

        void onCommentClick(View v, DynamicInfo info);
    }

    private class WeakHandler extends Handler{
        private final WeakReference<Context> context;

        private WeakHandler(Context context) {
            this.context = new WeakReference<Context>(context);
        }
    }

    static class SpringInterPolator extends LinearInterpolator{
        public SpringInterPolator() {
        }

        @Override
        public float getInterpolation(float input) {
            return (float) Math.sin(input * Math.PI);
        }
    }
}
