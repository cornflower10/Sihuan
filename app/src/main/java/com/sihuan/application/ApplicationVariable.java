package com.sihuan.application;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.sihuan.communication.OkHttpStack;
import com.squareup.okhttp.OkUrlFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ApplicationVariable extends Application {

	private static ApplicationVariable applicationVariable;

	public static synchronized ApplicationVariable getInstance() {
		return applicationVariable;
	}

	public static RequestQueue queues;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		applicationVariable = this;
		queues = Volley.newRequestQueue(this,new OkHttpStack());

	}

	public  RequestQueue getHttpQueues(){
		return queues;
	};



	/**
	 * Adds the specified request to the global queue, if tag is specified
	 * then it is used else Default TAG is used.
	 *
	 * @param req
	 * @param tag
	 */
	public <T> void addToRequestQueue(Request<T> req, String tag) {
		// set the default tag if tag is empty
		req.setTag(tag);

		VolleyLog.d("Adding request to queue: %s", req.getUrl());

		queues.add(req);
	}


	/**
	 * Cancels all pending requests by the specified TAG, it is important
	 * to specify a TAG so that the pending/ongoing requests can be cancelled.
	 *
	 * @param tag
	 */
	public void cancelPendingRequests(Object tag) {
		if (queues != null) {
			queues.cancelAll(tag);
		}
	}

	
}
