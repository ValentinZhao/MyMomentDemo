package Manager;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.test.zhaoziliang.mymomentdemo.adapter.PhotoPagerAdapter;

import java.util.ArrayList;

import widget.DotIndicator;
import widget.HackyViewPager;

/**
 * Created by zhaoziliang on 16/10/25.
 */

public class PhotoManager implements PhotoPagerAdapter.OnPhotoViewClickListener, ViewPager.OnPageChangeListener{
    private Context mContext;
    private DotIndicator indicator;
    private HackyViewPager pager;
    private PhotoPagerAdapter adapter;
    private Rect finalBounds;
    private Point globalOffset;
    private View container;

    public PhotoManager(Context mContext, HackyViewPager pager, View container, DotIndicator indicator) {
        if(container != null){
            finalBounds = new Rect();
            globalOffset = new Point();
            this.mContext = mContext;
            this.pager = pager;
            this.container = container;
            this.indicator = indicator;

            this.pager.addOnPageChangeListener(this);
            adapter = new PhotoPagerAdapter(mContext);
            adapter.setPhotoViewClickListener(this);
        } else {
            throw new IllegalArgumentException("container cannot be null!");
        }
    }

    public static PhotoManager create(Context mContext, HackyViewPager pager, View container, DotIndicator indicator){
        return new PhotoManager(mContext, pager, container, indicator);
    }

    public void showPhoto(ArrayList<String> photoAddress, ArrayList<Rect> originViewBounds, int curPos){
        adapter.resetDatas(photoAddress, originViewBounds);
        pager.setAdapter(adapter);
        pager.setCurrentItem(curPos);
        indicator.setCurrentSelection(curPos);
        indicator.setDotViewNum(photoAddress.size());
        pager.setLocked(photoAddress.size() == 1);
        container.getGlobalVisibleRect(finalBounds, globalOffset);
        showPhotoPager(originViewBounds, curPos);
    }

    private AnimatorSet curAnimator;

    private void showPhotoPager(ArrayList<Rect> originViewBounds, int curPos) {
        Rect startBounds = originViewBounds.get(curPos);

        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        float ratio = calculateRatio(startBounds, finalBounds);

        pager.setPivotX(0);
        pager.setPivotY(0);

        container.setVisibility(View.VISIBLE);

        final AnimatorSet set = new AnimatorSet();
        set.play(ObjectAnimator.ofFloat(pager, View.X, startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(pager, View.Y, startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(pager, View.SCALE_X, ratio, 1f))
                .with(ObjectAnimator.ofFloat(pager, View.SCALE_Y, ratio, 1f));
        set.setDuration(400);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                curAnimator = set;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                curAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                curAnimator = null;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
    }

    private float calculateRatio(Rect startBounds, Rect finalBounds) {
        float ratio;
        if ((float) finalBounds.width() / finalBounds.height() > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            ratio = (float) startBounds.height() / finalBounds.height();
            float startWidth = ratio * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        }
        else {
            // Extend start bounds vertically
            ratio = (float) startBounds.width() / finalBounds.width();
            float startHeight = ratio * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }
        return ratio;
    }

    @Override
    public void onPhotoViewClick(View view, Rect originBound, int curPos) {
        //如果展开动画没有展示完全就关闭，那么就停止展开动画进而执行退出动画
        if (curAnimator != null) {
            curAnimator.cancel();
        }

        container.getGlobalVisibleRect(finalBounds, globalOffset);

        originBound.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        float ratio = calculateRatio(originBound, finalBounds);

        pager.setPivotX(0);
        pager.setPivotY(0);

        final AnimatorSet set = new AnimatorSet();
        set.play(ObjectAnimator.ofFloat(pager, View.X, originBound.left))
                .with(ObjectAnimator.ofFloat(pager, View.Y, originBound.top))
                .with(ObjectAnimator.ofFloat(pager, View.SCALE_X, 1f, ratio))
                .with(ObjectAnimator.ofFloat(pager, View.SCALE_Y, 1f, ratio));

        set.setDuration(400);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                curAnimator = set;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                curAnimator = null;
                container.clearAnimation();
                container.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                curAnimator = null;
                container.clearAnimation();
                container.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        indicator.setCurrentSelection(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void destroy() {
        adapter.destroy();
        mContext = null;
        adapter = null;
        pager = null;
        finalBounds = null;
        globalOffset = null;
        container = null;
    }

}