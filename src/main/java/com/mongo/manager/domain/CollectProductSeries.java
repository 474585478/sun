package com.mongo.manager.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : jly
 * @date : 2022年4月11日 16点36分
 */
@Document(value = "col_collect_product_series")
public class CollectProductSeries extends BaseEntity {


    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    //"藏品系列名称"
    private String seriesName;


    //"总系列id"
    private ObjectId parentId;

    //"总系列名称"
    private String parentName;

    //"藏品故事"
    private String seriesStory;

    //"系列首页图片url"
    private String seriesImageUrl;

    //"系列商品最低价"
    private BigDecimal minPrice;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    //"藏品系列发布日期"
    private Date releaseTime;

    private Integer isShow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public ObjectId getParentId() {
        return parentId;
    }

    public void setParentId(ObjectId parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getSeriesStory() {
        return seriesStory;
    }

    public void setSeriesStory(String seriesStory) {
        this.seriesStory = seriesStory;
    }

    public String getSeriesImageUrl() {
        return seriesImageUrl;
    }

    public void setSeriesImageUrl(String seriesImageUrl) {
        this.seriesImageUrl = seriesImageUrl;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}
