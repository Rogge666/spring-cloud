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
public class ApiResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private ResponseCode code = ResponseCode.Base.ERROR;
	private String msg;
	private Object data;

	public ApiResponse() {

	}

	public ApiResponse(ResponseCode code) {
		this(code, null);
	}

	public ApiResponse(ResponseCode code, String msg) {
		this(code, msg, null);
	}

	public ApiResponse(ResponseCode code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static ApiResponse creatSuccess() {
		return creatSuccess((Object) null);
	}

	public static ApiResponse creatSuccess(String msg) {
		return new ApiResponse(ResponseCode.Base.SUCCESS, msg);
	}

	public static ApiResponse creatSuccess(Object data) {
		return creatSuccess(data, Util.getCurrentLanguage());
	}

	public static ApiResponse creatSuccess(String msg, Object data) {
		return new ApiResponse(ResponseCode.Base.SUCCESS, msg, data);
	}

	public static ApiResponse creatSuccess(Object data, LanguageEnum language) {
		return new ApiResponse(ResponseCode.Base.SUCCESS, ResponseCode.Base.SUCCESS.getExplain(language), data);
	}

	public static ApiResponse creatFail(ResponseCode responseCode) {
		return creatFail(responseCode, Util.getCurrentLanguage());
	}

	public static ApiResponse creatFail(ResponseCode responseCode, Map<String, Object> params) {
		return creatFail(responseCode, Util.getCurrentLanguage(), params);
	}

	public static ApiResponse creatFail(ResponseCode responseCode, LanguageEnum language) {
		return new ApiResponse(responseCode, responseCode.getExplain(language));
	}

	public static ApiResponse creatFail(ResponseCode responseCode, LanguageEnum language, Map<String, Object> params) {
		return new ApiResponse(responseCode, Util.convertParam(responseCode.getExplain(language), params));
	}

	public static ApiResponse getApiResponse(ServiceApiResponse serviceApiResponse) {
		return new ApiResponse(serviceApiResponse.getCode(), Util.convertParam(serviceApiResponse.getCode().getExplain(Util.getCurrentLanguage()), serviceApiResponse.getParams()), serviceApiResponse.getData());
	}

	public String getMsg() {
		return msg;
	}

	public ResponseCode getCode() {
		return code;
	}

	public void setCode(ResponseCode code) {
		this.code = code;
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

	@Override
	public String toString() {
		return "ApiResponse [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

	/**
	 * 生成请求结果Json
	 */
	public String toJson() {
		return JSON.toJSONString(this);
	}

}
