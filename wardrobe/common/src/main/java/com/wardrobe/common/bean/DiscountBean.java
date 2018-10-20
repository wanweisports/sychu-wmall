package com.wardrobe.common.bean;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.util.Arith;

/**
 * Created by cxs on 2018/10/17.
 */
public class DiscountBean {

    private double sumOldPrice; //原总金额（打折前原商品总金额）
    private double sumOldDisPrice; //原总金额（打折后原商品总金额）
    private double sumPrice;    //支付总金额（打折后使用衣橱币或者优惠券商品总金额）
    private int useYcoid;
    private double couponPrice;
    private double freight;
    private double discount;

    private int cpid; //优惠券id,使用合法（满多少减正常）的优惠券id
    private double userDiscountSubPrice; //用户折扣减去金额

    public double getSumOldPrice() {
        return sumOldPrice;
    }

    public void setSumOldPrice(double sumOldPrice) {
        this.sumOldPrice = sumOldPrice;
    }

    public double getSumOldDisPrice() {
        return sumOldDisPrice;
    }

    public void setSumOldDisPrice(double sumOldDisPrice) {
        this.sumOldDisPrice = sumOldDisPrice;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public int getUseYcoid() {
        return useYcoid;
    }

    public void setUseYcoid(int useYcoid) {
        this.useYcoid = useYcoid;
    }

    public double getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(double couponPrice) {
        this.couponPrice = couponPrice;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getCpid() {
        return cpid;
    }

    public void setCpid(int cpid) {
        this.cpid = cpid;
    }

    @Desc("计算用户等级折扣减去多少金额")
    public double countUserDiscountSubPrice() {
        return userDiscountSubPrice = Arith.sub(this.sumOldPrice, this.sumOldDisPrice);
    }
}
