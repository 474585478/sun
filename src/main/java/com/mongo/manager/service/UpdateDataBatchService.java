package com.mongo.manager.service;

import com.mongo.manager.domain.CusCustomer;
import com.mongo.manager.domain.OrdOrderEntity;
import com.mongo.manager.domain.ProductDto;
import com.mongo.manager.utils.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

/**
 * 1、更新cus_customer表中，获取所有数据，增加一个字段lastWord，值为invitationCode字段的最后一个字母；
 * 2、更新ord_order表中，统计每个产品的订单数，并在ord_order表中的对应productInfo下增加orderCount字段记录。
 *
 *   db.cus_customer.find({}).forEach(doc => {
 *     const lastChar = doc.invitationCode.slice(-1);
 *     db.cus_customer.updateOne({ _id: doc._id }, { $set: {lastWord: lastChar} });
 *   });
 *
 * 	db.ord_order.find({productInfo:{$exists:true}}).forEach(doc =>{
 * 	     db.ord_order.updateOne({doc.productInfo._id : array.}, {$set:{productInfo.orderCount : doc.orderCount}})
 *        });
 *
 *        	db.ord_order.aggregate([
 *        {
 * 	$match:{productInfo:{$exists:true}}
 *    },
 *   {
 *     $group: {
 *       _id: "$productInfo._id",
 *       orderCount: { $sum: 1 }
 *     }
 *   }
 * ])
 */
@Service
public class UpdateDataBatchService {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 1、更新cus_customer表中，获取所有数据，增加一个字段lastWord，值为invitationCode字段的最后一个字母；
     * 查询语句：  db.cus_customer.find({}).forEach(doc => {
     *          const lastChar = doc.invitationCode.slice(-1);
     *          db.cus_customer.updateOne({ _id: doc._id }, { $set: {lastWord: lastChar} });
     *         });
     */
    public void a() {
        List<CusCustomer> list = mongoTemplate.findAll(CusCustomer.class);
        for (CusCustomer cusCustomer:list) {
            String str = cusCustomer.getInvitationCode();
            String lastChar = str.substring(str.length() - 1);
            Update update = new Update();
            update.set("lastWord",lastChar);
            mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(new ObjectId(cusCustomer.getId()))), update, CusCustomer.class);
        }
    }

    /**
     * 2、更新ord_order表中，统计每个产品的订单数，并在ord_order表中的对应productInfo下增加orderCount字段记录。
     * 查询语句:
     * 	db.ord_order.find({productInfo:{$exists:true}}).forEach(doc =>{
     * 	     db.ord_order.updateOne({doc.productInfo._id : array.}, {$set:{productInfo.orderCount : doc.orderCount}})
     *        });
     *
     *        	db.ord_order.aggregate([
     *        {
     * 	$match:{productInfo:{$exists:true}}
     *    },
     *   {
     *     $group: {
     *       _id: "$productInfo._id",
     *       orderCount: { $sum: 1 }
     *     }
     *   }
     * ])
     */
    public void b() {
        Aggregation aggregation = Aggregation.newAggregation(
                match(Criteria.where("productInfo").exists(true)),
                group("productInfo._id")
                        .count().as("count"));
        AggregationResults<ProductDto> aggregationResults = mongoTemplate.aggregate(aggregation,"ord_order", ProductDto.class);
        List<ProductDto> productDtoList = aggregationResults.getMappedResults();
        //将产品id和数量组成map
        Map<String,Integer> map = productDtoList.stream()
                .collect(Collectors.toMap(ProductDto::getId,ProductDto::getCount));
        List<OrdOrderEntity> list = mongoTemplate.findAll(OrdOrderEntity.class);
        for (OrdOrderEntity ordOrderEntity:list) {
            if (ordOrderEntity.getProductInfo() != null){
                Update update = new Update();
                //根据产品id获得订单数量
                update.set("productInfo.orderCount",map.get(ordOrderEntity.getProductInfo().getId()));
                mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(new ObjectId(ordOrderEntity.getId()))), update, OrdOrderEntity.class);
            }
        }
    }
}
