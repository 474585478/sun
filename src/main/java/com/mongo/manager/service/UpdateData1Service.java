package com.mongo.manager.service;

import com.mongo.manager.domain.CusCustomer;
import com.mongo.manager.domain.OrdOrderEntity;
import com.mongo.manager.utils.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 1、更新cus_customer表中，_id字段为ObjectId("628dca13548ab73642363dd3")的数据，status字段修改为10；
 * 2、更新cus_customer表中，所有realName字段中带“王”的数据，status字段修改为12；
 * 3、更新cus_customer表中，status为10或12的数据，status全部增加1；
 * 4、更新cus_customer表中，status为11或13的数据，增加字段times，times数据为1,2,3,4,5的数组；
 *
 * db.cus_customer.update({_id:ObjectId("628dca13548ab73642363dd3")}, {$set:{status:10}});   db.cus_customer.find({_id:ObjectId("628dca13548ab73642363dd3")});
 * db.cus_customer.updateMany({realName:{$regex:/王/}}, {$set:{status:12}});   db.cus_customer.find({"realName":{$regex:/王/}});
 * db.cus_customer.updateMany({status:{$in:[10,12]}},{$inc:{status:1}});   db.cus_customer.find({status:{$in:[10,12]}});
 * db.cus_customer.updateMany({status:{$in:[11,13]}},{$set:{times:[1,2,3,4,5]}});   db.cus_customer.find({status:{$in:[11,13]}});
 */
@Service
public class UpdateData1Service {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 1、更新cus_customer表中，_id字段为ObjectId("628dca13548ab73642363dd3")的数据，status字段修改为10；
     * 查询语句：db.cus_customer.update({_id:ObjectId("628dca13548ab73642363dd3")}, {$set:{status:10}});   db.cus_customer.find({_id:ObjectId("628dca13548ab73642363dd3")});
     */
    public void a() {
        Query query = Query.query(Criteria.where("delFlag").is(0)
                .and("_id").is("628dca13548ab73642363dd3"));
        Update update = Update.update("status",10);
        mongoTemplate.updateMulti(query, update, CusCustomer.class);
    }

    /**
     * 2、更新cus_customer表中，所有realName字段中带“王”的数据，status字段修改为12；
     * 查询语句:db.cus_customer.updateMany({realName:{$regex:/王/}}, {$set:{status:12}});   db.cus_customer.find({"realName":{$regex:/王/}});
     */
    public void b() {
        String name = "王";
        String formatStr = StringUtils.escapeExprSpecialWord(name);
        Pattern pattern = Pattern.compile("^.*" + formatStr + ".*$", Pattern.CASE_INSENSITIVE);
        Query query = Query.query(Criteria.where("delFlag").is(0)
                .and("realName").regex(pattern));
        Update update = Update.update("status",12);
        mongoTemplate.updateMulti(query, update, CusCustomer.class);
    }

    /**
     * 3、更新cus_customer表中，status为10或12的数据，status全部增加1；
     * 查询语句：db.cus_customer.updateMany({status:{$in:[10,12]}},{$inc:{status:1}});
     *          db.cus_customer.find({status:{$in:[10,12]}});
     */
    public void c() {
        Query query = Query.query(Criteria.where("delFlag").is(0).and("status").in(10,12));
        Update update = new Update();
        update.inc("status",1);
        mongoTemplate.updateMulti(query, update, CusCustomer.class);
    }

    /**
     * 4、更新cus_customer表中，status为11或13的数据，增加字段times，times数据为1,2,3,4,5的数组；
     * 查询：db.cus_customer.updateMany({status:{$in:[11,13]}},{$set:{times:[1,2,3,4,5]}});   db.cus_customer.find({status:{$in:[11,13]}});
     */
    public void d() {
        Query query = Query.query(Criteria.where("delFlag").is(0).and("status").in(11,13));
        Update update = new Update();
        update.set("times",new int[]{1,2,3,4,5});
        mongoTemplate.updateMulti(query, update, CusCustomer.class);
    }

}
