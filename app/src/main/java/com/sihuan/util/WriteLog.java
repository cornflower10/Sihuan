package com.sihuan.util;



import android.util.Log;

import com.sihuan.common.Constants;


public class WriteLog {
	public static void WriteLogCatInfo(String sWriteStr) {
		if (Constants.DEBUG)
			Log.i(Constants.DEBUG_TAG, sWriteStr);
	}
	
	public static void WriteLogCatWarn(String sWriteStr) {
		if (Constants.DEBUG)
			Log.w(Constants.DEBUG_TAG, sWriteStr);
	}
	
	public static void WriteLogCatVerbose(String sWriteStr) {
		if (Constants.DEBUG)
			Log.v(Constants.DEBUG_TAG, sWriteStr);
	}
	
	public static void WriteLogCatDebug(String sWriteStr) {
		if (Constants.DEBUG)
			Log.d(Constants.DEBUG_TAG, sWriteStr);
	}
	
	public static void WriteLogCatError(String sWriteStr) {
		if (Constants.DEBUG)
			Log.e(Constants.DEBUG_TAG, sWriteStr);
	}
}
