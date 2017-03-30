package com.test.testdemo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created on 2016/1/31.
 */
public class ListViewAdapter extends BaseAdapter implements View.OnClickListener {
    private static final String TAG = "Vertical_ListAdapter";
    LayoutInflater inflater;
    Context context;
    List<String> data = new ArrayList<>();
    private LVCallback mCallback;
    public static HashMap<Integer, Integer> isPraised;

    public ListViewAdapter(Context context, List<String> data, LVCallback callback) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
        mCallback = callback;
        init();
        animation = AnimationUtils.loadAnimation(context,R.anim.scale);
    }


    public void init() {
        isPraised = new HashMap<Integer, Integer>();
        for (int i = 0;i< data.size();i++){
            isPraised.put(i,R.mipmap.icon_praise);
        }
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

    Holder h;
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        h = new Holder();
        if (view == null) {
            view = inflater.inflate(R.layout.list_item, null);
            h.tv_content = (TextView) view.findViewById(R.id.tv_content);
            h.img_praise = (ImageView) view.findViewById(R.id.img_praise);
            h.img_reply = (ImageView) view.findViewById(R.id.img_reply);
            view.setTag(h);
        } else {
            h = (Holder) view.getTag();
        }
        h.tv_content.setText(data.get(position));
        h.img_praise.setImageResource(isPraised.get(position));
        h.img_praise.setOnClickListener(this);
        h.img_praise.setTag(position);
        if (click_id == position){
            h.img_praise.startAnimation(animation);
        }

        h.img_reply.setOnClickListener(this);
        h.img_reply.setTag(position);

        h.tv_content.setOnClickListener(this);
        h.tv_content.setTag(position);


        return view;
    }

    private Animation animation;
    private int click_id;

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.img_praise:
                mCallback.onPraiseClick(v);
                break;
            case R.id.img_reply:
                mCallback.onReplyClick(v);
                break;
            case R.id.tv_content:
                mCallback.onContentClick(v);
                break;
            default:
                break;
        }

//        h.img_praise.startAnimation(animation);
        click_id = (int) v.getTag();
        Log.d(TAG,"onClick");
    }

    public interface LVCallback{
        public void onPraiseClick(View v);
        public void onReplyClick(View v);
        public void onContentClick(View v);
    }


    class Holder {
        TextView tv_content;
        ImageView img_reply,img_praise;
    }
}
