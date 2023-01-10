package model;

import java.io.Serializable;

//class for list database record in multiple pages
public class Page implements Serializable
{
    private static final long serialVersionUID = -2213069645383858323L;
    private int pageNow = 1; // current page
    private int pageSize = 4; // number of record list in single page
    private int totalCount; // overall number of record
    private int totalPageCount; // overall number of page
    private int startPos; // start position
    private boolean hasFirst;// check if current is first page
    private boolean hasPre;//check if has previous page
    private boolean hasNext;// check if has next page
    private boolean hasLast;// check if current is last page


    public Page(int totalCount, int pageNow) {
        this.totalCount = totalCount;
        this.pageNow = pageNow;
    }


    public int getTotalPageCount() {
        totalPageCount = getTotalCount() / getPageSize();
        return (totalCount % pageSize == 0) ? totalPageCount : totalPageCount + 1;
    }
    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }
    public int getPageNow() {
        return pageNow;
    }
    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


    public int getStartPos() {
        return (pageNow - 1) * pageSize;
    }
    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    public boolean isHasFirst() {
        return (pageNow == 1) ? false : true;
    }
    public void setHasFirst(boolean hasFirst) {
        this.hasFirst = hasFirst;
    }

    public boolean isHasPre() {
        return isHasFirst() ? true : false;
    }
    public void setHasPre(boolean hasPre) {
        this.hasPre = hasPre;
    }

    public boolean isHasNext() {
        // 如果有尾页就有下一页，因为有尾页表明不是最后一页
        return isHasLast() ? true : false;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasLast() {
        // 如果不是最后一页就有尾页
        return (pageNow == getTotalCount()) ? false : true;
    }

    public void setHasLast(boolean hasLast) {
        this.hasLast = hasLast;
    }
}
