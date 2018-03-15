package queryProduct.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import data.pojo.RecParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import queryProduct.service.impl.SearchProductServiceImpl;
import template.ResponseTemplate;

@RestController
@RequestMapping("/SearchProductController")
public class SearchProductController {

    Logger logger = LogManager.getLogger(SearchProductController.class);
    @Autowired
    SearchProductServiceImpl searchProductService;

    /**
     * 为elasticsearch 创建索引，字段指定分词为ik分词
     * @param recParameter
     */
    @RequestMapping("/createIndexMappingUseIK")
    public void createIndexMappingUseIK(RecParameter recParameter) {
        searchProductService.createIndexMappingUseIK();
    }

    /**
     * 创建索引并添加数据
     * @param recParameter
     */
    @RequestMapping("/bulkCreateIndexAndAddData")
    public void bulkCreateIndexAndAddData(RecParameter recParameter) {
        searchProductService.bulkCreateIndexAndAddData();
    }

    /**
     * 根据文本内容进行搜索
     * @param recParameter 接受参数
     * @return
     */
    @RequestMapping("/productSearch")
    @ResponseBody
    public JSONPObject productSearch(RecParameter recParameter) {

        logger.debug("productSearch----------------");
        String text=recParameter.getText();
        String callback = recParameter.getCallback();
        JSONObject jsonObject = searchProductService.productSearch(text);
        ResponseTemplate response=new ResponseTemplate(0, "成功", jsonObject);
        return new JSONPObject(callback,response);
    }

}
