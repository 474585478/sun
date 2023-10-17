package com.mongo.manager.service.impl;

import com.mongo.manager.domain.CollectProduct;
import com.mongo.manager.interfaceVo.Req.CollectProductSearch;
import com.mongo.manager.service.CollectProductService;
import com.mongo.manager.utils.ObjectUtils;
import com.mongodb.QueryBuilder;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * <Description>TODO <br>
 *
 * @author JLY
 * @version 1.0
 * @date 2023/10/12 16:06
 */
@Service
public class CollectProductServiceImpl implements CollectProductService {
    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 获取商品列表
     *
     * @param search
     * @return
     */
    public List<CollectProduct> getColProductList(CollectProductSearch search) {
        List<CollectProduct> list = null;
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.and("delFlag").is(0);
        if(ObjectUtils.isNotEmpty(search.getStatus())){
            queryBuilder.and("status").is(search.getStatus());
        }
        if(ObjectUtils.isNotEmpty(search.getProductName())){
            //模糊查询
            Pattern pattern = Pattern.compile("^.*" + search.getProductName() + ".*$", Pattern.CASE_INSENSITIVE);
            queryBuilder.and("productName").regex(pattern);
        }
        if (ObjectUtils.isNotEmpty(search.getStartTime())&&ObjectUtils.isNotEmpty(search.getEndTime())){
            queryBuilder.and("createTime").greaterThanEquals(search.getSeriesId()).lessThanEquals(search.getEndTime());
        }else if (ObjectUtils.isNotEmpty(search.getStartTime())){
            queryBuilder.and("createTime").greaterThanEquals(search.getSeriesId());
        }else if (ObjectUtils.isNotEmpty(search.getEndTime())){
            queryBuilder.and("createTime").lessThanEquals(search.getEndTime());
        }

        BasicQuery query = new BasicQuery(queryBuilder.get().toString());

        //排序
        query.with(Sort.by(Sort.Direction.DESC, "releaseTime"));
        //分页
        query.skip((search.getCurrent() - 1) * search.getPageSize()).limit(search.getPageSize());
        list = mongoTemplate.find(query,CollectProduct.class);
        return list;
    }
    /**
     * 根据id获取商品
     *
     * @param
     * @return
     */
    public CollectProduct getColProductByID(String id) {
        CollectProduct collectProduct = null;
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.and("_id").is(new ObjectId(id));

        collectProduct = mongoTemplate.findOne(new BasicQuery(queryBuilder.get().toString()),CollectProduct.class);

        collectProduct = mongoTemplate.findById(new ObjectId(id),CollectProduct.class);

        return collectProduct;
    }

    /**
     * 保存商品信息 并返回id
     *
     * @param
     * @return
     */
    public String saveCollectProduct(CollectProduct collectProduct) {
        collectProduct = mongoTemplate.save(collectProduct);
        return collectProduct.getId();
    }

    /**
     * 批量保存商品信息
     *
     * @param
     * @return
     */
    public void saveCollectProduct(List<CollectProduct> collectProducts) {
        mongoTemplate.save(collectProducts,"collectProducts");
    }


    /**
     * 根据id修改商品信息
     *
     * @param
     * @return
     */
    public void updateCollectProductById(CollectProduct collectProduct) {
        Update update = new Update();
        if (ObjectUtils.isNotEmpty(collectProduct.getProductName())){
            update.set("productName",collectProduct.getProductName());
        }
        QueryBuilder queryUpdate = new QueryBuilder();
        queryUpdate.and("_id").is(new ObjectId(collectProduct.getId()));
        mongoTemplate.updateFirst(new BasicQuery(queryUpdate.get().toString()),update,CollectProduct.class);
    }

    /**
     * 根据条件批量修改商品信息
     *
     * @param
     * @return
     */
    public void updateCollectProductByQuery() {
        Update update = new Update();
        update.set("status",2);

        QueryBuilder queryUpdate = new QueryBuilder();
        queryUpdate.and("status").is(1);
        mongoTemplate.updateMulti(new BasicQuery(queryUpdate.get().toString()),update,CollectProduct.class);
    }

    /**
     * 减少库存
     *
     * @param
     * @return
     */
    public void reduceInventory(int count,String id) {
        Update update = new Update();

        update.inc("stockCount",Math.negateExact(count));

        QueryBuilder queryUpdate = new QueryBuilder();
        queryUpdate.and("_id").is(new ObjectId(id));
        mongoTemplate.updateFirst(new BasicQuery(queryUpdate.get().toString()),update,CollectProduct.class);
    }
    /**
     * 删除商品
     *
     * @param
     * @return
     */
    public void removeProduct(int count,String id) {
        QueryBuilder queryUpdate = new QueryBuilder();
        queryUpdate.and("_id").is(new ObjectId(id));
        mongoTemplate.remove(new BasicQuery(queryUpdate.get().toString()),CollectProduct.class);
    }


}
