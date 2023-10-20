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
public class UpdateDataBatch {
}
