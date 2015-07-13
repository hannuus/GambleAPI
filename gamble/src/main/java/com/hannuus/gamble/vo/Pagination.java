package com.hannuus.gamble.vo;

import java.util.List;

public class Pagination<T> {
	private List<T> datas;
	private String p;

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
}
