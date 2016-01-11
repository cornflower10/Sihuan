package com.sihuan.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.sihuan.R;

public class initActivity extends BaseActivity {

    private ImageView iv_captcha;
    private EditText et_captcha;
    private Button btn_ok;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_init);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        iv_captcha =  getViewById(R.id.iv_captcha);
        et_captcha =  getViewById(R.id.et_captcha);
        btn_ok =  getViewById(R.id.btn_ok);
        btn_ok.setClickable(false);
        btn_ok.setAlpha(0.5f);
    }

    @Override
    protected void setListener() {
        et_captcha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!TextUtils.isEmpty(editable)){
                    btn_ok.setClickable(true);
                    btn_ok.setAlpha(1.0f);
                }else
                {
                    btn_ok.setClickable(false);
                    btn_ok.setAlpha(0.5f);
                }

            }
        });
    }

    public void click(View view){
       int id = view.getId();

       switch (id){

           case R.id.bt_refresh:
               /**
                * 刷新验证码
                */



           break;

           case R.id.btn_ok:
               /**
                * 提交验证码
                */


               if(TextUtils.isEmpty(et_captcha.getText())){
                   return;
               }

               break;
       }

    }

}
