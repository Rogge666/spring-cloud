package com.rogge.common.conf.redis;

import com.rogge.common.model.SessionUser;
import com.rogge.common.model.Visitor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 存储会话用户信息管理
 * @author droxy
 */
@Component
public class SessionUserInfo {

	@Resource
	private RedisDao redisDao;

	@Resource
	private OnlineUserInfo onlineUserInfo;

	/**
	 * 获取用户存放在session会话中的id
	 */
	public String getSessionUserIdKey(String userType) {
		return "USER-ID-" + userType;
	}

	/**
	 * 获取session会话存放在redis的key
	 */
	public String getSessionUserKey(String userType, int userId) {
		return "SESSION-USER:" + userType + ":" + userId;
	}

	/**
	 * 存储用户信息到当前session
	 */
	public void setSessionUser(HttpSession session, SessionUser sessionUser, Visitor visitor, boolean store) {
		// session写id
		session.setAttribute(getSessionUserIdKey(sessionUser.getUserType()), sessionUser.getId());
		// 写入redis
		setSessionUser(sessionUser);
		// 标记在线
		if (store) {
			onlineUserInfo.add(session, visitor);
		}
	}

	public void setSessionUser(HttpSession session, SessionUser sessionUser) {
		setSessionUser(session, sessionUser, null, false);
	}

	/**
	 * 存储用户信息到指定用户id
	 */
	public void setSessionUser(SessionUser sessionUser) {
		// redis写内容
		redisDao.set(getSessionUserKey(sessionUser.getUserType(), sessionUser.getId()), sessionUser);
		// 设置redis过期(超过30分钟没操作手机自动失效)
		redisDao.expire(getSessionUserKey(sessionUser.getUserType(), sessionUser.getId()), 30 * 60);
	}

	/**
	 * 获取用户类型
	 */
	private static <T extends SessionUser> String getUserType(Class<T> clazz) {
		try {
			SessionUser sessionUser = clazz.newInstance();
			return sessionUser.getUserType();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取当前session会话用户信息
	 */
	public <T extends SessionUser> T getSessionUser(HttpSession session, Class<T> clazz) {

		Integer userId = (Integer) session.getAttribute(getSessionUserIdKey(getUserType(clazz)));
		if (userId != null) {
			return redisDao.get(getSessionUserKey(getUserType(clazz), userId), clazz);
		}
		return null;
	}

	/**
	 * 根据id获取用户信息
	 */
	public <T extends SessionUser> T getSessionUser(int userId, Class<T> clazz) {
		if (userId != 0) {
			return redisDao.get(getSessionUserKey(getUserType(clazz), userId), clazz);
		}
		return null;
	}

	/**
	 * 删除当前用户会话
	 */
	public <T extends SessionUser> void removeSessionUser(HttpSession session, Class<T> clazz) {
		if (session == null) {
			return;
		}
		Integer userId = (Integer) session.getAttribute(getSessionUserIdKey(getUserType(clazz)));
		if (userId != 0) {
			SessionUser sessionUser = getSessionUser(session, clazz);
			if (sessionUser != null) {
				// 删除在线信息
				onlineUserInfo.remove(sessionUser);
			}
			redisDao.del(getSessionUserKey(getUserType(clazz), userId));
		}
		session.removeAttribute(getSessionUserIdKey(getUserType(clazz)));

	}

	/**
	 * 删除指定用户
	 */
	public void removeSessionUser(SessionUser sessionUser) {
		redisDao.del(getSessionUserKey(sessionUser.getUserType(), sessionUser.getId()));
	}

	/**
	 * 获取当前会话
	 */
	public <T extends SessionUser> T getCurrentSessionUser(Class<T> clazz) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return getSessionUser(request.getSession(), clazz);
	}

	/**
	 * 获取当前会话(静态)
	 */
	public static <T extends SessionUser> T getSessionUser(Class<T> clazz) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		SessionUserInfo sessionUserInfo = (SessionUserInfo) ctx.getBean("sessionUserInfo");
		return sessionUserInfo.getSessionUser(request.getSession(), clazz);
	}

}
