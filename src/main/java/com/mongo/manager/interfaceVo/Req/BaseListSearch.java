package com.mongo.manager.interfaceVo.Req;

import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.query.Criteria;


/**
 * @author : willow
 * @date : 16:22 2021/1/7
 */
public class BaseListSearch {
    //@ApiModelProperty(value = "当前页码")
    private Integer current = 1;
    //@ApiModelProperty(value = "单页数量")
    private Integer pageSize = 20;
    private DBObject dataScope;
    private Criteria dataScopeCriteria;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public DBObject getDataScope() {
        return dataScope;
    }

    public void setDataScope(DBObject dataScope) {
        this.dataScope = dataScope;
    }

    public Criteria getDataScopeCriteria() {
        return dataScopeCriteria;
    }

    public void setDataScopeCriteria(Criteria dataScopeCriteria) {
        this.dataScopeCriteria = dataScopeCriteria;
    }
}
