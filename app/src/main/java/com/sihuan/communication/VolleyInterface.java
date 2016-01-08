package com.sihuan.communication;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by xiejingbao on 2016/1/7.
 */
public abstract class VolleyInterface {

    public static Response.Listener listener;
    public static Response.ErrorListener errorListener;

    public VolleyInterface( Response.Listener listener,
                           Response.ErrorListener errorListener){
        this.listener = listener;
        this.errorListener = errorListener;
    }

    public Response.Listener<String> loadingListener(){
        listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                onLoadingSuccess(result);
            }
        };
        return listener;
    }

    public Response.ErrorListener errorListener(){
      errorListener = new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError volleyError) {

              onLoadingError(volleyError);
          }
      } ;
        return errorListener;
    }


    public abstract void onLoadingSuccess(String result);

    public abstract void onLoadingError(VolleyError volleyError);
}
