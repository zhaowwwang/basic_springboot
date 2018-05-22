package com.basic.core.util;

import java.util.Iterator;

/**
 * @author: wangzw
 * @description: 字符串工具类
 * @version: 1.0
 * @date: 2017/11/22 14:04
 */
public class BasicStringUtils {
    /** 空字符串。 */
    public static final String EMPTY_STRING = "";

    /**
     * @author: wangzw
     * @description: 是否为空
     * @version: 1.0
     * @date: 2017/11/22 14:02
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if ("".equals(FilterNull(o.toString()))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @author: wangzw
     * @description: 是否不为空
     * @version: 1.0
     * @date: 2017/11/22 14:03
     */
    public static boolean isNotEmpty(Object o) {
        if (o == null) {
            return false;
        }
        if ("".equals(FilterNull(o.toString()))) {
            return false;
        } else {
            return true;
        }
    }

    public static String FilterNull(Object o) {
        return o != null && !"null".equals(o.toString()) ? o.toString().trim() : "" ;
    }

    /**
     * @author: wangzw
     * @description: 判空函数
     * @version: 1.0
     * @date: 2017/11/22 11:23
     */
    public static boolean isEmpty(String str) {
        return ((str == null) || (str.length() == 0));
    }

    /**
     * @author: wangzw
     * @description: 判非空函数
     * @version: 1.0
     * @date: 2017/11/22 11:24
     */
    public static boolean isNotEmpty(String str) {
        return ((str != null) && (str.length() > 0));
    }

