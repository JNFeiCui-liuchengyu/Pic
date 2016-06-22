package com.feicui.pic;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by liuchengyu on 2016/6/22.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private LayoutInflater          mInflater;
    private List<Jeson.ResultsBean> mData;
    Context context;
    private static final String TAG = "MyAdapter";
    public MyAdapter(Context context, List<Jeson.ResultsBean> list) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.mData = list;
    }
public void addAllData(List<Jeson.ResultsBean> list){
    mData.addAll(list);
}
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(R.layout.item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Jeson.ResultsBean json = mData.get(position);
        String url=json.getUrl();
        String desc=json.getDesc();
        String time=json.getPublishedAt();
        String createAt=json.getCreatedAt();
        String type=json.getType();
        Log.d(TAG, "onBindViewHolder: "+desc);
        Picasso.with(context).load(url).into(holder.iv);
        ViewCompat.setTransitionName(holder.iv,url );
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
