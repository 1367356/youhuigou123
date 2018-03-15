package queryProduct.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import data.http.HttpUtil;
import data.pojo.Product;
import data.pojo.RecParameter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.XML;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import queryProduct.constant.Constants;
import template.ResponseTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class QueryProductController {
    Logger log= LogManager.getLogger(QueryProductController.class);
    HttpUtil httpUtil=new HttpUtil();

    @RequestMapping("/queryProductByPidGet")
    @ResponseBody
    public JSONPObject queryProductByPidGet(RecParameter recParameter){

        String id=recParameter.getUid();
        String callback = recParameter.getCallback();

        Map requestParamMap = new HashMap();
        requestParamMap.put("id",id);

        String url=new Constants().backgroundURL+"/QueryProductService/QueryProductBypid";
        HttpResponse httpResponse;
        Product product=null;
        com.alibaba.fastjson.JSONObject jsonObject=null;
        try {
            httpResponse = httpUtil.HttpGet(requestParamMap, url);
            HttpEntity entity = httpResponse.getEntity();
            String s = EntityUtils.toString(entity);
            //将返回的xml字符串形式转化为json格式
            org.json.JSONObject xmlJSONObj = XML.toJSONObject(s);
            jsonObject = (JSONObject) JSON.parse(xmlJSONObj.toString());
            log.debug(s);
//            product = (Product)httpResponse.getEntity();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseTemplate response=new ResponseTemplate(0, "成功", jsonObject);
        return new JSONPObject(callback, response);
//        return new JSONPObject(callback, response);
    }

    @RequestMapping("/queryProductByPidPost")
    @ResponseBody
//    public ResponseTemplate queryProductByPidPost(String id){
    public JSONPObject queryProductByPid(RecParameter recParameter){

        String callback = recParameter.getCallback();
        String id = recParameter.getUid();

        log.debug(id);
        Map requestParamMap = new HashMap();
        requestParamMap.put("id",id);

        String url=new Constants().backgroundURL+"/QueryProductService/QueryProductBypid";
        HttpResponse httpResponse;
        Product product=null;
        try {
            httpResponse = httpUtil.HttpPost(requestParamMap, url);
            HttpEntity entity = httpResponse.getEntity();
            String s = EntityUtils.toString(entity);
            System.out.println(s+"dayin");
            log.debug(s+"logdayin");
            product = (Product)httpResponse.getEntity();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseTemplate response=new ResponseTemplate(0, "成功", product);
//        return response;
        return new JSONPObject(callback, response);
    }
}
