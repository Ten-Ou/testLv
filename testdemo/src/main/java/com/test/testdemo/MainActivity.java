package com.test.testdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListViewAdapter.LVCallback, ListView2Adapter.LvCallback {

    private ListView lv;
    private ListViewAdapter adapter;
    private ListView2Adapter adapter2;
    private List<TestEntity> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        lv = (ListView) findViewById(R.id.lv);
//        adapter = new ListViewAdapter(this, list, this);
//        lv.setAdapter(adapter);
        adapter2 = new ListView2Adapter(this, list, this);
        lv.setAdapter(adapter2);
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getAction().equals("1")) {
//                adapter2.setItemOnValue(i);
//            }else {
//                adapter2.setItemOffValue(i);
//            }
//        }
//        adapter2.notifyDataSetChanged();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                list.get(3).setAction("0");
                adapter2.notifyDataSetChanged();
            }
        },10 * 1000);
    }
//https://github.com/oushuxu/testLv.git
    private void initData() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                list.add(new TestEntity("这是第" + i + "条数据！", "1", "1"));
            } else if (i % 3 == 0) {
                list.add(new TestEntity("这是第" + i + "条数据！", "0", "3"));
            } else if (i % 4 == 0) {
                list.add(new TestEntity("这是第" + i + "条数据！", "1", "4"));
            } else {
                list.add(new TestEntity("这是第" + i + "条数据！", "0", "2"));
            }
        }
    }

    @Override
    public void onPraiseClick(View v) {
        Toast.makeText(MainActivity.this, "点赞成功！，位置 ： "
                + (Integer) v.getTag(), Toast.LENGTH_SHORT).show();
        adapter.isPraised.put((Integer) v.getTag(), R.mipmap.icon_praised);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onReplyClick(View v) {
        Toast.makeText(MainActivity.this, "回复第"
                + (Integer) v.getTag() + "条数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onContentClick(View v) {
        Toast.makeText(MainActivity.this, "listView 内部内容的按钮被点击到了！，内容 ： "
                + list.get((Integer) v.getTag()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        if (list.get(position).getAction().equals("1")) {
            list.get(position).setAction("0");
        } else {
            list.get(position).setAction("1");
        }
        adapter2.notifyDataSetChanged();
        Toast.makeText(MainActivity.this, "listView 开关 "
                + list.get((Integer) v.getTag()), Toast.LENGTH_SHORT).show();
    }
}
