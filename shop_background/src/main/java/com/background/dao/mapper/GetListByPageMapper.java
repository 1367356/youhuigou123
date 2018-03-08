package com.background.dao.mapper;

import java.util.List;

import excel.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface GetListByPageMapper {	
	List<Product> getListByPage(@Param("start") int start, @Param("offset") int offset);
}
