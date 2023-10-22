package com.mongo.manager.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class QueryAggregateCDto {

    private String id;
    //订单数
    private int orderNoTotal;
    //营业额
    private BigDecimal priceTotal;
    //所有orderNo
    public List<String> orderNoList;
}
