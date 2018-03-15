package queryProduct.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import data.pojo.Product;
import data.pojo.RecParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import queryProduct.service.impl.QueryPageServiceImpl;
import template.responseTemplate.ResponseTemplate;

import java.util.List;

/**
 * 一页查询
 */
@RestController
@RequestMapping("/QueryPageController")
public class QueryPageController {

    @Autowired
    QueryPageServiceImpl queryPageServiceImpl;
    /**
     * 根据页数查询商品
     * @param
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public JSONPObject queryPage(RecParameter recParameter) {
        String page=recParameter.getPage();
        String callback = recParameter.getCallback();
        JSONObject jsonObject = queryPageServiceImpl.queryPage(page);
        ResponseTemplate responseTemplate = new ResponseTemplate(0,"成功",jsonObject);
        return new JSONPObject(callback, responseTemplate);
    }
}
