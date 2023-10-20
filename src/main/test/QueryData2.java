/**
 * 1、查询my_collect_product表中，log字段长度是1的10条数据；
 * 2、查询my_collect_product表中，buyTime在2022-04-29至2022-05-01的10条数据，并根据buyTime倒叙排列；
 * 3、查询my_collect_product表中，times的值中有2或4的数据。
 * db.my_collect_product.find({log:{$exists:true,$nin:[null],$size:1}}).limit(10);
 * db.my_collect_product.find({buyTime:{$gt:ISODate("2022-04-29T00:00:00Z"),$lt:ISODate("2022-05-02T00:00:00Z")}}).sort({buyTime:-1}).limit(10);
 * db.my_collect_product.find({times:{$in:[2,4]}})
 */
public class QueryData2 {
}
