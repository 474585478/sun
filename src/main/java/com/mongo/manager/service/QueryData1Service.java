package com.mongo.manager.service;

import com.mongo.manager.domain.OrdOrderEntity;
import com.mongo.manager.utils.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 查询数据1
 * 1、查询ord_order表中，status是2的所有数据，并分页，分页数自定；
 * 2、查询ord_order表中，price字段大于30的10条数据，只显示orderNo、productInfo和buyers字段；
 * 3、查询ord_order表中，不含有payOrderNo字段的10条数据；
 * 4、查询ord_order表中，otherProInfo中name字段中包含“头牌艺伎”的10条数据；
 *
 * db.ord_order.find({"status":2}).skip(10).limit(10);
 * db.ord_order.find({"price":{$gt:30}}).limit(10);
 * db.ord_order.find({"payOrderNo":{$exists:false}}).limit(10);
 * db.ord_order.find({"otherProInfo.name":{$regex:/头牌艺伎/}}).limit(10);
 */
@Service
public class QueryData1Service {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 1、查询ord_order表中，status是2的所有数据，并分页，分页数自定；
     * 查询语句：db.ord_order.find({"status":2}).skip(10).limit(10)
     */
    public void a() {
        Criteria criteria = Criteria.where("delFlag").is(0);
        criteria.and("status").is(2);
        Query query = Query.query(criteria).skip(10).limit(10);
        List<OrdOrderEntity> list = mongoTemplate.find(query,OrdOrderEntity.class);
    }

    /**
     * 2、查询ord_order表中，price字段大于30的10条数据，只显示orderNo、productInfo和buyers字段；
     * 查询语句：db.ord_order.find({"price":{$gt:30}},{orderNo:1,productInfo:1,buyers:1}).limit(10);
     */
    public void b() {
        Criteria criteria = Criteria.where("delFlag").is(0);
        criteria.and("price").gt(30);
        Query query = Query.query(criteria).limit(10);
        //或者实体类只映射这几个字段
        query.fields().include("orderNo").include("productInfo").include("buyers");
        List<OrdOrderEntity> list = mongoTemplate.find(query,OrdOrderEntity.class);
    }

    /**
     * 3、查询ord_order表中，不含有payOrderNo字段的10条数据；
     * 查询语句：db.ord_order.find({"payOrderNo":{$exists:false}}).limit(10);
     */
    public void c() {
        Criteria criteria = Criteria.where("delFlag").is(0);
        criteria.and("payOrderNo").exists(false);
        Query query = Query.query(criteria).limit(10);
        List<OrdOrderEntity> list = mongoTemplate.find(query,OrdOrderEntity.class);
    }

    /**
     * 4、查询ord_order表中，otherProInfo中name字段中包含“头牌艺伎”的10条数据；
     * 查询语句：db.ord_order.find({"otherProInfo.name":{$regex:/头牌艺伎/}}).limit(10);
     */
    public void d() {
        String name = "头牌艺伎";
        Criteria criteria = Criteria.where("delFlag").is(0);
        String formatStr = StringUtils.escapeExprSpecialWord(name);
        Pattern pattern = Pattern.compile("^.*" + formatStr + ".*$", Pattern.CASE_INSENSITIVE);
        criteria.and("otherProInfo.name").regex(pattern);
        Query query = Query.query(criteria).limit(10);
        List<OrdOrderEntity> list = mongoTemplate.find(query,OrdOrderEntity.class);
    }

}
