package com.test.zhaoziliang.mymomentdemo.adapter;

<<<<<<< HEAD
import android.app.Activity;
=======
>>>>>>> cdfcdfc46b6e5ef6f5ecad79807a164d7f0434e0
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
<<<<<<< HEAD

import com.test.zhaoziliang.mymomentdemo.R;
=======
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.test.zhaoziliang.mymomentdemo.R;
import com.test.zhaoziliang.mymomentdemo.Utils.ImageLoaders;
>>>>>>> cdfcdfc46b6e5ef6f5ecad79807a164d7f0434e0

import java.util.ArrayList;

import Impl.InterfaceImpls;
<<<<<<< HEAD
import bean.MomentItem;
=======
>>>>>>> cdfcdfc46b6e5ef6f5ecad79807a164d7f0434e0
import bean.UserInfo;
import widget.MyMomentPicsLayout;

/**
 * Created by zhaoziliang on 16/10/18.
 */

<<<<<<< HEAD
public class MyAdapter extends BaseAdapter{
=======
public class MyAdapter extends BaseAdapter implements View.OnClickListener{
>>>>>>> cdfcdfc46b6e5ef6f5ecad79807a164d7f0434e0
    private ArrayList<UserInfo> data;
    public Context context;
    ViewHolder holder;

    public MyAdapter(Context context, ArrayList<UserInfo> data){
        this.context = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserInfo info = (UserInfo) getItem(position);
        if(convertView == null){
            convertView = View.inflate(context, R.layout.moment_item, null);
            holder = new ViewHolder(convertView);
<<<<<<< HEAD
            holder.momentItem = new MomentItem((Activity) context, context, convertView);
            holder.momentItem.initUIWidgets();
=======
            holder.iv_headshot = (ImageView) convertView.findViewById(R.id.iv_item_headshot);
            holder.tv_usrId = (TextView) convertView.findViewById(R.id.tv_item_usrId);
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_item_content);
            holder.tv_update_time = (TextView) convertView.findViewById(R.id.tv_item_update_time);
            holder.btn_comment = (ImageButton) convertView.findViewById(R.id.btn_item_comment);
>>>>>>> cdfcdfc46b6e5ef6f5ecad79807a164d7f0434e0
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
<<<<<<< HEAD
        holder.momentItem.bindDatas(info);
=======
        ImageLoaders.setImage(info.headshotUrl, holder.iv_headshot);
        holder.btn_comment.setOnClickListener(this);
        holder.tv_usrId.setText(info.userId);
        holder.tv_content.setText(info.content);
        holder.tv_update_time.setText("1小时前");
>>>>>>> cdfcdfc46b6e5ef6f5ecad79807a164d7f0434e0
        holder.ninePicsLayout.setIsShowAll(info.isShowAll);
        holder.ninePicsLayout.setUrlList(info.content_imgs);
        return convertView;
    }

<<<<<<< HEAD

    static class ViewHolder{
        MomentItem momentItem;
=======
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_item_comment:
                Toast.makeText(context, "有点击!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    static class ViewHolder{
        ImageView iv_headshot;
        TextView tv_usrId;
        TextView tv_content;
        TextView tv_update_time;
        ImageButton btn_comment;
>>>>>>> cdfcdfc46b6e5ef6f5ecad79807a164d7f0434e0
        MyMomentPicsLayout ninePicsLayout;
        InterfaceImpls interfaceImpls;

        public ViewHolder(View view){
            ninePicsLayout = (MyMomentPicsLayout) view.findViewById(R.id.nine_grid_layout);
            interfaceImpls = transacator.Transit();
            ninePicsLayout.setInterfaceImpls(interfaceImpls);
        }
    }

    public static MyPresenterTransacator transacator;

    public MyPresenterTransacator getMyPresenterTransacator(){
        return transacator;
    }

    public void setMyPresenterTransacator(MyPresenterTransacator transacator){
        this.transacator = transacator;
    }

    public interface MyPresenterTransacator{
        public InterfaceImpls Transit();
    }
}
