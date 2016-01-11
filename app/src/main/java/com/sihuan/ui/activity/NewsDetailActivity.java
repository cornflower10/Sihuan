package com.sihuan.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.sihuan.R;

/**
 * Created by admin on 2016/1/11.
 */
public class NewsDetailActivity extends BaseActivity{
    @Override
    protected void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String id = intent.getStringExtra("info");
        setContentView(R.layout.newsdetail_mmm);
        TextView tv = (TextView)findViewById(R.id.newscontent);
        tv.setText(id+"同日，国家互联网信息办公室发言人姜军就“快播”案发表谈话称，所有利用网络技术开展服务的网站，都应对其传播的内容承担法律责任。\n" +
                "　　华中师范大学社会学梅志罡教授表示，当部分网民接触到这类不良内容后，也许会失去辨识能力，导致认知、行为错乱，可能危害个人，也可能危害社会。\n" +
                "　　记者从武汉市公安局网安支队了解到，目前此案正在调查中，案情将在第一时间向荆楚网披露。");
    }

    @Override
    protected void setListener() {

    }
}
