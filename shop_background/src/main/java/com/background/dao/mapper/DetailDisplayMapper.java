package com.background.dao.mapper;

import java.util.List;

import data.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DetailDisplayMapper {
	Product getProductByUid(String uid);
	List<Product> selectRecProduct(@Param("nextInt") int nextInt, @Param("offset") int offset);
}
