package com.mongo.manager.controller;

import com.mongo.manager.domain.CollectProduct;
import com.mongo.manager.interfaceVo.Req.CollectProductSearch;
import com.mongo.manager.service.CollectProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <Description>TODO <br>
 *
 * @author JLY
 * @version 1.0
 * @date 2023/10/12 20:51
 */
@RestController
@RequestMapping("/product/collectProduct")
public class CollectProductController {
    @Autowired
    CollectProductService service;
    @GetMapping("/getColProductByID")
    public CollectProduct getColProductByID(@RequestParam String id){
        return service.getColProductByID(id);
    }

    @PostMapping("/getColProductList")
    public List<CollectProduct> getColProductList(@RequestBody CollectProductSearch search){
        return service.getColProductList(search);
    }

    @PostMapping("/saveCollectProduct")
    public String saveCollectProduct(@RequestBody CollectProduct collectProduct){
        return service.saveCollectProduct(collectProduct);
    }


}
