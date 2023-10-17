package com.mongo.manager.homework;

import com.mongo.manager.domain.CusCustomer;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HomeworkService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 更新cus_customer表中，增加createTime字段，字段值为数据添加的时间；
     */
    private void update(){
        List<CusCustomer> list = mongoTemplate.findAll(CusCustomer.class);
        for (CusCustomer cusCustomer:list) {
            cusCustomer.setCreateTime(new Date());
            mongoTemplate.save(cusCustomer);
        }
    }

    /**
     * 按日取出ord_order表中每天订单金额最大的订单；
     */
    private void queryMaxOrder(){
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("createTime").andExpression("$dateToString: { format: \"%Y-%m-%d\", date: \"$createTime\" }"),
                Aggregation.group("createTime,orderNo").max("{$multiply:[\"$price\",\"$count\"]}").as("maxAmount"),
                Aggregation.group("_id").first("order_id: \"$_id.order_id\", order_amount: \"$maxAmount\"").as("maxOrder"),
                Aggregation.project("_id").and("day").as("day")
                        .and("maxOrder.order_id").as("orderId")
                        .and("maxOrder.order_amount").as("maxOrder.order_amount"),
                Aggregation.sort(Sort.Direction.ASC,"day")

        );
        AggregationResults<OrdOrderDto> aggregate = mongoTemplate.aggregate(aggregation, "ord_order", OrdOrderDto.class);
        List<OrdOrderDto> list = aggregate.getMappedResults();
    }

    /**
     * 判断merge_log中是否有重复使用的材料（materials字段）；
     */
    private void checkIfRepeat(){
//        Criteria criteria = Criteria.where("count").gt(1);
//        Aggregation aggregation = Aggregation.newAggregation(
//                Aggregation.project("materials"),
//                Aggregation.unwind("materials")
////                Aggregation.group("_id,materials.materialsId").count(),
////                Aggregation.match(criteria)
////                Aggregation.group("_id._id").addToSet("_id.name").as("materials")
//        );
        String command = "db.merge_log.aggregate([\n" +
                "    {\"$project\": {\"materials\":1}},\n" +
                "    {\"$unwind\":\"$materials\"},\n" +
                "    {\"$group\": {\"_id\":{\"_id\":\"$_id\", \"Name\":\"$materials.materialsId\"}, \"count\":{\"$sum\":1}}},\n" +
                "    {\"$match\": {\"count\":{\"$gt\":1}}},\n" +
                "    {\"$group\": {\"_id\": \"$_id._id\", \"materials\":{\"$addToSet\":\"$_id.Name\"}}}\n" +
                "])";
        Document document = mongoTemplate.executeCommand(command);
    }

}
