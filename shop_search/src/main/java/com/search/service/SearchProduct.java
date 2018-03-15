package com.search.service;

import com.alibaba.fastjson.JSONObject;
import data.pojo.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.*;
import java.util.List;

@Path("/SearchProduct")
public interface SearchProduct {
    /**
     * 创建映射，字段使用使用IK分词器
     */
    @Path("/createIndexMappingUseIK")
    public void createIndexMappingUseIK();

    /**
     * 批量创建索引，并添加数据
     */
    @Path("/bulkCreateIndexAndAddData")
    public void bulkCreateIndexAndAddData();

    /**
     * 根据输入文本进行搜索
     */
    @Path("/productSearch")
    @GET
    @Consumes({"application/xml", "application/json"})
    public JSONObject productSearch(@PathParam("text") String text);

}
