package com.example.zhugeyouliao.ui.home.bena;

import com.example.zhugeyouliao.bean.BaseBean;

import java.util.List;

/**
 * @Author： longyu
 * @Time： 2021/1/14 10:28
 * @description： 首页推荐热门比赛
 */
public class RecommendHotBean extends BaseBean {

    /**
     * total : 1912
     * current : 1
     * pages : 638
     * size : 3
     * records : [{"matchLogo":"https://cdn.sportnanoapi.com/football/competition/63ca729248267615f4b039277e8e92b4.png","headUrl":"https://img.zhugeyl.com/1b57d08c30e846c392dedffdfa1820f0.jpg","matchShortName":"阿联酋超","likeCount":0,"type":1,"falseFansCount":336,"buyIntegral":1,"matchTypeStatus":8,"nickName":"明哥","hitRate":83,"falseRedRate":37,"isAttention":0,"matchScore":"0-0","winner":"平局","namiId":3442829,"gfTitle":"312312","collectionCount":0,"matchStartTimeStr":"2021-01-01 00:00:00","winTeamName":"伊蒂哈德卡尔巴","bTeamLogo":"https://cdn.sportnanoapi.com/football/team/f03551c825eb2cebb3ae09a4a40afdad.png","isLike":0,"signature":"厉害的很","aTeamId":20384,"sizeBall":"6.25,0.5,0.02,0","isExist":0,"matchId":"3442829","gfId":3859,"asianPlate":"0.92,0,0.96,0","matchStartTime":"2020-12-31T16:00:00.000+0000","bTeamId":21309,"gfPic":"https://img.zhugeyl.com/847f95e69d73479c9eef9669ad4e06bb-headMoRen.png","uId":17,"aTeamShortName":"伊蒂哈德卡尔巴","isRight":2,"createTime":"2020-12-31 21:13:13","euroloss":"19.0,1.01,19.0,0","winTeamId":20384,"aTeamLogo":"https://cdn.sportnanoapi.com/football/team/8f819f5b20638cba3620639263ac347f.png","bTeamShortName":"哈塔","lookCount":4},{"matchLogo":"https://cdn.sportnanoapi.com/football/competition/025795f2104cd8233c5d5c69f7e3a2e1.png","headUrl":"https://img.zhugeyl.com/1b57d08c30e846c392dedffdfa1820f0.jpg","matchShortName":"约旦甲","likeCount":0,"type":1,"falseFansCount":336,"buyIntegral":1,"matchTypeStatus":8,"nickName":"明哥","hitRate":83,"falseRedRate":37,"isAttention":0,"matchScore":"2-2","winner":"平局","namiId":3490179,"gfTitle":"撒打算打算打算打算的撒的","collectionCount":0,"matchStartTimeStr":"2020-12-31 20:10:00","winTeamName":"伊蒂哈德连姆沙","bTeamLogo":"https://cdn.sportnanoapi.com/football/team/95176c42d7c94e91d770f99356edf75f.png","isLike":0,"signature":"厉害的很","aTeamId":16360,"sizeBall":"10.44,4.5,0.03,0","isExist":0,"matchId":"3490179","gfId":3858,"asianPlate":"0.78,0,1.06,0","matchStartTime":"2020-12-31T12:10:00.000+0000","bTeamId":31669,"gfPic":"https://img.zhugeyl.com/847f95e69d73479c9eef9669ad4e06bb-headMoRen.png","uId":17,"aTeamShortName":"伊蒂哈德连姆沙","isRight":2,"createTime":"2020-12-31 20:07:38","euroloss":"18.25,1.01,18.25,0","winTeamId":16360,"aTeamLogo":"https://cdn.sportnanoapi.com/football/team/f7a85f1573b61bdcbae9ca8ada637c92.png","bTeamShortName":"巴拉艾玛","lookCount":1},{"matchLogo":"https://cdn.sportnanoapi.com/football/competition/d09a3b1ab92ce378a5277b79a98dac59.png","headUrl":"https://img.zhugeyl.com/1b57d08c30e846c392dedffdfa1820f0.jpg","matchShortName":"土甲","likeCount":0,"type":1,"falseFansCount":336,"buyIntegral":1,"matchTypeStatus":1,"nickName":"明哥","hitRate":83,"falseRedRate":37,"isAttention":0,"matchScore":"0-0","namiId":3436240,"gfTitle":"1","collectionCount":0,"matchStartTimeStr":"2020-12-27 00:00:00","winTeamName":"平局","bTeamLogo":"https://cdn.sportnanoapi.com/football/team/70e033e4c70751dc2273c39d413afad0.png","isLike":0,"signature":"厉害的很","aTeamId":21075,"isExist":0,"matchId":"3436240","gfId":3839,"matchStartTime":"2020-12-26T16:00:00.000+0000","bTeamId":19620,"gfPic":"https://img.zhugeyl.com/847f95e69d73479c9eef9669ad4e06bb-headMoRen.png","uId":17,"aTeamShortName":"巴里科斯士邦","isRight":0,"createTime":"2020-12-27 14:46:57","winTeamId":0,"aTeamLogo":"https://cdn.sportnanoapi.com/football/team/15756c350dae3457b2718a29dc0018cb.png","bTeamShortName":"阿达纳迪美斯普","lookCount":1}]
     */

