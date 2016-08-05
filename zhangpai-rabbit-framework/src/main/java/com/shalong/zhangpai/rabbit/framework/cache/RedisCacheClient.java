package com.shalong.zhangpai.rabbit.framework.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.util.Hashing;

/**
 * 实现使用Redis作为缓存的客户端封装
 * 
 * @author Huangyunjun
 */
public class RedisCacheClient extends CacheClientBase implements CacheClient {



	// 默认超时时间
	private static final int DEFAULT_TIMEOUT = 2000;

	// 默认最大活动连接数
	private static final int MAX_ACTIVE = 200;

	// 默认最大空闲连接数
	private static final int MAX_IDLE = 2;

	private ShardedJedisPool pool;

	public RedisCacheClient(String servers, String app) {
		super(app);

		String[] hosts = servers.trim().split("\\|");

		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();

		for (String host : hosts) {
			String[] ss = host.split(":");
			JedisShardInfo shard = new JedisShardInfo(ss[0], Integer.parseInt(ss[1]));
			shards.add(shard);
		}
		
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		 
	    config.setMaxIdle(MAX_IDLE);
	    config.setMaxTotal(MAX_ACTIVE);
	    config.setTestOnBorrow(true);
	    config.setTestOnReturn(true);
	    config.setMaxWaitMillis(15000);
	    config.setTimeBetweenEvictionRunsMillis(30000);

		pool = new ShardedJedisPool(config, shards, Hashing.MURMUR_HASH);
	}

	@Override
	public void cleanup() {
		pool.destroy();
	}

	@Override
	public void set(String key, String value) {
		ShardedJedis redis = pool.getResource();
		try {
			redis.set(key, value);
			pool.returnResource(redis);
		} catch (Exception e) {
			pool.returnBrokenResource(redis);
		}
	}

	@Override
	public void set(byte[] key, byte[] value) {
		ShardedJedis redis = pool.getResource();
	 
		redis.set(key, value);
		pool.returnResource(redis);
	}

	@Override
	public String setex(byte[] key, int seconds, byte[] value) {
		ShardedJedis redis = pool.getResource();
		String result = redis.setex(key, seconds, value);
		pool.returnResource(redis);
		return result;
	}
	  /**
     * 获取数据
     * 
     * @param key
     * @return
     */
	@Override
	public byte[] get(byte[] key) {
		ShardedJedis redis = pool.getResource();
		byte[] result = redis.get(key);
		pool.returnResource(redis);
		return result;
	}
	/**
     * 获取数据
     * @param key
     * @return
     */
	@Override
	public String get(String key) {
		ShardedJedis redis = pool.getResource();
		String result = redis.get(key);
		pool.returnResource(redis);
		return result;
	}

	@Override
	public Long incr(String key) {
		ShardedJedis redis = pool.getResource();
		Long result = redis.incr(key);
		pool.returnResource(redis);
		return result;
	}
 
	@Override
	public Long decr(String key) {
		ShardedJedis redis = pool.getResource();
		Long result = redis.decr(key);
		pool.returnResource(redis);
		return result;
	}

	@Override
	public void del(String... keys) {
		ShardedJedis redis = pool.getResource();
		for (int i = 0; i < keys.length; i++) {
			redis.getShard(keys[i]).del(keys[i]);
		}
		pool.returnResource(redis);
	}
	
	@Override
	public void del(byte[]... keys) {
		ShardedJedis redis = pool.getResource();
		for (int i = 0; i < keys.length; i++) {
			redis.getShard(keys[i]).del(keys[i]);
		}
		pool.returnResource(redis);
	}

	@Override
	public Long expire(String key, int seconds) {
		ShardedJedis redis = pool.getResource();
		Long result = redis.expire(key, seconds);
		pool.returnResource(redis);
		return result;
	}
	
	@Override
	public Long expire(byte[] key, int seconds) {
		ShardedJedis redis = pool.getResource();
		Long result = redis.expire(key, seconds);
		pool.returnResource(redis);
		return result;
	}

