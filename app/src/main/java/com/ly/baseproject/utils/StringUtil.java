package com.ly.baseproject.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Administrator on 2017/2/7.
 */

public class StringUtil {

    /**
     * 判断字符不为空返回相应的值
     *
     * @param input    输入值
     * @param emptyStr 空值
     * @return
     */
    public static String getFormatStr(String input, String emptyStr) {
        if (isEmpty(input)) {
            return emptyStr;
        } else {
            return input;
        }
    }

    /**
     * 描述：判断一个字符串是否为null或空值.
     *
     * @param str 指定的字符串
     * @return true or false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0 || str.trim().equals("null");
    }

    /**
     * 描述：不足2个字符的在前面补“0”.
     *
     * @param str 指定的字符串
     * @return 至少2个字符的字符串
     */
    public static String strFormatZero(String str) {
        try {
            if (str.length() <= 1) {
                str = "0" + str;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

//    public static String getUnderlinePhoneStr(String phone) {
//        if (RegexUtil.isMobilePhone(phone) && phone.length() == 11) {
//            String start = phone.substring(0, 3);
//            String middle = phone.substring(3, 7);
//            String end = phone.substring(7, 11);
//            return start + "-" + middle + "-" + end;
//        } else {
//            return phone;
//        }
//    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * 添加显示url canback=false
     *
     * @param result
     * @return
     */
    public static String getDisPlayUrl(String result) {
        if (!isEmpty(result)) {
            if (result.contains("canback=false")) {
                return result;
            }
            return result.contains("?") ? result + "&canback=false" : result + "?canback=false";
        } else {
            return "";
        }
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 字符串转布尔值
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 将一个InputStream流转换成字符串
     *
     * @param is
     * @return
     */
    public static String toConvertString(InputStream is) {
        StringBuffer res = new StringBuffer();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader read = new BufferedReader(isr);
        try {
            String line;
            line = read.readLine();
            while (line != null) {
                res.append(line);
                line = read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != isr) {
                    isr.close();
                    isr.close();
                }
                if (null != read) {
                    read.close();
                    read = null;
                }
                if (null != is) {
                    is.close();
                    is = null;
                }
            } catch (IOException e) {
            }
        }
        return res.toString();
    }

    /**
     * 获取字符串中文字符的长度（每个中文算2个字符）.
     *
     * @param str 指定的字符串
     * @return 中文字符的长度
     */
    public int chineseLength(String str) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        if (!isEmpty(str)) {
            for (int i = 0; i < str.length(); i++) {
                /* 获取一个字符 */
                String temp = str.substring(i, i + 1);
                /* 判断是否为中文字符 */
                if (temp.matches(chinese)) {
                    valueLength += 2;
                }
            }
        }
        return valueLength;
    }

    /**
     * 描述：获取字符串的长度.
     *
     * @param str 指定的字符串
     * @return 字符串的长度（中文字符计2个）
     */
    public static int strLength(String str) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                // 获取一个字符
                String temp = str.substring(i, i + 1);
                // 判断是否为中文字符
                if (temp.matches(chinese)) {
                    // 中文字符长度为2
                    valueLength += 2;
                } else {
                    // 其他字符长度为1
                    valueLength += 1;
                }
            }
        }
        return valueLength;
    }

    /**
     * 描述：获取指定长度的字符所在位置.
     *
     * @param str  指定的字符串
     * @param maxL 要取到的长度（字符长度，中文字符计2个）
     * @return 字符的所在位置
     */
    public int subStringLength(String str, int maxL) {
        int currentIndex = 0;
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < str.length(); i++) {
            // 获取一个字符
            String temp = str.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为2
                valueLength += 2;
            } else {
                // 其他字符长度为1
                valueLength += 1;
            }
            if (valueLength >= maxL) {
                currentIndex = i;
                break;
            }
        }
        return currentIndex;
    }

    /**
     * 截取url或路径的文件的名字
     *
     * @param fileStr url或路径
     * @return
     */
    public String subFileStrName(String fileStr) {
        if (fileStr.indexOf("http://") != -1) {
            return fileStr.substring(fileStr.lastIndexOf("/") + 1,
                    fileStr.length());
        } else {
            return fileStr.substring(fileStr.lastIndexOf(File.separator) + 1,
                    fileStr.length());
        }
    }

    /**
     * 描述：手机号格式验证.
     *
     * @param str 指定的手机号码字符串
     * @return 是否为手机号码格式:是为true，否则false
     */
    public Boolean isMobileNo(String str) {
        Boolean isMobileNo = false;
        try {
            Pattern p = Pattern
                    .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
            Matcher m = p.matcher(str);
            isMobileNo = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isMobileNo;
    }

    /**
     * 描述：是否只是字母和数字.
     *
     * @param str 指定的字符串
     * @return 是否只是字母和数字:是为true，否则false
     */
    public Boolean isNumberLetter(String str) {
        Boolean isNoLetter = false;
        String expr = "^[A-Za-z0-9]+$";
        if (str.matches(expr)) {
            isNoLetter = true;
        }
        return isNoLetter;
    }

    /**
     * 描述：是否只是数字.
     *
     * @param str 指定的字符串
     * @return 是否只是数字:是为true，否则false
     */
    public Boolean isNumber(String str) {
        Boolean isNumber = false;
        String expr = "^[0-9]+$";
        if (str.matches(expr)) {
            isNumber = true;
        }
        return isNumber;
    }

    /**
     * 描述：是否是中文.
     *
     * @param str 指定的字符串
     * @return 是否是中文:是为true，否则false
     */
    public Boolean isChinese(String str) {
        Boolean isChinese = true;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                // 获取一个字符
                String temp = str.substring(i, i + 1);
                // 判断是否为中文字符
                if (temp.matches(chinese)) {
                } else {
                    isChinese = false;
                }
            }
        }
        return isChinese;
    }

    /**
     * 描述：是否包含中文.
     *
     * @param str 指定的字符串
     * @return 是否包含中文:是为true，否则false
     */
    public Boolean isContainChinese(String str) {
        Boolean isChinese = false;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                // 获取一个字符
                String temp = str.substring(i, i + 1);
                // 判断是否为中文字符
                if (temp.matches(chinese)) {
                    isChinese = true;
                } else {

                }
            }
        }
        return isChinese;
    }

    /**
     * 描述：从输入流中获得String.
     *
     * @param is 输入流
     * @return 获得的String
     */
    public String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            // 最后一个\n删除
            if (sb.indexOf("\n") != -1
                    && sb.lastIndexOf("\n") == sb.length() - 1) {
                sb.delete(sb.lastIndexOf("\n"), sb.lastIndexOf("\n") + 1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 描述：截取字符串到指定字节长度.
     *
     * @param str    the str
     * @param length 指定字节长度
     * @return 截取后的字符串
     */
    public String cutString(String str, int length) {
        return cutString(str, length, "");
    }

    /**
     * 描述：截取字符串到指定字节长度.
     *
     * @param str    文本
     * @param length 字节长度
     * @param dot    省略符号
     * @return 截取后的字符串
     */
    public String cutString(String str, int length, String dot) {
        int strBLen = strlen(str, "GBK");
        if (strBLen <= length) {
            return str;
        }
        int temp = 0;
        StringBuffer sb = new StringBuffer(length);
        char[] ch = str.toCharArray();
        for (char c : ch) {
            sb.append(c);
            if (c > 256) {
                temp += 2;
            } else {
                temp += 1;
            }
            if (temp >= length) {
                if (dot != null) {
                    sb.append(dot);
                }
                break;
            }
        }
        return sb.toString();
    }

    /**
     * 描述：截取字符串从第一个指定字符.
     *
     * @param str1   原文本
     * @param str2   指定字符
     * @param offset 偏移的索引
     * @return 截取后的字符串
     */
    public String cutStringFromChar(String str1, String str2, int offset) {
        if (isEmpty(str1)) {
            return "";
        }
        int start = str1.indexOf(str2);
        if (start != -1) {
            if (str1.length() > start + offset) {
                return str1.substring(start + offset);
            }
        }
        return "";
    }

    /**
     * 描述：获取字节长度.
     *
     * @param str     文本
     * @param charset 字符集（GBK）
     * @return the int
     */
    public int strlen(String str, String charset) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int length = 0;
        try {
            length = str.getBytes(charset).length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }

    /**
     * 获取大小的描述.
     *
     * @param size 字节个数
     * @return 大小的描述
     */
    public String getSizeDesc(long size) {
        String suffix = "B";
        if (size >= 1024) {
            suffix = "K";
            size = size >> 10;
            if (size >= 1024) {
                suffix = "M";
                // size /= 1024;
                size = size >> 10;
                if (size >= 1024) {
                    suffix = "G";
                    size = size >> 10;
                    // size /= 1024;
                }
            }
        }
        return size + suffix;
    }

    /**
     * 描述：ip地址转换为10进制数.
     *
     * @param ip the ip
     * @return the long
     */
    public long ip2int(String ip) {
        ip = ip.replace(".", ",");
        String[] items = ip.split(",");
        return Long.valueOf(items[0]) << 24 | Long.valueOf(items[1]) << 16
                | Long.valueOf(items[2]) << 8 | Long.valueOf(items[3]);
    }

    /**
     * 判断字符串是否是数字
     *
     * @param str
     * @return
     */
    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 按逗号截取成String[]
     *
     * @param str
     * @return
     */
    public static String[] splitByComma(String str) {
        return str.split(",");
    }

    /**
     * 从list<String>种获取相似字符串
     *
     * @param str
     * @param list
     * @return
     */
    public List<String> getSimilarStrFromList(String str, List<String> list) {
        List<String> list2 = new ArrayList<String>();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String string2 = (String) iterator.next();
            if (string2.indexOf(str) != -1) {
                list2.add(string2);
            }
        }
        return list2;
    }


    /**
     * 格式化价格保留2位小数 如 12.1==>12.00 11 ==>11.00
     *
     * @param d
     * @return
     */
    public static String formatPrice(double d) {
        DecimalFormat gf = new DecimalFormat("0.00");
        return gf.format(d);
    }

    /**
     * 字符串数组格式为字符串
     *
     * @param strs
     * @return
     */
    public static String arrayToString(String[] strs) {
        String temp = "";
        for (int i = 0; i < strs.length; i++) {
            if (i == 0) {
                temp = temp + "" + strs[i];
            } else {
                temp = temp + "," + strs[i];
            }

        }
        return temp;
    }

    public static String arrayToStrings(String[] strs) {
        String temp = "";
        for (int i = 0; i < strs.length; i++) {
            if (i == 0) {
                temp = temp + "" + strs[i];
            } else {
                temp = temp + ";" + strs[i];
            }

        }
        return temp;
    }

    /**
     * 格式化手机号码为132****2131
     *
     * @param phoneNum
     * @return
     */
    public static String formatPhone(String phoneNum) {
        String temp = "";
        if (null != phoneNum && phoneNum.length() == 11) {
            temp = phoneNum.substring(0, 3) + "****"
                    + phoneNum.substring(7, 11);
        } else {
            temp = phoneNum;
        }
        return temp;
    }

    /**
     * 格式化手机号码为132xxxx2131
     *
     * @param phoneNum
     * @return
     */
    public static String formatPhonex(String phoneNum) {
        String temp = "";
        if (null != phoneNum && phoneNum.length() == 11) {
            temp = phoneNum.substring(0, 3) + "xxxx"
                    + phoneNum.substring(7, 11);
        } else {
            temp = phoneNum;
        }
        return temp;
    }

    /**
     * 全角/半角转换
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    // 替换、过滤特殊字符
    public static String StringFilter(String str) throws PatternSyntaxException {
        str = str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("！", "!");//替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 返回电话字符串数组
     *
     * @param tel
     * @return 电话字符串数组
     */
    public static String[] getNumbers(String tel) {
        String[] numbers = new String[]{};
        if (!isEmpty(tel)) {
            numbers = tel.split(",");
        }
        return numbers;
    }

    /**
     * @param value
     * @return
     */
    public static String formatIntValue(int value) {
        return value == 0 ? "暂无" : String.valueOf(value);
    }

    /**
     * @param value
     * @return
     */
    public static String formatDoubleValue(double value) {
        return value == 0.0 ? "暂无" : String.valueOf(value);
    }

//    /**
//     * 获取文章显示地址
//     *
//     * @param articleId 文章id
//     * @return 文章详情地址
//     */
//    public static String getArticleDisPlayUrl(long articleId) {
//        return AppConfig.ServerAddress + "/tjtt/appNewsDetail?id=" + articleId;
////        return "http://192.168.0.114:1337"+"/tjtt/appNewsDetail?id=" + articleId;
//    }

    /**
     * 获取格式化的阅读量
     *
     * @return
     */
    public static BigDecimal getFormatReadNum(float readNum) {
        BigDecimal read = new BigDecimal(readNum);
        float readDouble = readNum / 10000;
        BigDecimal bd = new BigDecimal(readDouble);
        bd = bd.setScale(1, BigDecimal.ROUND_DOWN);
        return bd;
    }

    /**
     * 格式化阅读量信息
     * * 0-9999直接返回，大于9999显示1 1.x
     *
     * @param readNum 阅读量
     * @return
     */
    public static String getStrFromatReadNum(int readNum) {
        if (readNum < 1) {
            return "0";
        }
        if (readNum < 9999) {
            return String.valueOf((int) readNum);
        } else {
            return "" + getFormatReadNum(readNum) + "万";
        }
    }

    public static String[] getLbas(String label) {
        if (isEmpty(label)) {
            return null;
        }
        String a = "，";
        //将因为逗号转为中文逗号
        String result = label.replaceAll(",", a);
        String[] lbes = result.split(a);
        return lbes;
    }

    public static String[] getImgs(String label) {
        if (isEmpty(label)) {
            return null;
        }
        String[] lbes = label.split(",");
        return lbes;
    }

    public static String createDeviceId() {
        String deviceId = "UXAPP" + getFixLenthString(11);
        return deviceId;
    }

    /*
     * 返回长度为【strLength】的随机数，在前面补0
     */
    private static String getFixLenthString(int strLength) {

        Random rm = new Random();

        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);

        // 返回固定的长度的随机数
        return fixLenthString.substring(1, strLength + 1);
    }

    public static String getNumPercentage(int progress, int max) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(0);
        String result = numberFormat.format((float) progress / (float) max * 100) + "%";
        return result;
    }

    public static String fromatCityInfo(String city) {
        if (isEmpty(city)) {
            return "";
        }
        String result;
        if (city.endsWith("市")){
           result = city.substring(0,city.length()-1);
        }else {
            result = city;
        }

        return result;
    }
}
