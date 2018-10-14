package com.wardrobe.common.view;

/**
 * Created by 陈小松 on 2018/9/22.
 */
public class UserCouponInputView extends BaseInputView {

    private String scids;
    private String serviceType;
    private Integer cpid;
    private String dbids; //配送ids，多个逗号分隔

    public String getScids() {
        return scids;
    }

    public void setScids(String scids) {
        this.scids = scids;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getCpid() {
        return cpid;
    }

    public void setCpid(Integer cpid) {
        this.cpid = cpid;
    }

    public String getDbids() {
        return dbids;
    }

    public void setDbids(String dbids) {
        this.dbids = dbids;
    }
}
