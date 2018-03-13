package com.background.service;

import data.pojo.Product;

import javax.ws.rs.*;
import java.util.List;

@Path("QueryProductService")
public interface QueryProductService {
    /**
     * 查询商品根据Uid
     * @return
     */
    @Path("/QueryProductBypid")
    @GET                                                //post 参数在请求体中，get在url中
    @Consumes({"application/xml", "application/json"})    //返回void用consumes
    Product QueryProductByid(@QueryParam("id") String id);

    /**
     * 查询商品根据Uid
     * @return
     */
    @Path("/QueryProductByPage")
    @GET                                                //post 参数在请求体中，get在url中
    @Consumes({"application/xml", "application/json"})    //返回void用consumes
    List<Product> QueryProductByPage(@QueryParam("page") String page);
}