    private int total;
    private int current;
    private int pages;
    private int size;
    /**
     * matchLogo : https://cdn.sportnanoapi.com/football/competition/63ca729248267615f4b039277e8e92b4.png
     * headUrl : https://img.zhugeyl.com/1b57d08c30e846c392dedffdfa1820f0.jpg
     * matchShortName : 阿联酋超
     * likeCount : 0
     * type : 1
     * falseFansCount : 336
     * buyIntegral : 1
     * matchTypeStatus : 8
     * nickName : 明哥
     * hitRate : 83
     * falseRedRate : 37
     * isAttention : 0
     * matchScore : 0-0
     * winner : 平局
     * namiId : 3442829
     * gfTitle : 312312
     * collectionCount : 0
     * matchStartTimeStr : 2021-01-01 00:00:00
     * winTeamName : 伊蒂哈德卡尔巴
     * bTeamLogo : https://cdn.sportnanoapi.com/football/team/f03551c825eb2cebb3ae09a4a40afdad.png
     * isLike : 0
     * signature : 厉害的很
     * aTeamId : 20384
     * sizeBall : 6.25,0.5,0.02,0
     * isExist : 0
     * matchId : 3442829
     * gfId : 3859
     * asianPlate : 0.92,0,0.96,0
     * matchStartTime : 2020-12-31T16:00:00.000+0000
     * bTeamId : 21309
     * gfPic : https://img.zhugeyl.com/847f95e69d73479c9eef9669ad4e06bb-headMoRen.png
     * uId : 17
     * aTeamShortName : 伊蒂哈德卡尔巴
     * isRight : 2
     * createTime : 2020-12-31 21:13:13
     * euroloss : 19.0,1.01,19.0,0
     * winTeamId : 20384
     * aTeamLogo : https://cdn.sportnanoapi.com/football/team/8f819f5b20638cba3620639263ac347f.png
     * bTeamShortName : 哈塔
     * lookCount : 4
     */

