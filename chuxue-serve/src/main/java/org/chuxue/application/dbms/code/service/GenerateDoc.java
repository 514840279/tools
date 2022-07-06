package org.chuxue.application.dbms.code.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.chuxue.application.bean.manager.dbms.SysDbmsGenerateCodeInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsColsInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetView;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSheetViewType;
import org.springframework.core.io.ClassPathResource;

import com.alibaba.nacos.common.utils.StringUtils;

/**
 * @文件名 GenerateDoc.java
 * @包名 org.danyuan.application.dbms.code.service
 * @描述 数据文档生成
 * @时间 2019年1月17日 下午2:27:52
 * @author Administrator
 * @版本 V1.0
 */
public class GenerateDoc {

	// 构造constraint对象
	static String	dataList	= "●,○";
	static String	dataList2	= "INT,FLOAT,DOUBLE,DECIMAL,DATE,TIME,TIMESTAMP,DATETIME,CHAR,VARCHAR,TEXT";

	/**
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @方法名 generate
	 * @功能 生成数据文档
	 * @参数 @param sysDbmsGenerateCodeInfo
	 * @参数 @param tabsInfo
	 * @参数 @param colsInfos
	 * @参数 @param username
	 * @参数 @param pathString
	 * @返回 void
	 * @author Administrator @throws
	 */
	@SuppressWarnings({ "resource" })
	public static void generateXls(SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo, SysDbmsTabsTableInfo tabsInfo, List<SysDbmsTabsColsInfo> colsInfos, String username, String pathString) throws IOException {
		File file = new File(pathString);
		HSSFWorkbook wb = null;
		if (!file.exists()) {
			// 读取模板文件路径
			// File modelfile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX +
			// "static/model/datamodel.xlsx");
			ClassPathResource classPathResource = new ClassPathResource("static/model/datamodel.xls");
			InputStream modelfins = classPathResource.getInputStream();
			POIFSFileSystem modelfs = new POIFSFileSystem(modelfins);
			// 读取Excel模板
			wb = new HSSFWorkbook(modelfs);
		} else {
			//
			FileInputStream fins = new FileInputStream(file);
			POIFSFileSystem fs = new POIFSFileSystem(fins);
			wb = new HSSFWorkbook(fs);

		}
		HSSFSheet demoSheet = wb.getSheet("demo");
		String sheetName = tabsInfo.getTabsDesc();
		if (StringUtils.isBlank(sheetName)) {
			sheetName = tabsInfo.getTabsName();
		}
		HSSFSheet newSheet = wb.createSheet(sheetName);

		newSheet = copySheet(demoSheet, newSheet);

		// 日期
		newSheet.getRow(1).getCell(42).setCellValue(new Date());

		// 作成者
		newSheet.getRow(1).getCell(52).setCellValue(sysDbmsGenerateCodeInfo.getCreateUser());
		// 数据库选择
//		newSheet.getRow(7).getCell(0).setCellValue(tabsInfo.getDbType());
		// 表头
		// 表名称
		newSheet.getRow(9).getCell(11).setCellValue(tabsInfo.getTabsName());
		// 表含义
		newSheet.getRow(10).getCell(11).setCellValue(tabsInfo.getTabsDesc() == null ? "" : tabsInfo.getTabsDesc());
		// 表描述
		newSheet.getRow(10).getCell(30).setCellValue(tabsInfo.getDiscription() == null ? "" : tabsInfo.getDiscription());
		// 表格
		int index = 0;
		int index2 = 0;
		for (SysDbmsTabsColsInfo cols : colsInfos) {

			String colsNameString = cols.getColsName().toLowerCase();
			if ("uuid".equals(colsNameString) || "delete_flag".equals(colsNameString) || "discription".equals(colsNameString) || "create_time".equals(colsNameString) || "create_user".equals(colsNameString) || "update_time".equals(colsNameString) || "update_user".equals(colsNameString) || "sort".equals(colsNameString)) {
				index2++;
				setDataValidationList((11 + index2), (11 + index2), 26, 29, dataList, newSheet);
				setDataValidationList((11 + index2), (11 + index2), 30, 34, dataList2, newSheet);
				setDataValidationList((11 + index2), (11 + index2), 38, 41, dataList, newSheet);
				continue;
			} else {
				index++;
				// セル幅のコピー
				Row rowFrom = newSheet.getRow(13);
				int rowIndex = 19 + index;
				int nextRowIndex = rowIndex + 1;
				Row rowTo = newSheet.createRow(rowIndex);
				rowTo.setHeight(rowFrom.getHeight());
				// 合并单元格
				newSheet.addMergedRegion(CellRangeAddress.valueOf("A" + nextRowIndex + ":B" + nextRowIndex));
				newSheet.addMergedRegion(CellRangeAddress.valueOf("C" + nextRowIndex + ":K" + nextRowIndex));
				newSheet.addMergedRegion(CellRangeAddress.valueOf("L" + nextRowIndex + ":Z" + nextRowIndex));
				newSheet.addMergedRegion(CellRangeAddress.valueOf("AA" + nextRowIndex + ":AD" + nextRowIndex));
				newSheet.addMergedRegion(CellRangeAddress.valueOf("AE" + nextRowIndex + ":AI" + nextRowIndex));
				setDataValidationList(rowIndex, rowIndex, 27 - 1, 30 - 1, dataList, newSheet);
				newSheet.addMergedRegion(CellRangeAddress.valueOf("AJ" + nextRowIndex + ":AL" + nextRowIndex));
				setDataValidationList(rowIndex, rowIndex, 31 - 1, 35 - 1, dataList2, newSheet);
				newSheet.addMergedRegion(CellRangeAddress.valueOf("AM" + nextRowIndex + ":AP" + nextRowIndex));
				newSheet.addMergedRegion(CellRangeAddress.valueOf("AQ" + nextRowIndex + ":AU" + nextRowIndex));
				setDataValidationList(rowIndex, rowIndex, 39 - 1, 42 - 1, dataList, newSheet);
				newSheet.addMergedRegion(CellRangeAddress.valueOf("AV" + nextRowIndex + ":BJ" + nextRowIndex));

				Cell cellFrom = null;
				Cell cellTo = null;
				newSheet.setDefaultColumnStyle(18 + index, newSheet.getColumnStyle(13));
				newSheet.setColumnWidth(18 + index, newSheet.getColumnWidth(13));
				for (int intCol = 0; intCol < rowFrom.getLastCellNum(); intCol++) {
					// セル幅のコピー
					newSheet.setDefaultColumnStyle(intCol, newSheet.getColumnStyle(intCol));
					newSheet.setColumnWidth(intCol, newSheet.getColumnWidth(intCol));
					cellFrom = rowFrom.getCell(intCol);
					cellTo = rowTo.createCell(intCol);

					// セルスタイルとタイプのコピー
					cellTo.setCellStyle(cellFrom.getCellStyle());

					// タイトル内容のコピー
					// 不同数据类型处理
					CellType cellFromType = cellFrom.getCellType();
//					cellTo.setCellType(cellFromType);
					if (cellFromType == CellType.NUMERIC) {
						if (DateUtil.isCellDateFormatted(cellFrom)) {
							cellTo.setCellValue(cellFrom.getDateCellValue());
						} else {
							cellTo.setCellValue(cellFrom.getNumericCellValue());
						}
					} else if (cellFromType == CellType.STRING) {
						cellTo.setCellValue(cellFrom.getRichStringCellValue());
					} else if (cellFromType == CellType.BLANK) {
						// nothing21
					} else if (cellFromType == CellType.BOOLEAN) {
						cellTo.setCellValue(cellFrom.getBooleanCellValue());
					} else if (cellFromType == CellType.ERROR) {
						cellTo.setCellErrorValue(cellFrom.getErrorCellValue());
					} else if (cellFromType == CellType.FORMULA) {
						cellTo.setCellFormula(cellFrom.getCellFormula());
					} else { // nothing29
					}
				}

				// 字段名
				newSheet.getRow(rowIndex).getCell(2).setCellValue(cols.getColsName());
				// 字段含义
				newSheet.getRow(rowIndex).getCell(11).setCellValue(cols.getColsDesc() == null ? "" : cols.getColsDesc());
				// 是否主键
				newSheet.getRow(rowIndex).getCell(26).setCellValue("");
				// 数据类型
				newSheet.getRow(rowIndex).getCell(30).setCellValue(cols.getColsType() == null ? "" : cols.getColsType().toString().toUpperCase());
				// 数据长度
				newSheet.getRow(rowIndex).getCell(35).setCellValue(cols.getColsLength() == null ? "255" : cols.getColsLength().toString());
				// 不为空
				newSheet.getRow(rowIndex).getCell(38).setCellValue("");
				// default value
				newSheet.getRow(rowIndex).getCell(42).setCellValue("");
				// 描述信息
				newSheet.getRow(rowIndex).getCell(47).setCellValue(cols.getDiscription() == null ? "" : cols.getDiscription());
			}

		}

		newSheet.setZoom(85);
		newSheet.setActiveCell(new CellAddress("A1"));
		
		newSheet.setPrintGridlines(true);

		wb.setActiveSheet(0);

		// 设置打印区域
		wb.setPrintArea(wb.getSheetIndex(sheetName), "$A$1:$BO$" + (colsInfos.size() + 14));
		FileOutputStream fout = new FileOutputStream(pathString);
		wb.write(fout);
		fout.close();

		wb.close();
	}

