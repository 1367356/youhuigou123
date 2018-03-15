package test;


import com.search.elasticsearch.ElasticsearchConfig;
import data.pojo.Product;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.mustache.SearchTemplateRequestBuilder;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


public class ElasticSearchUtil {

    private static TransportClient client;
    public ElasticSearchUtil() {
        this.client=new ElasticsearchConfig().getElasticsearchClient();  //创建客户端。
    }

    //创建索引，并给索引某些字段指定iK分词，以后向该索引中查询时，就会用ik分词。
    public void createIndex() throws IOException {
        //创建映射
        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("properties")
                //      .startObject("m_id").field("type","keyword").endObject()
                .startObject("title").field("type", "text").field("analyzer", "ik_smart").endObject()
                .startObject("content").field("type", "text").field("analyzer", "ik_max_word").endObject()
                .endObject()
                .endObject();

        //pois：索引名   cxyword：类型名（可以自己定义）
        PutMappingRequest putmap = Requests.putMappingRequest("index").type("type").source(mapping);
        //创建索引
        client.admin().indices().prepareCreate("index").execute().actionGet();
        //为索引添加映射
        client.admin().indices().putMapping(putmap).actionGet();
    }

    public void createIndex1() throws IOException {
        IndexResponse response = client.prepareIndex("index", "type", "1")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("title", "title")
                        .field("content", "content")
                        .endObject()
                ).get();
    }

    public void updateByClient() throws IOException, ExecutionException, InterruptedException {
        //每次添加id应该不同，相当于数据表中的主键，相同 的话将会进行覆盖
        UpdateResponse response = client.update(new UpdateRequest("index", "type", "1")
                .doc(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("title", "中华人民共和国国歌,国歌是最好听的歌")
                        .field("content","中华人民共和国国歌,国歌是最好听的歌")
                        .endObject()
                )).get();
    }
    public void createIndex2() throws IOException {
        IndexResponse response = client.prepareIndex("index", "type", "2")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("title", "中华民族是伟大的民族")
                        .field("content", "中华民族是伟大的民族")
                        .endObject()
                ).get();
    }

    /**
     * 批量创建索引，并添加数据
     * @throws IOException
     */
    public void bulkApi(List<Product> listProduct) throws IOException {

        BulkRequestBuilder bulkRequest = client.prepareBulk();

// either use client#prepare, or use Requests# to directly build index/delete requests
        bulkRequest.add(client.prepareIndex("twitter", "tweet", "1")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )
        );

        bulkRequest.add(client.prepareIndex("twitter", "tweet", "2")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "another post")
                        .endObject()
                )
        );

        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
        }
    }

    //获取单条数据
    public void getDataByID() {
        try {
            //创建映射
            XContentBuilder mapping = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject("properties")
                    //      .startObject("m_id").field("type","keyword").endObject()
                    .startObject("title").field("type", "text").endObject()
                    //，对数据按照设定规则处理，ik_max_word 是分词类型，最细粒度切割，
                    .startObject("content").field("type", "text").field("analyzer", "ik_max_word").endObject()
                    .endObject()
                    .endObject();

            //pois：索引名   cxyword：类型名（可以自己定义）
            PutMappingRequest putmap = Requests.putMappingRequest("blog1").type("article").source(mapping);
            //创建索引
            client.admin().indices().prepareCreate("blog1").execute().actionGet();  //创建
            //为索引添加映射
            client.admin().indices().putMapping(putmap).actionGet();  //映射添加进去

            //搜索数据
            GetResponse response = client.prepareGet("blog1", "article", "1").execute().actionGet();
            //输出结果
            System.out.println(response.toString());

            //关闭client
            //client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test2() throws IOException {

        BulkRequestBuilder bulkRequest = client.prepareBulk();

// either use client#prepare, or use Requests# to directly build index/delete requests
        bulkRequest.add(client.prepareIndex("twitter", "tweet", "1")  //创建索引
                .setSource(jsonBuilder()   //请求体，数据
                        .startObject()
                        .field("analyzer","ik_max_word")  //指定分词器
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )
        );

        bulkRequest.add(client.prepareIndex("twitter", "tweet", "2")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "another post")
                        .endObject()
                )
        );

        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
        }

        BulkItemResponse[] items = bulkResponse.getItems();  //一次发送多个请求，会返回一个响应数组。
        DocWriteResponse response = items[0].getResponse();
        String index = response.getIndex();
        System.out.println(index);
    }

    public void query() {
             try{
            //搜索数据
            GetResponse response = client.prepareGet("blog", "article", "1").execute().actionGet();
            //输出结果
            System.out.println(response.getSourceAsString());
            //关闭client
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void simpleSearch() {
        IndicesAdminClient indicesAdminClient = client.admin().indices();
        SearchResponse response = client.prepareSearch().get();
//        AnalyzeRequestBuilder request = new AnalyzeRequestBuilder(indicesAdminClient, "cloud_repair", "中华人民共和国国歌");  //ik_post
        // request.setAnalyzer("ik");
//        request.setTokenizer("ik");
//        // Analyzer（分析器）、Tokenizer（分词器）
//        List listAnalysis = request.execute().actionGet().getTokens();
        System.out.println(response.getSuccessfulShards());
        // listAnalysis中的结果就是分词的结果
    }



    public void basicSearch() {
        SearchResponse response = client.prepareSearch("index", "index2")  //指定search 的索引
                .setTypes("type", "type2")                //指定search的类型
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)  //SearchType  先查询再获取
                .setQuery(QueryBuilders.termQuery("title", "看看"))                 // Query
//                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                .setFrom(0).setSize(60).setExplain(true)
                .get();

        System.out.println(response.getSuccessfulShards());
    }

    public void get() {
        GetResponse response = client.prepareGet("index", "type", "1").get();
        Map<String, Object> source = response.getSource();
        Set<String> strings = source.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            System.out.println(source.get(iterator.next()));
        }
    }

    public void search() {
        SearchResponse response1 = client.prepareSearch("index1", "index")  //指定多个索引
                .setTypes("type1", "type")  //指定类型
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchQuery("title", "中华人民共和国国歌"))  // Query
//                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                .setFrom(0).setSize(60).setExplain(true)
                .get();
        long totalHits1= response1.getHits().totalHits;  //命中个数
        System.out.println(totalHits1);

        SearchResponse response2 = client.prepareSearch("index1", "index")  //指定多个索引
                .setTypes("type1", "type")  //指定类型
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchQuery("content", "中华人民共和国国歌"))  // Query
//                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                .setFrom(0).setSize(60).setExplain(true)
                .get();
        long totalHits2 = response2.getHits().totalHits;  //命中个数
        System.out.println(totalHits2);
    }

    public void searchTemplate() {
        Map<String, Object> template_params = new HashMap<>();
        template_params.put("title", "中国");

        SearchResponse sr = new SearchTemplateRequestBuilder(client)
                .setScript("template_gender")
//                .setScriptType(ScriptType.STORED)
                .setScriptParams(template_params)
                .setRequest(new SearchRequest())
                .get()
                .getResponse();
    }
}
