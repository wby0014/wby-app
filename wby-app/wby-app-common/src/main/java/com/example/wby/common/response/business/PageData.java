package com.example.wby.common.response.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
        description = "分页数据返回内容"
)
public class PageData<T> extends ListData<T> {
    @ApiModelProperty(
            value = "当前页码",
            example = "1"
    )
    private Integer pageNo;
    @ApiModelProperty(
            value = "每页大小",
            example = "20"
    )
    private Integer pageSize;
    @ApiModelProperty(
            value = "总页数",
            example = "2"
    )
    private Long totalPage;

    public PageData() {
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

    public Long getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }
}

