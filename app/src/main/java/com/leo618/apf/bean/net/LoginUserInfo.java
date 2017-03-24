package com.leo618.apf.bean.net;


import com.leo618.apf.base.BaseBean;

/**
 * function : 登录用户信息数据模式对象
 *
 * <p>Created by lzj on 2016/1/28.<p/>
 */
@SuppressWarnings("unused")
public class LoginUserInfo extends BaseBean {
    /**
     * account_address :
     * account_email :
     * account_icon : http://www.simuwang.com/ucenter/image/new_logo.png
     * account_id : 121752
     * account_name : u_470210790602
     * account_phone : 15999590943
     */

    private DataBean data;
    /**
     * data : {"account_address":"","account_email":"","account_icon":"http://www.simuwang.com/ucenter/image/new_logo.png","account_id":"121752","account_name":"u_470210790602","account_phone":"15999590943"}
     * token : kqiiQKkrzJzNAe2NlN6iQRNlUjTKDoHLAv9jeb3IAuALZ6rYNF3KU5okpEpBPV6uyHjSCWpNuj2Ug3WMba1Ur596ALckoXMSiiFRf2RoGOVJ0JJW6WTiBQAjBkwGPlp+AiyAK7S3vDKfxVGyqwZRiJ9kLtu2Fo2erUxdpCzfZ4s=
     */

    private String token;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private URLParsmsBean url_param;

    public URLParsmsBean getUrl_param() {
        return url_param;
    }

    public void setUrl_param(URLParsmsBean url_param) {
        this.url_param = url_param;
    }

    public static class DataBean {
        private String account_address;
        private String account_email;
        private String account_icon;
        private String account_id;
        private String account_name;
        private String account_phone;
        private boolean force_status;
        private String force_style;

        public String getForce_style() {
            return force_style;
        }

        public void setForce_style(String force_style) {
            this.force_style = force_style;
        }

        public boolean isForce_status() {
            return force_status;
        }

        public void setForce_status(boolean force_status) {
            this.force_status = force_status;
        }

        public String getAccount_address() {
            return account_address;
        }

        public void setAccount_address(String account_address) {
            this.account_address = account_address;
        }

        public String getAccount_email() {
            return account_email;
        }

        public void setAccount_email(String account_email) {
            this.account_email = account_email;
        }

        public String getAccount_icon() {
            return account_icon;
        }

        public void setAccount_icon(String account_icon) {
            this.account_icon = account_icon;
        }

        public String getAccount_id() {
            return account_id;
        }

        public void setAccount_id(String account_id) {
            this.account_id = account_id;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }

        public String getAccount_phone() {
            return account_phone;
        }

        public void setAccount_phone(String account_phone) {
            this.account_phone = account_phone;
        }
    }

    public static class URLParsmsBean{
        private String pp_account;
        private String pp_token;

        public String getPp_account() {
            return pp_account;
        }

        public void setPp_account(String pp_account) {
            this.pp_account = pp_account;
        }

        public String getPp_token() {
            return pp_token;
        }

        public void setPp_token(String pp_token) {
            this.pp_token = pp_token;
        }
    }

}
