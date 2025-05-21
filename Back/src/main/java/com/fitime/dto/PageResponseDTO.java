package com.fitime.dto;

import java.util.List;

public class PageResponseDTO<T> {

    private List<T> list;
    private int totalCount;
    private int totalPage;
    private int currentPage;

    public PageResponseDTO() {}

    public PageResponseDTO(List<T> list, int totalCount, int pageSize, int currentPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.totalPage = (pageSize > 0) ? (int) Math.ceil((double) totalCount / pageSize) : 0;
        this.currentPage = currentPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
