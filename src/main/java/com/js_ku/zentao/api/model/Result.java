package com.js_ku.zentao.api.model;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.StringUtils;

/**
 * Created by da-li on 2018/2/26.
 */
public class Result<T>{

	private String status;

	@SerializedName(value="data",alternate={"user"})
	private T data;

	public boolean isSuccess() {
		return !StringUtils.isEmpty(getStatus()) && getStatus().equals("success");
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
