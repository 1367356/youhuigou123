package queryProduct.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import data.http.HttpUtil;
import data.pojo.Product;
import data.pojo.RecParameter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.XML;
import org.springframework.stereotype.Service;
import queryProduct.constant.Constants;
import queryProduct.controller.QueryProductController;
import queryProduct.service.SearchProductService;

import java.util.HashMap;
import java.util.Map;

@Service
public class SearchProductServiceImpl implements SearchProductService{
    Logger log= LogManager.getLogger(QueryProductController.class);
    HttpUtil httpUtil=new HttpUtil();
    @Override
    public void createIndexMappingUseIK() {

            Map requestParamMap = new HashMap();

            String url= new Constants().searchURL+"/SearchProduct/createIndexMappingUseIK";
            HttpResponse httpResponse;
            Product product=null;
            com.alibaba.fastjson.JSONObject jsonObject=null;
            try {
                httpResponse = httpUtil.HttpGet(requestParamMap, url);
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    log.debug("使用IK分词创建索引成功");
                }
//            product = (Product)httpResponse.getEntity();
            } catch (Exception e) {
                e.printStackTrace();
            }
//        ResponseTemplate response=new ResponseTemplate(0, "成功", product);

            return;
    }

    @Override
    public void bulkCreateIndexAndAddData() {

        Map requestParamMap = new HashMap();
        String url= new Constants().searchURL+"/SearchProduct/bulkCreateIndexAndAddData";
        HttpResponse httpResponse;
        Product product=null;
        com.alibaba.fastjson.JSONObject jsonObject=null;
        try {
            httpResponse = httpUtil.HttpGet(requestParamMap, url);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                log.debug("批量创建索引，并添加数据成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public JSONObject productSearch(String text) {
        HttpResponse httpResponse;
        Product product=null;
        com.alibaba.fastjson.JSONObject jsonObject=null;
        Map requestParamMap = new HashMap();
        requestParamMap.put("text",text);
        String url= new Constants().searchURL+"/SearchProduct/searchProductByText";
        log.debug(url);
        try {
            httpResponse = httpUtil.HttpGet(requestParamMap, url);
                HttpEntity entity = httpResponse.getEntity();
                String s = EntityUtils.toString(entity);
//                jsonObject=JSONObject.parseObject(s);
                //将返回的xml字符串形式转化为json格式
                org.json.JSONObject xmlJSONObj = XML.toJSONObject(s);
                jsonObject = (JSONObject) JSON.parse(xmlJSONObj.toString());
//                s = jsonObject.getJSONObject("data").getJSONObject("MyJSONObject").toString();
//                xmlJSONObj = XML.toJSONObject(s);
//               jsonObject = (JSONObject) JSON.parse(xmlJSONObj.toString());
            log.debug(s);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                log.debug("搜素成功");
            }
//            product = (Product)httpResponse.getEntity();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        ResponseTemplate response=new ResponseTemplate(0, "成功", product);
        return jsonObject;
    }
}
