package com.rogge.common.conf.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RedisDao {

	@Resource
    private RedisTemplate<Serializable, Serializable> redisTemplate;

	/**
	 * 发送频道消息
	 */
	public void sendChannelMessage(String channel, Serializable message) {
		redisTemplate.convertAndSend(channel, message);
	}

	/**
	 * 查询key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public Boolean exists(final Serializable key) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.exists(RedisSerializer.serialize(key));
			}
		});
	}

	/**
	 * 删除key
	 * 
	 * @param keys
	 * @return
	 */
	public Long del(final Serializable... keys) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[][] ks = new byte[keys.length][];
				for (int i = 0; i < keys.length; i++) {
					ks[i] = RedisSerializer.serialize(keys[i]);
				}
				return connection.del(ks);
			}
		});
	}

	/**
	 * 设置key过期时间
	 * 
	 * @param key
	 * @param seconds
	 * @return
	 */
	public Boolean expire(final Serializable key, final long seconds) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.expire(RedisSerializer.serialize(key), seconds);
			}
		});
	}

	/**
	 * 查询key过期时间
	 * 
	 * @param key
	 * @return
	 */
	public Long ttl(final Serializable key) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.ttl(RedisSerializer.serialize(key));
			}
		});
	}

	/**
	 * 保存值
	 * 
	 * @param key
	 * @param value
	 */
	public void set(final Serializable key, final Serializable value) {
		redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				byte[] v = RedisSerializer.serialize(value);
				connection.set(k, v);
				return true;
			}
		});
	}

	/**
	 * 获取值
	 */
	public <T extends Serializable> T get(final Serializable key, final Class<T> clazz) {
		return redisTemplate.execute(new RedisCallback<T>() {
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				byte[] result = connection.get(k);
				return RedisSerializer.deserialize(result, clazz);
			}
		});
	}

	/**
	 * 原子加1
	 */
	public Long incr(final Serializable key) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				return connection.incr(k);
			}
		});
	}

	/**
	 * 原子减1
	 */
	public Long decr(final Serializable key) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				return connection.decr(k);
			}
		});
	}

	/**
	 * 向Set集合添加N项
	 */
	public Long sAdd(final Serializable key, final Serializable... values) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				byte[][] vs = new byte[values.length][];
				for (int i = 0; i < values.length; i++) {
					vs[i] = RedisSerializer.serialize(values[i]);
				}
				return connection.sAdd(k, vs);
			}
		});
	}

	/**
	 * 统计Set集合多少项
	 * 
	 * @param key
	 * @return
	 */
	public Long sCard(final Serializable key) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				return connection.sCard(k);
			}
		});
	}

	/**
	 * 判断Set集合中是否存在某项
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean sIsMember(final Serializable key, final Serializable value) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				byte[] v = RedisSerializer.serialize(value);
				return connection.sIsMember(k, v);
			}
		});
	}

	/**
	 * 获取Set集合所有项值
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T extends Serializable> Set<T> sMembers(final Serializable key, final Class<T> clazz) {
		return redisTemplate.execute(new RedisCallback<Set<T>>() {
			public Set<T> doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				Set<byte[]> sets = connection.sMembers(k);
				Set<T> result = new HashSet<T>();
				if (sets != null && sets.size() > 0) {
					for (byte[] bs : sets) {
						result.add(RedisSerializer.deserialize(bs, clazz));
					}
				}
				return result;
			}
		});
	}

	/**
	 * 删除Set集合中的N项
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public Long sRem(final Serializable key, final Serializable... values) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				byte[][] vs = new byte[values.length][];
				for (int i = 0; i < values.length; i++) {
					vs[i] = RedisSerializer.serialize(values[i]);
				}
				return connection.sRem(k, vs);
			}
		});
	}

	/**
	 * 向SortSet集合添加一项
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean zAdd(final Serializable key, final double score, final Serializable value) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				byte[] v = RedisSerializer.serialize(value);
				return connection.zAdd(k, score, v);
			}
		});
	}

	/**
	 * 统计SortSet集合多少项
	 * 
	 * @param key
	 * @return
	 */
	public Long zCard(final Serializable key) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				return connection.zCard(k);
			}
		});
	}

	/**
	 * 删除SortSet集合中的N项
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public Long zRem(final Serializable key, final Serializable... values) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				byte[][] vs = new byte[values.length][];
				for (int i = 0; i < values.length; i++) {
					vs[i] = RedisSerializer.serialize(values[i]);
				}
				return connection.zRem(k, vs);
			}
		});
	}

	/**
	 * 设置hash
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public Boolean hSet(final Serializable key, final Serializable field, final Serializable value) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				byte[] f = RedisSerializer.serialize(field);
				byte[] v = RedisSerializer.serialize(value);
				return connection.hSet(k, f, v);
			}
		});
	}

	/**
	 * 删除hash下N个field
	 * 
	 * @param key
	 * @param fields
	 * @return
	 */
	public Long hDel(final Serializable key, final Serializable... fields) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				byte[][] fs = new byte[fields.length][];
				for (int i = 0; i < fields.length; i++) {
					fs[i] = RedisSerializer.serialize(fields[i]);
				}
				return connection.hDel(k, fs);
			}
		});
	}

	/**
	 * 判断hash中是否存在filed
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public Boolean hExists(final Serializable key, final Serializable field) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				byte[] f = RedisSerializer.serialize(field);
				return connection.hExists(k, f);
			}
		});
	}

	/**
	 * 获取hash中field值
	 * 
	 * @param key
	 * @param field
	 * @param clazz
	 * @return
	 */
	public <T extends Serializable> T hGet(final Serializable key, final Serializable field, final Class<T> clazz) {
		return redisTemplate.execute(new RedisCallback<T>() {
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				byte[] f = RedisSerializer.serialize(field);
				byte[] result = connection.hGet(k, f);
				return RedisSerializer.deserialize(result, clazz);
			}
		});
	}

	/**
	 * 获取hash所有key
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T extends Serializable> Set<T> hKeys(final Serializable key, final Class<T> clazz) {
		return redisTemplate.execute(new RedisCallback<Set<T>>() {
			public Set<T> doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				Set<byte[]> result = connection.hKeys(k);
				Set<T> sets = new HashSet<T>();
				if (result != null && result.size() > 0) {
					for (byte[] b : result) {
						T t = RedisSerializer.deserialize(b, clazz);
						sets.add(t);
					}
				}
				return sets;
			}
		});
	}

	/**
	 * 获取hash所有value
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T extends Serializable> List<T> hVals(final Serializable key, final Class<T> clazz) {
		return redisTemplate.execute(new RedisCallback<List<T>>() {
			public List<T> doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				List<byte[]> result = connection.hVals(k);
				List<T> sets = new ArrayList<T>();
				if (result != null && result.size() > 0) {
					for (byte[] b : result) {
						T t = RedisSerializer.deserialize(b, clazz);
						sets.add(t);
					}
				}
				return sets;
			}
		});
	}

	/**
	 * 统计List长度
	 * 
	 * @param key
	 * @return
	 */
	public Long lLen(final Serializable key) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				return connection.lLen(k);
			}
		});
	}

	/**
	 * 设置List项值
	 * 
	 * @param key
	 * @return
	 */
	public Boolean lSet(final Serializable key, final int index, final Serializable value) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				byte[] v = RedisSerializer.serialize(value);
				connection.lSet(k, index, v);
				return true;
			}
		});
	}

	/**
	 * 获取List项值
	 * 
	 * @param key
	 * @return
	 */
	public <T extends Serializable> T lIndex(final Serializable key, final int index, final Class<T> clazz) {
		return redisTemplate.execute(new RedisCallback<T>() {
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				byte[] result = connection.lIndex(k, index);
				return RedisSerializer.deserialize(result, clazz);
			}
		});
	}

	/**
	 * 移除List第一项
	 * 
	 * @param key
	 * @return
	 */
	public <T extends Serializable> T lPop(final Serializable key, final Class<T> clazz) {
		return redisTemplate.execute(new RedisCallback<T>() {
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				byte[] result = connection.lPop(k);
				return RedisSerializer.deserialize(result, clazz);
			}
		});
	}

	/**
	 * 移除List最后一项
	 * 
	 * @param key
	 * @return
	 */
	public <T extends Serializable> T rPop(final Serializable key, final Class<T> clazz) {
		return redisTemplate.execute(new RedisCallback<T>() {
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				byte[] result = connection.rPop(k);
				return RedisSerializer.deserialize(result, clazz);
			}
		});
	}

	/**
	 * 向List头部追加N项
	 * 
	 * @param key
	 * @return
	 */
	public Long lPush(final Serializable key, final Serializable... values) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				byte[][] vs = new byte[values.length][];
				for (int i = 0; i < values.length; i++) {
					vs[i] = RedisSerializer.serialize(values[i]);
				}
				return connection.lPush(k, vs);
			}
		});
	}

	/**
	 * 向List尾部追加N项
	 * 
	 * @param key
	 * @return
	 */
	public Long rPush(final Serializable key, final Serializable... values) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				byte[][] vs = new byte[values.length][];
				for (int i = 0; i < values.length; i++) {
					vs[i] = RedisSerializer.serialize(values[i]);
				}
				return connection.rPush(k, vs);
			}
		});
	}

	/**
	 * 获取List集合范围记录
	 * 
	 * @param key
	 * @param start
	 *            开始位置
	 * @param end
	 *            结束位置
	 * @param clazz
	 * @return
	 */

	public <T extends Serializable> List<T> lrange(final Serializable key, final int start, final int end, final Class<T> clazz) {
		return redisTemplate.execute(new RedisCallback<List<T>>() {
			public List<T> doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				List<byte[]> list = connection.lRange(k, start, end);
				List<T> result = new ArrayList<T>();
				if (list != null && list.size() > 0) {
					for (byte[] bs : list) {
						result.add(RedisSerializer.deserialize(bs, clazz));
					}
				}
				return result;
			}
		});
	}

	/**
	 * 删除List中的记录
	 * 
	 * @param key
	 * @param count
	 *            要删除的数量，如果为负从尾部开始检查
	 * @param value
	 * @return 删除后List的长度
	 */
	public Long lRem(final Serializable key, final int count, final Serializable value) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = RedisSerializer.serialize(key);
				byte[] v = RedisSerializer.serialize(value);
				return connection.lRem(k, count, v);
			}
		});
	}

}
