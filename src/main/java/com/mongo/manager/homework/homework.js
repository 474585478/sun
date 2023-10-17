db.merge_log.find();

//更新cus_customer表中，增加createTime字段，字段值为数据添加的时间；
db.getCollection("cus_customer").updateMany({}, {$set: {createTime: new Date()}}, false, true);
//按日取出ord_order表中每天订单金额最大的订单；
db.ord_order.aggregate(
    [
        { $group: {
                _id: { day: { $dateToString: { format: "%Y-%m-%d", date: "$createTime" } }, order_id: "$orderNo" },
                maxAmount: { $max: {$multiply:["$price","$count"]}}
            }
        },
        { $group: {
                _id: "$_id.day",
                maxOrder: { $first: { order_id: "$_id.order_id", order_amount: "$maxAmount" } }
            }
        },
        { $project: {
                _id: 0,
                day: "$_id",
                order_id: "$maxOrder.order_id",
                order_amount: "$maxOrder.order_amount"
            }
        },
        { $sort: { day: 1 } }
    ])
//判断merge_log中是否有重复使用的材料（materials字段）
db.merge_log.aggregate([
    {"$project": {"materials":1}},
    {"$unwind":"$materials"},
    {"$group": {"_id":{"_id":"$_id", "Name":"$materials.materialsId"}, "count":{"$sum":1}}},
    {"$match": {"count":{"$gt":1}}},
    {"$group": {"_id": "$_id._id", "materials":{"$addToSet":"$_id.Name"}}}
])
//判断merge_log中是否存在不符合合成规则
//（ product_merge_rule 表）的数据；
//product_merge_rule表中materials里的_id字段，对应my_collect_product表中collectProduct的_id字段；
//merge_log表中materials中的materialsId字段，对应的是my_collect_product表中的_id字段。