    /**
     * @author: wangzw
     * @description: 检查字符串是否是空白，空字符串或只有空白字符
     * @version: 1.0
     * @date: 2017/11/22 11:25
     */
    public static boolean isBlank(String str) {
        int length;
        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @author: wangzw
     * @description: 检查字符串是否非是空白，空字符串或只有空白字符
     * @version: 1.0
     * @date: 2017/11/22 11:26
     */
    public static boolean isNotBlank(String str) {
        int length;
        if ((str == null) || ((length = str.length()) == 0)) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

     /* ============================================================================ */
     /*  默认值函数。                                                                 */
     /*                                                                              */
     /*  当字符串为null empty或blank时 将字符串转换成指定的默认字符串                   */
     /* ============================================================================ */

    /**
     * @author: wangzw
     * @description: 如果字符串是null，返回系统指定的字符串，否则返回字符串本身。
     * @version: 1.0
     * @date: 2017/11/22 11:27
     */
    public static String defaultIfNull(String str) {
        return (str == null) ? EMPTY_STRING : str;
    }

    /**
     * @author: wangzw
     * @description: 如果字符串是null,返回用户指定的字符串，否则返回字符串本身。
     * @version: 1.0
     * @date: 2017/11/22 11:27
     */
    public static String defaultIfNull(String str, String defaultStr) {
        return (str == null) ? defaultStr : str;
    }

    /**
     * @author: wangzw
     * @description: 如果字符串是null或"",返回系统/用户指定的字符串，否则返回字符串本身。
     * @version: 1.0
     * @date: 2017/11/22 11:27
     */
    public static String defaultIfEmpty(String str) {
        return isEmpty(str) ? EMPTY_STRING : str;
    }

    public static String defaultIfEmpty(String str, String defaultStr) {
        return isEmpty(str) ? defaultStr : str;
    }

    /**
     * @author: wangzw
     * @description: 如果字符串是null或""或"  ",返回系统/用户指定的字符串，否则返回字符串本身。
     * @version: 1.0
     * @date: 2017/11/22 11:49
     */
    public static String defaultIfBlank(String str) {
        return isBlank(str) ? EMPTY_STRING : str;
    }

    public static String defaultIfBlank(String str, String defaultStr) {
        return isBlank(str) ? defaultStr : str;
    }

     /* ============================================================================ */
     /*  去空白（或指定字符）的函数。                                                */
     /*                                                                              */
     /*  以下方法用来除去一个字串中的空白或指定字符。                                */
     /* ============================================================================ */

    /**
     * @author: wangzw
     * @description: 注意，和String.trim不同，此方法使用Character.isWhitespace来判定空白，因而可以除去英文字符集之外的其它空白，如中文空格
     * @version: 1.0
     * @date: 2017/11/22 11:38
     */
    public static String trimNullAll(String str) {
        return trim(str, null, 0);
    }

    /**
     * @author: wangzw
     * @description: 字符串开始替换
     * @version: 1.0
     * @date: 2017/11/22 11:43
     */
    public static String trimNullStart(String str) {
        return trim(str, null, -1);
    }

    /**
     * @author: wangzw
     * @description: 字符串尾部替换
     * @version: 1.0
     * @date: 2017/11/22 11:51
     */
    public static String trimNullEnd(String str) {
        return trim(str, null, 1);
    }

    public static String trimAll(String str, String stripChars) {
        return trim(str, stripChars, 0);
    }

    /**
     * @author: wangzw
     * @description: 替换字符串str指定的stripChars
     * @version: 1.0
     * @date: 2017/11/22 11:44
     */
    public static String trimStart(String str, String stripChars) {
        return trim(str, stripChars, -1);
    }

    public static String trimEnd(String str, String stripChars) {
        return trim(str, stripChars, 1);
    }

    /**
     * @author: wangzw
     * @description: 除去字符串头尾部的指定字符，如果字符串是null，依然返回null
     * @param str 要处理的字符串
     * @param stripChars 要除去的字符，如果为null表示除去空白字符
     * @param mode -1表示trimStart，0表示trim全部，1表示trimEnd
     * @version: 1.0
     * @date: 2017/11/22 11:35
     * @return 除去指定字符后的的字符串，如果原字串为null，则返回null
     */
    private static String trim(String str, String stripChars, int mode) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        int start = 0;
        int end = length;
        //扫描字符串头部
        if (mode <= 0) {
            if (stripChars == null) {
                while ((start < end) && (Character.isWhitespace(str.charAt(start)))) {
                    start++;
                }
            } else if (stripChars.length() == 0) {
                return str;
            } else {
                while ((start < end) && (stripChars.indexOf(str.charAt(start)) != -1)) {
                    start++;
                }
            }
        }
        //扫描字符串尾部
        if (mode >= 0) {
            if (stripChars == null) {
                while ((start < end) && (Character.isWhitespace(str.charAt(end - 1)))) {
                    end--;
                }
            } else if (stripChars.length() == 0) {
                return str;
            } else {
                while ((start < end) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
                    end--;
                }
            }
        }
        if ((start > 0) || (end < length)) {
            return str.substring(start, end);
        }
        return str;
    }

     /* ============================================================================ */
     /*  比较函数。                                                                  */
     /*                                                                              */
     /*  以下方法用来比较两个字符串是否相同。                                           */
     /* ============================================================================ */

    /**
     * @author: wangzw
     * @description: 比较两个字符串（大小写敏感）
     * @version: 1.0
     * @date: 2017/11/22 11:53
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }
        return str1.equals(str2);
    }

    /**
     * @author: wangzw
     * @description: 比较两个字符串（大小写不敏感）
     * @version: 1.0
     * @date: 2017/11/22 11:54
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }
        return str1.equalsIgnoreCase(str2);
    }

     /* ============================================================================ */
     /*  字符串类型判定函数。                                                        */
     /*                                                                              */
     /*  判定字符串的类型是否为：字母、数字、空白等                                  */
     /* ============================================================================ */

    /**
     * @author: wangzw
     * @description: 判断字符串是否只包含unicode字母
     * @version: 1.0
     * @date: 2017/11/22 11:54
     */
    public static boolean isAlpha(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @author: wangzw
     * @description: 判断字符串是否只包含unicode字母和空格' '
     * @version: 1.0
     * @date: 2017/11/22 11:55
     */
    public static boolean isAlphaSpace(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isLetter(str.charAt(i)) && (str.charAt(i) != ' ')) {
                return false;
            }
        }
        return true;
    }

