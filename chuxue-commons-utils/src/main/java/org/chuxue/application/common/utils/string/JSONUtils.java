package org.chuxue.application.common.utils.string;

import java.util.Map;

import org.chuxue.application.common.base.BaseException;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 文件名 ： JSONUtils.java
 * 包 名 ： org.danyuan.application.common.utils.string
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2018年12月10日 下午3:28:12
 * 版 本 ： V1.0
 */
public class JSONUtils<T> {

	private static final Logger logger = LoggerFactory.getLogger(JSONUtils.class);
	
	/**
	 * 将json转化为Map
	 *
	 * @param jsonStr
	 * @param obj
	 * @return
	 */
	public static final Map<String, Object> stringToMap(String jsonString) {
		Map<String, Object> mapTypes = JSON.parseObject(jsonString);
		return mapTypes;
	}
	
	/**
	 * 将json转化为实体obj
	 *
	 * @param jsonStr
	 * @param obj
	 * @return
	 */
	public static <T> T stringToObj(String jsonStr, Class<T> obj) {
		if (jsonStr == null || jsonStr.isEmpty()) {
			return null;
		}
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			T t = objectMapper.readValue(jsonStr, obj);
			return t;
		} catch (Exception e) {
			logger.error("stringToObj:", e.getMessage());
			throw new BaseException(-1, e.getMessage());
		}
		
	}

	/**
	 * json to xml
	 *
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	public static String json2xml(String json) {
		JSONObject jsonObj = new JSONObject(json);
		return "<xml>" + XML.toString(jsonObj) + "</xml>";
	}
	
	/**
	 * xml to json
	 *
	 * @param xml
	 * @return
	 */
	public static String xml2json(String xml) {
		JSONObject xmlJSONObj = XML.toJSONObject(xml.replace("<xml>", "").replace("</xml>", ""));
		return xmlJSONObj.toString();
	}
}
