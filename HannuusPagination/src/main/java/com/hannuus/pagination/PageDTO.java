package com.hannuus.pagination;

import java.util.List;

/**
 * 分页数据项封装类
 * 
 * @author cuesky
 * @date 2015年8月31日 下午9:06:07
 * @param <T>
 *            Domain Class
 */
public class PageDTO<T> {

	/** 总行数 */
	private int total;
	/** 数据项 */
	private List<T> rows;

	public PageDTO() {
	}

	public PageDTO(int total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "PageDTO [total=" + total + ", rows="
				+ (rows == null ? 0 : rows.size()) + "]";
	}

}
