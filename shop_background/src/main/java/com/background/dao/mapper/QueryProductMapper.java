package com.background.dao.mapper;

import data.pojo.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryProductMapper {
    Product QueryProductBypid(String id);
    Product QueryProductByid(String id);
}
