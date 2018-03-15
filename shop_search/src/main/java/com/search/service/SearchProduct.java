package com.search.service;

import com.search.pojo.MyJSONObject;

import javax.ws.rs.*;

@Path("SearchProduct")
public interface SearchProduct {
    /**
     * 创建映射，字段使用使用IK分词器
     */
    @Path("/createIndexMappingUseIK")
    @GET
    @Consumes({"application/xml", "application/json"})
    void createIndexMappingUseIK();

    /**
     * 批量创建索引，并添加数据
     */
    @Path("/bulkCreateIndexAndAddData")
    @GET
    @Consumes({"application/xml", "application/json"})
    void bulkCreateIndexAndAddData();

    /**
     * 根据输入文本进行搜索
     */
    @Path("/searchProductByText")
    @GET
    @Produces({"application/xml", "application/json"})
    MyJSONObject productSearch(@QueryParam("text") String text);

}
