package com.background.service.impl;

import com.background.dao.mapper.QueryProductMapper;
import com.background.service.QueryProductService;
import data.pojo.Product;
import data.redis.JedisPoolUtil;
import data.seriableObject.Serialize;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryProductServiceImpl implements QueryProductService{

    Logger logger = LogManager.getLogger(QueryProductServiceImpl.class);
    JedisPoolUtil jpu=new JedisPoolUtil();
    private Jedis jedis=jpu.getJedis();
    @Autowired
    QueryProductMapper queryProductMapper;

    public Product QueryProductByid(String id) {
        long timeMillis1 = System.currentTimeMillis();
        logger.debug(id+"logger id");
        //取对象
        logger.debug("id序列化");
        byte[] byt=jedis.get(id.getBytes());
        Object obj=null;
        if (byt!=null && byt.length != 0) {
            logger.debug("使用redis");
            obj=new Serialize().unserizlize(byt);
        }
        if(obj!=null && obj instanceof Product){
            logger.debug("redis 得到对象");
            long timeMillis3 = System.currentTimeMillis();
            logger.debug(timeMillis3-timeMillis1);
            return (Product)obj;
        }
        Product product = queryProductMapper.QueryProductByid(id);
        logger.debug("得到产品");
        if(product !=null){
            jedis.set(id.getBytes(), new Serialize().serialize(product));
        }
        long timeMillis2 = System.currentTimeMillis();
        logger.debug(timeMillis2-timeMillis1);
        return product;
    }


    @Override
    //每页的商品个数
    public List<Product> QueryProductByPage(String page) {
        int pageNum;
        int productNumber=20;
        List<Product> productList = new ArrayList<>();
        long timeMillis1 = System.currentTimeMillis();
        if(page !=null ){
            pageNum = Integer.parseInt(page);
            int startIndex = (pageNum-1) * productNumber;
            while(startIndex<(pageNum*productNumber)){
                String id = Integer.toString(startIndex);
                Product product = QueryProductBypid(id);
                productList.add(product);
                startIndex++;
            }
            long timeMillis2= System.currentTimeMillis();
            logger.debug(timeMillis2-timeMillis1);
            return productList;
        }

        return null;
    }


    public Product QueryProductBypid(String id) {
        byte[] byt=jedis.get(id.getBytes());
        Object obj=null;
        if (byt!=null && byt.length != 0) {
            obj=new Serialize().unserizlize(byt);
        }
        if(obj!=null && obj instanceof Product){
            return (Product)obj;
        }
        Product product = queryProductMapper.QueryProductBypid(id);
        if(product !=null){
            jedis.set(id.getBytes(), new Serialize().serialize(product));
        }
        return product;
    }

}
