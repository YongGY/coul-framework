
package com.coul.common.utils.reflect;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

import com.coul.common.model.MapBean;


/**
 * BeanUtils工具类
 * 
 * 
 */
public final class SpringBeanUtils extends org.springframework.beans.BeanUtils {
	/**
	 * Bean属性装载到Map容器
	 * 
	 * @param javaBean
	 *            javaBean对象
	 * @return MapBean对象
	 */
	public static MapBean toMap(Object javaBean) {
		MapBean mapBean = new MapBean();
		if (javaBean == null) {
			return mapBean;
		}

		Class<?> cls = javaBean.getClass();
		PropertyDescriptor pds[] = SpringBeanUtils.getPropertyDescriptors(cls);
		for (int i = 0; i < pds.length; ++i) {
			PropertyDescriptor pd = pds[i];
			if ("class".equals(pd.getName())) {
				continue;
			}

			try {
				Method readMethod = pd.getReadMethod();
				if (!Modifier.isPublic(readMethod.getDeclaringClass()
						.getModifiers())) {
					readMethod.setAccessible(true);
				}

				Object value = readMethod.invoke(javaBean, new Object[0]);
				if (value == null) {
					continue;
				}

				mapBean.put(pd.getName(), value);
			} catch (Throwable e) {
				throw new RuntimeException(
						"Could not copy properties from source to target", e);
			}
		}
		return mapBean;
	}

	/**
	 * Bean属性装载到Map容器
	 * 
	 * @param javaBean
	 *            javaBean对象
	 * @return javaBean对象
	 */
	public static <T> T fromMap(T javaBean, Map<?, ?> mapBean) {
		Class<?> cls = javaBean.getClass();
		PropertyDescriptor targetPds[] = getPropertyDescriptors(cls);
		for (int i = 0; i < targetPds.length; ++i) {
			PropertyDescriptor targetPd = targetPds[i];
			if ("class".equals(targetPd.getName())) {
				continue;
			}
			if (targetPd.getWriteMethod() == null) {
				continue;
			}

			Object value = mapBean.get(targetPd.getName());
			if (value == null) {
				continue;
			}

			try {
				Method writeMethod = targetPd.getWriteMethod();
				if (!Modifier.isPublic(writeMethod.getDeclaringClass()
						.getModifiers())) {
					writeMethod.setAccessible(true);
				}
				writeMethod.invoke(javaBean, new Object[] { value });
			} catch (Throwable e) {
				throw new RuntimeException(
						"Could not copy properties from source to target", e);
			}
		}
		return javaBean;
	}

	/**
	 * 获取属性的值
	 * 
	 * @param javaBean
	 *            javaBean对象
	 * @param pd
	 *            javaBean属性
	 * @return javaBean属性值
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getPropertyValue(Object javaBean, PropertyDescriptor pd) {
		T value = null;
		try {
			Method method = pd.getReadMethod();
			if (method != null) {
				value = (T) method.invoke(javaBean);
				return value;
			}
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("通过getter取字段值失败！", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("通过getter取字段值失败！", e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("通过getter取字段值失败！", e);
		} catch (Throwable t) {
			throw new RuntimeException("通过getter取字段值失败！", t);
		}
		return value;
	}

	/**
	 * 设置属性值
	 * 
	 * @param javaBean
	 *            javaBean对象
	 * @param pd
	 *            javaBean属性
	 */
	public static void setPropertyValue(Object javaBean, PropertyDescriptor pd,
			Object value) {
		try {
			if (value == null) {
				return;
			}

			// 通过setter保存
			Method method = pd.getWriteMethod();
			if (method == null) {
				return;
			}

			Class<?> cls = null;
			Class<?>[] clss = method.getParameterTypes();
			if (clss != null && clss.length == 1) {
				cls = clss[0];
			}

			if (cls == null) {
				return;
			}

			if (!cls.isPrimitive()) {
				method.invoke(javaBean, value);
			} else if (double.class == cls) {
				method.invoke(javaBean, ((Double) value).doubleValue());
			} else if (long.class == cls) {
				method.invoke(javaBean, ((Long) value).longValue());
			} else if (short.class == cls) {
				method.invoke(javaBean, ((Short) value).shortValue());
			} else if (int.class == cls) {
				method.invoke(javaBean, ((Integer) value).intValue());
			} else if (boolean.class == cls) {
				method.invoke(javaBean, ((Boolean) value).booleanValue());
			}
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("通过setter保存字段值失败！", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("通过setter保存字段值失败！", e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("通过setter保存字段值失败！", e);
		} catch (Throwable t) {
			throw new RuntimeException("通过setter保存字段值失败！", t);
		}
	}
}
