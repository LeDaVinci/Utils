package com.wanghang.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class Screen {


	private Context mContext;
	private int width;
	private int hight;
	private int realWidth;
	private int realHight;
	private final int TVWidth = 1280;
	private final int TVHight = 720;

	public static final int SCREEN_PORT = 0x101;
	public static final int SCREEN_LAND = 0x102;

	public Screen(Context context) {
		this.mContext = context;
		getScreen();
	}
	
	private void getScreen() {
		WindowManager manager = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		Display display = manager.getDefaultDisplay();
		display.getMetrics(dm);
		hight = dm.heightPixels;
		width = dm.widthPixels;
		try {
			Class<?> displayClass = Class.forName("android.view.Display");
			Method method = displayClass.getMethod("getRealMetrics",DisplayMetrics.class);
			method.invoke(display, dm);
			realWidth = dm.widthPixels;
			realHight = dm.heightPixels; 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return hight;
	}
	
	public int getRealWidth() {
		return realWidth;
	}
	
	public int getRealHeight() {
		return realHight;
	}

	public static boolean isScreenOriatationPortrait(Context context) {
		return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
	}

	public static int getCurrentScreenOritattion(Context context) {
		return isScreenOriatationPortrait(context) ? SCREEN_PORT : SCREEN_LAND;
	}

	public float castX(float x) {
		return x * TVWidth / width;
	}

	public float castY(float y) {
		return y * TVHight / hight;
	}

}
