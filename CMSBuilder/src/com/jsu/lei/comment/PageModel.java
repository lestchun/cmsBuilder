package com.jsu.lei.comment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PageModel<T>  {
	
	private static final int DEFAULT_PAGESIZE=20;
	private static final int DEFAULT_BEGIN=0;	
	private List<T> content= new ArrayList<T>();
	private int total=0;
	private int currentPage=0;
	private int pageSize=DEFAULT_PAGESIZE;
	private int totalPage=total/pageSize+(total%pageSize==0?0:1);
	
	public PageModel(List<T> content, int total, int currentPage, int pageSize,
			int totalPage) {
		super();
		this.content = content;
		this.total = total;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
	}
	
	public PageModel(List<T> content, int total, int currentPage, int pageSize) {
		super();
		this.content = content;
		this.total = total;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public PageModel() {
		super();
	}

	public PageModel(List<T> content, int total, int currentPage) {
		super();
		this.content = content;
		this.total = total;
		this.currentPage = currentPage;
	}

	public List<T> getContent() {
		return content;
	}

	public int getNumber() {
		return 0;
	}

	public int getNumberOfElements() {
		return 0;
	}

	public int getSize() {
		return content.size();
	}

	public long getTotalElements() {
		return total;
	}

	public int getTotalPages() {
		return totalPage;
	}

	public boolean hasContent() {
		return (null!=content&&content.size()>0);
	}

	public boolean hasNextPage() {
		return totalPage>currentPage;
	}

	public boolean hasPreviousPage() {
		return currentPage>DEFAULT_BEGIN;
	}

	public boolean isFirstPage() {
		return currentPage==DEFAULT_BEGIN;
	}

	public boolean isLastPage() {
		return currentPage==totalPage;
	}

	public Iterator<T> iterator() {
		return content.iterator();
	}
	 
	public void setContent(List<T> content) {
		this.content = content;
	}

}
