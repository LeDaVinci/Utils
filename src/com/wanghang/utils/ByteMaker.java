package com.wanghang.utils;

public class ByteMaker {
	
	public static byte[] append(byte[] head, byte[] second) {
		int headLength;
		int secondLength = second.length;
		byte[] bytes;
		if (head == null) {
			headLength = 0;
			bytes = new byte[headLength + secondLength];
		} else {
			headLength = head.length;
			bytes = new byte[headLength + secondLength];
			System.arraycopy(head, 0, bytes, 0, headLength);
		}
		System.arraycopy(second, 0, bytes, headLength, secondLength);
		return head = bytes;
	}
	
	@Deprecated
	/**
	 * 
	 * @param n
	 * @param bytes
	 * @param start
	 * 				从高位开始存�?
	 */
	public static void int2bytes(int n, byte[] bytes, int start) {
		for (int i = 0; i < 4; i++) {
			bytes[i + start] = (byte) (n >> (24 - i * 8));
		}
	}

	@Deprecated
	/**
	 * 
	 * @param n
	 * @param bytes
	 * @param end
	 * 				从高位开始存�?
	 */
	public static void int2bytes_sensor(int n, byte[] bytes, int end) {
		for (int i = 0; i < 4; i++) {
			bytes[end - i] = (byte) (n >> (24 - i * 8));
		}
	}
	
	@Deprecated
	/**
	 * 
	 * @param x
	 * @param bytes
	 * @param start
	 * 				从高位开始存�?
	 */
	public static void float2bytes(float x, byte[] bytes, int start) {
		int l = Float.floatToIntBits(x);
		for (int i = 0; i < 4; i++) {
			bytes[start + i] = Integer.valueOf(l).byteValue();
			l = l >> 8;
		}
	}
	
	@Deprecated
	public static int byte2int(byte b) {
		return (int) (b & 0xff);
	}

	@Deprecated
	/**
	 * 
	 * @param b
	 * @param start
	 * @return 从高位开始存�?
	 */
	public static int bytes2int(byte[] b, int start) {
		return (int) (((b[start] & 0xff) << 24) | ((b[start + 1] & 0xff) << 16)
				| ((b[start + 2] & 0xff) << 8) | ((b[start + 3] & 0xff) << 0));
	}
	
	/**
	 * 
	 * @param bytes
	 * @param start
	 * @param length 
	 * @return 从高位到低位存储
	 */
	public static int bytes2int(byte[] bytes, int start, int length) {
		int a = 0;
		for(int i= 0; i< length; i++) {
			a |= (bytes[start + i] & 0xff) << ((length - 1 - i)*8);
		}
		return a;
	}
	
	/**
	 * 
	 * @param n
	 * @param bytes
	 * @param start
	 * @param length
	 * 				从高位到低位存储
	 */
	public static void int2bytes(int n, byte[] bytes, int start, int length) {
		for(int i = 0; i < length; i++) {
			bytes[start + i] = (byte) (n >> 8 * (length - 1 - i));
		}
	}
	
	/**
	 * 
	 * @param n
	 * @param length int�?占的字节�? ,应该不大�?4
	 * @return 高位到低位存�?
	 */
	public static byte[] int2bytes(int n, int length) {
		byte[] newBytes = new byte[length];
		for (int i = 0; i < newBytes.length; i++) {
			newBytes[i] = (byte) (n >> 8 * (length - 1 - i));
		}
		return newBytes;
	}
	
	/**
	 * 
	 * @param n 
	 * @return 32位int转化为bytes,高位到低位存�?
	 */
	 public static byte[] int2bytes(int n) {
		 return int2bytes(n, 4);
	 }
 	
	/**
	 * 复制出src从start到end的内�?
	 * @param src
	 * @param start
	 * @param length
	 * @return src从start到start + length的内�?
	 */
	
	public static byte[] copyBytes(byte[] src, int start, int length) {
		byte[] des = new byte[length];
		System.arraycopy(src, start, des, 0, length);
		return des;
	}

	public static void copyBytes(byte[] from, byte[] to, int start) {
		for (int i = 0; i < from.length; i++) {
			to[start + i] = from[i];
		}
	}
	
    public static String intToString(int i) {     
        byte[] result = new byte[4];      
        result[0] = (byte)((i >> 24) & 0xFF);  
        result[1] = (byte)((i >> 16) & 0xFF);  
        result[2] = (byte)((i >> 8) & 0xFF);  
        result[3] = (byte)(i & 0xFF);  
        return new String(result);  
    } 
	
    public static int stringToInt(String str) {
    	byte[] bytes = str.getBytes();
        int value = 0;  
        for (int i = 0; i < 4; i++) {  
            int shift = (4 - 1 - i) * 8;  
            value += (bytes[i] & 0x000000FF) << shift;
        }  
        return value;  
    }
}
