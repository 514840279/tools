package org.chuxue.application.common.utils.files;

import java.io.File;

import org.chuxue.application.common.base.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @文件名 FileDelete.java
 * @包名 org.danyuan.application.common.utils.files
 * @描述 TODO(用一句话描述该文件做什么)
 * @时间 2019年1月18日 下午6:27:52
 * @author Administrator
 * @版本 V1.0
 */
public class FileDelete {
	
	private static final Logger logger = LoggerFactory.getLogger(FileDelete.class);

	/***
	 * 删除指定文件夹下所有文件
	 *
	 * @param path
	 *            文件夹完整绝对路径
	 * @return
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (String element : tempList) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + element);
			} else {
				temp = new File(path + File.separator + element);
			}
			if (temp.isFile()) {
				if (!temp.delete()) {
					throw new BaseException(-1, "文件沒有刪除掉,.");
				}
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + element);// 先删除文件夹里面的文件
				delFolder(path + "/" + element);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	
	/***
	 * 删除文件夹
	 *
	 * @param folderPath文件夹完整绝对路径
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.delete()) {
				throw new BaseException(-1, "文件沒有刪除掉,.");
			}
		} catch (Exception e) {
			logger.error("删除文件时出错:{}", e.getMessage());
		}
	}
}
