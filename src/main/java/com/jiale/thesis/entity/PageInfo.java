package com.jiale.thesis.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageInfo<T> implements Serializable {
//    当前页
    private int pageNum;
//    每页数量
    private int pageSize;
//    总记录数
    private long total;
//    总也数
    private int pages;
//    结果集
    private List<T> List;
//    是否是第一页
//    private boolean isFirstPage = false;
//    是否为最后一页
//    private boolean isLastPage = false;

    public PageInfo(List<T> list){

    }
}


