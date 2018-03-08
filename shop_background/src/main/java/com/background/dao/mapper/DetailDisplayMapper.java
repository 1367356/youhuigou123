package com.background.dao.mapper;

import java.util.List;

import excel.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Repository
public interface DetailDisplayMapper {
	Product getProductByUid(String uid);
	List<Product> selectRecProduct(@Param("nextInt") int nextInt, @Param("offset") int offset);
}
