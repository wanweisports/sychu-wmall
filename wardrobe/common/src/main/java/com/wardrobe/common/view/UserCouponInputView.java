package com.wardrobe.common.view;

/**
 * Created by 陈小松 on 2018/9/22.
 */
public class UserCouponInputView extends BaseInputView {

    private String scids;
    private String serviceType;
    private Integer cpid;

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
}
