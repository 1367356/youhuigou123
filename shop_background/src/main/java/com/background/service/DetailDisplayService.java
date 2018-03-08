package com.background.service;

import java.util.List;

import excel.pojo.Product;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("DetailDisplayService")
public interface DetailDisplayService {

	/**
	 * 查询商品根据Uid
	 * @return
	 */
	@Path("/selectProductAndRecByUid")
	@GET
	@Produces({"application/xml", "application/json"})    //返回void用consumes
	List<Product> selectProductAndRecByUid(@PathParam("uid") String uid);
}