	/**
	 * @方法名 copySheet
	 * @功能 复制sheet
	 * @参数 @param sheetFrom
	 * @参数 @param sheetTo
	 * @参数 @return
	 * @返回 HSSFSheet
	 * @author Administrator @throws
	 */
	private static HSSFSheet copySheet(HSSFSheet sheetFrom, HSSFSheet sheetTo) {

		// 初期化
		CellRangeAddress region = null;
		Row rowFrom = null;
		Row rowTo = null;
		Cell cellFrom = null;
		Cell cellTo = null;
		// セル結合のコピー
		for (int i = 0; i < sheetFrom.getNumMergedRegions(); i++) {
			region = sheetFrom.getMergedRegion(i);

			if ((region.getFirstColumn() >= sheetFrom.getFirstRowNum()) && (region.getLastRow() <= sheetFrom.getLastRowNum())) {
				sheetTo.addMergedRegion(region);
			}
		}

		// セルのコピー
		for (int intRow = sheetFrom.getFirstRowNum(); intRow <= sheetFrom.getLastRowNum(); intRow++) {
			rowFrom = sheetFrom.getRow(intRow);
			rowTo = sheetTo.createRow(intRow);
			if (null == rowFrom) {
				continue;
			}
			rowTo.setHeight(rowFrom.getHeight());
			for (int intCol = 0; intCol < rowFrom.getLastCellNum(); intCol++) {
				// セル幅のコピー
				sheetTo.setDefaultColumnStyle(intCol, sheetFrom.getColumnStyle(intCol));
				sheetTo.setColumnWidth(intCol, sheetFrom.getColumnWidth(intCol));
				cellFrom = rowFrom.getCell(intCol);
				cellTo = rowTo.createCell(intCol);
				if (null == cellFrom) {
					continue;
				}
				// セルスタイルとタイプのコピー
				cellTo.setCellStyle(cellFrom.getCellStyle());
				// タイトル内容のコピー
				// 不同数据类型处理
				CellType cellFromType = cellFrom.getCellType();
//				cellTo.setCellType(cellFromType);
				if (cellFromType == CellType.NUMERIC) {
					if (DateUtil.isCellDateFormatted(cellFrom)) {
						cellTo.setCellValue(cellFrom.getDateCellValue());
					} else {
						cellTo.setCellValue(cellFrom.getNumericCellValue());
					}
				} else if (cellFromType == CellType.STRING) {
					cellTo.setCellValue(cellFrom.getRichStringCellValue());
				} else if (cellFromType == CellType.BLANK) {
					// nothing21
				} else if (cellFromType == CellType.BOOLEAN) {
					cellTo.setCellValue(cellFrom.getBooleanCellValue());
				} else if (cellFromType == CellType.ERROR) {
					cellTo.setCellErrorValue(cellFrom.getErrorCellValue());
				} else if (cellFromType == CellType.FORMULA) {
					cellTo.setCellFormula(cellFrom.getCellFormula());
				} else { // nothing29
				}
			}
		}

		// 枠線の設定
		sheetTo.setDisplayGridlines(false);
		// sheetTo.setDisplayGuts(true);
		// sheetTo.setDisplayRowColHeadings(true);
		// 剪切
		// sheetTo.shiftRows(13, 15, 31, false, false, false);
		// Excelのズーム設定
		sheetTo.setZoom(85, 100);

		// シートを戻る。
		return sheetTo;

	}

