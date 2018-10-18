package com.jeefw.model.sys.param;

import core.support.ExtJSBaseParameter;

/**
 * @Description: 客户信息查询参数
 * @Author: czy
 * @Created Date: 2018-10-17
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public class CustomerParameter extends ExtJSBaseParameter {

    private String sexCn;
    private String statusCn;
    private String $like_customerName;

    public String getSexCn() {
        return sexCn;
    }

    public void setSexCn(String sexCn) {
        this.sexCn = sexCn;
    }

    public String getStatusCn() {
        return statusCn;
    }

    public void setStatusCn(String statusCn) {
        this.statusCn = statusCn;
    }

    public String get$like_customerName() {
        return $like_customerName;
    }

    public void set$like_customerName(String $like_customerName) {
        this.$like_customerName = $like_customerName;
    }
}
