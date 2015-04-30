package com.degloba.utils;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.degloba.utils.Assert;

/**
 * Paging object that contains the current page data and paging information such as the total number of records.
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 5859907455479273251L;

    public static final int DEFAULT_PAGE_SIZE = 10;

    private int pageSize = DEFAULT_PAGE_SIZE; // The number of records per page

    private long start; // The current position of the first data page in the List, starting from 0

    private List<T> data = new ArrayList<T>(); // Records stored in the page, type typically List

    private long resultCount; // Total number of records

    /**
     * The default constructor.
     *
     * @param start This page data starting position in the database
     * @param totalSize The total number of records in the database
     * @param data This page contains data
     */
    public Page(long start, long totalSize, List<T> data) {
        Assert.isTrue(start >= 0, "Start must not be negative!");
        Assert.isTrue(totalSize >= 0, "Total size must not be negative!");
        this.start = start;
        this.resultCount = totalSize;
        this.data = data;
        if (this.data == null) {
            this.data = new ArrayList<T>();
        }
    }

    /**
     * The position of the first record to get the interception of
     * @return The first record of the sampling position
     */
    public long getStart() {
        return start;
    }

    /**
     * The default constructor.
     *
     * @param start This page data starting position in the database
     * @param totalSize The total number of records in the database
     * @param pageSize This page capacity
     * @param data This page contains data
     */
    public Page(long start, long totalSize, int pageSize, List<T> data) {
        this(start, totalSize, data);
        Assert.isTrue(pageSize > 0, "Page size must be greater than 0!");
        this.pageSize = pageSize;
    }

    /**
     * Taking the total number of records.
     * @return The total number of records that meet the search criteria
     */
    public long getResultCount() {
        return this.resultCount;
    }

    /**
     * Taking the total number of pages.
     * @return Record the total number of pages that match the query criteria
     */
    public long getPageCount() {
        if (resultCount % pageSize == 0) {
            return resultCount / pageSize;
        } else {
            return resultCount / pageSize + 1;
        }
    }

    /**
     * Take each page of data capacity.
     * @return The number of records per page can hold
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Take this page in each record.
     * @return Current data page
     */
    public List<T> getData() {
        return data;
    }

    /**
     * Take this page current page number, 0 for the first page
     * @return The current page number
     */
    public long getPageIndex() {
        return start / pageSize;
    }

    /**
     * Is there a next page.
     * @return f the current page is the last page, it returns false, otherwise it returns true
     */
    public boolean hasNextPage() {
        return this.getPageIndex() < this.getPageCount() - 1;
    }

    /**
     * Is there a previous page.
     * @return If the current page number is 0, it returns true, false otherwise
     */
    public boolean hasPreviousPage() {
        return this.getPageIndex() > 0;
    }

    /**
     * Get Any page first data location in the data set.
     *
     * @param pageIndex Page numbers starting from 0
     * @param pageSize Per capacity
     * @return The first page of data location query results in qualifying.
     */
    public static int getStartOfPage(int pageIndex, int pageSize) {
        return pageIndex * pageSize;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
