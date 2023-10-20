import org.junit.Test;

/**
 * 查询数据1
 * 1、查询ord_order表中，status是2的所有数据，并分页，分页数自定；
 * 2、查询ord_order表中，price字段大于30的10条数据，只显示orderNo、productInfo和buyers字段；
 * 3、查询ord_order表中，不含有payOrderNo字段的10条数据；
 * 4、查询ord_order表中，otherProInfo中name字段中包含“头牌艺伎”的10条数据；
 *
 * db.ord_order.find({"status":2}).skip(10).limit(10);
 * db.ord_order.find({"price":{$gt:30}}).limit(10);
 * db.ord_order.find({"payOrderNo":{$exists:false}}).limit(10);
 * db.ord_order.find({"otherProInfo.name":{$regex:/头牌艺伎/}}).limit(10);
 */
public class QueryData1 {

    /**
     * 1、查询ord_order表中，status是2的所有数据，并分页，分页数自定；
     * 查询语句：db.ord_order.find({"status":2}).skip(10).limit(10)
     */
    @Test
    public void a() {
        System.out.println("A");
    }

}
