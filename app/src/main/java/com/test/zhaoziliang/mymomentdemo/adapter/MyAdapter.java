package com.test.zhaoziliang.mymomentdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.test.zhaoziliang.mymomentdemo.R;

import java.util.ArrayList;

import Impl.InterfaceImpls;
import bean.MomentItem;
import bean.UserInfo;
import widget.MyMomentPicsLayout;

/**
 * Created by zhaoziliang on 16/10/18.
 */

public class MyAdapter extends BaseAdapter{
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
            holder.momentItem = new MomentItem((Activity) context, context, convertView);
            holder.momentItem.initUIWidgets();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.momentItem.bindDatas(info);
        holder.ninePicsLayout.setIsShowAll(info.isShowAll);
        holder.ninePicsLayout.setUrlList(info.content_imgs);
        return convertView;
    }


    static class ViewHolder{
        MomentItem momentItem;
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
