package com.background.service.impl;

import java.util.List;

import com.background.dao.mapper.GetListByPageMapper;
import com.background.service.GetListByPageService;
import data.pojo.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetListByPageServiceImpl implements GetListByPageService {

	Logger log= LogManager.getLogger(GetListByPageServiceImpl.class);
	@Autowired
	private GetListByPageMapper getListByPage;
	
	@Override
	public List<Product> getListByPage(int page) {
		// TODO Auto-generated method stub
//		int page = Integer.parseInt(page1);
		log.warn("warn");
		log.error("error");
		List<Product> listPro = getListByPage.getListByPage(page*20,20);  // 替换为 repository
		return listPro;
	}


}
