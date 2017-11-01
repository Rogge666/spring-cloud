package com.rogge.common.core;

import java.io.Serializable;
import java.util.Map;

/**
 * API结果返回对象
 * 
 * <p>
 * <font color=red>注意：此类用于Service(dubbo)层返回{@link ApiResponse}数据信息，在Controll层调用Service层得到此对象后，需要包装后才能得到ApiResponse</font>
 * </p>
 * 
 * <p>
 * 使用举例：<br>
 * <br>
 * 
 * {@code ApiResponse apiResponse = ApiResponse.getApiResponse(serviceApiResponse);}
 *
 * </p>
 *
 * @author droxy
 * @date 2017年3月24日 上午10:30:03
 *
 */
public class ServiceApiResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private ResponseCode code = ResponseCode.Base.ERROR;
	private Map<String, Object> params;
	private Object data;

	public ServiceApiResponse() {

	}

	public ServiceApiResponse(ResponseCode code) {
		this.code = code;
	}

	public ServiceApiResponse(ResponseCode code, Object data) {
		this.code = code;
		this.data = data;
	}

	public ServiceApiResponse(ResponseCode code, Map<String, Object> params, Object data) {
		this.code = code;
		this.params = params;
		this.data = data;
	}

	public static ServiceApiResponse creatSuccess() {
		return creatSuccess(null);
	}

	public static ServiceApiResponse creatSuccess(Object data) {
		return creatSuccess(ResponseCode.Base.SUCCESS, data);
	}

	public static ServiceApiResponse creatSuccess(ResponseCode responseCode, Object data) {
		return creatSuccess(responseCode, null, data);
	}

	public static ServiceApiResponse creatSuccess(ResponseCode responseCode, Map<String, Object> params, Object data) {
		return new ServiceApiResponse(responseCode, params, data);
	}

	public static ServiceApiResponse creatFail(ResponseCode responseCode) {
		return creatFail(responseCode, null);
	}

	public static ServiceApiResponse creatFail(ResponseCode responseCode, Object data) {
		return creatFail(responseCode, null, data);
	}

	public static ServiceApiResponse creatFail(ResponseCode responseCode, Map<String, Object> params) {
		return creatFail(responseCode, params, null);
	}

	public static ServiceApiResponse creatFail(ResponseCode responseCode, Map<String, Object> params, Object data) {
		return new ServiceApiResponse(responseCode, params, data);
	}

	public ResponseCode getCode() {
		return code;
	}

	public void setCode(ResponseCode code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}
