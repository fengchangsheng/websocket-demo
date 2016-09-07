package com.fcs;

import org.mission.jedis.proxy.RedisProxy;
import org.mission.jedis.proxy.RedisProxyFactory;

public class RedisAccessor {

	public static RedisProxy getDefaultClient() {
		return RedisProxyFactory.getProxy();
	}
	
	public static void releaseClient() {
		RedisProxyFactory.releaseProxy();
	}
}
