package com.rogge.common.model;

import java.io.Serializable;

/**
 * 会话用户(一般指登录用户)
 * 
 * @author droxy
 *
 */
public abstract class SessionUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 用户主键id **/
	public abstract Integer getId();

	/** 用户名 **/
	public abstract String getUsername();

	/** 用户类型名称 **/
	public String getUserType() {
		return getClass().getSimpleName();
	}

}
