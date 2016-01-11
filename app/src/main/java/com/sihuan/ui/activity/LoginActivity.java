package com.sihuan.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sihuan.R;

public class LoginActivity extends BaseActivity {


    private EditText et_name,et_passwd;
    private Button btn_login;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        et_name =  getViewById(R.id.et_name);
        et_passwd = getViewById(R.id.et_passwd);

        btn_login = getViewById(R.id.btn_login);

        unClick();

    }


    /**
     * 可以点击
     */
    private void canClick() {
        btn_login.setAlpha(1f);
        btn_login.setClickable(true);
    }

    /**
     * 不可以点击
     */
    private void unClick() {

        btn_login.setAlpha(0.5f);
        btn_login.setClickable(false);
    }


    @Override
    protected void setListener() {
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if((!TextUtils.isEmpty(editable.toString()))&&(!TextUtils.isEmpty(et_passwd.getText()))){
                    canClick();
                }else
                    unClick();

            }
        });

        et_passwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if((!TextUtils.isEmpty(editable.toString()))&&(!TextUtils.isEmpty(et_name.getText()))){
                    canClick();
                }else
                    unClick();

            }
        });

    }


    /**
     * 登陆
     */
    public void click(View view){



    }

}
