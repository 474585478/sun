package com.mongo.manager.service;

import com.mongo.manager.domain.CollectProduct;
import com.mongo.manager.interfaceVo.Req.CollectProductSearch;

import java.util.List;

/**
 * <Description>TODO <br>
 *
 * @author JLY
 * @version 1.0
 * @date 2023/10/12 16:07
 */
public interface CollectProductService {
    public List<CollectProduct> getColProductList(CollectProductSearch search) ;
    public CollectProduct getColProductByID(String id);
    public String saveCollectProduct(CollectProduct collectProduct);
    public void saveCollectProduct(List<CollectProduct> collectProducts);
    public void updateCollectProductById(CollectProduct collectProduct);
    public void updateCollectProductByQuery();
    public void reduceInventory(int count,String id);
    public void removeProduct(int count,String id);

}
