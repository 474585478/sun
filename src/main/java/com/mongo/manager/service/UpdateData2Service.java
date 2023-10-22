package com.mongo.manager.service;

import com.mongo.manager.domain.CusCustomer;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1、更新cus_customer表中，status大于2的所有数据，更新status字段为12，要求如果数据库值大于12则不更新；
 * 2、更新cus_customer表中，times包含3的所有数据，更新times字段中的3为0；
 * 3、更新cus_customer表中，status为12的所有数据，更新times数组字段，向数组末尾推送一个为6的值；
 * 4、更新cus_customer表中，status为12的所有数据，更新times数组字段，删除数组为6的值；
 *
 * db.cus_customer.updateMany({status:{$gt:2}}, {$set:{status:12}}, {$max:{status:12}});   db.cus_customer.find({status:{$gt:2,$lt:13}});
 * db.cus_customer.updateMany({times:{$in:[3]}},{$set:{"times.$.value":0}});   db.cus_customer.find({times:{$in:[3]}});
 * db.cus_customer.updateMany({status:12},{$push:{"times":6}}); db.cus_customer.find({status:12});
 * db.cus_customer.updateMany({status:12},{$pop:{"times":6}}); db.cus_customer.find({status:12});
 */
@Service
public class UpdateData2Service {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 1、更新cus_customer表中，status大于2的所有数据，更新status字段为12，要求如果数据库值大于12则不更新；
     * 查询语句：db.cus_customer.updateMany({status:{$gt:2}}, {$set:{status:12}}, {$max:{status:12}});
     */
    public void a() {
        Query query = Query.query(Criteria.where("delFlag").is(0)
                .and("status").gt(2));
        Update update = Update.update("status",12);
        update.max("status",12);
        mongoTemplate.updateMulti(query, update, CusCustomer.class);
    }

    /**
     * 2、更新cus_customer表中，times包含3的所有数据，更新times字段中的3为0；
     * 查询语句:db.students.updateMany(
     *    {times:{$in:[3]}},
     *    { $set: { "times.$[element]" : 0 } },
     *    { arrayFilters: [  "element": 3 } ] }
     *  )
     * db.cus_customer.find({times:{$in:[3]}});
     */
    public void b() {
        Query query = Query.query(Criteria.where("delFlag").is(0)
                .and("times").in(Arrays.asList(3)));
        Update update = new Update();
        update.set("times.$[element]", 0);
        Criteria criteriaDefinition = Criteria.where("times").in(Arrays.asList(3));
        update.filterArray(criteriaDefinition);
        mongoTemplate.updateMulti(query, update, CusCustomer.class);
    }

    /**
     * 3、更新cus_customer表中，status为12的所有数据，更新times数组字段，向数组末尾推送一个为6的值；
     * 查询语句：db.cus_customer.updateMany({status:12},{$push:{"times":6}}); db.cus_customer.find({status:12});
     */
    public void c() {
        Query query = Query.query(Criteria.where("delFlag").is(0)
                .and("status").is(12));
        Update update = new Update();
        update.push("time",6);
        mongoTemplate.updateMulti(query, update, CusCustomer.class);
    }

    /**
     * 4、更新cus_customer表中，status为12的所有数据，更新times数组字段，删除数组为6的值；
     * 查询语句： db.cus_customer.updateMany({status:12},{$pop:{"times":6}}); db.cus_customer.find({status:12});
     */
    public void d() {
        Query query = Query.query(Criteria.where("delFlag").is(0)
                .and("status").is(12));
        Update update = new Update();
        update.pop("times", Update.Position.LAST);
        mongoTemplate.updateMulti(query, update, CusCustomer.class);
    }

}
