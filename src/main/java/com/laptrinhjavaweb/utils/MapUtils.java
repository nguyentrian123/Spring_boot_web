package com.laptrinhjavaweb.utils;

import java.util.Map;

public class MapUtils {

	public static <T>  T getObject(Map<String, String> params, String key, Class<T> tClass)
	{

		Object object = params.getOrDefault(key, null); // hỗ trợ ktra null
		
		if(object != null) // Check Dkien, nếu mà object == null thì trả về null rồi check
		{
			// nếu object # null thì ta ép sang KDL cần
			if(tClass.getTypeName().equals("java.lang.Long"))
			{
				object = object != "" ? Long.valueOf(object.toString()) : null; 
			}
			else if(tClass.getTypeName().equals("java.lang.Integer")){
				object = object != "" ?  Integer.parseInt(object.toString()) : null;
			}else if(tClass.getTypeName().equals("java.lang.String")){
			
				object = object != "" ?  object.toString() : null;
			}
			return tClass.cast(object);
		}
		
	
		return null;
	}
}
