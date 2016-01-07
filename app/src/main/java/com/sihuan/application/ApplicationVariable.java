package com.sihuan.application;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ApplicationVariable extends Application {

	private static ApplicationVariable applicationVariable;

	public static ApplicationVariable getInstance() {
		return applicationVariable;
	}

	public static RequestQueue queues;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		applicationVariable = this;
		
	}



	
}
