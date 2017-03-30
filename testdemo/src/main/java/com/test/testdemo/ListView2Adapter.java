package com.test.testdemo;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.test.testdemo.ListViewAdapter.isPraised;


/**
 * Created on 2016/1/31.
 */
public class ListView2Adapter extends BaseAdapter implements View.OnClickListener {
    private static final String TAG = "Vertical_ListAdapter";
    LayoutInflater inflater;
    Context context;
    List<TestEntity> data = new ArrayList<>();
    private LvCallback mCallback;
    private SparseBooleanArray selected;
    private int old;

    public ListView2Adapter(Context context, List<TestEntity> data, LvCallback callback) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
        mCallback = callback;
        selected = new SparseBooleanArray();
//        init();
    }


    public void init() {
        isPraised = new HashMap<Integer, Integer>();
        for (int i = 0; i < data.size(); i++) {
            isPraised.put(i, R.mipmap.icon_praise);
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
        TestEntity entity = data.get(position);
        if (view == null) {
            view = inflater.inflate(R.layout.list_item2, null);
            h.tv_content = (TextView) view.findViewById(R.id.tv_content);
            h.tv_On = (TextView) view.findViewById(R.id.tvOn);
            view.setTag(h);
        } else {
            h = (Holder) view.getTag();
        }
        h.tv_content.setText(entity.getContent());

        if ("1".equals(entity.getFunctionFlag())) {
            onlyOpen(entity, h.tv_On);
        } else if ("2".equals(entity.getFunctionFlag())) {
            onlyClose(entity, h.tv_On);
        } else if ("3".equals(entity.getFunctionFlag()) || "4".equals(entity.getFunctionFlag())) {
            if ("1".equals(entity.getAction())) {
                open(entity, h.tv_On);
            } else {
                close(entity, h.tv_On);
            }
        } else {
            //
        }
        h.tv_On.setOnClickListener(this);
        h.tv_On.setTag(position);
        return view;
    }


    private void onlyOpen(TestEntity entity, TextView tv) {
        tv.setText("ON");
        if (entity.getAction().equals("1")) {
            tv.setBackgroundResource(R.drawable.turn_on_bg);
        } else {
            tv.setBackgroundResource(R.drawable.turn_off_bg);
        }
    }

    private void onlyClose(TestEntity entity, TextView tv) {
        tv.setText("OFF");
        if (entity.getAction().equals("1")) {
            tv.setBackgroundResource(R.drawable.turn_on_bg);
        } else {
            tv.setBackgroundResource(R.drawable.turn_off_bg);
        }
    }

    private void close(TestEntity entity, TextView tv) {
        tv.setText("OFF");
        tv.setBackgroundResource(R.drawable.turn_off_bg);
    }

    private void open(TestEntity entity, TextView tv) {
        tv.setText("ON");
        tv.setBackgroundResource(R.drawable.turn_on_bg);
    }

    private int click_id;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tvOn:
                mCallback.onClick(v);
                break;
            default:
                break;
        }

//        h.img_praise.startAnimation(animation);
        click_id = (int) v.getTag();
        Log.d(TAG, "onClick");
    }

    public void setSelectedItem(int selected) {
        if (old != -1) {
            this.selected.put(old, false);
        }
        this.selected.put(selected, true);
        old = selected;
    }

    public void setItemOnValue(int pos) {
        this.selected.put(pos, true);
    }

    public void setItemOffValue(int pos) {
        this.selected.put(pos, false);
    }

    public interface LvCallback {
        public void onClick(View v);
    }


    class Holder {
        TextView tv_content, tv_On;
    }
}
