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
public class UpdateData1 {
}
