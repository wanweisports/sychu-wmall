package com.wardrobe.common.view;

/**
 * Created by cxs on 2018/8/24.
 */
public class DeviceInputView extends BaseInputView {

    private Integer did;
    private String type;
    private Integer dbid;

    public DeviceInputView() {
    }

    public DeviceInputView(Integer did) {
        this.did = did;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }
}
