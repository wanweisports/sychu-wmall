package com.wardrobe.common.view;

/**
 * Created by cxs on 2018/8/24.
 */
public class DeviceInputView extends BaseInputView {

    private Integer did;

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
}
