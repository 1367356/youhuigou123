package queryProduct.service;

import com.alibaba.fastjson.JSONObject;
import data.pojo.RecParameter;

public interface SearchProductService{

    public void createIndexMappingUseIK();

    public void bulkCreateIndexAndAddData();

    public JSONObject productSearch(String text);

}