    /**
     * @author: wangzw
     * @description: 判断字符串是否只包含unicode字母和数字
     * @version: 1.0
     * @date: 2017/11/22 11:55
     */
    public static boolean isAlphanumeric(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isLetterOrDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @author: wangzw
     * @description: 判断字符串是否只包含unicode字母数字和空格' '
     * @version: 1.0
     * @date: 2017/11/22 11:56
     */
    public static boolean isAlphanumericSpace(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isLetterOrDigit(str.charAt(i)) && (str.charAt(i) != ' ')) {
                return false;
            }
        }
        return true;
    }

     /* ============================================================================ */
     /*  大小写转换                                                                   */
     /* ============================================================================ */

    /**
     * @author: wangzw
     * @description: 将字符串转换成大写
     * @version: 1.0
     * @date: 2017/11/22 13:21
     */
    public static String toUpperCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    /**
     * @author: wangzw
     * @description: 将字符串转换成小写
     * @version: 1.0
     * @date: 2017/11/22 13:21
     */
    public static String toLowerCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();
    }

    /**
     * @author: wangzw
     * @description: 将字符串的首字符转成大写（Character.toTitleCase），其它字符不变
     * @version: 1.0
     * @date: 2017/11/22 13:21
     */
    public static String capitalize(String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0)) {
            return str;
        }
        return new StringBuffer(strLen).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1)).toString();
    }

    /**
     * @author: wangzw
     * @description: 将字符串的首字符转成小写，其它字符不变
     * @version: 1.0
     * @date: 2017/11/22 13:22
     */
    public static String uncapitalize(String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0)) {
            return str;
        }
        return new StringBuffer(strLen).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
    }

    /**
     * @author: wangzw
     * @description: 反转字符串的大小写
     * @version: 1.0
     * @date: 2017/11/22 13:22
     */
    public static String swapCase(String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0)) {
            return str;
        }
        StringBuffer buffer = new StringBuffer(strLen);
        char ch = 0;
        for (int i = 0; i < strLen; i++) {
            ch = str.charAt(i);

            if (Character.isUpperCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isTitleCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isLowerCase(ch)) {
                ch = Character.toUpperCase(ch);
            }
            buffer.append(ch);
        }
        return buffer.toString();
    }


     /* ============================================================================ */
     /*  字符串连接函数。                                                             */
     /*                                                                              */
     /*  将多个对象按指定分隔符连接成字符串。                                           */
     /* ============================================================================ */
    /**
     * @author: wangzw
     * @description: 将数组中的元素连接成一个字符串
     * @version: 1.0
     * @date: 2017/11/22 13:29
     */
    public static String join(Object[] array) {
        return join(array, null);
    }

    /**
     * @author: wangzw
     * @description: 将数组中的元素连接成一个字符串
     * @param array 要连接的数组
     * @param separator 分隔符
     * @version: 1.0
     * @date: 2017/11/22 13:28
     */
    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        int arraySize = array.length;
        int bufSize = (arraySize == 0) ? 0 : ((((array[0] == null) ? 16 : array[0].toString().length()) + 1) * arraySize);
        StringBuffer buf = new StringBuffer(bufSize);
        for (int i = 0; i < arraySize; i++) {
            if (i > 0) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    /**
     * @author: wangzw
     * @description: 将Iterator中的元素连接成一个字符串
     * @version: 1.0
     * @date: 2017/11/22 13:30
     */
    public static String join(Iterator iterator, String separator) {
        if (iterator == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(256); // Java默认值是16, 可能偏小
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
            if (iterator.hasNext()) {
                buf.append(separator);
            }
        }
        return buf.toString();
    }

     /* ============================================================================ */
     /*  字符串查找函数 —— 字符或字符串。                                          */
     /*                                                                              */
     /*  在字符串中查找指定字符或字符串。                                            */
     /* ============================================================================ */

    /**
     * @author: wangzw
     * @description: 在字符串中查找指定字符，并返回第一个匹配的索引值。如果字符串为null或未找到，则返回-1
     * @version: 1.0
     * @date: 2017/11/22 13:31
     */
    public static int indexOf(String str, String searchChar) {
        if ((str == null) || (str.length() == 0)) {
            return -1;
        }
        return str.indexOf(searchChar);
    }

    /**
     * @author: wangzw
     * @description:
     * @param str 要扫描的字符串
     * @param searchChar 要查找的字符
     * @param startPos 开始搜索的索引值，如果小于0，则看作0
     * @version: 1.0
     * @date: 2017/11/22 13:31
     */
    public static int indexOf(String str, String searchChar, int startPos) {
        if ((str == null) || (str.length() == 0)) {
            return -1;
        }
        return str.indexOf(searchChar, startPos);
    }
    /**
     * @author: wangzw
     * @description: 从字符串尾部开始查找指定字符串，并返回第一个匹配的索引值。如果字符串为null或未找到，则返回-1
     * @version: 1.0
     * @date: 2017/11/22 13:35
     */
    public static int lastIndexOf(String str, String searchStr) {
        if ((str == null) || (searchStr == null)) {
            return -1;
        }
        return str.lastIndexOf(searchStr);
    }
    /**
     * @author: wangzw
     * @description: 从字符串尾部开始查找指定字符串，并返回第一个匹配的索引值。如果字符串为null或未找到，则返回-1
     * @param str 要扫描的字符串
     * @param searchStr 要查找的字符串
     * @param startPos 从指定索引开始向前搜索
     * @version: 1.0
     * @date: 2017/11/22 13:35
     */
    public static int lastIndexOf(String str, String searchStr, int startPos) {
        if ((str == null) || (searchStr == null)) {
            return -1;
        }
        return str.lastIndexOf(searchStr, startPos);
    }

    /**
     * @author: wangzw
     * @description: 检查字符串中是否包含指定的字符串。如果字符串为null，将返回false
     * @version: 1.0
     * @date: 2017/11/22 13:37
     */
    public static boolean contains(String str, String searchStr) {
        if ((str == null) || (searchStr == null)) {
            return false;
        }
        return str.indexOf(searchStr) >= 0;
    }

    /**
     * @author: wangzw
     * @description: 取得长度为指定字符数的最左边的子串
     * @version: 1.0
     * @date: 2017/11/22 13:39
     */
    public static String leftSub(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return EMPTY_STRING;
        }
        if (str.length() <= len) {
            return str;
        } else {
            return str.substring(0, len);
        }
    }

    /**
     * @author: wangzw
     * @description: 取得长度为指定字符数的最右边的子串
     * @version: 1.0
     * @date: 2017/11/22 13:41
     */
    public static String rightSub(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return EMPTY_STRING;
        }
        if (str.length() <= len) {
            return str;
        } else {
            return str.substring(str.length() - len);
        }
    }

    /**
     * @author: wangzw
     * @description: 取得从指定索引开始计算的、长度为指定字符数的子串
     * @param str 字符串
     * @param pos 起始索引，如果为负数，则看作0
     * @param len 子串的长度，如果为负数，则看作长度为0
     * @version: 1.0
     * @date: 2017/11/22 13:41
     */
    public static String mid(String str, int pos, int len) {
        if (str == null) {
            return null;
        }
        if ((len < 0) || (pos > str.length())) {
            return EMPTY_STRING;
        }
        if (pos < 0) {
            pos = 0;
        }
        if (str.length() <= (pos + len)) {
            return str.substring(pos);
        } else {
            return str.substring(pos, pos + len);
        }
    }

     /* ============================================================================ */
     /*  搜索并取子串函数。                                                          */
     /* ============================================================================ */

    /**
     * @author: wangzw
     * @description: 取得第一个出现的分隔子串之前的子串
     * @version: 1.0
     * @date: 2017/11/22 13:50
     */
    public static String substringBefore(String str, String separator) {
        if ((str == null) || (separator == null) || (str.length() == 0)) {
            return str;
        }
        if (separator.length() == 0) {
            return EMPTY_STRING;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * @author: wangzw
     * @description: 取得第一个出现的分隔子串之后的子串
     * @version: 1.0
     * @date: 2017/11/22 13:50
     */
    public static String substringAfter(String str, String separator) {
        if ((str == null) || (str.length() == 0)) {
            return str;
        }
        if (separator == null) {
            return EMPTY_STRING;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return EMPTY_STRING;
        }
        return str.substring(pos + separator.length());
    }

    /**
     * @author: wangzw
     * @description: 取得最后一个的分隔子串之前的子串
     * @version: 1.0
     * @date: 2017/11/22 13:50
     */
    public static String substringBeforeLast(String str, String separator) {
        if ((str == null) || (separator == null) || (str.length() == 0)
                || (separator.length() == 0)) {
            return str;
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * @author: wangzw
     * @description: 取得最后一个的分隔子串之后的子串
     * @version: 1.0
     * @date: 2017/11/22 13:51
     */
    public static String substringAfterLast(String str, String separator) {
        if ((str == null) || (str.length() == 0)) {
            return str;
        }
        if ((separator == null) || (separator.length() == 0)) {
            return EMPTY_STRING;
        }
        int pos = str.lastIndexOf(separator);
        if ((pos == -1) || (pos == (str.length() - separator.length()))) {
            return EMPTY_STRING;
        }
        return str.substring(pos + separator.length());
    }

    /**
     * @author: wangzw
     * @description: 取得指定分隔符的前两次出现之间的子串
     * @version: 1.0
     * @date: 2017/11/22 13:51
     */
    public static String substringBetween(String str, String tag) {
        return substringBetween(str, tag, tag, 0);
    }

    /**
     * @author: wangzw
     * @description: 取得两个分隔符之间的子串
     * @param str 字符串
     * @param open 要搜索的分隔子串1
     * @param close 要搜索的分隔子串2
     * @version: 1.0
     * @date: 2017/11/22 13:51
     */
    public static String substringBetween(String str, String open, String close) {
        return substringBetween(str, open, close, 0);
    }

    /**
     * @author: wangzw
     * @description: 取得两个分隔符之间的子串
     * @param str 字符串
     * @param open 要搜索的分隔子串1
     * @param close 要搜索的分隔子串2
     * @param fromIndex 从指定index处搜索
     * @version: 1.0
     * @date: 2017/11/22 13:52
     */
    public static String substringBetween(String str, String open, String close, int fromIndex) {
        if ((str == null) || (open == null) || (close == null)) {
            return null;
        }
        int start = str.indexOf(open, fromIndex);
        if (start != -1) {
            int end = str.indexOf(close, start + open.length());
            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }

     /* ============================================================================ */
     /*  Perl风格的chomp和chop函数。                                                 */
     /* ============================================================================ */

    /**
     * @author: wangzw
     * @description: 删除字符串末尾的换行符。如果字符串不以换行结尾，则什么也不做
     * @version: 1.0
     * @date: 2017/11/22 13:54
     */
    public static String chomp(String str) {
        if ((str == null) || (str.length() == 0)) {
            return str;
        }
        if (str.length() == 1) {
            char ch = str.charAt(0);
            if ((ch == '\r') || (ch == '\n')) {
                return EMPTY_STRING;
            } else {
                return str;
            }
        }
        int lastIdx = str.length() - 1;
        char last = str.charAt(lastIdx);
        if (last == '\n') {
            if (str.charAt(lastIdx - 1) == '\r') {
                lastIdx--;
            }
        } else if (last == '\r') {
        } else {
            lastIdx++;
        }
        return str.substring(0, lastIdx);
    }

    /**
     * @author: wangzw
     * @description: 删除字符串末尾的指定字符串。如果字符串不以该字符串结尾，则什么也不做
     * @version: 1.0
     * @date: 2017/11/22 13:54
     */
    public static String chomp(String str, String separator) {
        if ((str == null) || (str.length() == 0) || (separator == null)) {
            return str;
        }
        if (str.endsWith(separator)) {
            return str.substring(0, str.length() - separator.length());
        }
        return str;
    }

    /**
     * @author: wangzw
     * @description: 删除最后一个字符,如果字符串以\r\n结尾，则同时删除它们
     * @version: 1.0
     * @date: 2017/11/22 13:55
     */
    public static String chop(String str) {
        if (str == null) {
            return null;
        }
        int strLen = str.length();
        if (strLen < 2) {
            return EMPTY_STRING;
        }
        int lastIdx = strLen - 1;
        String ret = str.substring(0, lastIdx);
        char last = str.charAt(lastIdx);
        if (last == '\n') {
            if (ret.charAt(lastIdx - 1) == '\r') {
                return ret.substring(0, lastIdx - 1);
            }
        }
        return ret;
    }

     /* ============================================================================ */
     /*  反转字符串。                                                                */
     /* ============================================================================ */

    /**
     * @author: wangzw
     * @description: 反转字符串中的字符顺序
     * @version: 1.0
     * @date: 2017/11/22 13:49
     */
    public static String reverse(String str) {
        if ((str == null) || (str.length() == 0)) {
            return str;
        }

        return new StringBuffer(str).reverse().toString();
    }

     /* ============================================================================ */
     /*  取得字符串的缩略。                                                          */
     /* ============================================================================ */

    /**
     * @author: wangzw
     * @description: 将字符串转换成指定长度的缩略，例如： 将"Now is the time for all good men"转换成"Now is the time for..."
     * @param str 要检查的字符串
     * @param maxWidth 最大长度，不小于4，如果小于4，则看作4
     * @version: 1.0
     * @date: 2017/11/22 13:45
     */
    public static String abbreviate(String str, int maxWidth) {
        return abbreviate(str, 0, maxWidth);
    }

    /**
     * @author: wangzw
     * @description: 将字符串转换成指定长度的缩略，例如： 将"Now is the time for all good men"转换成"...is the time for..."
     * @param str 要检查的字符串
     * @param offset 左边界偏移量
     * @param maxWidth 最大长度，不小于4，如果小于4，则看作4
     * @version: 1.0
     * @date: 2017/11/22 13:45
     */
    public static String abbreviate(String str, int offset, int maxWidth) {
        if (str == null) {
            return null;
        }

        // 调整最大宽度
        if (maxWidth < 4) {
            maxWidth = 4;
        }

        if (str.length() <= maxWidth) {
            return str;
        }

        if (offset > str.length()) {
            offset = str.length();
        }

        if ((str.length() - offset) < (maxWidth - 3)) {
            offset = str.length() - (maxWidth - 3);
        }

        if (offset <= 4) {
            return str.substring(0, maxWidth - 3) + "...";
        }

        // 调整最大宽度
        if (maxWidth < 7) {
            maxWidth = 7;
        }

        if ((offset + (maxWidth - 3)) < str.length()) {
            return "..." + abbreviate(str.substring(offset), maxWidth - 3);
        }

        return "..." + str.substring(str.length() - (maxWidth - 3));
    }

     /* ============================================================================ */
     /*  比较两个字符串的异同。                                                      */
     /*                                                                              */
     /*  查找字符串之间的差异，比较字符串的相似度。                                  */
     /* ============================================================================ */

    /**
     * @author: wangzw
     * @description: 比较两个字符串，取得第二个字符串中，和第一个字符串不同的部分
     * @version: 1.0
     * @date: 2017/11/22 13:45
     */
    public static String difference(String str1, String str2) {
        if (str1 == null) {
            return str2;
        }
        if (str2 == null) {
            return str1;
        }
        int index = indexOfDifference(str1, str2);
        if (index == -1) {
            return EMPTY_STRING;
        }
        return str2.substring(index);
    }

    /**
     * @author: wangzw
     * @description: 比较两个字符串，取得两字符串开始不同的索引值
     * @version: 1.0
     * @date: 2017/11/22 13:44
     */
    public static int indexOfDifference(String str1, String str2) {
        if ((str1 == str2) || (str1 == null) || (str2 == null)) {
            return -1;
        }
        int i;
        for (i = 0; (i < str1.length()) && (i < str2.length()); ++i) {
            if (str1.charAt(i) != str2.charAt(i)) {
                break;
            }
        }
        if ((i < str2.length()) || (i < str1.length())) {
            return i;
        }
        return -1;
    }

    /**
     * @author: wangzw
     * @description: 取得两个字符串的相似度，0代表字符串相等，数字越大表示字符串越不像
     * @version: 1.0
     * @date: 2017/11/22 13:43
     */
    public static int getLevenshteinDistance(String s, String t) {
        s = defaultIfNull(s);
        t = defaultIfNull(t);
        int[][] d; // matrix
        int n; // length of s
        int m; // length of t
        int i; // iterates through s
        int j; // iterates through t
        char s_i; // ith character of s
        char t_j; // jth character of t
        int cost; // cost
        // Step 1
        n = s.length();
        m = t.length();
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        // Step 2
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }
        // Step 3
        for (i = 1; i <= n; i++) {
            s_i = s.charAt(i - 1);
            // Step 4
            for (j = 1; j <= m; j++) {
                t_j = t.charAt(j - 1);
                // Step 5
                if (s_i == t_j) {
                    cost = 0;
                } else {
                    cost = 1;
                }
                // Step 6
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + cost);
            }
        }
        // Step 7
        return d[n][m];
    }

    /**
     * @author: wangzw
     * @description: 取得最小数
     * @version: 1.0
     * @date: 2017/11/22 13:55
     */
    private static int min(int a, int b, int c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            a = c;
        }
        return a;
    }

}
