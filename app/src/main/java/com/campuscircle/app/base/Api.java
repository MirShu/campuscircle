package com.campuscircle.app.base;

import java.text.MessageFormat;

public class Api {
    public static final String BASE_SERVER_URL = "http://api.biqinglin.com/SchoolApi/";

    public static final String BASEURL = "http://v.juhe.cn/";
    public static final String USERKEY = "70b52ac2225c6a42adc3575302673f4b";
    public static final String TOUTIAO = MessageFormat.format("{0}toutiao/index", BASEURL);

    public static final String GET_HOME_NEWS_LIST = MessageFormat.format("{0}GetHomeNewsList", BASE_SERVER_URL);  //首页列表的接口

    public static final String GET_CODE = MessageFormat.format("{0}GetCode", BASE_SERVER_URL);  //注册获取验证码

    public static final String ADD_NEWS = MessageFormat.format("{0}AddNews", BASE_SERVER_URL);  //发布卖实物信息

    public static final String GET_NEWS_LIST = MessageFormat.format("{0}GetNewsList", BASE_SERVER_URL);  //列表

    public static final String REGISTER = MessageFormat.format("{0}Register", BASE_SERVER_URL);  //注册

    public static final String LOGIN = MessageFormat.format("{0}Login", BASE_SERVER_URL);  //登录

    public static final String CUSTOMERSIGN = MessageFormat.format("{0}CustomerSign", BASE_SERVER_URL);  //签到

    public static final String EDITCUSTOMERINFO = MessageFormat.format("{0}EditCustomerInfo", BASE_SERVER_URL);  //编辑用户信息

    public static final String GETCONTACTINFO = MessageFormat.format("{0}GetContactInfo", BASE_SERVER_URL);  //获取用户联系方式

    public static final String SHOWNEWSSETTINGMODEL = MessageFormat.format("{0}ShowNewsSettingModel", BASE_SERVER_URL);  //获取咨询配置项

}
