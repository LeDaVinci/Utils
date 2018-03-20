package com.wanghang.utils;

import android.util.Log;

public class LogUtil {
	private static final String TAG = "SQM";
	public static final boolean DEBUG = true;
	
	public static void g(String tag, String msg) {
		d(TAG, tag + ": " + msg);
	}
	
	public static void d(String tag, String msg) {
		if (DEBUG) {
			Log.d(tag, msg);
		}
	}
	
	public static void e(String tag, String msg) {
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		Log.e(tag, methodName + ":" + msg);
	}
	
	public static void i(String tag, String msg) {
		Log.i(tag, msg);
	}
	
}
