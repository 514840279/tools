package org.chuxue.application.job;

import java.io.File;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

/**
 * @文件名 FileItemWriteConfig.java
 * @包名 org.danyuan.application.job
 * @描述 TODO(用一句话描述该文件做什么)
 * @时间 2019年11月1日 下午4:48:27
 * @author Administrator
 * @版本 V1.0
 */
@Configuration
public class FileItemWriteConfig {

	@Bean
	public FlatFileItemWriter<SysUserBaseInfo> myFlatFileItemWriter() throws Exception {
		FlatFileItemWriter<SysUserBaseInfo> writer = new FlatFileItemWriter<>();
		String fileString = "F://user.csv";
		File file = new File(fileString);
		if (!file.exists()) {
			if (!file.createNewFile()) {
				System.out.println("文件創建失敗");
			}
		}
		writer.setResource(new FileSystemResource(fileString));
		writer.setLineSeparator("\n");
		writer.setEncoding("UTF-8");
		writer.setLineAggregator(new LineAggregator<SysUserBaseInfo>() {
			
			@Override
			public String aggregate(SysUserBaseInfo info) {
				return info.toLine();
			}
		});
		writer.afterPropertiesSet();
		return writer;
		
	}
	
}
