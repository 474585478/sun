package com.mongo.manager.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *  ord_order 实体类
 *  create by sun 2023-10-21
 */
@Data
@Document(value = "ord_order")
public class OrdOrderEntity extends BaseEntity {

    @Id
    public String id;
    //字段省略

    public ProductInfoVO ProductInfo;
}
