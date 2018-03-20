package com.wanghang.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;

public class FileUtil {
	public static void createDir(String path) {
		File desFile = new File(path);
		if (!desFile.exists()) {
			desFile.mkdir();
			desFile.setReadable(true);
			desFile.setWritable(true);
		}
	}

	public static boolean createFile(String parentDirPath, String fileName) {
		File file = new File(parentDirPath, fileName);
		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
			file.setReadable(true);
			file.setWritable(true);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean checkAPExits(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

	public static String readStringFromFile(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileReader freader;
		try {
			String line = "";
			String cache = "";
			freader = new FileReader(file);
			BufferedReader reader = new BufferedReader(freader);
			while ((cache = reader.readLine()) != null) {
				line = line + cache;
			}
			reader.close();
			return line;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return return the default file path, it will return
	 *         /data/data/PackageName/files/
	 * */
	public static String getDefaultFilePath(Context context) {
		return context.getFilesDir().getPath();
	}

	/**
	 * @return return the default file path, it will return
	 *         /data/data/PackageName/files/
	 * */
	public static String getDefaultFilePath(Context context, String fileName) {
		return context.getFilesDir().getPath() + "/" + fileName;
	}

	/**
	 * update value in sqm.ini
	 * 
	 * @param key
	 * @param value
	 */
	public static void updateParameter(final String key, final String value) {
		new Thread(new Runnable() {
			public void run() {
				File file = new File("/data/tmp/sqm.ini");
				if (file.exists()) {
					BufferedReader reader = null;
					BufferedWriter writer = null;
					FileOutputStream fos = null;
					try {
						reader = new BufferedReader(new InputStreamReader(
								new FileInputStream(file)));
						ByteArrayOutputStream os = new ByteArrayOutputStream();
						writer = new BufferedWriter(new OutputStreamWriter(os));
						String str;
						while ((str = reader.readLine()) != null) {
							if (str.startsWith(key)) {
								String[] split = str.split("=");
								split[1] = value;
								str = split[0] + "= " + split[1];
							}
							writer.write(str);
							writer.newLine();
							writer.flush();
						}

						fos = new FileOutputStream(file);
						fos.write(os.toByteArray());
						fos.flush();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							if (reader != null) {
								reader.close();
							}
							if (writer != null) {
								writer.close();
							}
							if (fos != null) {
								fos.close();
							}
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				}
			}
		}).start();
	}
}
