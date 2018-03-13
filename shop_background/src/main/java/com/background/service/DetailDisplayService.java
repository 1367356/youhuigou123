package com.background.service;

import java.util.List;

import data.pojo.Product;

import javax.ws.rs.*;

@Path("/DetailDisplayService")
public interface DetailDisplayService {

	/**
	 * 查询商品根据Uid
	 * @return
	 */
	@Path("/selectProductAndRecByUid")
	@GET
	@Produces({"application/xml", "application/json"})    //返回void用consumes
	List<Product> selectProductAndRecByUid(@QueryParam("uid") String uid);
}
