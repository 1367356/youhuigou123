package queryProduct.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import data.http.HttpUtil;
import data.pojo.Product;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.XML;
import org.springframework.stereotype.Service;
import queryProduct.constant.Constants;
import queryProduct.controller.QueryProductController;
import queryProduct.service.QueryPageService;

import java.util.HashMap;
import java.util.Map;

@Service
public class QueryPageServiceImpl implements QueryPageService {
    Logger log= LogManager.getLogger(QueryProductController.class);
    HttpUtil httpUtil=new HttpUtil();
    @Override
    public JSONObject queryPage(String page) {
//        int page = Integer.parseInt(page);

        log.debug(page);
        Map requestParamMap = new HashMap();
        requestParamMap.put("page",page);

        String url= new Constants().backgroundURL+"/QueryProductService/QueryProductByPage";
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
//        ResponseTemplate response=new ResponseTemplate(0, "成功", product);

        return jsonObject;
//        return null;
    }
}
