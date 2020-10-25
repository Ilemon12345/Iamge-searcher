package com.agileengine.api.imagestorage;

import java.util.List;
import java.util.Objects;

/**
 * @author yevhenii yurkov
 * @since 0.0.1
 */
public class PictureListApi {

    private List<PictureApi> pictures;
    private int page;
    private int pageCount;
    private boolean hasMore;

    public List<PictureApi> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureApi> pictures) {
        this.pictures = pictures;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PictureListApi that = (PictureListApi) o;
        return page == that.page &&
                pageCount == that.pageCount &&
                hasMore == that.hasMore &&
                Objects.equals(pictures, that.pictures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pictures, page, pageCount, hasMore);
    }

    @Override
    public String toString() {
        return "PictureListApi{" +
                "pictures=" + pictures +
                ", page=" + page +
                ", pageCount=" + pageCount +
                ", hasMore=" + hasMore +
                '}';
    }
}
