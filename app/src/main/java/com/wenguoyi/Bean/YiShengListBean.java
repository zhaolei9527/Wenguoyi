package com.wenguoyi.Bean;

import java.util.List;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/4
 * 功能描述：
 */
public class YiShengListBean {


    /**
     * status : 1
     * yisheng : [{"id":"3","kid":"7","zid":"2","name":"昝朝元","head":"/Public/uploads/news/2018-05-18/5afeea0301541.jpg","shanchang":"从事临床一线工作多年，擅长诊治荨麻疹 过敏性皮炎 神经性皮炎 体癣 股癣 香港脚 白癜风 系统性红斑狼疮 梅毒 尖锐湿疣 外阴瘙痒 阴道炎 外阴白班病 皮肤瘙痒症 少年白发 脂溢性皮炎等","yiyuan":"成都中医药大学二附院","keshi":"皮肤性病科","zhicheng":"副主任医师"},{"id":"5","kid":"4","zid":"2","name":"李隆文","head":"/Public/uploads/news/2018-05-18/5afeea618dcf2.jpg","shanchang":"内科常见疾病的诊治，另外兼有对祖国医学的认识，可以指导一定程度的养生保健的方法及人生哲学的认识。擅长神经科疾病的诊治，主攻方向是脑血管疾病的诊治及精神心理疾病的诊治","yiyuan":"中山大学第一附属医院","keshi":"神经内科","zhicheng":"副主任医师"},{"id":"4","kid":"1","zid":"2","name":"刘国英","head":"/Public/uploads/news/2018-05-18/5afeea2e0e9ff.jpg","shanchang":"妇产科常见疾病，尤其月经紊乱，更年期综合症，更年期异常出血，妇科肿瘤，孕期保健，孕期相关检查，不孕不育等诊断和治疗。","yiyuan":"承德市第六医院","keshi":"妇产科","zhicheng":"副主任医师"},{"id":"2","kid":"11","zid":"4","name":"何玉宝","head":"/Public/uploads/news/2018-05-18/5afee9cdaac00.png","shanchang":"四肢及脊柱骨折，手外伤，运动损伤，腰肌劳损，肩周炎，股骨头缺血性坏死，骨髓炎，骨关节炎，类风湿性关节炎，痛风，骨肿瘤，扁平足，踇外翻，腰椎间盘突出，颈椎病等骨科常见疾病的诊断和治疗。","yiyuan":"北京市垂杨柳医院","keshi":"骨科","zhicheng":"主治医师"},{"id":"1","kid":"5","zid":"1","name":"谭剑敏","head":"/Public/uploads/news/2018-05-18/5afee952a8c9c.jpg","shanchang":"泌尿外科微创手术及男科疾病、尤其擅长膀胱全切术、肾窦内肾盂切开取石术、输尿管癌行肾、输尿管全切术，肾癌根治术等，能熟练掌握前列腺电切、膀胱肿瘤电切、复杂的输尿管镜下激光碎石等腔内手术。对阳痿、早泄、急慢性前列腺疾病、男性不育症、性功能不全等有丰富的临床经验和治疗方法，均获得良好的治疗效果","yiyuan":"上海长征医院闸北分院","keshi":"泌尿外科","zhicheng":"主任医师"}]
     * fy : 0
     * keshi : [{"id":"1","title":"妇产科"},{"id":"2","title":"儿科"},{"id":"3","title":"综合门诊"},{"id":"4","title":"神经内科"},{"id":"5","title":"泌尿外科"},{"id":"6","title":"中医科"},{"id":"11","title":"骨科"},{"id":"10","title":"其他科室"},{"id":"9","title":"重症医学科"},{"id":"8","title":"康复医学科"},{"id":"7","title":"皮肤性病科"}]
     * zhicheng : [{"id":"1","title":"主任医师"},{"id":"2","title":"副主任医师"},{"id":"3","title":"实习医生"},{"id":"4","title":"主治医师"},{"id":"5","title":"住院医师"},{"id":"6","title":"专家"}]
     */

    private int status;
    private int fy;
    private List<YishengBean> yisheng;
    private List<KeshiBean> keshi;
    private List<ZhichengBean> zhicheng;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFy() {
        return fy;
    }

    public void setFy(int fy) {
        this.fy = fy;
    }

    public List<YishengBean> getYisheng() {
        return yisheng;
    }

    public void setYisheng(List<YishengBean> yisheng) {
        this.yisheng = yisheng;
    }

    public List<KeshiBean> getKeshi() {
        return keshi;
    }

    public void setKeshi(List<KeshiBean> keshi) {
        this.keshi = keshi;
    }

    public List<ZhichengBean> getZhicheng() {
        return zhicheng;
    }

    public void setZhicheng(List<ZhichengBean> zhicheng) {
        this.zhicheng = zhicheng;
    }

    public static class YishengBean {
        /**
         * id : 3
         * kid : 7
         * zid : 2
         * name : 昝朝元
         * head : /Public/uploads/news/2018-05-18/5afeea0301541.jpg
         * shanchang : 从事临床一线工作多年，擅长诊治荨麻疹 过敏性皮炎 神经性皮炎 体癣 股癣 香港脚 白癜风 系统性红斑狼疮 梅毒 尖锐湿疣 外阴瘙痒 阴道炎 外阴白班病 皮肤瘙痒症 少年白发 脂溢性皮炎等
         * yiyuan : 成都中医药大学二附院
         * keshi : 皮肤性病科
         * zhicheng : 副主任医师
         */

        private String id;
        private String kid;
        private String zid;
        private String name;
        private String head;
        private String shanchang;
        private String yiyuan;
        private String keshi;
        private String zhicheng;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKid() {
            return kid;
        }

        public void setKid(String kid) {
            this.kid = kid;
        }

        public String getZid() {
            return zid;
        }

        public void setZid(String zid) {
            this.zid = zid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getShanchang() {
            return shanchang;
        }

        public void setShanchang(String shanchang) {
            this.shanchang = shanchang;
        }

        public String getYiyuan() {
            return yiyuan;
        }

        public void setYiyuan(String yiyuan) {
            this.yiyuan = yiyuan;
        }

        public String getKeshi() {
            return keshi;
        }

        public void setKeshi(String keshi) {
            this.keshi = keshi;
        }

        public String getZhicheng() {
            return zhicheng;
        }

        public void setZhicheng(String zhicheng) {
            this.zhicheng = zhicheng;
        }
    }

    public static class KeshiBean {
        /**
         * id : 1
         * title : 妇产科
         */

        private String id;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class ZhichengBean {
        /**
         * id : 1
         * title : 主任医师
         */

        private String id;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
