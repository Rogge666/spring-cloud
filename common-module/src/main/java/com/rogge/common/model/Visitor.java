package com.rogge.common.model;

import java.io.Serializable;
import java.util.Date;

public class Visitor implements Serializable {

	private static final long serialVersionUID = 1L;

	private SessionUser sessionUser;
	private String sessionId;
	private String ip;
	private String browser;
	private Date loginDate;

	// 在线时间
	private String onlineTimeStr;

	public String getOnlineTimeStr() {
		String str = "";
		Date date = new Date();
		long gap = date.getTime() - loginDate.getTime();
		if (gap < 10 * 1000) {
			onlineTimeStr = "刚刚";
		} else {
			if (gap < 60 * 1000) {
				str = gap / 1000 + "秒";
			} else if (gap < 60 * 60 * 1000) {
				str = gap / (60 * 1000) + "分钟";
			} else if (gap < 24 * 60 * 60 * 1000) {
				str = gap / (60 * 60 * 1000) + "小时";
			} else {
				str = gap / (24 * 60 * 60 * 1000) + "天";
			}
			onlineTimeStr = str + "之前";
		}
		return onlineTimeStr;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public SessionUser getSessionUser() {
		return sessionUser;
	}

	public void setSessionUser(SessionUser sessionUser) {
		this.sessionUser = sessionUser;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setOnlineTimeStr(String onlineTimeStr) {
		this.onlineTimeStr = onlineTimeStr;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

}
