package com.mongo.manager.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author : jly
 * @date : 2022年4月11日 16点05分
 */
@Document(value = "col_collect_product")
public class CollectProduct extends BaseEntity implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    //"藏品名称"
    private String productName;

    //"藏品展示图"
    private String showImage;

    //"藏品图片列表"
    private List<String> imageUrlArr;

    //"藏品状态 0 未发布; 1 可购买; 2 已售罄; 默认0"
    private Integer  status = 0;

    //"藏品金额"
    private BigDecimal price;

    //"藏品详情"
    private String productDetail;

    //"藏品数量"
    private Integer count;

    //"显示类型：没有/1初始边框；2无边框"
    private Integer showType;

    //"藏品实际库存数量"
    private Integer stockCount;

    //"非销售库存"
    private Integer unSaleStockCount;

    //"藏品标签"
    private List<String> label;

    //"藏品系列"
    private CollectProductSeries series;

    //"藏品等级"
    private String productGrade;

    //"藏品等级0:N  1:R  2:SR 3:SSR 4:UR "
    private Integer gradeType;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    //"藏品发布开始日期"
    private Date releaseTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    //"藏品发布结束日期"
    private Date releaseEndTime;

    //"区块链地址"
    private String blockchainAddress;

    //"产品类型：1 藏品 3：盲盒"
    private Integer productType = 1;

    //"购买的盲盒ID"
    private ObjectId giftRandomId;

    //"是否展示 0：不展示 1：展示"
    private Integer isShow;

    //"限制购买数量"
    private Integer limit;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    //"预售开始时间"
    private Date preSaleStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    //"预售结束时间"
    private Date preSaleEndTime;

    //"编号前缀"
    private String prefix;

    //"当前编号"
    private Integer no;

    //"是否上链"
    private Integer addToChain;

    //"是否已经真的上链"
    private Integer onChainStatus;


    private List<String> txid;

    private String appId;


    //"是否已经向文昌链发行"
    private Integer avataChainStatus=0;

    //"是否已经上文昌链"
    private Integer onAvataChainStatus=0;

    //"文昌链地址"
    private String avataAddress;

    //"文昌链交易hash"
    private String avataHashAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getShowImage() {
        return showImage;
    }

    public void setShowImage(String showImage) {
        this.showImage = showImage;
    }

    public List<String> getImageUrlArr() {
        return imageUrlArr;
    }

    public void setImageUrlArr(List<String> imageUrlArr) {
        this.imageUrlArr = imageUrlArr;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Integer getUnSaleStockCount() {
        return unSaleStockCount;
    }

    public void setUnSaleStockCount(Integer unSaleStockCount) {
        this.unSaleStockCount = unSaleStockCount;
    }

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public CollectProductSeries getSeries() {
        return series;
    }

    public void setSeries(CollectProductSeries series) {
        this.series = series;
    }

    public String getProductGrade() {
        return productGrade;
    }

    public void setProductGrade(String productGrade) {
        this.productGrade = productGrade;
    }

    public Integer getGradeType() {
        return gradeType;
    }

    public void setGradeType(Integer gradeType) {
        this.gradeType = gradeType;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getReleaseEndTime() {
        return releaseEndTime;
    }

    public void setReleaseEndTime(Date releaseEndTime) {
        this.releaseEndTime = releaseEndTime;
    }

    public String getBlockchainAddress() {
        return blockchainAddress;
    }

    public void setBlockchainAddress(String blockchainAddress) {
        this.blockchainAddress = blockchainAddress;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public ObjectId getGiftRandomId() {
        return giftRandomId;
    }

    public void setGiftRandomId(ObjectId giftRandomId) {
        this.giftRandomId = giftRandomId;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Date getPreSaleStartTime() {
        return preSaleStartTime;
    }

    public void setPreSaleStartTime(Date preSaleStartTime) {
        this.preSaleStartTime = preSaleStartTime;
    }

    public Date getPreSaleEndTime() {
        return preSaleEndTime;
    }

    public void setPreSaleEndTime(Date preSaleEndTime) {
        this.preSaleEndTime = preSaleEndTime;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getAddToChain() {
        return addToChain;
    }

    public void setAddToChain(Integer addToChain) {
        this.addToChain = addToChain;
    }

    public Integer getOnChainStatus() {
        return onChainStatus;
    }

    public void setOnChainStatus(Integer onChainStatus) {
        this.onChainStatus = onChainStatus;
    }

    public List<String> getTxid() {
        return txid;
    }

    public void setTxid(List<String> txid) {
        this.txid = txid;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    public String getAvataHashAddress() {
        return avataHashAddress;
    }

    public void setAvataHashAddress(String avataHashAddress) {
        this.avataHashAddress = avataHashAddress;
    }
}
