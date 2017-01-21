package com.garlicts.plugin.distributed.ftp;

import java.lang.reflect.Constructor;

import com.garlicts.FrameworkConstant;
import com.garlicts.config.PropertiesProvider;
import com.garlicts.ioc.BeanContainerAbility;
import com.garlicts.plugin.DistributedPlugin;
import com.garlicts.util.ClassUtil;

public class FtpPlugin extends DistributedPlugin {

	FtpConstant ftpConstant;
	
	@Override
	public void init() {
		
		String ftpHost = PropertiesProvider.getString(FrameworkConstant.FTP_HOST);
		String ftpPort = PropertiesProvider.getString(FrameworkConstant.FTP_PORT, "21");
		String ftpUsername = PropertiesProvider.getString(FrameworkConstant.FTP_USERNAME);
		String ftpPassword = PropertiesProvider.getString(FrameworkConstant.FTP_PASSWORD);
		ftpConstant = new FtpConstant();
		ftpConstant.setHost(ftpHost);
		ftpConstant.setPort(Integer.parseInt(ftpPort));
		ftpConstant.setUsername(ftpUsername);
		ftpConstant.setPassword(ftpPassword);
		
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void register() {
		//将FtpTemplate注册到Bean容器
		Class<?> ftpTemplateClass = ClassUtil.loadClass("com.garlicts.plugin.distributed.ftp.FtpTemplate");
		try {
			Constructor<?> constructor = ftpTemplateClass.getConstructor(FtpConstant.class);
			FtpTemplate ftpTemplateInstance = (FtpTemplate) constructor.newInstance(ftpConstant);
			BeanContainerAbility.setBean(ftpTemplateClass, ftpTemplateInstance);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
