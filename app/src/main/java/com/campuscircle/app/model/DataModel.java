package com.campuscircle.app.model;

import com.alibaba.fastjson.annotation.JSONField;

public class DataModel {
    @JSONField(name = "AccessToken")
    private String accessToken;
    @JSONField(name = "RewardPoint")
    private int rewardPoint;
    @JSONField(name = "Nickname")
    private String nickname;
    @JSONField(name = "AvatarUrl")
    private String avatarUrl;
    @JSONField(name = "AvatarPictureId")
    private int avatarPictureId;
    @JSONField(name = "AuditStatus")
    private String auditStatus;
    @JSONField(name = "Remark")
    private String remark;

    @JSONField(name = "IsSign")
    private boolean issign;

    @JSONField(name = "Phone")
    private String Phone;

    @JSONField(name = "InKindWantRecords")
    private int InKindWantRecords;

    @JSONField(name = "InKindGiveRecords")
    private int InKindGiveRecords;

    @JSONField(name = "VirtualWantRecords")
    private int VirtualWantRecords;

    @JSONField(name = "VirtualGiveRecords")
    private int VirtualGiveRecords;

    @JSONField(name = "RentWantRecords")
    private int RentWantRecords;

    @JSONField(name = "RentGiveRecords")
    private int RentGiveRecords;

    @JSONField(name = "CarpoolWantRecords")
    private int CarpoolWantRecords;

    @JSONField(name = "CarpoolGiveRecords")
    private int CarpoolGiveRecords;

    @JSONField(name = "FosterCareWantRecords")
    private int FosterCareWantRecords;

    @JSONField(name = "FosterCareGiveRecords")
    private int FosterCareGiveRecords;

    @JSONField(name = "OtherWantRecords")
    private int OtherWantRecords;

    @JSONField(name = "OtherGiveRecords")
    private int OtherGiveRecords;

    @JSONField(name = "WorkStudyGiveRecords")
    private int WorkStudyGiveRecords;

    @JSONField(name = "ReportDealCount")
    private int ReportDealCount;

    public int getInKindWantRecords() {
        return InKindWantRecords;
    }

    public void setInKindWantRecords(int inKindWantRecords) {
        InKindWantRecords = inKindWantRecords;
    }

    public int getInKindGiveRecords() {
        return InKindGiveRecords;
    }

    public void setInKindGiveRecords(int inKindGiveRecords) {
        InKindGiveRecords = inKindGiveRecords;
    }

    public int getVirtualWantRecords() {
        return VirtualWantRecords;
    }

    public void setVirtualWantRecords(int virtualWantRecords) {
        VirtualWantRecords = virtualWantRecords;
    }

    public int getVirtualGiveRecords() {
        return VirtualGiveRecords;
    }

    public void setVirtualGiveRecords(int virtualGiveRecords) {
        VirtualGiveRecords = virtualGiveRecords;
    }

    public int getRentWantRecords() {
        return RentWantRecords;
    }

    public void setRentWantRecords(int rentWantRecords) {
        RentWantRecords = rentWantRecords;
    }

    public int getRentGiveRecords() {
        return RentGiveRecords;
    }

    public void setRentGiveRecords(int rentGiveRecords) {
        RentGiveRecords = rentGiveRecords;
    }

    public int getCarpoolWantRecords() {
        return CarpoolWantRecords;
    }

    public void setCarpoolWantRecords(int carpoolWantRecords) {
        CarpoolWantRecords = carpoolWantRecords;
    }

    public int getCarpoolGiveRecords() {
        return CarpoolGiveRecords;
    }

    public void setCarpoolGiveRecords(int carpoolGiveRecords) {
        CarpoolGiveRecords = carpoolGiveRecords;
    }

    public int getFosterCareWantRecords() {
        return FosterCareWantRecords;
    }

    public void setFosterCareWantRecords(int fosterCareWantRecords) {
        FosterCareWantRecords = fosterCareWantRecords;
    }

    public int getFosterCareGiveRecords() {
        return FosterCareGiveRecords;
    }

    public void setFosterCareGiveRecords(int fosterCareGiveRecords) {
        FosterCareGiveRecords = fosterCareGiveRecords;
    }

    public int getOtherWantRecords() {
        return OtherWantRecords;
    }

    public void setOtherWantRecords(int otherWantRecords) {
        OtherWantRecords = otherWantRecords;
    }

    public int getOtherGiveRecords() {
        return OtherGiveRecords;
    }

    public void setOtherGiveRecords(int otherGiveRecords) {
        OtherGiveRecords = otherGiveRecords;
    }

    public int getWorkStudyGiveRecords() {
        return WorkStudyGiveRecords;
    }

    public void setWorkStudyGiveRecords(int workStudyGiveRecords) {
        WorkStudyGiveRecords = workStudyGiveRecords;
    }

    public int getReportDealCount() {
        return ReportDealCount;
    }

    public void setReportDealCount(int reportDealCount) {
        ReportDealCount = reportDealCount;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public boolean isIssign() {
        return issign;
    }

    public void setIssign(boolean issign) {
        this.issign = issign;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(int rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getAvatarPictureId() {
        return avatarPictureId;
    }

    public void setAvatarPictureId(int avatarPictureId) {
        this.avatarPictureId = avatarPictureId;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}