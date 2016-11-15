package widget;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by zhaoziliang on 16/11/14.
 */

public class MyMomentPopup extends BasePopupWindow implements View.OnClickListener{

    public MyMomentPopup(Activity context) {
        super(context);
    }

    public MyMomentPopup(Activity context, int w, int h) {
        super(context, w, h);
    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        return null;
    }

    @Override
    public View initAnimaView() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }
}
