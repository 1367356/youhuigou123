package queryProduct.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import data.pojo.RecParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import queryProduct.service.impl.SearchProductServiceImpl;

@RestController
@RequestMapping("/SearchProduct")
public class SearchProductController {

    @Autowired
    SearchProductServiceImpl searchProductService;
    @RequestMapping("/createIndexMappingUseIK")
    public void createIndexMappingUseIK() {
        searchProductService.createIndexMappingUseIK();
    }

    @RequestMapping("/bulkCreateIndexAndAddData")
    public void bulkCreateIndexAndAddData() {
        searchProductService.bulkCreateIndexAndAddData();
    }

    @RequestMapping("/productSearch")
    @ResponseBody
    public JSONPObject productSearch(RecParameter recParameter) {

        String callback = recParameter.getCallback();
        JSONObject jsonObject = searchProductService.productSearch(recParameter);
        return new JSONPObject(callback,jsonObject);
    }

}
