package com.jsu.lei.util;


public class ClassUtil {
	
	
	public static boolean isWrapClass(Class<?> clz) {
		if (clz.getName().indexOf("String") != -1) {
			return true;
		}
		try {
			return ((Class<?>) clz.getField("TYPE").get(null)).isPrimitive();
		} catch (Exception e) {
			return false;
		}
	}
}
