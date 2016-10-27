package com.autodesk.easyhome.shejijia;

/**
 * Created by John_Libo on 2016/8/15.
 */
public class AppConfig {
    /**
     * 调试模式
     */
    public static final boolean DEBUG = true;


    public static final String ERROR_PARSER = "1001";
    public static final String ERROR_IO = "1002";
    public static final String ERROR_NONET = "1003";
    public static final String ERROR_REQ = "1004";
    public static final String ERROR_PARSER_MSG = "数据解析失败";
    public static final String ERROR_IO_MSG = "系统繁忙，请您稍后再试";
    public static final String ERROR_NONET_MSG = "请检查您的网络设置";
    public static final String ERROR_REQ_MSG = "请求失败";

    public static final int COMMON_USER_TYPE = 1;  //普通用户类型

    public static final String PWD_REG = "^[a-zA-Z0-9]{6,20}$";  //密码格式验证正则表达式


    public static final String SUCCESS = "1";
    public static final String NOTHING = "0";
    //测试环境
//    public static final String BASE_URL = "http://101.200.167.130:8080/jrjz-api/api";
    //正式环境（ip）
//    public static final String BASE_URL = "http://223.72.146.122:8080/jrjz-api/api";
    //正式环境（域名）
    public static final String BASE_URL = "http://jrjz.juran.com.cn/jrjz-api/api";
    public static final String BASE_IMG_URL = "http://jrjz.juran.com.cn/jrjz-api";
    //    public static final String ABOUT_JURAN = "http://mp.weixin.qq.com/s?__biz=MzIxNDM4OTY3Mw==&mid=100000015&idx=1&sn=8f967c4b219ab2dc69400489b5d1b6b0&scene=18#wechat_redirect";
    public static final String ABOUT_JURAN = "http://jrjz.juran.com.cn/jrjz-api/static/html/AboutUs.html";
    public static final String PROTOCOL = "file:///android_asset/Protocol.html";
    //下载链接
    public static final String DOWNLOAD = "http://a.app.qq.com/o/simple.jsp?pkgname=com.autodesk.easyhome.shejijia";
    public static final String DOWNLOAD_H5 = "http://jrjz.juran.com.cn/JuRanCode/index.html";
//    public static final String DOWNLOAD_H5 = "http://cuohe.damaimob.com/JuRanCode/index.html";


    public static final String Wx_App_Id = "wxfd04ee1c78a46319";

//    /**
//     * 用户信息全局常量
//     */
//
//    public static final String ACCESSTOKEN = "accessToken";
//    public static final String  UID = "uid";
//    public static final String IS_LOGIN = "isLogin";

    //充值页面广播action
    public static final String TOPUP_RECIVER_ACTION = "com.jrjz_project.topup.sendbroadcast";

    public static  boolean WXSuccess = false;

}