    @Override
    public boolean hexists(String key,String field) {
        ShardedJedis redis = pool.getResource();
        boolean result = redis.hexists(key,field);
        pool.returnResource(redis);
        return result;
    }

    @Override
    public boolean hexists(byte[] key,byte[] field) {
        ShardedJedis redis = pool.getResource();
        boolean result = redis.hexists(key,field);
        pool.returnResource(redis);
        return result;
    }

    @Override
	public Long lpush(String key, String value) {
		ShardedJedis redis = pool.getResource();
		Long result = redis.lpush(key, value);
		pool.returnResource(redis);
		return result;
	}

	@Override
	public List<String> lrange(String key, int start, int end) {
		ShardedJedis redis = pool.getResource();
		List<String> result = redis.lrange(key, start, end);
		pool.returnResource(redis);
		return result;
	}

	@Override
	public Boolean exists(String key) {
		ShardedJedis redis = pool.getResource();
		Boolean result = redis.exists(key);
		pool.returnResource(redis);
		return result;
	}

	@Override
	public String hmset(String key, Map<String, String> hash) {
		ShardedJedis redis = pool.getResource();
		String result = redis.hmset(key, hash);
		pool.returnResource(redis);
		return result;
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		ShardedJedis redis = pool.getResource();
		Map<String, String> result = redis.hgetAll(key);
		pool.returnResource(redis);
		return result;
	}
	/**
     * 获取数据
     * 
     * @param key
     * @return
     */
	@Override
	public String hget(String key, String field) {
		ShardedJedis redis = pool.getResource();
		String result = redis.hget(key, field);
		pool.returnResource(redis);
		return result;
	}

	@Override
	public Long llen(String key) {
		ShardedJedis redis = pool.getResource();
		Long result = redis.llen(key);
		pool.returnResource(redis);
		return result;
	}

	/*
	 * @Override public List<String> brpop(int timeout, String... keys) {
	 * ShardedJedis redis = pool.getResource();
	 * 
	 * pool.returnResource(redis); for (int i = 0; i < keys.length; i++) {
	 * keys[i] = resolveKey(keys[i]); } return redis.brpop(timeout, keys);
	 * 
	 * }
	 */
	/**
     * 存储REDIS队列 顺序存储
     * @param byte[] key reids键名
     * @param byte[] value 键值
     */
	@Override
	public Long lpush(byte[] key, byte[] value) {
		ShardedJedis redis = pool.getResource();
		Long result = redis.lpush(key, value);
		pool.returnResource(redis);
		return result;
	}
	/**
     * 存储REDIS队列 反向存储
     * @param byte[] key reids键名
     * @param byte[] value 键值
     */
	@Override
	public Long rpush(byte[] key, byte[] value) {
		ShardedJedis redis = pool.getResource();
		Long result = redis.rpush(key, value);
		pool.returnResource(redis);
		 
		return result;
	}

	/*
	 * @Override public List<byte[]> brpop(int timeout, byte[]... keys) {
	 * ShardedJedis redis = pool.getResource();
	 * 
	 * pool.returnResource(redis); for (int i = 0; i < keys.length; i++) {
	 * keys[i] = resolveKey(new String(keys[i])).getBytes(); } return
	 * redis.brpop(timeout, keys);
	 * 
	 * }
	 */

	@Override
	public Map<byte[], byte[]> hgetAll(byte[] key) {
		ShardedJedis redis = pool.getResource();
		Map<byte[], byte[]> result = redis.hgetAll(key);
		pool.returnResource(redis);
		return result;
	}

	@Override
	public String hmset(byte[] key, Map<byte[], byte[]> hash) {
		ShardedJedis redis = pool.getResource();
		String result = redis.hmset(key, hash);
		pool.returnResource(redis);
		return result;
	}

	@Override
	public List<byte[]> sort(String key, String patterns) {
		ShardedJedis redis = pool.getResource();
		List<byte[]> result = redis.sort(key.getBytes(), new SortingParams().get(patterns));
		pool.returnResource(redis);
		return result;
	}

