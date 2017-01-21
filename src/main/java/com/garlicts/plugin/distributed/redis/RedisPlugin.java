package com.garlicts.plugin.distributed.redis;

import java.lang.reflect.Constructor;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.garlicts.FrameworkConstant;
import com.garlicts.config.PropertiesProvider;
import com.garlicts.ioc.BeanContainerAbility;
import com.garlicts.plugin.DistributedPlugin;
import com.garlicts.util.ClassUtil;

public class RedisPlugin extends DistributedPlugin {
	
	JedisPool jedisPool;

	@Override
	public void init() {
		
		//读redis配置  格式为 192.168.8.100
		String redisServer = PropertiesProvider.getString(FrameworkConstant.REDIS_SERVER);
		jedisPool = new JedisPool(new JedisPoolConfig(), redisServer);

	}

	@Override
	public void destroy() {
		jedisPool.destroy();
	}

	@Override
	public void register() {
		//将RedisTemplate注册到Bean容器
		Class<?> redisTemplateClass = ClassUtil.loadClass("com.garlicts.plugin.distributed.redis.RedisTemplate");
		try {
			Constructor<?> constructor = redisTemplateClass.getConstructor(JedisPool.class);
			RedisTemplate redisTemplate = (RedisTemplate) constructor.newInstance(jedisPool);
			BeanContainerAbility.setBean(redisTemplateClass, redisTemplate);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
