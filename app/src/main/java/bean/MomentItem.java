package bean;

<<<<<<< HEAD
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.zhaoziliang.mymomentdemo.R;
import com.test.zhaoziliang.mymomentdemo.Utils.ImageLoaders;

import Configuration.ConfigurationValues;
import widget.MyMomentPopup;

=======
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

>>>>>>> cdfcdfc46b6e5ef6f5ecad79807a164d7f0434e0
/**
 * Created by zhaoziliang on 16/10/28.
 */

public class MomentItem implements View.OnClickListener{
    public ImageView iv_headshot;
<<<<<<< HEAD
    public ImageView iv_comment;
    public TextView tv_usrId;
    public TextView tv_content;
    public TextView tv_update_time;
    public FrameLayout btn_comment;

    private View itemView;
    private UserInfo userInfo;
    private DynamicInfo dynamicInfo;
    private Context context;
    private Activity mContext;

    private MyMomentPopup mPopupWindow;

    public MomentItem(Activity mContext, Context context, View view){
        this.mContext = mContext;
        this.context = context;
        this.itemView = view;
        dynamicInfo = new DynamicInfo();
        dynamicInfo.likeState = ConfigurationValues.LIKE_STATE;
        mPopupWindow = new MyMomentPopup(mContext);
    }

    public void initUIWidgets(){
        iv_headshot = (ImageView) itemView.findViewById(R.id.iv_item_headshot);
        tv_usrId = (TextView) itemView.findViewById(R.id.tv_item_usrId);
        tv_content = (TextView) itemView.findViewById(R.id.tv_item_content);
        tv_update_time = (TextView) itemView.findViewById(R.id.tv_item_update_time);
        iv_comment = (ImageView) itemView.findViewById(R.id.btn_item_comment);
        btn_comment = (FrameLayout) itemView.findViewById(R.id.comment_button);
    }

    public void bindDatas(UserInfo info){
        this.userInfo = info;
        ImageLoaders.setImage(info.headshotUrl, iv_headshot);
        btn_comment.setOnClickListener(this);
        tv_usrId.setText(info.userId);
        tv_content.setText(info.content);
        tv_update_time.setText("1小时前");
=======
    public TextView tv_usrId;
    public TextView tv_content;
    public TextView tv_update_time;
    public ImageButton btn_comment;

    private View itemView;

    public MomentItem(View view){
        this.itemView = view;
>>>>>>> cdfcdfc46b6e5ef6f5ecad79807a164d7f0434e0
    }


    @Override
    public void onClick(View v) {
<<<<<<< HEAD
        switch (v.getId()){
            case R.id.comment_button:
//                Toast.makeText(context, "点击了评论按钮!", Toast.LENGTH_SHORT).show();
                mPopupWindow.showPopupWindow(iv_comment);
                break;
            default:
                break;
        }
=======

>>>>>>> cdfcdfc46b6e5ef6f5ecad79807a164d7f0434e0
    }
}
