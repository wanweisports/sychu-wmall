package com.wardrobe.controller;

/**
 * Created by wangjun on 2018/8/16.
 */
public class ProductsTypeSettingsRequest {

    private String category;
    private String style;
    private String material;

    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

    public void setStyle(String style) {
        this.style = style;
    }
    public String getStyle() {
        return style;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
    public String getMaterial() {
        return material;
    }
}
