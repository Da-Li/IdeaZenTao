package com.js_ku.zentao.api.model;

/**
 * Created by da-li on 2018/2/26.
 */
public class BugData {

	private Integer id;
	private Integer product;
	private String title;

	public BugData() {

	}
	public BugData(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


}
