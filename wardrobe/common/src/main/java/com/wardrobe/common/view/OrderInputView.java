package com.wardrobe.common.view;

/**
 * Created by cxs on 2018/8/6.
 */
public class OrderInputView extends BaseInputView {

    private Integer orderId;
    private String orderType; //1：购物  2：充值
    private Integer uid;
    private String status;
    private String ono;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOno() {
        return ono;
    }

    public void setOno(String ono) {
        this.ono = ono;
    }
}
