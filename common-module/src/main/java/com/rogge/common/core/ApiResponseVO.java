package com.rogge.common.core;

import com.alibaba.fastjson.JSON;
import com.rogge.common.model.LanguageEnum;
import com.rogge.common.util.Util;

import java.io.Serializable;
import java.util.Map;

/**
 * API结果返回对象
 * 
 * <p>
 * <font color=red>注意：在Controller层调用才有国际化效果，否则只会取到中文</font>
 * </p>
 * 
 * @author droxy
 * @date 2017年3月24日 上午10:30:03
 *
 */
public class ApiResponseVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int code;
	private String msg;
	private Object data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
