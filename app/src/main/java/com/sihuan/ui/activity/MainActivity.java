package com.sihuan.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.VolleyError;
import com.sihuan.R;
import com.sihuan.communication.VolleyInterface;
import com.sihuan.communication.VolleyRequest;
import com.sihuan.util.ToastUtil;
import com.sihuan.util.WriteLog;

public class MainActivity extends BaseActivity {
    private FloatingActionButton fab;
    private Toolbar toolbar;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        fab = getViewById(R.id.fab);
         toolbar = getViewById(R.id.toolbar);
        WriteLog.WriteLogCatError("=====fab=======");
        setSupportActionBar(toolbar);
        mmmm();
    }


    public void mmmm(){

        // String url ="https://www.baidu.com";
        String url ="https://mmmoffice.com/";
        VolleyRequest.RequsetGet(this,url , "baidu", new VolleyInterface(
                VolleyInterface.listener,VolleyInterface.errorListener) {
            @Override
            public void onLoadingSuccess(String result) {
                WriteLog.WriteLogCatError("result"+result);
                ToastUtil.show(result);
            }

            @Override
            public void onLoadingError(VolleyError volleyError) {

                WriteLog.WriteLogCatError("volleyError===="+volleyError.getMessage());
                ToastUtil.show("volleyError===="+volleyError.getMessage());
            }
        });
    }

    @Override
    protected void setListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
