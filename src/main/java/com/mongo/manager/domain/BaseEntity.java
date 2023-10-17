package com.mongo.manager.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Entity基类
 *
 * @author early
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // 可以接收，但是接口不返回
    private int delFlag;

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
