package com.example.wby.common.response.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(
        description = "列表数据返回内容"
)
public class ListData<T> {
    @ApiModelProperty(
            value = "列表数据",
            example = "[]"
    )
    private List<T> list;
    @ApiModelProperty(
            value = "列表数据总数",
            example = "20"
    )
    private Long total;

    public ListData() {
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}

