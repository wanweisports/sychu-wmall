package com.wardrobe.common.view;

import com.wardrobe.common.annotation.GsonExclude;
import com.wardrobe.common.util.StrUtil;

import java.io.Serializable;

public class BaseInputView implements Serializable {

	@GsonExclude
    private int page;

    @GsonExclude
    private int pageSize = 10; //默认每页显示10条

    @GsonExclude
    private String orderBy = StrUtil.EMPTY; //排序sql

    @GsonExclude
    private String groupBy;

    @GsonExclude
    private String operatorId;

    public int getPage() {
        return page;
    }

    public void setPage(Double page) {
        if(page != null) {
            this.page = Double.valueOf(page).intValue();
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(Double pageSize) {
        if(pageSize != null) {
            this.pageSize = Double.valueOf(pageSize).intValue();
        }else{
            this.pageSize = 0;
        }
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

    public static void main(String[] args) {
        System.out.println(StrUtil.getUUID());
    }
    
}