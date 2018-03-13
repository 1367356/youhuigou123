package com.background.service;

import java.util.List;

import data.pojo.Product;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/GetListByPageService")
public interface GetListByPageService {
	/**
	 * 查询商品根据Uid
	 * @return
	 */
	@Path("/getListByPage")
	@GET
	@Produces({"application/xml", "application/json"})    //返回void用consumes
	List<Product> getListByPage(@PathParam("page") int page);
}
