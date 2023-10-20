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
public class UpdateDate2 {
}
