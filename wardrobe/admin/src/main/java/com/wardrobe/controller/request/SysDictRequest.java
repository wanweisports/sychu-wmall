package com.wardrobe.controller.request;

/**
 * Created by wangjun on 2018/8/21.
 */
public class SysDictRequest {
    private Integer dictId;
    private String dictValue;

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }
    public Integer getDictId() {
        return dictId;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }
    public String getDictValue() {
        return dictValue;
    }
}
