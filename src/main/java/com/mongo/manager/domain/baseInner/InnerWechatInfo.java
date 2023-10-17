package com.mongo.manager.domain.baseInner;


public class InnerWechatInfo {

    /** 会员openid */
    private String openId;
    /** 会员unionId */
    private String unionId;
    /** 会员微信昵称，需要考虑有emoji的情况 */
    private String nickName;
    /** 会员微信头像 */
    private String headUrl;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
