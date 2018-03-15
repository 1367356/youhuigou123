package com.search.pojo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;

//@JsonInclude(JsonInclude.Include.NON_NULL)//不包含有null值的字段,即字段值为null的转换为json字符串时会被省略
@XmlRootElement(name="MyJSONObject")
public class MyJSONObject {
    JSONObject jsonObject;

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
