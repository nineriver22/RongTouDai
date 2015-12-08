package databean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by caixiao on 2015/8/1 0001.
 */
public class ProjectBean implements Serializable {

    public List<VarlistEntity> varlist;
    public String imgPath;
    public int sum;

    public static class VarlistEntity {

        public int id;
        public String title;
        public int hits;
        public String keywords;
        public int status;
        public int stage;
        public int extend;
        public String attachment;
        public String thumb;
        public String endtime;
    }
}
