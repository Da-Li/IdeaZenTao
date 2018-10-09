package com.js_ku.zentao.api.model;

import org.apache.commons.lang.StringUtils;

/**
 * Created by da-li on 2018/2/26.
 */
public class BaseResponse {

	private String status;
	
	private boolean success;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isSuccess() {
		return !StringUtils.isEmpty(getStatus()) && "success".equals(getStatus());
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
