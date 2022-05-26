package org.tools.rollcall.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoadData {
	
	@Transactional
	public Boolean loadData(String path) {
		Boolean flag = true;
		
		return flag;
	}
	
}
