package com.mongo.manager.service;

import com.mongo.manager.domain.MyCollectProduct;
import com.mongo.manager.domain.OrdOrderEntity;
import com.mongo.manager.utils.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 1、查询my_collect_product表中，log字段长度是1的10条数据；
 * 2、查询my_collect_product表中，buyTime在2022-04-29至2022-05-01的10条数据，并根据buyTime倒叙排列；
 * 3、查询my_collect_product表中，times的值中有2或4的数据。
 * db.my_collect_product.find({log:{$exists:true,$nin:[null],$size:1}}).limit(10);
 * db.my_collect_product.find({buyTime:{$gt:ISODate("2022-04-29T00:00:00Z"),$lt:ISODate("2022-05-02T00:00:00Z")}}).sort({buyTime:-1}).limit(10);
 * db.my_collect_product.find({times:{$in:[2,4]}})
 */
@Service
public class QueryData2Service {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 1、查询my_collect_product表中，log字段长度是1的10条数据；
     *  查询语句：db.my_collect_product.find({log:{$exists:true,$nin:[null],$size:1}}).limit(10);
     */
    public void a() {
        Criteria criteria = Criteria.where("delFlag").is(0);
        criteria.and("log").exists(true).size(1);
        Query query = Query.query(criteria).limit(10);
        List<MyCollectProduct> list = mongoTemplate.find(query,MyCollectProduct.class);
    }

    /**
     * 2、查询my_collect_product表中，buyTime在2022-04-29至2022-05-01的10条数据，并根据buyTime倒叙排列；
     * 查询语句： * db.my_collect_product.find({buyTime:{$gt:ISODate("2022-04-29T00:00:00Z"),$lt:ISODate("2022-05-02T00:00:00Z")}}).sort({buyTime:-1}).limit(10);
     */
    public void b() {
        Criteria criteria = Criteria.where("delFlag").is(0);
        criteria.and("buyTime").gt("2022-04-29").lt("2022-05-02");
        Sort sort = Sort.by(Sort.Direction.DESC,"buyTime");
        Query query = Query.query(criteria).with(sort).limit(10);
        List<MyCollectProduct> list = mongoTemplate.find(query,MyCollectProduct.class);
    }

    /**
     * 3、查询my_collect_product表中，times的值中有2或4的数据。
     * 查询语句：db.my_collect_product.find({times:{$in:[2,4]}})
     */
    public void c() {
        Criteria criteria = Criteria.where("delFlag").is(0);
        criteria.and("times").in(Arrays.asList(2,4));
        Query query = Query.query(criteria);
        List<MyCollectProduct> list = mongoTemplate.find(query,MyCollectProduct.class);
    }

}
