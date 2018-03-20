package com.wanghang.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.util.Log;

public class NetWorkUtil {
	
	/**
	 * get the local ip
	 * @return ip like :127.0.0.1
	 */
	public static String getLocalIp() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()
							&& !inetAddress.isLinkLocalAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.e("WifiPreference IpAddress", ex.toString());
		}
		return null;
	
	}
	
	/**
	 * transform int ipAddress to the String like 127.0.0.1
	 * @param ipAddress
	 * @return
	 */
	public static String ipAddress2IpStr(int ipAddress) {
		final long[] mask = { 0x000000FF, 0x0000FF00, 0x00FF0000, 0xFF000000 };
		final StringBuilder ipStr = new StringBuilder();
		for (int i = 0; i < mask.length; i++) {
			ipStr.append((ipAddress & mask[i]) >> (i * 8));
			if (i < mask.length - 1) {
				ipStr.append(".");
			}
		}
		return ipStr.toString();
	}
}
