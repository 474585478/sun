package com.mongo.manager;

import com.mongo.manager.ManagerApplication;
import com.mongo.manager.domain.OrdOrderEntity;
import com.mongo.manager.service.QueryData1Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

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
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManagerApplication.class)
public class QueryData1 {

//    @Resource
//    private MongoTemplate mongoTemplate;
    @Resource
    private QueryData1Service queryData1Service;
    /**
     * 1、查询ord_order表中，status是2的所有数据，并分页，分页数自定；
     * 查询语句：db.ord_order.find({"status":2}).skip(10).limit(10)
     */
    @Test
    public void a() {
        queryData1Service.a();
//        Criteria criteria = Criteria.where("delFlag").is(0);
//        criteria.and("status").is(2);
//        Query query = Query.query(criteria).skip(10).limit(10);
//        List<OrdOrderEntity> list = mongoTemplate.find(query,OrdOrderEntity.class);
//        System.out.println(list);
    }

}
