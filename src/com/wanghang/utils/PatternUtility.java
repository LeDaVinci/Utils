package com.wanghang.utils;


public class PatternUtility {

	
	public static String getJsonString(String resource){
		/*Pattern pattern = Pattern.compile("{[^{}]*}");
		Matcher matcher = pattern.matcher(resource);
		StringBuffer buffer = new StringBuffer();
		while(matcher.find()){             
		    buffer.append(matcher.group());   
		}
		return buffer.toString();*/
		int start = resource.indexOf("{");
		int end = resource.indexOf("}");
		return resource.substring(start, end+1);
	}
	
	public static boolean checkContainsJson(String resource){
		return resource.contains("{") && resource.contains("}");
	}
	
	public static String getStrFromResContainsJson(String resource){
		int index = resource.indexOf(",");
		return resource.substring(0, index);
	}
	
	public static boolean checkIsValidPicUrl(String url) {
		if (url.endsWith(".gif") || url.endsWith(".png") || url.endsWith(".jpg")) {
			return true;
		}
		return false;
	}
	
	public static boolean checkIsValidServerUrl(String url) {
		if (url.startsWith("ftp") || url.startsWith("sftp")) {
			return true;
		}
		return false;
	}
	
	public static String getRemoteFileDirPath(String path) {
		if (path.contains(".")) {
			int endIndex = path.lastIndexOf("/");
			return path.substring(0, endIndex);
		};
		return path;
	}
	
	public static String getRemoteFileName(String path) {
		if (path.contains(".")) {
			int startIndex = path.lastIndexOf("/");
			return path.substring(startIndex + 1);
		};
		return null;
	}
	
	public static  String matchEPGIp(String address) {
		String ntpHost = address;
		if (address.contains("/")) {
			int startIndex = address.indexOf("/") + 2;
			int endIndex = address.indexOf(":", startIndex);
			if (endIndex == -1) {
				ntpHost = address.substring(startIndex);
			} else {
				ntpHost = address.substring(startIndex, endIndex);
			}
		}
		return ntpHost;
	}
	
	public static int matchEPGport(String address) {
		String port = "0";
		if (address.contains("/")) {
			int startIndex = address.indexOf("/") + 2;
			startIndex = address.indexOf(":", startIndex);
			int endIndex = address.indexOf("/", startIndex);
			if (endIndex == -1) {
				port = address.substring(startIndex);
			} else {
				port = address.substring(startIndex, endIndex);
			}
		}
		return Integer.valueOf(port);
	}
}
