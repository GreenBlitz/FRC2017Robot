package org.usfirst.frc.team4590.utils;

import java.lang.reflect.Field;

public class ReflectionHelper {
	public static Object getPrivateField(Object object, Class cls, String fieldName){
		
		Field field = null;
		try {
			field = cls.getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e1) {
			return null;
		}
		field.setAccessible(true);
		try {
			return field.get(object);
		} catch (IllegalArgumentException | IllegalAccessException e) {}
		
		return null;
	}
	
	public static boolean setPrivateField(Object object, Class cls, Object value, String fieldName){
		
		Field field = null;
		try {
			field = cls.getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e1) {
			return false;
		}
		field.setAccessible(true);
		try {
			field.set(object, value);
			return true;
		} catch (IllegalArgumentException | IllegalAccessException e) {
		}
		
		return false;
	}
	
	
}
