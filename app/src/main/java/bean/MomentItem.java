package bean;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zhaoziliang on 16/10/28.
 */

public class MomentItem implements View.OnClickListener{
    public ImageView iv_headshot;
    public TextView tv_usrId;
    public TextView tv_content;
    public TextView tv_update_time;
    public ImageButton btn_comment;

    private View itemView;

    public MomentItem(View view){
        this.itemView = view;
    }


    @Override
    public void onClick(View v) {

    }
}
