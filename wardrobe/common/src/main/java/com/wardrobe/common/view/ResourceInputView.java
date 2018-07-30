package com.wardrobe.common.view;

/**
 * Created by Administrator on 2018/1/12.
 */
public class ResourceInputView extends BaseInputView {

    private Integer resourceId;

    private int resourceServiceId;

    private String resourceServiceType;

    private String resourceType;

    public ResourceInputView(){

    }

    public ResourceInputView(int resourceServiceId, String resourceServiceType){
        this.resourceServiceId = resourceServiceId;
        this.resourceServiceType = resourceServiceType;
    }

    public ResourceInputView(int resourceServiceId, String resourceServiceType, String resourceType){
        this.resourceServiceId = resourceServiceId;
        this.resourceServiceType = resourceServiceType;
        this.resourceType = resourceType;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public int getResourceServiceId() {
        return resourceServiceId;
    }

    public void setResourceServiceId(int resourceServiceId) {
        this.resourceServiceId = resourceServiceId;
    }

    public String getResourceServiceType() {
        return resourceServiceType;
    }

    public void setResourceServiceType(String resourceServiceType) {
        this.resourceServiceType = resourceServiceType;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
}
