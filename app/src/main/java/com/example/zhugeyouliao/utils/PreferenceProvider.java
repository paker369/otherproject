package com.example.zhugeyouliao.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 保存登录等信息
 */
public class PreferenceProvider {

    private Context context;
    /**
     * 保存在手机里面的文件名
     */
    public final String SP_NAME = "zhuge_config";
    public final int SP_MODE = Context.MODE_PRIVATE;
    private SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    public PreferenceProvider(Context context) {
        this.context = context;
    }

    /**
     * 根据类型调用不同的保存方法
     *
     * @param context 上下文
     * @param key     添加的键
     * @param value   添加的值
     * @return 是否添加成功（可以使用apply提交）
     */
    public boolean put(Context context, String key, Object value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, SP_MODE);
        }
        editor = sp.edit();
        if (value == null) {
            editor.putString(key, null);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            editor.putString(key, value.toString());
        }
        return editor.commit();
    }


    public Object get(Context context, String key, Object defaultObject) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,
                SP_MODE);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }


    public void setToken(String token) {
        put(context, "token", token);
    }

    public String getToken() {
        return (String) get(context, "token", "");
    }

    public void setUserId(String userId) {
        put(context, "userId", userId);
    }

    public String getUserId() {
        return (String) get(context, "userId", "");
    }

}
