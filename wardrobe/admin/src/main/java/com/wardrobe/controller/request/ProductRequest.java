package com.wardrobe.controller.request;

import java.math.BigDecimal;

/**
 * Created by wangjun on 2018/8/21.
 */
public class ProductRequest {

    private int cid;
    private String commName;
    private String category;
    private String style;
    private String material;
    private String productDesc;
    private BigDecimal price;
    private BigDecimal couPrice;
    private Integer saleCount;
    private String status;
    private String hot;
    private String newly;
    private Integer groupId;
    private String commNo;
    private String brandName;

    private int coid;
    private String colorName;

    private String size;
    private String stock;

    public int getCid() {
        return cid;
    }
    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCommName() {
        return commName;
    }
    public void setCommName(String commName) {
        this.commName = commName;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getStyle() {
        return style;
    }
    public void setStyle(String style) {
        this.style = style;
    }

    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }

    public String getProductDesc() {
        return productDesc;
    }
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCouPrice() {
        return couPrice;
    }
    public void setCouPrice(BigDecimal couPrice) {
        this.couPrice = couPrice;
    }

    public Integer getSaleCount() {
        return saleCount;
    }
    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getHot() {
        return hot;
    }
    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getNewly() {
        return newly;
    }
    public void setNewly(String newly) {
        this.newly = newly;
    }

    public Integer getGroupId() {
        return groupId;
    }
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getCommNo() {
        return commNo;
    }

    public void setCommNo(String commNo) {
        this.commNo = commNo;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getCoid() {
        return coid;
    }
    public void setCoid(int coid) {
        this.coid = coid;
    }

    public String getColorName() {
        return colorName;
    }
    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public String getStock() {
        return stock;
    }
    public void setStock(String stock) {
        this.stock = stock;
    }
}