	private static void setDataValidationList(int j, int k, int l, int m, String data, HSSFSheet sheet) {
		// 设置下拉列表的内容
		String[] textlist = data.split(",");
		// 加载下拉列表内容
		DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);
		// 设置数据有效性加载在哪个单元格上。

		// 四个参数分别是：起始行、终止行、起始列、终止列
		CellRangeAddressList regions = new CellRangeAddressList(j, k, l, m);
		/*
		 * CellRangeAddressList regions = new CellRangeAddressList( 6,5, 6,5);
		 */
		// 数据有效性对象
		HSSFDataValidation data_validation_list = new HSSFDataValidation(regions, constraint);
		data_validation_list.setSuppressDropDownArrow(false);
		sheet.addValidationData(data_validation_list);
	}

	private static void setDataValidationList(int j, int k, int l, int m, String data, XSSFSheet sheet) {
		// 设置下拉列表的内容
		String[] textlist = data.split(",");
		// 加载下拉列表内容
		// 设置数据有效性加载在哪个单元格上。
		XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
		XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(textlist);
		// 四个参数分别是：起始行、终止行、起始列、终止列
		CellRangeAddressList regions = new CellRangeAddressList(j, k, l, m);
		/*
		 * CellRangeAddressList regions = new CellRangeAddressList( 6,5, 6,5);
		 */
		// 数据有效性对象
		XSSFDataValidation data_validation_list = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, regions);
		// data_validation_list.setSuppressDropDownArrow(false);

		sheet.addValidationData(data_validation_list);
	}

	@SuppressWarnings({ "resource" })
	public static void generateXlsx(SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo, SysDbmsTabsTableInfo tabsInfo, List<SysDbmsTabsColsInfo> colsInfos, String username, String pathString) throws IOException {
		File file = new File(pathString);
		XSSFWorkbook wb = null;
		if (!file.exists()) {
			// 读取模板文件路径
			// File modelfile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX +
			// "static/model/datamodel.xlsx");
			ClassPathResource classPathResource = new ClassPathResource("static/model/datamodel.xlsx");
			InputStream modelfins = classPathResource.getInputStream();
			// 读取Excel模板
			wb = new XSSFWorkbook(modelfins);
		} else {
			//
			FileInputStream fins = new FileInputStream(file);
			wb = new XSSFWorkbook(fins);

		}
		XSSFSheet demoSheet = wb.getSheet("demo");
		String sheetName = tabsInfo.getTabsDesc();
		if (StringUtils.isBlank(sheetName)) {
			sheetName = tabsInfo.getTabsName();
		}
		XSSFSheet newSheet = wb.createSheet(sheetName);

		newSheet = copySheet(demoSheet, newSheet);

		// 日期
		newSheet.getRow(1).getCell(42).setCellValue(new Date());

		// 作成者
		newSheet.getRow(1).getCell(52).setCellValue(sysDbmsGenerateCodeInfo.getCreateUser());
		// 数据库选择
//		newSheet.getRow(7).getCell(0).setCellValue(tabsInfo.getDbType());
		// 表头
		// 表名称
		newSheet.getRow(9).getCell(11).setCellValue(tabsInfo.getTabsName());
		// 表含义
		newSheet.getRow(10).getCell(11).setCellValue(tabsInfo.getTabsDesc() == null ? "" : tabsInfo.getTabsDesc());
		// 表描述
		newSheet.getRow(10).getCell(30).setCellValue(tabsInfo.getDiscription() == null ? "" : tabsInfo.getDiscription());
		// 表格
		int index = 0;
		int index2 = 0;
		for (SysDbmsTabsColsInfo cols : colsInfos) {

			String colsNameString = cols.getColsName().toLowerCase();
			if ("uuid".equals(colsNameString) || "delete_flag".equals(colsNameString) || "discription".equals(colsNameString) || "create_time".equals(colsNameString) || "create_user".equals(colsNameString) || "update_time".equals(colsNameString) || "update_user".equals(colsNameString) || "sort".equals(colsNameString)) {
				index2++;
				setDataValidationList((11 + index2), (11 + index2), 26, 29, dataList, newSheet);
				setDataValidationList((11 + index2), (11 + index2), 30, 34, dataList2, newSheet);
				setDataValidationList((11 + index2), (11 + index2), 38, 41, dataList, newSheet);
				continue;
			} else {
				index++;
				// セル幅のコピー
				Row rowFrom = newSheet.getRow(13);
				int newRowIndex = 19 + index;
				int nextRowIndex = newRowIndex + 1;
				Row rowTo = newSheet.createRow(newRowIndex);
				rowTo.setHeight(rowFrom.getHeight());
				// 合并单元格
				newSheet.addMergedRegion(CellRangeAddress.valueOf("A" + nextRowIndex + ":B" + nextRowIndex));
//
				newSheet.addMergedRegion(CellRangeAddress.valueOf("C" + nextRowIndex + ":K" + nextRowIndex));
				newSheet.addMergedRegion(CellRangeAddress.valueOf("L" + nextRowIndex + ":Z" + nextRowIndex));
				newSheet.addMergedRegion(CellRangeAddress.valueOf("AA" + nextRowIndex + ":AD" + nextRowIndex));
				newSheet.addMergedRegion(CellRangeAddress.valueOf("AE" + nextRowIndex + ":AI" + nextRowIndex));
				setDataValidationList(newRowIndex, newRowIndex, 27 - 1, 30 - 1, dataList, newSheet);
				newSheet.addMergedRegion(CellRangeAddress.valueOf("AJ" + nextRowIndex + ":AL" + nextRowIndex));
				setDataValidationList(newRowIndex, newRowIndex, 31 - 1, 35 - 1, dataList2, newSheet);
				newSheet.addMergedRegion(CellRangeAddress.valueOf("AM" + nextRowIndex + ":AP" + nextRowIndex));
				newSheet.addMergedRegion(CellRangeAddress.valueOf("AQ" + nextRowIndex + ":AU" + nextRowIndex));
				setDataValidationList(newRowIndex, newRowIndex, 39 - 1, 42 - 1, dataList, newSheet);
				newSheet.addMergedRegion(CellRangeAddress.valueOf("AV" + nextRowIndex + ":BJ" + nextRowIndex));

				Cell cellFrom = null;
				Cell cellTo = null;
				newSheet.setDefaultColumnStyle(18 + index, newSheet.getColumnStyle(13));
				newSheet.setColumnWidth(18 + index, newSheet.getColumnWidth(13));
				for (int intCol = 0; intCol < rowFrom.getLastCellNum(); intCol++) {
					// セル幅のコピー
					newSheet.setDefaultColumnStyle(intCol, newSheet.getColumnStyle(intCol));
					newSheet.setColumnWidth(intCol, newSheet.getColumnWidth(intCol));
					cellFrom = rowFrom.getCell(intCol);
					cellTo = rowTo.createCell(intCol);

					// セルスタイルとタイプのコピー
					cellTo.setCellStyle(cellFrom.getCellStyle());

					// タイトル内容のコピー
					// 不同数据类型处理
					CellType cellFromType = cellFrom.getCellType();
//					cellTo.setCellType(cellFromType);
					if (cellFromType == CellType.NUMERIC) {
						if (DateUtil.isCellDateFormatted(cellFrom)) {
							cellTo.setCellValue(cellFrom.getDateCellValue());
						} else {
							cellTo.setCellValue(cellFrom.getNumericCellValue());
						}
					} else if (cellFromType == CellType.STRING) {
						cellTo.setCellValue(cellFrom.getRichStringCellValue());
					} else if (cellFromType == CellType.BLANK) {
						// nothing21
					} else if (cellFromType == CellType.BOOLEAN) {
						cellTo.setCellValue(cellFrom.getBooleanCellValue());
					} else if (cellFromType == CellType.ERROR) {
						cellTo.setCellErrorValue(cellFrom.getErrorCellValue());
					} else if (cellFromType == CellType.FORMULA) {
						cellTo.setCellFormula(cellFrom.getCellFormula());
					} else { // nothing29
					}
				}

				// 字段名
				newSheet.getRow(newRowIndex).getCell(2).setCellValue(cols.getColsName());
				// 字段含义
				newSheet.getRow(newRowIndex).getCell(11).setCellValue(cols.getColsDesc() == null ? "" : cols.getColsDesc());
				// 是否主键
				newSheet.getRow(newRowIndex).getCell(26).setCellValue("");
				// 数据类型
				newSheet.getRow(newRowIndex).getCell(30).setCellValue(cols.getColsType() == null ? "" : cols.getColsType().toString().toUpperCase());
				// 数据长度
				newSheet.getRow(newRowIndex).getCell(35).setCellValue(cols.getColsLength() == null ? "255" : cols.getColsLength().toString());
				// 不为空
				newSheet.getRow(newRowIndex).getCell(38).setCellValue("");
				// default value
				newSheet.getRow(newRowIndex).getCell(42).setCellValue("");
				// 描述信息
				newSheet.getRow(newRowIndex).getCell(47).setCellValue(cols.getDiscription() == null ? "" : cols.getDiscription());
			}

		}

		newSheet.setZoom(85);
		newSheet.setActiveCell(new CellAddress("A1"));
		// 打印预览试图
		CTSheetView view = newSheet.getCTWorksheet().getSheetViews().getSheetViewArray(0);
		view.setView(STSheetViewType.PAGE_BREAK_PREVIEW);
		// 设置打印区域
		wb.setPrintArea(wb.getSheetIndex(sheetName), "$A$1:$BL$" + (colsInfos.size() + 14));
		wb.setActiveSheet(0);
		FileOutputStream fout = new FileOutputStream(pathString);
		wb.write(fout);
		fout.close();

		wb.close();

	}

	private static XSSFSheet copySheet(XSSFSheet sheetFrom, XSSFSheet sheetTo) {
		// 初期化
		CellRangeAddress region = null;
		Row rowFrom = null;
		Row rowTo = null;
		Cell cellFrom = null;
		Cell cellTo = null;
		// セル結合のコピー
		for (int i = 0; i < sheetFrom.getNumMergedRegions(); i++) {
			region = sheetFrom.getMergedRegion(i);

			if ((region.getFirstColumn() >= sheetFrom.getFirstRowNum()) && (region.getLastRow() <= sheetFrom.getLastRowNum())) {
				sheetTo.addMergedRegion(region);
			}
		}

		// セルのコピー
		for (int intRow = sheetFrom.getFirstRowNum(); intRow <= sheetFrom.getLastRowNum(); intRow++) {
			rowFrom = sheetFrom.getRow(intRow);
			rowTo = sheetTo.createRow(intRow);
			if (null == rowFrom) {
				continue;
			}
			rowTo.setHeight(rowFrom.getHeight());
			for (int intCol = 0; intCol < rowFrom.getLastCellNum(); intCol++) {
				// セル幅のコピー
				sheetTo.setDefaultColumnStyle(intCol, sheetFrom.getColumnStyle(intCol));
				sheetTo.setColumnWidth(intCol, sheetFrom.getColumnWidth(intCol));
				cellFrom = rowFrom.getCell(intCol);
				cellTo = rowTo.createCell(intCol);
				if (null == cellFrom) {
					continue;
				}
				// セルスタイルとタイプのコピー
				cellTo.setCellStyle(cellFrom.getCellStyle());
				// タイトル内容のコピー
				// 不同数据类型处理
				CellType cellFromType = cellFrom.getCellType();
//				cellTo.setCellType(cellFromType);
				if (cellFromType == CellType.NUMERIC) {
					if (DateUtil.isCellDateFormatted(cellFrom)) {
						cellTo.setCellValue(cellFrom.getDateCellValue());
					} else {
						cellTo.setCellValue(cellFrom.getNumericCellValue());
					}
				} else if (cellFromType == CellType.STRING) {
					cellTo.setCellValue(cellFrom.getRichStringCellValue());
				} else if (cellFromType == CellType.BLANK) {
					// nothing21
				} else if (cellFromType == CellType.BOOLEAN) {
					cellTo.setCellValue(cellFrom.getBooleanCellValue());
				} else if (cellFromType == CellType.ERROR) {
					cellTo.setCellErrorValue(cellFrom.getErrorCellValue());
				} else if (cellFromType == CellType.FORMULA) {
					cellTo.setCellFormula(cellFrom.getCellFormula());
				} else { // nothing29
				}
			}
		}

		// 枠線の設定
		sheetTo.setDisplayGridlines(false);
		// sheetTo.setDisplayGuts(true);
		// sheetTo.setDisplayRowColHeadings(true);
		// 剪切
		// sheetTo.shiftRows(13, 15, 31, false, false, false);
		// Excelのズーム設定
		sheetTo.setZoom(85);

		// シートを戻る。
		return sheetTo;
	}

}
