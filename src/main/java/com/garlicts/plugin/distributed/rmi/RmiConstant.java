package com.garlicts.plugin.distributed.rmi;

public interface RmiConstant {
	 String ZK_CONNECTION_STRING = "127.0.0.1:2181";
	 int ZK_SESSION_TIMEOUT = 5000;
	 String ZK_ROOT_PATH = "/registry";
	 String ZK_PROVIDER_PATH = ZK_ROOT_PATH + "/provider";
}
