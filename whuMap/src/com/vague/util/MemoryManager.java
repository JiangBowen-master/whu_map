package com.vague.util;

import java.io.File;

import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

public class MemoryManager {
	private static final String TAG = "MemoryManager";
	private static final int MAXMEMORY=300*1024*1024;//�������е�����ڴ� ģ����(0-16m)
	/**
	 * �ж�ϵͳ�Ƿ��ڵ��ڴ�������
	 * @param context
	 * @return
	 */
	public static boolean hasAcailMemory() {
		// ��ȡ�ֻ��ڲ��ռ��С
		long memory = getAvailableInternalMemorySize();
		Log.i(TAG, memory+"");
		if (memory < MAXMEMORY) {
			//Ӧ�ý����ڵ��ڴ�״̬������
			return false;
		} else {
			return true;
		}
	}

	/**
	 * ��ȡ�ֻ��ڲ����ÿռ��С
	 * 
	 * @return
	 */
	public static long getAvailableInternalMemorySize() {
		File path = Environment.getDataDirectory();// ��ȡ Android ����Ŀ¼
		StatFs stat = new StatFs(path.getPath());// һ��ģ��linux��df�����һ����,���SD�����ֻ��ڴ��ʹ�����
		long blockSize = stat.getBlockSize();// ���� Int ����С�����ֽ�Ϊ��λ��һ���ļ�ϵͳ
		long availableBlocks = stat.getAvailableBlocks();// ���� Int ����ȡ��ǰ���õĴ洢�ռ�
		return availableBlocks * blockSize;
	}

	/**
	 * ��ȡ�ֻ��ڲ��ռ��С
	 * 
	 * @return
	 */
	public static long getTotalInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();// ��ȡ��������õ��ļ�ϵͳ��
		return totalBlocks * blockSize;
	}

	/**
	 * ��ȡ�ֻ��ⲿ���ÿռ��С
	 * 
	 * @return
	 */
	public static long getAvailableExternalMemorySize() {
		if (externalMemoryAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();
			return availableBlocks * blockSize;
		} else {
			throw new RuntimeException("Don't have sdcard.");
		}
	}

	/**
	 * ��ȡ�ֻ��ⲿ�ռ��С
	 * 
	 * @return
	 */
	public static long getTotalExternalMemorySize() {
		if (externalMemoryAvailable()) {
			File path = Environment.getExternalStorageDirectory();// ��ȡ�ⲿ�洢Ŀ¼�� SDCard
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long totalBlocks = stat.getBlockCount();
			return totalBlocks * blockSize;
		} else {
			throw new RuntimeException("Don't have sdcard.");
		}
	}

	/**
	 * �ⲿ�洢�Ƿ����
	 * 
	 * @return
	 */
	public static boolean externalMemoryAvailable() {
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}
}
