/**
 * 1、在ord_order表中，筛选status是1的，然后按人统计订单数及其营业额（payPrice字段）；
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
public class QueryAggregate {
}
