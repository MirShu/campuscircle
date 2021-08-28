package com.campuscircle.app.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class ListModel {
    @JSONField(name = "NewsInfoModels")
    private List<NewsInfoModelsDTO> newsInfoModels;

    public List<NewsInfoModelsDTO> getNewsInfoModels() {
        return newsInfoModels;
    }

    public void setNewsInfoModels(List<NewsInfoModelsDTO> newsInfoModels) {
        this.newsInfoModels = newsInfoModels;
    }

    public static class NewsInfoModelsDTO {
        @JSONField(name = "NewsId")
        private int newsId;
        @JSONField(name = "NewsCategoryId")
        private int newsCategoryId;
        @JSONField(name = "NewsCategoryName")
        private String newsCategoryName;
        @JSONField(name = "NewsType")
        private int newsType;
        @JSONField(name = "Title")
        private String title;
        @JSONField(name = "OutSiteLink")
        private Object outSiteLink;
        @JSONField(name = "UniversityId")
        private int universityId;
        @JSONField(name = "Short")
        private String shortX;
        @JSONField(name = "Phone")
        private String phone;
        @JSONField(name = "QQ")
        private String qq;
        @JSONField(name = "Wechat")
        private String wechat;
        @JSONField(name = "Email")
        private String email;
        @JSONField(name = "AeecssToken")
        private Object aeecssToken;
        @JSONField(name = "Price")
        private int price;
        @JSONField(name = "ShowDays")
        private int showDays;
        @JSONField(name = "JobInfo")
        private String jobInfo;
        @JSONField(name = "Salary")
        private String salary;
        @JSONField(name = "WorkAddress")
        private String workAddress;
        @JSONField(name = "BasicRequirements")
        private String basicRequirements;
        @JSONField(name = "WorkStartTime")
        private String workStartTime;
        @JSONField(name = "WorkEndTime")
        private String workEndTime;
        @JSONField(name = "RouteStart")
        private String routeStart;
        @JSONField(name = "RouteEnd")
        private String routeEnd;
        @JSONField(name = "LastSite")
        private int lastSite;
        @JSONField(name = "StartDateTime")
        private String startDateTime;
        @JSONField(name = "EndDateTime")
        private String endDateTime;
        @JSONField(name = "NewsStatus")
        private String newsStatus;
        @JSONField(name = "Remark")
        private String remark;
        @JSONField(name = "Nickname")
        private String nickname;
        @JSONField(name = "AvatarUrl")
        private String avatarUrl;
        @JSONField(name = "CreateDateTime")
        private String createDateTime;
        @JSONField(name = "ReportRemark")
        private Object reportRemark;
        @JSONField(name = "PictureListModel")
        private List<?> pictureListModel;

        public int getNewsId() {
            return newsId;
        }

        public void setNewsId(int newsId) {
            this.newsId = newsId;
        }

        public int getNewsCategoryId() {
            return newsCategoryId;
        }

        public void setNewsCategoryId(int newsCategoryId) {
            this.newsCategoryId = newsCategoryId;
        }

        public String getNewsCategoryName() {
            return newsCategoryName;
        }

        public void setNewsCategoryName(String newsCategoryName) {
            this.newsCategoryName = newsCategoryName;
        }

        public int getNewsType() {
            return newsType;
        }

        public void setNewsType(int newsType) {
            this.newsType = newsType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getOutSiteLink() {
            return outSiteLink;
        }

        public void setOutSiteLink(Object outSiteLink) {
            this.outSiteLink = outSiteLink;
        }

        public int getUniversityId() {
            return universityId;
        }

        public void setUniversityId(int universityId) {
            this.universityId = universityId;
        }

        public String getShortX() {
            return shortX;
        }

        public void setShortX(String shortX) {
            this.shortX = shortX;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getAeecssToken() {
            return aeecssToken;
        }

        public void setAeecssToken(Object aeecssToken) {
            this.aeecssToken = aeecssToken;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getShowDays() {
            return showDays;
        }

        public void setShowDays(int showDays) {
            this.showDays = showDays;
        }

        public String getJobInfo() {
            return jobInfo;
        }

        public void setJobInfo(String jobInfo) {
            this.jobInfo = jobInfo;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getWorkAddress() {
            return workAddress;
        }

        public void setWorkAddress(String workAddress) {
            this.workAddress = workAddress;
        }

        public String getBasicRequirements() {
            return basicRequirements;
        }

        public void setBasicRequirements(String basicRequirements) {
            this.basicRequirements = basicRequirements;
        }

        public String getWorkStartTime() {
            return workStartTime;
        }

        public void setWorkStartTime(String workStartTime) {
            this.workStartTime = workStartTime;
        }

        public String getWorkEndTime() {
            return workEndTime;
        }

        public void setWorkEndTime(String workEndTime) {
            this.workEndTime = workEndTime;
        }

        public String getRouteStart() {
            return routeStart;
        }

        public void setRouteStart(String routeStart) {
            this.routeStart = routeStart;
        }

        public String getRouteEnd() {
            return routeEnd;
        }

        public void setRouteEnd(String routeEnd) {
            this.routeEnd = routeEnd;
        }

        public int getLastSite() {
            return lastSite;
        }

        public void setLastSite(int lastSite) {
            this.lastSite = lastSite;
        }

        public String getStartDateTime() {
            return startDateTime;
        }

        public void setStartDateTime(String startDateTime) {
            this.startDateTime = startDateTime;
        }

        public String getEndDateTime() {
            return endDateTime;
        }

        public void setEndDateTime(String endDateTime) {
            this.endDateTime = endDateTime;
        }

        public String getNewsStatus() {
            return newsStatus;
        }

        public void setNewsStatus(String newsStatus) {
            this.newsStatus = newsStatus;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public String getCreateDateTime() {
            return createDateTime;
        }

        public void setCreateDateTime(String createDateTime) {
            this.createDateTime = createDateTime;
        }

        public Object getReportRemark() {
            return reportRemark;
        }

        public void setReportRemark(Object reportRemark) {
            this.reportRemark = reportRemark;
        }

        public List<?> getPictureListModel() {
            return pictureListModel;
        }

        public void setPictureListModel(List<?> pictureListModel) {
            this.pictureListModel = pictureListModel;
        }
    }
}
