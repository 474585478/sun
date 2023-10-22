package com.mongo.manager.service;

import com.mongo.manager.domain.CusCustomer;
import com.mongo.manager.domain.OrdOrderEntity;
import com.mongo.manager.domain.ProductDto;
import com.mongo.manager.domain.QueryAggregateCDto;
import com.mongo.manager.utils.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * 1、在ord_order表中，筛选status是1的，然后按人统计订单数及其营业额（payPrice字段；
 * 2、按年统计订单数及营业额，并根据年升序排列；
 * 3、按日统计订单数及营业额，并根据日期升序排列，并显示所有的orderNo值；
 * 4、根据cus_customer表，查询id为ObjectId(“6265e530c1e05d7875c372b0”)会员通过活动ID为ObjectId(“6274da418f8b424ca8b4cc60”)邀请的会员，
 *   并显示邀请的时间到inviteTime字段中，活动邀请记录存在act_activity_member_log表中；
 *
 *   db.ord_order.aggregate([
 *     {
 *         $match: {status: 1}
 *     },
 *     {
 *         $group: {
 *             _id: "$buyers.realName",
 *             order: {$sum: 1},
 * 						payPrice: {$sum: {$multiply:["$payPrice","$count"]}},
 *         }
 *     }
 * ]);
 *
 * db.ord_order.aggregate([
 *  {
 *   $match:{createTime:{$exists:true}}
 *  },
 *  {
 *    $project:{
 * 	   dateFormatter:{$dateToString:{format:"%Y-%m-%d",date:"$createTime"}},
 * 	   orderNo:"$orderNo",
 * 		 priceTotal:{$multiply:["$payPrice","$count"]}
 *          }
 *  },
 *  {
 *  $group:{
 *      _id:"$dateFormatter",
 * 		 orderNo:{$sum: 1},
 * 		 priceTotal:{$sum: "$priceTotal"}
 *    }
 *  },
 *  {
 *  $sort:{_id:1}
 *  }
 * ]);
 *
 * db.act_activity_member_log.find({});
 * db.cus_customer.find({});
 * db.cus_customer.find({_id:ObjectId("6265e530c1e05d7875c372b0")});
 */
@Service
public class QueryAggregateService {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 1、在ord_order表中，筛选status是1的，然后按人统计订单数及其营业额（payPrice字段）；
     *  查询语句：db.cus_customer.update({_id:ObjectId("628dca13548ab73642363dd3")}, {$set:{status:10}});   db.cus_customer.find({_id:ObjectId("628dca13548ab73642363dd3")});
     *   db.ord_order.aggregate([
     *     {
     *         $match: {status: 1}
     *     },
     *     {
     *         $group: {
     *             _id: "$buyers.realName",
     *             order: {$sum: 1},
     * 			payPrice: {$sum: {$multiply:["$payPrice","$count"]}},
     *         }
     *     }
     * ]);
     */
    public void a() {
        Aggregation aggregation = Aggregation.newAggregation(
                match(Criteria.where("status").is(1)),
                group("buyers.realName")
                        .count().as("count")
                        //相乘
                        .sum(ArithmeticOperators.Multiply.valueOf("payPrice").multiplyBy("count")).as("payPrice"));
        AggregationResults<OrdOrderEntity> aggregationResults = mongoTemplate.aggregate(aggregation,"ord_order", OrdOrderEntity.class);
        List<OrdOrderEntity> productDtoList = aggregationResults.getMappedResults();
    }

