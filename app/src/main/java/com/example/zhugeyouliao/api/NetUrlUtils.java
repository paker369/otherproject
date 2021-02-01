package com.example.zhugeyouliao.api;

import com.example.zhugeyouliao.utils.StringUtils;

/**
 * URL地址
 */
public class NetUrlUtils {

    //该项目接口命名，根据后台接口的真实地址，全参数命名
    public static String BASEURL = "https://zhugeyl.com/api";
//    public static String BASEURL = "http://8.134.18.154:8095";

    /**
     * 图片地址拼接
     *
     * @param srcUrl
     * @return
     */
    public static String createPhotoUrl(String srcUrl) {
        if (StringUtils.isEmpty(srcUrl)) {
            return "";
        }
        if (srcUrl.startsWith("http")) {
            return srcUrl;
        } else {
            return BASEURL + srcUrl;
        }
    }

    //发送验证码
    public static String SEND_PHONE_CODE = BASEURL + "/user/sendPhoneCode";
    //注册
    public static String REGISTER_USER = BASEURL + "/user/registerUser";
    //登录
    public static String LOGIN = BASEURL + "/user/login";


    //首页轮播图
    public static String HOME_BANNER = BASEURL + "/backstageCarousel/getCarouseList";
    //首页热门比赛
    public static String HOME_HOT_CONTEST = BASEURL + "/greatForecast/getOrdinaryByLike";
    //首页
    public static String HOME_RECOMMEND = BASEURL + "/greatForecast/getRecommendByRead";
    //足球列表
    public static String FOOTBALL = BASEURL + "/football/getFootballAllStatus";
    //篮球列表
    public static String BASKETBALL = BASEURL + "/basketball/getBasketballAllStatus";
    //电竞列表
    public static String COMPUTER = BASEURL + "/computerMatch/getComputerAllStatus";
    //足球详情-历史交锋
    public static String GET_TEAM_BATTLE = BASEURL + "/football/getTeamsBattle";
    //足球详情-历史记录
    public static String GET_FOOTBALL_TEAM_MATCH_HISTORY = BASEURL + "/football/getTeamMatchHistory";
    //足球详情-获取该场比赛的文字直播
    public static String GET_FOOTBALL_WORD_LIVE = BASEURL + "/football/getFootballWordLive";
    //足球详情-赛果预测
    public static String GET_CREATE_FORECAST_LIST = BASEURL + "/greatForecast/getGreatForecastList";
    //足球详情-比赛视频
    public static String GET_URL_LIVE = BASEURL + "/liveUrl/getUrlLive";

    //篮球详情- 诸葛密料-历史记录
    public static String GET_BASEKETBALL_TEAM_MATCH_HISTORY = BASEURL + "/basketball/getTeamMatchHistory";
    //电子竞技详情- 诸葛密料-历史记录
    public static String GET_ELECTRONIC_SPORTS = BASEURL + "/computerMatch/getComputerMatchBattleList";
    //电子竞技详情- 诸葛密料-历史记录
    public static String GET_ELECTRONIC_SPORTS_1 = BASEURL + "/computerMatch/getComputerMatchHistoryList";
    //红人- 获取大神列表
    public static String GET_CREATE_GOD_LIST = BASEURL + "/user/getGreatGodList";

}