    private List<RecordsBean> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean extends BaseBean{
        private String matchLogo;
        private String headUrl;
        private String matchShortName;
        private int likeCount;
        private int type;
        private int falseFansCount;
        private int buyIntegral;
        private int matchTypeStatus;
        private String nickName;
        private int hitRate;
        private int falseRedRate;
        private int isAttention;
        private String matchScore;
        private String winner;
        private int namiId;
        private String gfTitle;
        private int collectionCount;
        private String matchStartTimeStr;
        private String winTeamName;
        private String bTeamLogo;
        private int isLike;
        private String signature;
        private int aTeamId;
        private String sizeBall;
        private int isExist;
        private String matchId;
        private int gfId;
        private String asianPlate;
        private String matchStartTime;
        private int bTeamId;
        private String gfPic;
        private int uId;
        private String aTeamShortName;
        private int isRight;
        private String createTime;
        private String euroloss;
        private int winTeamId;
        private String aTeamLogo;
        private String bTeamShortName;
        private int lookCount;

        public String getMatchLogo() {
            return matchLogo;
        }

        public void setMatchLogo(String matchLogo) {
            this.matchLogo = matchLogo;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public String getMatchShortName() {
            return matchShortName;
        }

        public void setMatchShortName(String matchShortName) {
            this.matchShortName = matchShortName;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getFalseFansCount() {
            return falseFansCount;
        }

        public void setFalseFansCount(int falseFansCount) {
            this.falseFansCount = falseFansCount;
        }

        public int getBuyIntegral() {
            return buyIntegral;
        }

        public void setBuyIntegral(int buyIntegral) {
            this.buyIntegral = buyIntegral;
        }

        public int getMatchTypeStatus() {
            return matchTypeStatus;
        }

        public void setMatchTypeStatus(int matchTypeStatus) {
            this.matchTypeStatus = matchTypeStatus;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getHitRate() {
            return hitRate;
        }

        public void setHitRate(int hitRate) {
            this.hitRate = hitRate;
        }

        public int getFalseRedRate() {
            return falseRedRate;
        }

        public void setFalseRedRate(int falseRedRate) {
            this.falseRedRate = falseRedRate;
        }

        public int getIsAttention() {
            return isAttention;
        }

        public void setIsAttention(int isAttention) {
            this.isAttention = isAttention;
        }

        public String getMatchScore() {
            return matchScore;
        }

        public void setMatchScore(String matchScore) {
            this.matchScore = matchScore;
        }

        public String getWinner() {
            return winner;
        }

        public void setWinner(String winner) {
            this.winner = winner;
        }

        public int getNamiId() {
            return namiId;
        }

        public void setNamiId(int namiId) {
            this.namiId = namiId;
        }

        public String getGfTitle() {
            return gfTitle;
        }

        public void setGfTitle(String gfTitle) {
            this.gfTitle = gfTitle;
        }

        public int getCollectionCount() {
            return collectionCount;
        }

        public void setCollectionCount(int collectionCount) {
            this.collectionCount = collectionCount;
        }

        public String getMatchStartTimeStr() {
            return matchStartTimeStr;
        }

        public void setMatchStartTimeStr(String matchStartTimeStr) {
            this.matchStartTimeStr = matchStartTimeStr;
        }

        public String getWinTeamName() {
            return winTeamName;
        }

        public void setWinTeamName(String winTeamName) {
            this.winTeamName = winTeamName;
        }

        public String getBTeamLogo() {
            return bTeamLogo;
        }

        public void setBTeamLogo(String bTeamLogo) {
            this.bTeamLogo = bTeamLogo;
        }

        public int getIsLike() {
            return isLike;
        }

        public void setIsLike(int isLike) {
            this.isLike = isLike;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getATeamId() {
            return aTeamId;
        }

        public void setATeamId(int aTeamId) {
            this.aTeamId = aTeamId;
        }

        public String getSizeBall() {
            return sizeBall;
        }

        public void setSizeBall(String sizeBall) {
            this.sizeBall = sizeBall;
        }

        public int getIsExist() {
            return isExist;
        }

        public void setIsExist(int isExist) {
            this.isExist = isExist;
        }

        public String getMatchId() {
            return matchId;
        }

        public void setMatchId(String matchId) {
            this.matchId = matchId;
        }

        public int getGfId() {
            return gfId;
        }

        public void setGfId(int gfId) {
            this.gfId = gfId;
        }

        public String getAsianPlate() {
            return asianPlate;
        }

        public void setAsianPlate(String asianPlate) {
            this.asianPlate = asianPlate;
        }

        public String getMatchStartTime() {
            return matchStartTime;
        }

        public void setMatchStartTime(String matchStartTime) {
            this.matchStartTime = matchStartTime;
        }

        public int getBTeamId() {
            return bTeamId;
        }

        public void setBTeamId(int bTeamId) {
            this.bTeamId = bTeamId;
        }

        public String getGfPic() {
            return gfPic;
        }

        public void setGfPic(String gfPic) {
            this.gfPic = gfPic;
        }

        public int getUId() {
            return uId;
        }

        public void setUId(int uId) {
            this.uId = uId;
        }

        public String getATeamShortName() {
            return aTeamShortName;
        }

        public void setATeamShortName(String aTeamShortName) {
            this.aTeamShortName = aTeamShortName;
        }

        public int getIsRight() {
            return isRight;
        }

        public void setIsRight(int isRight) {
            this.isRight = isRight;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getEuroloss() {
            return euroloss;
        }

        public void setEuroloss(String euroloss) {
            this.euroloss = euroloss;
        }

        public int getWinTeamId() {
            return winTeamId;
        }

        public void setWinTeamId(int winTeamId) {
            this.winTeamId = winTeamId;
        }

        public String getATeamLogo() {
            return aTeamLogo;
        }

        public void setATeamLogo(String aTeamLogo) {
            this.aTeamLogo = aTeamLogo;
        }

        public String getBTeamShortName() {
            return bTeamShortName;
        }

        public void setBTeamShortName(String bTeamShortName) {
            this.bTeamShortName = bTeamShortName;
        }

        public int getLookCount() {
            return lookCount;
        }

        public void setLookCount(int lookCount) {
            this.lookCount = lookCount;
        }
    }
}
