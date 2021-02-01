package com.example.zhugeyouliao.ui.home.bena;

import com.example.zhugeyouliao.bean.BaseBean;

import java.util.Comparator;
import java.util.List;

/**
 * @Author： longyu
 * @Time： 2021/1/30 18:02
 * @description： 红人数据
 */
public class RedPeopleJsonBean extends BaseBean {

    /**
     * total : 261
     * size : 6
     * pages : 44
     * current : 1
     * records : [{"uId":13,"uPhone":"15622120749","uPwd":null,"nickName":"西门大官人","headUrl":"https://img.zhugeyl.com/160a5c13364c4a1bb44f89a3902318c0.jpg","uSole":null,"integral":6931,"uGrade":1,"isBan":0,"uSalt":null,"uToken":null,"lastLoginTime":null,"isGreatGod":2,"createTime":null,"updateTime":null,"beGreatGodTime":null,"falseFansCount":509,"falseRedRate":40,"signature":null,"communityManager":3,"moderatorManager":null,"type":0,"code":null,"flag":false,"msg":null,"fansCount":529,"betCount":0,"winRate":40,"isFollow":0,"collectionCount":2,"remarkCount":25,"articleCount":240,"followCount":2,"hitRate":96,"forecastCount":185,"manager":null},{"uId":374,"uPhone":"15918488437","uPwd":null,"nickName":"大表哥带你玩球","headUrl":"https://img.zhugeyl.com/20-11-08c478e0049d30475eb8f80438074b4e77.jpg-zhuge","uSole":null,"integral":4524,"uGrade":1,"isBan":0,"uSalt":null,"uToken":null,"lastLoginTime":null,"isGreatGod":2,"createTime":null,"updateTime":null,"beGreatGodTime":null,"falseFansCount":402,"falseRedRate":30,"signature":"每天稳定发红单推荐！","communityManager":3,"moderatorManager":null,"type":0,"code":null,"flag":false,"msg":null,"fansCount":410,"betCount":0,"winRate":30,"isFollow":0,"collectionCount":0,"remarkCount":7,"articleCount":0,"followCount":4,"hitRate":83,"forecastCount":937,"manager":null},{"uId":14,"uPhone":"13196589316","uPwd":null,"nickName":"天王盖地虎","headUrl":"https://img.zhugeyl.com/3d8a4f4722cc48b89088b384f7009654.jpg","uSole":null,"integral":2663,"uGrade":1,"isBan":0,"uSalt":null,"uToken":null,"lastLoginTime":null,"isGreatGod":2,"createTime":null,"updateTime":null,"beGreatGodTime":null,"falseFansCount":392,"falseRedRate":40,"signature":null,"communityManager":3,"moderatorManager":null,"type":0,"code":null,"flag":false,"msg":null,"fansCount":402,"betCount":0,"winRate":40,"isFollow":0,"collectionCount":0,"remarkCount":1,"articleCount":79,"followCount":0,"hitRate":94,"forecastCount":53,"manager":null},{"uId":302,"uPhone":"13218765513","uPwd":null,"nickName":"李老八电竞","headUrl":"https://img.zhugeyl.com/880fbe87-9e74-454a-bd29-1bc4146be3a9.png","uSole":null,"integral":6935,"uGrade":1,"isBan":0,"uSalt":null,"uToken":null,"lastLoginTime":null,"isGreatGod":2,"createTime":null,"updateTime":null,"beGreatGodTime":null,"falseFansCount":383,"falseRedRate":11,"signature":"资深体坛分析师，专业的赛事资讯及预测。","communityManager":3,"moderatorManager":null,"type":0,"code":null,"flag":false,"msg":null,"fansCount":394,"betCount":0,"winRate":11,"isFollow":0,"collectionCount":0,"remarkCount":1,"articleCount":59,"followCount":0,"hitRate":77,"forecastCount":61,"manager":null},{"uId":318,"uPhone":"18824933507","uPwd":null,"nickName":"明明看球","headUrl":"https://img.zhugeyl.com/20-11-0692bd3f61118746dda36482a585cc3355.jpg-zhuge","uSole":null,"integral":4879,"uGrade":1,"isBan":0,"uSalt":null,"uToken":null,"lastLoginTime":null,"isGreatGod":2,"createTime":null,"updateTime":null,"beGreatGodTime":"2020-10-03T10:11:49.000+0000","falseFansCount":346,"falseRedRate":0,"signature":"统计NBA百家欧赔，体彩分析，做最专业的数据.","communityManager":3,"moderatorManager":null,"type":0,"code":null,"flag":false,"msg":null,"fansCount":352,"betCount":0,"winRate":0,"isFollow":0,"collectionCount":0,"remarkCount":0,"articleCount":4,"followCount":0,"hitRate":57,"forecastCount":132,"manager":null},{"uId":17,"uPhone":"13433893292","uPwd":null,"nickName":"明哥","headUrl":"https://img.zhugeyl.com/1b57d08c30e846c392dedffdfa1820f0.jpg","uSole":null,"integral":2597,"uGrade":1,"isBan":0,"uSalt":null,"uToken":null,"lastLoginTime":null,"isGreatGod":2,"createTime":null,"updateTime":null,"beGreatGodTime":null,"falseFansCount":336,"falseRedRate":37,"signature":"厉害的很","communityManager":1,"moderatorManager":null,"type":0,"code":null,"flag":false,"msg":null,"fansCount":347,"betCount":17,"winRate":78,"isFollow":0,"collectionCount":23,"remarkCount":16,"articleCount":108,"followCount":8,"hitRate":82,"forecastCount":104,"manager":null}]
     */

    private int total;
    private int size;
    private int pages;
    private int current;
    /**
     * uId : 13
     * uPhone : 15622120749
     * uPwd : null
     * nickName : 西门大官人
     * headUrl : https://img.zhugeyl.com/160a5c13364c4a1bb44f89a3902318c0.jpg
     * uSole : null
     * integral : 6931
     * uGrade : 1
     * isBan : 0
     * uSalt : null
     * uToken : null
     * lastLoginTime : null
     * isGreatGod : 2
     * createTime : null
     * updateTime : null
     * beGreatGodTime : null
     * falseFansCount : 509
     * falseRedRate : 40
     * signature : null
     * communityManager : 3
     * moderatorManager : null
     * type : 0
     * code : null
     * flag : false
     * msg : null
     * fansCount : 529
     * betCount : 0
     * winRate : 40
     * isFollow : 0
     * collectionCount : 2
     * remarkCount : 25
     * articleCount : 240
     * followCount : 2
     * hitRate : 96
     * forecastCount : 185
     * manager : null
     */

    private List<RedPeopleListBean> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public List<RedPeopleListBean> getRecords() {
        return records;
    }

    public void setRecords(List<RedPeopleListBean> records) {
        this.records = records;
    }
}
