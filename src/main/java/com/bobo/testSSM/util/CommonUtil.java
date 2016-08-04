package com.bobo.testSSM.util;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * <Description> 编码常用类<br>
 */
public final class CommonUtil {

    /**
     * random
     */
    private static final Random RANDOM = new Random();

    /**
     * Description: 字符串是为NULL或为空<br>
     * 
     * @param str <br>
     * @return <br>
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    /**
     * Description:字符串不为NULL也不为空 <br>
     * 
     * @param str <br>
     * @return <br>
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * Description: 判断数组是否为NULL或为空<br>
     * 
     * @param t <br>
     * @param <T> <br>
     * @return <br>
     */
    public static <T> boolean isEmpty(T[] t) {
        return t == null || t.length == 0;
    }

    /**
     * 判断数组不为NULL也不为空
     * 
     * @param t <br>
     * @param <T> <br>
     * @return <br>
     */
    public static <T> boolean isNotEmpty(T[] t) {
        return !isEmpty(t);
    }

    /**
     * Description: 集合是否为NULL或为空<br>
     * 
     * @param col <br>
     * @return <br>
     */
    public static boolean isEmpty(Collection<?> col) {
        return col == null || col.isEmpty();
    }

    /**
     * Description:集合不为NULL也不为空 <br>
     * 
     * @param col <br>
     * @return <br>
     */
    public static boolean isNotEmpty(Collection<?> col) {
        return !isEmpty(col);
    }

    /**
     * Description: map是否为NULL或为空<br>
     * 
     * @param map <br>
     * @return <br>
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * Description:map不为NULL也不为空 <br>
     * 
     * @param map <br>
     * @return <br>
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * Description: <br>
     * 
     * @param str <br>
     * @return <br>
     */
    public static String lowerCaseFirstChar(String str) {
        if (isNotEmpty(str)) {
            char firstChar = str.charAt(0);
            if (Character.isUpperCase(firstChar)) {
                StringBuilder sb = new StringBuilder(str);
                sb.setCharAt(0, Character.toLowerCase(firstChar));
                str = sb.toString();
            }
        }
        return str;
    }

    /**
     * 消息格式化
     * 
     * @param message message <br>
     * @param params params <br>
     * @return String <br>
     */
    public static String messageFormat(String message, Object... params) {
        return isNotEmpty(params) ? MessageFormat.format(message, params) : message;
    }

    /**
     * 判断是否是空对象
     * 
     * @param obj obj <br>
     * @return boolean <br>
     */
    public static boolean isNull(Object obj) {
        return null == obj;
    }

    /**
     * 获取事务ID
     * 
     * @return 事务ID <br>
     */
    public static String getTransactionID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Description: 获取指定位数的随机数<br>
     * 
     * @param size <br>
     * @return <br>
     */
    public static String getRandomNumber(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append((char) ('0' + RANDOM.nextInt(10)));
        }
        return sb.toString();
    }

    /**
     * Description: <br>
     * 
     * @param size <br>
     * @return <br>
     */
    public static String getRandomChar(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            switch (RANDOM.nextInt(10) % 3) {
                case 0:
                    sb.append((char) ('0' + RANDOM.nextInt(10)));
                    break;
                case 1:
                    sb.append((char) ('a' + RANDOM.nextInt(26)));
                    break;
                case 2:
                    sb.append((char) ('A' + RANDOM.nextInt(26)));
                    break;
                default:
                    ;
            }
        }
        return sb.toString();
    }

    /**
     * Description: <br>
     * 
     * @param obj <br>
     * @return <br>
     */
    public static String getString(Object obj) {
        String result = null;
        if (obj != null) {
            result = obj instanceof String ? (String) obj : obj.toString();
        }
        return result;
    }

    /**
     * 
     * Description:getDate <br> 
     *  
     * @param time <br
     * @return <br>
     */
    public static Date getDate(Long time) {
        if (time != null) {
            return new Date(time);
        }
        return null;
    }

    
    /**
     * Description: 判断是不是邮箱<br>
     * 
     * @param email <br>
     * @return <br>
     * @throws UtilException <br>
     */
	public static boolean isEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	/**
     * Description: 判断是不是手机号<br>
     * 
     * @param mobiles <br>
     * @return <br>
     * @throws UtilException <br>
     */
	public static boolean isMobileNo(String mobiles) {
		boolean flag = false;
		try {
			Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
}
