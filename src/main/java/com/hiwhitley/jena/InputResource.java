package com.hiwhitley.jena;

/**
 * Created by hiwhitley on 16-11-22.
 */
public class InputResource {

    /**
     * _id : {"$oid":"58331135581f5214d9269ccb"}
     * tel : 0571-86786210
     * shop_name : 山葵家精致料理(武林中山店)
     * taste : 9.
     * avePerPerson : 156
     * recommend : 三文鱼刺身,秋天童话,蝴蝶卷,芒果鹅肝手握,天妇罗炸大虾,三文鱼,烤鱼沙拉,牛油果反卷,汤汁炸豆腐,三文鱼炒饭,
     * address : 中山北路403-407号
     */

    private IdBean _id;
    private String tel;
    private String shop_name;
    private String taste;
    private String avePerPerson;
    private String recommend;
    private String address;

    public IdBean get_id() {
        return _id;
    }

    public void set_id(IdBean _id) {
        this._id = _id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getAvePerPerson() {
        return avePerPerson;
    }

    public void setAvePerPerson(String avePerPerson) {
        this.avePerPerson = avePerPerson;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static class IdBean {
        /**
         * $oid : 58331135581f5214d9269ccb
         */

        private String $oid;

        public String get$oid() {
            return $oid;
        }

        public void set$oid(String $oid) {
            this.$oid = $oid;
        }
    }
}
