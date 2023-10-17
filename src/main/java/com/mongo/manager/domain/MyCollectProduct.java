package com.mongo.manager.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @author : jly
 * @date : 2022年4月11日 17点17分
 */
@Document(value = "my_collect_product")
public class MyCollectProduct extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    //"购买时的产品信息"
    private CollectProduct collectProduct;

    //"用户信息"
    private CusCustomer customerInfo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    //"获得时间"
    private Date buyTime;

    //"购买交易对应区块链地址"
    private String blockchainAddress;


    //"配送状态：0未配送；1已配送"
    private Integer deliveryStatus = 0;

    //"藏品编码"
    private String sortNumber;

    //"配送地址id"

    private ObjectId deliveryId;

    //"状态"
    private Integer status = 0;

    //"是否合成消耗，消耗后只用于展示 不可转增、合成：0未使用；1已使用"
    private Integer useStatus = 0;

/*  //"合成材料ID信息"
    private List<InnerMergeProductSearch> materials;*/

    //"编号前缀"
    private String prefix;

    //"华为链数字资产ID"
    private String huaweiTokenId;

    //"华为链数字资产交易地址"
    private String huaweiTransAddr;

    //"是否锁定 0 :未锁定 1： 已锁定  锁定后只用于展示 不可转增、合成"
    private Integer lockStatus = 0;

    //"状态"
    private Integer chainStatus = 0;

    //"藏品可以转赠时间"
    private Date canGiveTime;

    //"是否已经向文昌链发行"
    private Integer avataChainStatus = 0;

    //"是否已经上文昌链"
    private Integer onAvataChainStatus = 0;

    //"文昌链地址"
    private String avataAddress;

    //"状态"
    private Integer copyStatus = 0;
    //"是否已经表为合成材料"
    private Integer materialsUse = 0;
    //"是否是合成藏品"
    private Integer isComposite = 0;
    //"藏品金额"
    private BigDecimal integral;
    //"藏品金额"
    private BigDecimal freeIntegral;
    //"藏品金额"
    private Integer canExchange;
    //"是否已经兑换成积分"
    private Integer toIntegral;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CollectProduct getCollectProduct() {
        return collectProduct;
    }

    public void setCollectProduct(CollectProduct collectProduct) {
        this.collectProduct = collectProduct;
    }

    public CusCustomer getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CusCustomer customerInfo) {
        this.customerInfo = customerInfo;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public String getBlockchainAddress() {
        return blockchainAddress;
    }

    public void setBlockchainAddress(String blockchainAddress) {
        this.blockchainAddress = blockchainAddress;
    }

    public Integer getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Integer deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(String sortNumber) {
        this.sortNumber = sortNumber;
    }

    public ObjectId getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(ObjectId deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getHuaweiTokenId() {
        return huaweiTokenId;
    }

    public void setHuaweiTokenId(String huaweiTokenId) {
        this.huaweiTokenId = huaweiTokenId;
    }

    public String getHuaweiTransAddr() {
        return huaweiTransAddr;
    }

    public void setHuaweiTransAddr(String huaweiTransAddr) {
        this.huaweiTransAddr = huaweiTransAddr;
    }

    public Integer getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }

    public Integer getChainStatus() {
        return chainStatus;
    }

    public void setChainStatus(Integer chainStatus) {
        this.chainStatus = chainStatus;
    }

    public Date getCanGiveTime() {
        return canGiveTime;
    }

    public void setCanGiveTime(Date canGiveTime) {
        this.canGiveTime = canGiveTime;
    }

    public Integer getAvataChainStatus() {
        return avataChainStatus;
    }

    public void setAvataChainStatus(Integer avataChainStatus) {
        this.avataChainStatus = avataChainStatus;
    }

    public Integer getOnAvataChainStatus() {
        return onAvataChainStatus;
    }

    public void setOnAvataChainStatus(Integer onAvataChainStatus) {
        this.onAvataChainStatus = onAvataChainStatus;
    }

    public String getAvataAddress() {
        return avataAddress;
    }

    public void setAvataAddress(String avataAddress) {
        this.avataAddress = avataAddress;
    }

    public Integer getCopyStatus() {
        return copyStatus;
    }

    public void setCopyStatus(Integer copyStatus) {
        this.copyStatus = copyStatus;
    }

    public Integer getMaterialsUse() {
        return materialsUse;
    }

    public void setMaterialsUse(Integer materialsUse) {
        this.materialsUse = materialsUse;
    }

    public Integer getIsComposite() {
        return isComposite;
    }

    public void setIsComposite(Integer isComposite) {
        this.isComposite = isComposite;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public BigDecimal getFreeIntegral() {
        return freeIntegral;
    }

    public void setFreeIntegral(BigDecimal freeIntegral) {
        this.freeIntegral = freeIntegral;
    }

    public Integer getCanExchange() {
        return canExchange;
    }

    public void setCanExchange(Integer canExchange) {
        this.canExchange = canExchange;
    }

    public Integer getToIntegral() {
        return toIntegral;
    }

    public void setToIntegral(Integer toIntegral) {
        this.toIntegral = toIntegral;
    }
}
