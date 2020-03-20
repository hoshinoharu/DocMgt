package com.rehoshi.docmgt.config;


public class PageConfig {

    private int pageIndex = -1;
    private int pageSize = -1;
    private boolean searchCount = false ;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageConfig index(int pageIndex){
        setPageIndex(pageIndex);
        return this ;
    }

    public PageConfig size(int pageSize){
        setPageSize(pageSize);
        return this ;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    /**
     * 是否需要分页
      * @return
     */
    public boolean needPage(){
        return pageIndex >= 0 && pageSize >= 0 ;
    }

    public boolean needPageThenReset(){
        boolean b = needPage();
        this.pageIndex = -1 ;
        this.pageSize = -1 ;
        this.searchCount = false ;
        return  b;
    }
}
