package com.mongo.manager.interfaceVo.Req;


import java.util.Date;

public class CollectProductSearch extends BaseListSearch {
    private Integer  status;
    private String seriesId;
    private String productName;
    private Date StartTime;
    private Date EndTime;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date startTime) {
        StartTime = startTime;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date endTime) {
        EndTime = endTime;
    }
}
