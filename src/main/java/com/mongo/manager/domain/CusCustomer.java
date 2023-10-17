package com.mongo.manager.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongo.manager.domain.baseInner.InnerWechatInfo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 会员名单 cus_customer
 * 
 * @author early
 */
@Document(value = "cus_customer")
public class CusCustomer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //"用户实名状态 1：未实名 2：已实名"
    private Integer status = 1;

    // 身份证号
    //"身份证号"
    private String idNumber;

    // 真实姓名
    //"真实姓名"
    private String realName;

//    // 人文图像
//    //"人文图像"
//    private String faceImage;

    /**
     * 登录账号
     */
    //"登录账号"
    private String userName;

    //"手机号"
    private String phone;

    /**
     * 密码
     */
    @JsonIgnore
    @JsonProperty
    private String password;

    /** 性别（male男性 female女性） */
    //"性别 （male男性 female女性 unknown 未知）"
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;


    /**
     * 备注
     */
    //"备注"
    private String remark;

    //"关联的微信信息"
    private InnerWechatInfo innerWechatInfo;

    //"成为会员时间"
    private Date memberTime;

    /** 地址 */
    //"区块链地址"
    private String blockchainAddress;

    //"唯一地址"
    private String uniqueAddress;

    //"审核成功的id"
    private String checkReqID;

    //"邀请码"
    private String invitationCode;
    //"邀请时间"
    private Date inviteDate;

    //"用户实名状态 1：普通用户 2：邀约用户（可分享）"
    private Integer customerType = 2;

    /** 实名认证时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    //"实名认证时间"
    private Date attestationTime;

    // "用户是否关注公众号  0：未关注 1：已关注"
    private Integer subscribe = 0;

    //"来源"
    private String routeid;

    //"上链状态"
    private Boolean chainStatus;
    //"是否设置密码"
    private Boolean hasSetPwd = false;

    //"认证银行卡号"
    private String cardNo;

    //"唯一标识"
    private String identification;

    //"文昌链地址"
    private String avataAddress;

    //"创建时间"
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public InnerWechatInfo getInnerWechatInfo() {
        return innerWechatInfo;
    }

    public void setInnerWechatInfo(InnerWechatInfo innerWechatInfo) {
        this.innerWechatInfo = innerWechatInfo;
    }

    public Date getMemberTime() {
        return memberTime;
    }

    public void setMemberTime(Date memberTime) {
        this.memberTime = memberTime;
    }

    public String getBlockchainAddress() {
        return blockchainAddress;
    }

    public void setBlockchainAddress(String blockchainAddress) {
        this.blockchainAddress = blockchainAddress;
    }

    public String getUniqueAddress() {
        return uniqueAddress;
    }

    public void setUniqueAddress(String uniqueAddress) {
        this.uniqueAddress = uniqueAddress;
    }

    public String getCheckReqID() {
        return checkReqID;
    }

    public void setCheckReqID(String checkReqID) {
        this.checkReqID = checkReqID;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public Date getInviteDate() {
        return inviteDate;
    }

    public void setInviteDate(Date inviteDate) {
        this.inviteDate = inviteDate;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public Date getAttestationTime() {
        return attestationTime;
    }

    public void setAttestationTime(Date attestationTime) {
        this.attestationTime = attestationTime;
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public String getRouteid() {
        return routeid;
    }

    public void setRouteid(String routeid) {
        this.routeid = routeid;
    }

    public Boolean getChainStatus() {
        return chainStatus;
    }

    public void setChainStatus(Boolean chainStatus) {
        this.chainStatus = chainStatus;
    }

    public Boolean getHasSetPwd() {
        return hasSetPwd;
    }

    public void setHasSetPwd(Boolean hasSetPwd) {
        this.hasSetPwd = hasSetPwd;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getAvataAddress() {
        return avataAddress;
    }

    public void setAvataAddress(String avataAddress) {
        this.avataAddress = avataAddress;
    }
}
