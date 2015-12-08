package databean;

/**
 * Created by caixiao on 2015/11/25 0025.
 */
public class LoginBean {

    public PdEntity pd;
    public boolean success;
    public String key;

    public static class PdEntity {

        /**
         * uid : 10
         * phone : 15350564805
         * score : 0
         * adminid : 0
         * avatar :
         * password : e10adc3949ba59abbe56e057f20f883e
         * levelid : 5
         * spend : 0.0
         * ismobile : false
         * regtime : 1441939341
         * username : gongying
         * regip : 192.168.1.11
         * overdue : 4294967295
         * email : 323242@qq.com
         * freeze : 0.0
         * name :
         * money : 0.0
         * experience : 10
         * randcode : 611076
         * groupid : 4
         * salt : 7fa732b517
         */

        public int uid;
        public String phone;
        public int score;
        public int adminid;
        public String avatar;
        public String password;
        public int levelid;
        public double spend;
        public boolean ismobile;
        public int regtime;
        public String username;
        public String regip;
        public long overdue;
        public String email;
        public double freeze;
        public String name;
        public double money;
        public int experience;
        public int randcode;
        public int groupid;
        public String salt;
    }
}
