package com.sihuan.communication;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.sihuan.application.ApplicationVariable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiejingbao on 2016/1/7.
 */
public class VolleyRequest {
    public static StringRequest stringRequest;
    public static Context context;

    public static void RequsetGet(Context mContext,String url,
                                  String tag,VolleyInterface vif){

        ApplicationVariable.getInstance().cancelPendingRequests(tag);

        stringRequest = new StringRequest(Request.Method.GET,url,
                vif.loadingListener(),vif.errorListener());
        stringRequest.getTimeoutMs();
        ApplicationVariable.getInstance().addToRequestQueue(stringRequest,tag);
        ApplicationVariable.getInstance().getHttpQueues().start();
    }

    public static void RequsetPost(Context mContext, String url, String tag,
                                   final HashMap<String,String> params, VolleyInterface vif){

        ApplicationVariable.getInstance().cancelPendingRequests(tag);

        stringRequest = new StringRequest(Request.Method.GET,url,vif.loadingListener(),vif.errorListener())
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        ApplicationVariable.getInstance().addToRequestQueue(stringRequest,tag);
        ApplicationVariable.getInstance().getHttpQueues().start();
    }

}
