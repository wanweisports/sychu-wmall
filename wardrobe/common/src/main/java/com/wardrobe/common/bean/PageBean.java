package com.wardrobe.common.bean;

import com.wardrobe.common.annotation.GsonExclude;

import java.util.List;
import java.util.Map;

public class PageBean {

	/**
	 * 要返回的某一页的记录列表
	 */
	private List list;

	/**
	 * 总记录数
	 */
	private int count;
	
	/**
	 * 总页数
	 */
	private int lastPage;
	
	/**
	 * 当前页
	 */
	@GsonExclude
	private int currentPage;
	
	/**
	 * 每页记录数
	 */
	private int pageSize;
	
	
	/**
	 * 是否为第一页
	 */
	@GsonExclude
	private boolean isFirstPage;
	
	/**
	 * 是否为最后一页
	 */
	@GsonExclude
	private boolean isLastPage;
	
	/**
	 * 是否有前一页
	 */
	@GsonExclude
	private boolean hasPreviousPage;
	
	/**
	 * 是否有下一页
	 */
	@GsonExclude
	private boolean hasNextPage;

	/**
	 * 上一页页数
	 */
	@GsonExclude
	private int pre;

	/**
	 * 下一页页数
	 */
	@GsonExclude
	private int next;

	@GsonExclude
	private Map<String, Object> paramsMap;

	public PageBean() {
	}

	public PageBean(List list, int currentPage, int pageSize, int count) {
		this.list = list;
		this.currentPage = (currentPage==0?1:currentPage);
		this.pageSize = pageSize;
		this.count = count;
	}

	/**
	 * 初始化分页信息
	 */
	public void init() {
		this.lastPage = countTotalPage(pageSize, count);
		this.isFirstPage = isFirstPage();
		this.isLastPage = isLastPage();
		this.hasPreviousPage = isHasPreviousPage();
		this.hasNextPage = isHasNextPage();
		this.pre = hasPreviousPage?currentPage - 1:-1;
		this.next = hasNextPage?currentPage + 1:-1;
	}

	/**
	 * 以下判断页的信息,只需getter方法(is方法)即可
	 * 
	 * @return
	 */
	public boolean isFirstPage() {
		return (currentPage == 1); //如果当前页是第1页
	}

	public boolean isLastPage() {
		return currentPage == lastPage; //如果当前页是最后一页
	}

	public boolean isHasPreviousPage() {
		return currentPage != 1; //只要当前页不是第1页
	}

	public boolean isHasNextPage() {
		return currentPage != lastPage && lastPage > 0; //只要当前页不是最后1页
	}

	/**
	 * 计算总页数,静态方法,供外部直接通过类名调用
	 * 
	 * @param pageSize 每页记录数       
	 * @param allRow 总记录数
	 *            
	 * @return 总页数
	 */
	public static int countTotalPage(final int pageSize, final int allRow) {
		int totalPage = allRow % ((pageSize == 0)?10:pageSize) == 0 ? allRow / ((pageSize == 0)?10:pageSize) : allRow
				/ ((pageSize == 0)?10:pageSize) + 1;
		return totalPage;
	}

	/**
	 * 计算当前页开始记录
	 * 
	 * @param pageSize 每页记录数         
	 * @param currentPage 当前第几页
	 *            
	 * @return 当前页开始记录号
	 */
	public static int countOffset(final int pageSize, final int currentPage) {
		final int offset = ((pageSize == 0)?10:pageSize) * (currentPage - 1);
		return offset;
	}

	/**
	 * 计算当前页,若为0或者请求的URL中没有"?page=",则用1代替
	 * 
	 * @param page 传入的参数(可能为空,即0,则返回1)
	 * @return 当前页
	 */
	public static int countCurrentPage(int page) {
		final int curPage = (page == 0 ? 1 : page);
		return curPage;
	}
	
	public PageBean pagedList() {
        int fromIndex = (currentPage-1) * pageSize;
        if (fromIndex >= list.size()) {
            return this;
        }
        
        int toIndex = currentPage * pageSize;
        if (toIndex >= list.size()) {
            toIndex = list.size();
        }
        list = list.subList(fromIndex, toIndex);
        return this;
    }

	public Map<String, Object> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) { this.pageSize = pageSize;
	}

	public void setIsFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public void setIsLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public int getPre() {
		return pre;
	}

	public void setPre(int pre) {
		this.pre = pre;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}
}
