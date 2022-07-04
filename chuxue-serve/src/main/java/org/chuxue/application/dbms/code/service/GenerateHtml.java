package org.chuxue.application.dbms.code.service;

import java.util.List;

import org.chuxue.application.bean.manager.dbms.SysDbmsGenerateCodeInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsColsInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;
import org.chuxue.application.common.utils.files.TxtFilesWriter;

import com.alibaba.nacos.common.utils.StringUtils;

/**
 * @文件名 GenerateHtml.java
 * @包名 org.danyuan.application.dbms.code.service
 * @描述 页面生成
 * @时间 2019年1月17日 下午2:26:35
 * @author Administrator
 * @版本 V1.0
 */
public class GenerateHtml {
	
	/**
	 * 方法名： generateVue3
	 * 功 能： 生成vue3版本的代码 依赖于 tools-manager ：https://github.com/514840279/tools-manager
	 * 参 数： @param sysDbmsGenerateCodeInfo
	 * 参 数： @param tabsInfo
	 * 参 数： @param colsInfos
	 * 参 数： @param username
	 * 参 数： @param pathString
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	public static void generateVue3(SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo, SysDbmsTabsTableInfo tabsInfo, List<SysDbmsTabsColsInfo> colsInfos, String username, String pathString) {
		String subServiceNameString = sysDbmsGenerateCodeInfo.getClassName().substring(0, 1).toLowerCase() + sysDbmsGenerateCodeInfo.getClassName().substring(1);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<template>\r\n");
		stringBuilder.append("  <div>\r\n");
		stringBuilder.append("    <Table :columns=\"columns\" :rootUrl=\"rootUrl\" />\r\n");
		stringBuilder.append("  </div>\r\n");
		stringBuilder.append("</template>\r\n");
		stringBuilder.append("\r\n");
		stringBuilder.append("<script setup lang=\"ts\">\r\n");
		stringBuilder.append("import Table from \"@components/table/Table.vue\";\r\n");
		stringBuilder.append("import { Column } from \"@interface/Table\";\r\n");
		stringBuilder.append("");

		stringBuilder.append("// 修改请求地址\r\n");
		stringBuilder.append("let rootUrl: String = \"/serve/" + subServiceNameString + "\",\r\n");
		stringBuilder.append("  columns: Array<Column> = [\r\n");
		for (SysDbmsTabsColsInfo sysDbmsTabsColsInfo : colsInfos) {
			
			// 属性
			String colsName = sysDbmsTabsColsInfo.getColsName().toLowerCase();
			if ("create_time".equals(colsName) || "create_user".equals(colsName) || "update_time".equals(colsName) || "update_user".equals(colsName) || "delete_flag".equals(colsName)) {
				continue;
			}
			stringBuilder.append("    {\r\n");
			stringBuilder.append("      name: \"" + GenerateEntity.makeProperties(colsName) + "\",\r\n");
			String title = StringUtils.isBlank(sysDbmsTabsColsInfo.getColsDesc()) ? sysDbmsTabsColsInfo.getColsName() : sysDbmsTabsColsInfo.getColsDesc();
			stringBuilder.append("      title: \"" + title + "\",\r\n");
			if (StringUtils.isNotBlank(sysDbmsTabsColsInfo.getColsAlign())) {
				stringBuilder.append("      align: \"" + sysDbmsTabsColsInfo.getColsAlign() + "\",\r\n");
			}
			if (sysDbmsTabsColsInfo.getColsVisible() != null) {
				stringBuilder.append("      show: " + sysDbmsTabsColsInfo.getColsVisible() + ",\r\n");
			} else if ("uuid".equals(colsName)) {
				stringBuilder.append("      show: false,\r\n");
			}
			if (sysDbmsTabsColsInfo.getColsWidth() != null) {
				stringBuilder.append("      width: " + sysDbmsTabsColsInfo.getColsVisible() + ",\r\n");
			}
			if (StringUtils.isNotBlank(sysDbmsTabsColsInfo.getUserIndex())) {
				stringBuilder.append("      search: true,\r\n");
			}
			stringBuilder.append("    },\r\n");
		}
		
		stringBuilder.append("  ];\r\n");
		stringBuilder.append("</script>\r\n");
		stringBuilder.append("\r\n");
		stringBuilder.append("<style lang=\"scss\" scoped></style>");

		// 文件写入
		String fineName = pathString + "/" + sysDbmsGenerateCodeInfo.getClassName().toLowerCase() + "/Index.vue";
		TxtFilesWriter.writeToFile(stringBuilder.toString(), fineName);
	}

	/**
	 * 方法名： generateRouter
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param sysDbmsGenerateCodeInfo
	 * 参 数： @param tabsInfo
	 * 参 数： @param colsInfos
	 * 参 数： @param username
	 * 参 数： @param pathString
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	public static void generateRouter(SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo, SysDbmsTabsTableInfo tabsInfo, List<SysDbmsTabsColsInfo> colsInfos, String username, String pathString) {
		String subServiceNameString = sysDbmsGenerateCodeInfo.getClassName().substring(0, 1).toLowerCase() + sysDbmsGenerateCodeInfo.getClassName().substring(1);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("    path: '/" + subServiceNameString + "',\r\n");
		stringBuilder.append("    name: '" + subServiceNameString + "',\r\n");
		stringBuilder.append("    component: () => import('../views/manager/" + sysDbmsGenerateCodeInfo.getClassName().toLowerCase() + "/Index.vue'),\r\n");
		stringBuilder.append("  }, { \r\n");
		
		// 文件写入
		String fineName = pathString + "/addRouter.json";
		TxtFilesWriter.appendWriteToFile(stringBuilder.toString(), fineName);
		
	}
	
}
