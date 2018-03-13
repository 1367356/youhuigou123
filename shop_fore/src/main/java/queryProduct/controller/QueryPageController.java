package queryProduct.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import queryProduct.service.impl.QueryPageServiceImpl;

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
     * @param page
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public JSONObject queryPage(String page) {
        JSONObject jsonObject = queryPageServiceImpl.queryPage(page);
        return jsonObject;
    }
}