    /**
     * 2、按年统计订单数及营业额，并根据年升序排列；
     * 查询语句:
     * db.ord_order.aggregate([
     *  {
     *   $match:{createTime:{$exists:true}}
     *  },
     *  {
     *    $project:{
     * 	   dateFormatter:{$dateToString:{format:"%Y",date:"$createTime"}},
     * 	   orderNo:"$orderNo",
     * 		 priceTotal:{$multiply:["$payPrice","$count"]}
     *          }
     *  },
     *  {
     *  $group:{
     *      _id:"$dateFormatter",
     * 		 orderNo:{$sum: 1},
     * 		 priceTotal:{$sum: "$priceTotal"}
     *    }
     *  },
     *  {
     *  $sort:{_id:1}
     *  }
     * ]);
     */
    public void b() {
        Aggregation aggregation = Aggregation.newAggregation(
                match(Criteria.where("createTime").exists(true)),
                project("order").and("payPrice").multiply("count").as("priceTotal")
                        .andExpression("$dateToString:{format:\"%Y\",date:\"$createTime\"}").as("dateFormatter"),
                group("dateFormatter")
                        .count().as("orderNo")
                        .sum("priceTotal").as("priceTotal"),
                sort(Sort.Direction.ASC,"dateFormatter"));
        AggregationResults<OrdOrderEntity> aggregationResults = mongoTemplate.aggregate(aggregation,"ord_order", OrdOrderEntity.class);
        List<OrdOrderEntity> productDtoList = aggregationResults.getMappedResults();
    }

    /**
     * 3.按日统计订单数及营业额，并根据日期升序排列，并显示所有的orderNo值
     * db.ord_order.aggregate([
     *  {
     *   $match:{createTime:{$exists:true}}
     *  },
     *  {
     *    $project:{
     * 	   dateFormatter:{$dateToString:{format:"%Y-%m-%d",date:"$createTime"}},
     * 	   orderNo:"$orderNo",
     * 		 priceTotal:{$multiply:["$payPrice","$count"]}
     *          }
     *  },
     *  {
     *  $group:{
     *      _id:"$dateFormatter",
     * 		 orderNo:{$sum: 1},
     * 		 priceTotal:{$sum: "$priceTotal"}
     *    }
     *  },
     *  {
     *  $sort:{_id:1}
     *  }
     * ]);
     */
    public void c() {
        Aggregation aggregation = Aggregation.newAggregation(
                match(Criteria.where("createTime").exists(true)),
                project("order").and("payPrice").multiply("count").as("priceTotal")
                        .andExpression("$dateToString:{format:\"%Y-%m-%d\",date:\"$createTime\"}").as("dateFormatter"),
                group("dateFormatter")
                        .count().as("orderNo")
                        .sum("priceTotal").as("priceTotal"),
                sort(Sort.Direction.ASC,"dateFormatter"));
        AggregationResults<QueryAggregateCDto> aggregationResults = mongoTemplate.aggregate(aggregation,"ord_order", QueryAggregateCDto.class);
        List<QueryAggregateCDto> queryAggregateCDtoList = aggregationResults.getMappedResults();
        //先查询出 日期 订单数 营业额  在循环查出所有的orderNo
        for (QueryAggregateCDto queryAggregateCDto:queryAggregateCDtoList) {
            String date = queryAggregateCDto.getId();
            Criteria criteria = Criteria.where("delFlag").is(0);
            criteria.and("createTime").gte(date).lte(date);
            Query query = Query.query(criteria);
            query.fields().include("orderNo");
            List<String> orderList = mongoTemplate.find(query,String.class);
            queryAggregateCDto.setOrderNoList(orderList);
        }
    }

    /**
     * 4、根据cus_customer表，查询id为ObjectId(“6265e530c1e05d7875c372b0”)会员通过活动ID为ObjectId(“6274da418f8b424ca8b4cc60”)邀请的会员，
     *   并显示邀请的时间到inviteTime字段中，活动邀请记录存在act_activity_member_log表中；
     * 查询：db.cus_customer.updateMany({status:{$in:[11,13]}},{$set:{times:[1,2,3,4,5]}});   db.cus_customer.find({status:{$in:[11,13]}});
     */
    public void d() {
        Query query = Query.query(Criteria.where("delFlag").is(0).and("status").in(11,13));
        Update update = new Update();
        update.set("times",new int[]{1,2,3,4,5});
        mongoTemplate.updateMulti(query, update, CusCustomer.class);
    }

}
