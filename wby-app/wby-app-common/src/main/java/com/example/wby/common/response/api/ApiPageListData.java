package com.example.wby.common.response.api;

import java.util.List;

public class ApiPageListData<T> {
    private Integer pageNo;
    private Integer pageSize;
    private Boolean hasNext;
    private Integer total;
    private List<T> list;
    private Integer totalPage;

    public ApiPageListData() {
    }

    public Integer getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getHasNext() {
        if (this.total != null && this.pageNo != null && this.pageSize != null) {
            Double num = Math.ceil((double)this.total * 1.0D / (double)this.pageSize);
            return this.pageNo < num.intValue() ? true : false;
        } else {
            return false;
        }
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}

