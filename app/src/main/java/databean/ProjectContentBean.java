package databean;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/8/31 0031.
 */
public class ProjectContentBean implements Serializable {

    public DataEntity data;

    public static class DataEntity {

        /**
         * progress : 土建已招标，准备施工。业主介绍说该项目2015年上半年完工。2016年春节后开始装修公开招标，意向单位可关注。
         * endtime : 2016年09月
         * startime : 2015年09月
         * introduce : 成都东方电气集团东方锅炉股份有限公司研发基地，兴建研发中心，七层建筑，总面积约为38170平方米
         * projtype : 设备
         * engineering : 内外装修
         * stage : 设计阶段
         * sources : 自筹资金
         * investment : 4563000元
         * xiangmuleibie : 3
         * expertise : 精装设计
         * title : 公共租赁住房建设三期工程
         * area : 38170平方米
         * facility : 空调通风设备、配电设备、消防设备、照明系统、保温材料、防火防水材料等
         * address : 裕华区裕华东路45号
         * pnumber : HFH-20150831-GC
         * thumb : 201508/9e177473c2.jpg
         * zone : 石家庄市
         */

        public String progress;
        public String endtime;
        public String startime;
        public String introduce;
        public String projtype;
        public String engineering;
        public String stage;
        public String sources;
        public String investment;
        public int xiangmuleibie;
        public String expertise;
        public String title;
        public String area;
        public String facility;
        public String address;
        public String pnumber;
        public String thumb;
        public String zone;
    }
}
