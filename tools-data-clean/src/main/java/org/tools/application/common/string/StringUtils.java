package org.tools.application.common.string;

public class StringUtils {

	public static String toHalf(String str) {
		return BCConvert.qj2bj(str);
	}
	
	public static String replaceSpace(String str) {
		if (str == null) {
			return null;
		}
		str = str.trim();
		str = str.replace(" ", "");
		str = str.replace("-", "");
		str = str.replace("_", "");
		str = str.replace("—", "");
		str = str.replace("null", "");
		str = str.replace("空", "");
		str = str.replace("无", "");
		if (str.isEmpty()) {
			return null;
		}
		return str;
	}

}
