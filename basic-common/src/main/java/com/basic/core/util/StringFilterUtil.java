package com.basic.core.util;

import org.apache.commons.lang.StringUtils;

/**
 * 字符串过滤
 * @author: wangzw
 * @date: 2017/12/25 18:10
 * @version: 1.0
 */
public class StringFilterUtil {

    /**
     * 字符串过滤表情
     * @author: wangzw
     * @version: 1.0
     * @date: 2017/12/25 18:10
     */
    public static String filterEmoji(String sourceVal) {
        if (StringUtils.isNotBlank(sourceVal)) {
            return sourceVal.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");
        } else {
            return sourceVal;
        }
    }
}
