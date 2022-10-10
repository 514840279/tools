package org.tools.rollcall.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tools.rollcall.dao.ClassRoomDao;
import org.tools.rollcall.dao.PersonDao;
import org.tools.rollcall.po.ClassRoom;
import org.tools.rollcall.po.Person;

@Service
public class LoadData {
	private static final Logger	logger	= LoggerFactory.getLogger(LoadData.class);
	
	@Autowired
	ClassRoomDao				classRoomDao;
	
	@Autowired
	PersonDao					personDao;
	
	@Transactional
	public Boolean loadData(String path) throws IOException {
		
		File file = new File(path);
		
		if (!file.exists()) {
			return false;
		}

		FileInputStream fileInputStream = null;
		Workbook wb = null;
		try {
			// 打开文件
			fileInputStream = new FileInputStream(file);
			wb = WorkbookFactory.create(fileInputStream);
			int sheetNumber = wb.getNumberOfSheets();
			for (int i = 0; i < sheetNumber; i++) {
				// 创建class
				String sheetName = wb.getSheetName(i);
				String id = UUID.randomUUID().toString().replace("-", "");
				ClassRoom room = new ClassRoom(id, sheetName);

				// excel 文件内容读取
				List<Person> list = readExcel(wb, sheetName, id);

				classRoomDao.save(room);
				personDao.saveAll(list);
				personDao.flush();
			}
			wb.close();
			fileInputStream.close();
			
		} catch (Exception e) {
			return false;
		} finally {
			if (wb != null) {
				wb.close();
			}
			if (fileInputStream != null) {
				fileInputStream.close();
			}
		}
		return true;
	}
	
	// excel 文件内容读取
	private List<Person> readExcel(Workbook wb, String sheetName, String pid) {
		Sheet sheet = wb.getSheet(sheetName);
		// Excel的行数
		int totalRows = sheet.getPhysicalNumberOfRows();
		List<Person> list = new ArrayList<>();
		// 遍历Excel的行
		for (int r = 1; r < totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			String no = row.getCell(0).getStringCellValue();
			String name = row.getCell(1).getStringCellValue();
			if (StringUtils.isNotBlank(name) || StringUtils.isNotBlank(no)) {
				String id = UUID.randomUUID().toString().replace("-", "");
				Person mode = new Person(id, no, name, pid);
				list.add(mode);
			}
		}
		return list;
	}
	
	// xlsx 读取方法
	public void parseExcel(File file) throws IOException {
		OPCPackage xlsxPackage = null;
		PrintStream output = System.out;
		
		try {
			xlsxPackage = OPCPackage.open(file.getAbsolutePath(), PackageAccess.READ);
			XSSFReader xssfReader = new XSSFReader(xlsxPackage);
			XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
			int index = 0;
			while (iter.hasNext()) {
				try (InputStream stream = iter.next()) {
					String sheetName = iter.getSheetName();
					output.println();
					output.println(sheetName + " [index=" + index + "]:");
				}
				++index;
			}
			output.close();
			xlsxPackage.close();
		} catch (InvalidFormatException e) {
			logger.error(e.getMessage());
		} catch (OpenXML4JException e) {
			logger.error(e.getMessage());
		}
	}
	
}