	@Override
	public List<byte[]> lrange(byte[] key, int start, int end) {
		ShardedJedis redis = pool.getResource();
		List<byte[]> result = redis.lrange(key, start, end);
		pool.returnResource(redis);
		return result;
	}

	@Override
	public long hincrBy(String key, String field, long value) {
		ShardedJedis redis = pool.getResource();
		long result = redis.hincrBy(key, field, value);
		pool.returnResource(redis);
		return result;
	}

	@Override
	public long hdel(String key, String field) {
		ShardedJedis redis = pool.getResource();
		long result = redis.hdel(key, field);
		pool.returnResource(redis);
		return result;
	}

	@Override
	public long setnx(String key, String value) {
		ShardedJedis redis = pool.getResource();
		long result = redis.setnx(key, value);
		pool.returnResource(redis);
		return result;
	}

	@Override
	public Long hset(byte[] key, byte[] field, byte[] value) {
		ShardedJedis redis = pool.getResource();
		Long result = redis.hset(key, field, value);
		pool.returnResource(redis);
		return result;
	}

	@Override
	public Long hdel(byte[] key, byte[] field) {
		ShardedJedis redis = pool.getResource();
		Long result = redis.hdel(key, field);
		pool.returnResource(redis);
		return result;
	}

	@Override
	public void zadd(String key, double score, String member) {
		ShardedJedis redis = pool.getResource();
		redis.zadd(key, score, member);
		pool.returnResource(redis);
	}

	@Override
	public Long hset(String key, String field, String value) {
		ShardedJedis redis = pool.getResource();
		Long result = redis.hset(key, field, value);
		pool.returnResource(redis);
		return result;
	}

	@Override
	public Set<byte[]> keys(byte[] pattern) {
		ShardedJedis redis = pool.getResource();
		Set<byte[]> result = redis.hkeys(pattern);
		pool.returnResource(redis);
		return result;
	}

	@Override
	public Set<String> keys(String pattern) {
		ShardedJedis redis = pool.getResource();
		Set<String> result = redis.hkeys(pattern);
		pool.returnResource(redis);
		return result;
	}
	/**
     * 获取数据
     * 
     * @param key
     * @return
     */
	@Override
	public byte[] hget(byte[] key, byte[] field) {
		ShardedJedis redis = pool.getResource();
		byte[] result = redis.hget(key, field);
		pool.returnResource(redis);
		return result;
	}
    @Override
    public Long zcard(String key) {
        ShardedJedis redis = pool.getResource();
        long result = redis.zcard(key);
        pool.returnResource(redis);
        return result;
    }

    @Override
    public Long zcard(byte[] key) {
        ShardedJedis redis = pool.getResource();
        long result = redis.zcard(key);
        pool.returnResource(redis);
        return result;
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, int start, int end) {
    	ShardedJedis redis = pool.getResource();
        Set<Tuple> result = redis.zrangeWithScores(key, start, end);
        pool.returnResource(redis);
        return result;
    }
    
    @Override
	public Long zcount(String key, double min, double max) {
    	ShardedJedis redis = pool.getResource();
        Long result = redis.zcount(key, min, max);
        pool.returnResource(redis);
        return result;
	}
    
	/**
	 * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
	 *	当 key 存在但不是有序集类型时，返回一个错误。
	 *
	 * @author narisu
	 * @created 2014年5月19日 上午11:55:37
	 *
	 * @param string
	 * @param valueOf
	 */
	public Long zrem(String key, String member) {
		ShardedJedis redis = pool.getResource();
		 
        Long result = redis.zrem(key, member);
        pool.returnResource(redis);
        return result;
	}
	
	 
 
	
	public static void main(String[] args) {
		 RedisCacheClient jedis = new RedisCacheClient("125.208.8.30:6379","sso_IdentyfyCode");

		 String ss=jedis.setex("1".getBytes(), 4, "we".getBytes());
		System.out.println(ss);
	}


}
