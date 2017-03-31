package com.leo618.apf.test;


import com.leo618.apf.base.BaseBean;

/**
 * function:
 *
 * <p></p>
 * Created by lzj on 2017/3/30.
 */
@SuppressWarnings("ALL")
public class PhoneNumBean extends BaseBean {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"prov":"四川","city":"test","name":"test","num":"1370011","provCode":"110000","type":"1"}
     */

    private ShowapiResBodyBean showapi_res_body;

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * prov : 四川
         * city : test
         * name : test
         * num : 1370011
         * provCode : 110000
         * type : 1
         */

        private String prov;
        private String city;
        private String name;
        private String num;
        private String provCode;
        private String type;

        public String getProv() {
            return prov;
        }

        public void setProv(String prov) {
            this.prov = prov;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getProvCode() {
            return provCode;
        }

        public void setProvCode(String provCode) {
            this.provCode = provCode;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "phoneInfo{" +
                    "prov='" + prov + '\'' +
                    ", city='" + city + '\'' +
                    ", name='" + name + '\'' +
                    ", num='" + num + '\'' +
                    ", provCode='" + provCode + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }
}
