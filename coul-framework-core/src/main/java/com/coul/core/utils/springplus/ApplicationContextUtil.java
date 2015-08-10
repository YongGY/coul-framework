package com.coul.core.utils.springplus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context; // 声明一个静态变量保存

	public void setApplicationContext(final ApplicationContext contex) {
		ApplicationContextUtil.context = contex;
	}

	public static ApplicationContext getContext() {
		return ApplicationContextUtil.context;
	}

	public static <T> T getBean(final String beanId) {
		return (T) ApplicationContextUtil.context.getBean(beanId);
	}

	public static boolean containsBean(final String beanId) {
		return ApplicationContextUtil.context.containsBean(beanId);
	}

	public static <T> T getBean(final String beanId, Object... args) {
		return (T) ApplicationContextUtil.context.getBean(beanId, args);
	}

	public static Object getBeanRmiLoc(final String beanRmiId,
			final String beanLocId) {
		if (true) {
			return ApplicationContextUtil.context.getBean(beanRmiId);
		}
		return ApplicationContextUtil.context.getBean(beanLocId);
	}

}
