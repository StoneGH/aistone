package com.stone.ai.util.str;

/**
 * 字符串工具类
 * Created by shitao on 2018/7/10.
 */
public class StringUtils {

    /**
     * 字符串为空或Null
     *
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        return null == str || "".equals(str);
    }

    /**
     * 字符串不为空或Null
     *
     * @param str
     * @return
     */
    public static boolean isNotNullOrEmpty(String str) {
        return null != str || !"".equals(str);
    }
}
