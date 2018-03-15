package com.search.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.search.pojo.MyJSONObject;
import com.search.service.SearchProduct;
import data.elasticSearch.ElasticsearchConfig;
import data.excel.ReadExcel;
import data.pojo.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@Service
public class SearchProductImpl implements SearchProduct{

    Logger logger= LogManager.getLogger(SearchProductImpl.class);
    private static TransportClient client;

    public SearchProductImpl() {
        client=new ElasticsearchConfig().getElasticsearchClient();
    }

    /**
     * 创建索引，并在需要的字段指定分词器，如果字段指定为ik，那么在搜索查询时，会对输入的搜索文本进行ik分词。
     */
    public void createIndexMappingUseIK() {
        //创建映射
        XContentBuilder mapping = null;
        try {
            mapping = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject("properties")
                    //      .startObject("m_id").field("type","keyword").endObject()
                    .startObject("id").field("type", "text").field("analyzer", "ik_smart").endObject()
                    .startObject("name").field("type", "text").field("analyzer", "ik_max_word").endObject()
                    .startObject("image").field("type", "text").field("analyzer", "ik_max_word").endObject()
                    .startObject("category").field("type", "text").field("analyzer", "ik_max_word").endObject()
                    .startObject("price").field("type", "text").field("analyzer", "ik_max_word").endObject()
                    .startObject("sale").field("type", "text").field("analyzer", "ik_max_word").endObject()
                    .startObject("platform").field("type", "text").field("analyzer", "ik_max_word").endObject()
                    .startObject("disSum").field("type", "text").field("analyzer", "ik_max_word").endObject()
                    .startObject("disSize").field("type", "text").field("analyzer", "ik_max_word").endObject()
                    .startObject("startTime").field("type", "text").field("analyzer", "ik_max_word").endObject()
                    .startObject("endTime").field("type", "text").field("analyzer", "ik_max_word").endObject()
                    .startObject("dishref").field("type", "text").field("analyzer", "ik_max_word").endObject()
                    .endObject()
                    .endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //pois：索引名   cxyword：类型名（可以自己定义）
        PutMappingRequest putmap = Requests.putMappingRequest("productik").type("productik").source(mapping);
        //创建索引
        client.admin().indices().prepareCreate("productik").execute().actionGet();
        //为索引添加映射
        client.admin().indices().putMapping(putmap).actionGet();
    }

    /**
     * 对elasticsearch批量创建索引，并添加数据
     */
    public void bulkCreateIndexAndAddData() {

        //        createIndexMappingUseIK();  //创建映射，指定ik分词
        ReadExcel re=new ReadExcel();
        File file = null;
        try {
            file = ResourceUtils.getFile("D:/javatest/test2.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List listProduct = re.readExcel(file);  //读取excel中的数据进入list

        //创建批量创建请求
        BulkRequestBuilder bulkRequest = client.prepareBulk();

        Iterator<Product> iterator = listProduct.iterator();
        int i=0;
        while (iterator.hasNext()){
            Product product = iterator.next();
            //向搜索中逐条添加数据
            try {
                bulkRequest.add(client.prepareIndex("product", "product", ""+i++)  //id 可以作为pid使用
                        .setSource(jsonBuilder()
                                .startObject()
                                .field("pid",i)
                                .field("id", product.getId())
                                .field("name",product.getName())
                                .field("image", product.getImage())
                                .field("category", product.getCategory())
                                .field("price", product.getPrice())
                                .field("sale", product.getSale())
                                .field("platform", product.getPlatform())
                                .field("disSum", product.getDisSum())
                                .field("disSize", product.getDisSize())
                                .field("startTime", product.getStartTime())
                                .field("endTime", product.getEndTime())
                                .field("dishref", product.getDisherf())
                                .endObject()
                        )
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
            System.out.println("创建失败");
        }
    }

    /**
     * 搜索
     * @param text 搜索内容
     * @return  搜索结果
     */
    public MyJSONObject productSearch(String text) {
        SearchResponse response1 = client.prepareSearch("product")  //指定多个索引
                .setTypes("product")  //指定类型
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchQuery("name", text))  // Query，匹配查询
//                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter，过滤
                .setFrom(0).setSize(60).setExplain(true)
                .get();

        SearchHit[] searchHits = response1.getHits().getHits();//命中个数

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < searchHits.length; i++) {
            Map<String, DocumentField> fields = searchHits[i].getFields(); //得到搜索DocumentField
            String sourceAsString = searchHits[i].getSourceAsString();
            jsonObject.put(i+"",sourceAsString);
            jsonArray.add(jsonObject);
        }
        logger.debug("返回jsonObject");
        MyJSONObject myJSONObject=new MyJSONObject();
        myJSONObject.setJsonObject(jsonObject);
        return myJSONObject;
    }
}
