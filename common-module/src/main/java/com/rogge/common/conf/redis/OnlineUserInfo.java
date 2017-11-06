package com.rogge.common.conf.redis;

import com.github.pagehelper.StringUtil;
import com.rogge.common.model.SessionUser;
import com.rogge.common.model.Visitor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 在线用户信息管理
 * 
 * @author droxy
 *
 */
@Component
public class OnlineUserInfo implements InitializingBean {

	private Log logger = LogFactory.getLog(getClass());

	@Resource
	private RedisDao redisDao;

	/** 用户登录列表 **/
	public static final String ONLINE_USER_HASH = "ONLINE-USER-HASH";

	/** 用户登录列表field + [动态userId] **/
	public static final String ONLINE_USER_HASH_FIELD = "ONLINE-USER-";

	public String getField(SessionUser sessionUser) {
		return ONLINE_USER_HASH_FIELD + sessionUser.getUserType() + "@" + sessionUser.getId();
	}

	/**
	 * 新增用户
	 * 
	 * @param visitor
	 */
	public void add(HttpSession session, Visitor visitor) {
		SessionUser sessionUser = visitor.getSessionUser();
		Visitor v;
		if ((v = redisDao.hGet(ONLINE_USER_HASH, getField(sessionUser), Visitor.class)) != null && !session.getId().equals(v.getSessionId())) {
			logger.warn(sessionUser.getUserType() + "用户 " + sessionUser.getId() + " 账号异地(" + visitor.getIp() + ")登录");
		}
		redisDao.hSet(ONLINE_USER_HASH, getField(sessionUser), visitor);
	}

	/**
	 * 移除用户
	 * 
	 * @param sessionUser
	 */
	public void remove(SessionUser sessionUser) {
		Visitor visitor = redisDao.hGet(ONLINE_USER_HASH, getField(sessionUser), Visitor.class);
		if (visitor != null && visitor.getSessionId() != null) {
			redisDao.hDel(ONLINE_USER_HASH, getField(sessionUser));
			logger.debug(sessionUser.getUserType() + "用户(" + sessionUser.getId() + ")下线");
		}
	}

	/**
	 * 获取用户
	 * 
	 * @param sessionUser
	 */
	public Visitor get(SessionUser sessionUser) {
		return redisDao.hGet(ONLINE_USER_HASH, getField(sessionUser), Visitor.class);
	}

	/**
	 * 获取在线用户列表
	 * 
	 * @return
	 */
	public List<Visitor> getList() {
		List<Visitor> visitors = redisDao.hVals(ONLINE_USER_HASH, Visitor.class);
		Collections.sort(visitors, new Comparator<Visitor>() {
			public int compare(Visitor o1, Visitor o2) {
				return (o2.getLoginDate().getTime() - o1.getLoginDate().getTime()) < 0 ? -1 : 1;
			}
		});
		return visitors;
	}

	/**
	 * 根据条件获取在线用户列表
	 * 
	 * @return
	 */
	public List<Visitor> getList(String name) {
		List<Visitor> list = getList();
		// 过滤条件
		if (!StringUtil.isEmpty(name)) {
			List<Visitor> _list = new ArrayList<Visitor>(list);
			Iterator<Visitor> iterator = _list.iterator();
			while (iterator.hasNext()) {
				Visitor v = iterator.next();
				SessionUser sessionUser = v.getSessionUser();
				if (!sessionUser.getUsername().contains(name)) {
					iterator.remove();
				}

			}
			list = _list;
		}
		return list;
	}

	public void afterPropertiesSet() throws Exception {
		new Thread() {
			public void run() {
				clear();
			}
		}.start();
	}

	private void clear() {

		// 清理已过期失效用户
		while (true) {
			List<Visitor> visitors = getList();
			if (visitors != null) {
				Iterator<Visitor> iterator = visitors.iterator();
				while (iterator.hasNext()) {
					Visitor visitor = iterator.next();
					SessionUser sessionUser = visitor.getSessionUser();
					long gap = new Date().getTime() - visitor.getLoginDate().getTime();
					long unit = 1800 * 1000;
					if (gap > unit * 5) {
						remove(sessionUser);
						iterator.remove();
					}
				}
			}
			try {
				Thread.sleep(30 * 60 * 1000);// 30分钟检查一次
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
