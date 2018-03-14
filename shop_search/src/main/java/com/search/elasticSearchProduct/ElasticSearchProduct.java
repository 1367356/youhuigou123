package com.search.elasticSearchProduct;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.search.elasticsearch.ElasticsearchConfig;
import data.excel.ReadExcel;
import data.pojo.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * 将商品放置到搜索服务器中。
 */
@RestController
public class ElasticSearchProduct {

    Logger logger= LogManager.getLogger(ElasticSearchProduct.class);
    private static TransportClient client;

    public ElasticSearchProduct() {
        this.client=new ElasticsearchConfig().getElasticsearchClient();
    }

    public void createIndexMapping() throws IOException {
        //创建映射
        XContentBuilder mapping = XContentFactory.jsonBuilder()
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
        //pois：索引名   cxyword：类型名（可以自己定义）
        PutMappingRequest putmap = Requests.putMappingRequest("product").type("product").source(mapping);
        //创建索引
        client.admin().indices().prepareCreate("product").execute().actionGet();
        //为索引添加映射
        client.admin().indices().putMapping(putmap).actionGet();
    }

    /**
     * 批量创建索引，并添加数据
     * @throws IOException
     */
    public void bulkApi(List<Product> listProduct) throws IOException {
        //创建批量创建请求
        BulkRequestBuilder bulkRequest = client.prepareBulk();

        Iterator<Product> iterator = listProduct.iterator();
        int i=0;
        while (iterator.hasNext()){
            Product product = iterator.next();

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
        }
        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
            System.out.println("创建失败");
        }
    }

    /**
     * 商品搜索
     */
    @RequestMapping("/productSearch")
    @ResponseBody
    public JSONObject productSearch(String text) {
        SearchResponse response1 = client.prepareSearch("product")  //指定多个索引
                .setTypes("product")  //指定类型
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchQuery("name", text))  // Query
//                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                .setFrom(0).setSize(60).setExplain(true)
                .get();

        SearchHit[] searchHits = response1.getHits().getHits();//命中个数

        JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < searchHits.length; i++) {
            Map<String, DocumentField> fields = searchHits[i].getFields();

            String sourceAsString = searchHits[i].getSourceAsString();
            jsonObject.put(i+"",sourceAsString);
            jsonArray.add(jsonObject);
        }

        return jsonObject;
    }

    @RequestMapping("/getById")
    public void get(String id) {
        GetResponse response = client.prepareGet("product", "product", id).get();  //根据id查询，每次一个
        Map<String, Object> source = response.getSource();
        Set<String> strings = source.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            System.out.println(source.get(iterator.next()));
        }
    }

    @RequestMapping("/bulkCreateIndex")
    @Test
    public void bulkcreate() throws IOException {
//        createIndexMapping();  //创建映射，指定ik分词
        ReadExcel re=new ReadExcel();
        File file = ResourceUtils.getFile("D:/javatest/test2.xls");
        List listProduct = re.readExcel(file);
        bulkApi(listProduct);
    }
}
