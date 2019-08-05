package com.taotao.common.pojo;

import java.io.Serializable;

/**
 * 上传图片返回值
 */
public class PictureResult implements Serializable {


	private Integer error;
	private String url;
	private String message;

	public PictureResult(Integer state, String url) {
		this.url = url;
		this.error = state;
	}
	public PictureResult(Integer state, String url, String errorMessage) {
		this.url = url;
		this.error = state;
		this.message = errorMessage;
	}
	public PictureResult(){

	}
	public Integer getError() {
		return error;
	}
	public void setError(Integer error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
