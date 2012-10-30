package com.example.wifiqq_byudp;

import java.util.Map;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 持久化存储工具
 */
public class MySP {
	private SharedPreferences sp = null;
	
	public MySP(Context context) {
		sp = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public MySP(Context context, String name) {
		this(context, name, Context.MODE_PRIVATE);
	}

	public MySP(Context context, String name, int mode) {
		sp = context.getSharedPreferences(name, mode);
	}
	

	public boolean contains(String key) {
		return sp.contains(key);
	}
	
	public Map<String,?> getAll(){
		return sp.getAll();
	}
	
	public boolean clear() {
		return sp.edit().clear().commit();
	}

	public boolean remove(String key) {
		return sp.edit().remove(key).commit();
	}
	
	
	
	
	public boolean getBoolean(String key, boolean defValue) {
		return sp.getBoolean(key, defValue);
	}

	public float getFloat(String key, float defValue) {
		return sp.getFloat(key, defValue);
	}

	public int getInt(String key, int defValue) {
		return sp.getInt(key, defValue);
	}

	public long getLong(String key, long defValue) {
		return sp.getLong(key, defValue);
	}

	public String getString(String key, String defValue) {
		return sp.getString(key, defValue);
	}
	
	public String getValue(String key){
		return getString(key,null);
	}

	public boolean putBoolean(String key, boolean value) {
		return sp.edit().putBoolean(key, value).commit();
	}

	public boolean putFloat(String key, float value) {
		return sp.edit().putFloat(key, value).commit();
	}

	public boolean putInt(String key, int value) {
		return sp.edit().putInt(key, value).commit();
	}

	public boolean putLong(String key, long value) {
		return sp.edit().putLong(key, value).commit();
	}

	public boolean putString(String key, String value) {
		return sp.edit().putString(key, value).commit();
	}
	
	
	/**********************************************************************/
	
	public boolean get(String key, boolean defValue) {
		return sp.getBoolean(key, defValue);
	}

	public float get(String key, float defValue) {
		return sp.getFloat(key, defValue);
	}

	public int get(String key, int defValue) {
		return sp.getInt(key, defValue);
	}

	public long get(String key, long defValue) {
		return sp.getLong(key, defValue);
	}

	public String get(String key, String defValue) {
		return sp.getString(key, defValue);
	}

	public boolean put(String key, boolean value) {
		return sp.edit().putBoolean(key, value).commit();
	}

	public boolean put(String key, float value) {
		return sp.edit().putFloat(key, value).commit();
	}

	public boolean put(String key, int value) {
		return sp.edit().putInt(key, value).commit();
	}

	public boolean put(String key, long value) {
		return sp.edit().putLong(key, value).commit();
	}

	public boolean put(String key, String value) {
		return sp.edit().putString(key, value).commit();
	}
	
	
	
	public boolean put(String key, Object value) {
		boolean r = false;
		if(value instanceof Boolean)
			r = putBoolean(key, (Boolean)value);
		else if(value instanceof Float)
			r = putFloat(key, (Float)value);
		else if(value instanceof Integer)
			r = putInt(key, (Integer)value);
		else if(value instanceof Long)
			r = putLong(key, (Long)value);
		else if(value instanceof String)
			r = putString(key, (String)value);
		return r;
	}
	
	public Object get(String key, Object defValue) {
		Object obj = null;
		if(defValue instanceof Boolean)
			obj = getBoolean(key, (Boolean)defValue);
		else if(defValue instanceof Float)
			obj = getFloat(key, (Float)defValue);
		else if(defValue instanceof Integer)
			obj = getInt(key, (Integer)defValue);
		else if(defValue instanceof Long)
			obj = getLong(key, (Long)defValue);
		else if(defValue instanceof String)
			obj = getString(key, (String)defValue);
		return obj;
	}
}
