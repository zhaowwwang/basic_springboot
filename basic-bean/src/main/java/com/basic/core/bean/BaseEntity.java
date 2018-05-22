package com.basic.core.bean;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: wangzw
 * @Date: 2017/8/24 9:44
 * @Description: 所有实体的基类
 * @Version: 1.0
 */
public class BaseEntity implements Serializable{
    private static final long serialVersionUID = -4657391917290885761L;

    public final static int DEFAULT_PAGE_No = 1;
    public final static int DEFAULT_PAGE_SIZE = 10;

    private Integer pageNo = DEFAULT_PAGE_No;
    private Integer pageSize = DEFAULT_PAGE_SIZE;
    private String startTime;
    private String endTime;

    private Date createTime;
    private Date updateTime;

    @JsonIgnore
    public Integer getPageNo() {
        return pageNo;
    }

    @JsonProperty
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    @JsonIgnore
    public Integer getPageSize() {
        return pageSize;
    }

    @JsonProperty
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @JsonIgnore
    public int getStartOfPage() {
        // 页数是从第一页是从1开始计算的
        if (pageNo == 0) {
            pageNo = DEFAULT_PAGE_No;
        }
        return (pageNo - 1) * pageSize;
    }

    @JsonIgnore
    public String getStartTime() {
        return startTime;
    }

    @JsonProperty
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @JsonIgnore
    public String getEndTime() {
        return endTime;
    }

    @JsonProperty
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
