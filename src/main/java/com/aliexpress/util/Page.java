package com.aliexpress.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<T> implements Serializable {
    private int pageNum;
    private int pageSize = 15;
    private int startRow;
    private int endRow;
    private int total;
    private int pages = 1;
    private int prePage;
    private int nextPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int navigatePages;
    private List<Integer> navigatepageNums;
    private int navigateFirstPage;
    private int navigateLastPage;
    private int firstPage;
    private int lastPage;
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public void generate(int pages, int pageSize, int total, List<T> result) {
        this.pages = pages;
        this.pageSize = pageSize;
        this.total = total;
        this.list = result;
        this.pageNum = pages;

        this.firstPage = 1;
        pageSize = pageSize == 0 ? 15 : pageSize;
        this.lastPage = (int) Math.ceil((double) total / pageSize);

        this.isFirstPage = this.firstPage == this.pageNum;
        this.isLastPage = this.lastPage == this.pageNum;
        this.hasPreviousPage = this.pageNum>this.firstPage&&this.pageNum < this.lastPage;
        this.hasNextPage = this.pageNum < this.lastPage;

        this.prePage = this.hasPreviousPage ? this.pageNum - 1 : 1;
        this.nextPage = this.hasNextPage ? this.pageNum + 1 : this.lastPage;

        this.startRow = (this.pageNum - 1) * this.pageSize + 1;
        this.endRow = this.pageNum * this.pageSize;

        this.navigatePages = (int) Math.ceil((double) total / pageSize);
        this.navigateFirstPage = 1;
        this.navigateLastPage = lastPage;
        this.navigatepageNums = new ArrayList<>();
        for (int i = 1; i <= this.navigatePages; i++) {
            navigatepageNums.add(i);
        }
    }
}
