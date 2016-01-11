package com.sihuan.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.sihuan.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/1/11.
 */
public class NewsActivity extends BaseActivity {
    SimpleAdapter adapter;
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.news_mmm);

        ListView listView = (ListView) findViewById(R.id.listView);

        adapter = new SimpleAdapter(this, getData(), R.layout.newslayout,new String[]{"title","info","imgmmm"},
                new int[]{R.id.title,R.id.info,R.id.imgmmm});

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   Map<String,Object> m = (Map<String,Object>)adapter.getItem(position);
                   Intent intent = new Intent();
                   intent.putExtra("info",(String)m.get("info"));
                   intent.setClass(NewsActivity.this,NewsDetailActivity.class);
                   startActivity(intent);

           }

        });

    }
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "2016.1.10");
        map.put("info", "这里是新闻");
        map.put("imgmmm", R.drawable.background_mmm);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "2016.1.9");
        map.put("info", "这里是新闻");
        map.put("imgmmm", R.drawable.background_mmm);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "2016.1.8");
        map.put("info", "这里是新闻");
        map.put("imgmmm", R.drawable.background_mmm);
        list.add(map);

        return list;
    }
    @Override
    protected void setListener() {

    }
}
