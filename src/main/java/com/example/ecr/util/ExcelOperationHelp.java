package com.example.ecr.util;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hs
 */
public class ExcelOperationHelp {
    /**
     * 判断字符串 是否为数字（小数），并四舍五入不保留小数点
     *
     * @param str
     * @return
     */
    public static String isNumberForRound(String str) {
        boolean strResult = NumberUtil.isNumber(str);
        if (strResult) {
            return NumberUtil.toStr(NumberUtil.round(str, 0));
        }
        return str;
    }

    /**
     * 字符串去除空格
     *
     * @param strObj
     * @return
     */

    public static String strTrimExt(String strObj) {
        //.replaceAll("\u0008", "")
        //.replaceAll("\\s*", "")
        String str = StrUtil.trim(strObj).replaceAll("\u00a0", "").replaceAll("\\s*", "");
        return str;
    }

    /**
     * 空格 替换 html \\<br/>
     *
     * @param strUan
     * @return
     */
    public static String uanStrOpt(String strUan) {
        String str = strTrimExt(strUan);
        return str.length() > 8 ? stringInsertByInterval(str, "<br/>", 8) : str;
    }

    public static String stringInsertByInterval(String original,
                                                String insertString, int interval) {
        if (original == null) return "";
        Integer len = original.length();
        if (interval >= len) return original;

        String rtnString = original;
        if (original.length() > interval) {
            List<String> strList = new ArrayList<String>();
            Pattern p = Pattern.compile("(.{" + interval + "}|.*)");
            Matcher m = p.matcher(original);
            while (m.find()) {
                strList.add(m.group());
            }
            strList = strList.subList(0, strList.size() - 1);
            rtnString = StringUtils.join(strList, insertString);
        }
        return rtnString;
    }

    /**
     * 串第12位 替换 html \\<br/>
     *
     * @param strEcr
     * @return
     */
    public static String ecrStrOpt(String strEcr) {
        if (strEcr.length() < 12) {
            return strEcr;
        }
        StringBuilder sb = new StringBuilder(strEcr);
        sb.insert(12, "<br/>");
        String str = sb.toString();
        return str;
    }

    /**
     * 判断字符串中的空格个数，给rowHigh赋值。
     * 即表格中每行的动态高。
     *
     * @param strEcr
     * @return
     */
    public static double rowHigh(String strEcr) {
        int high = 55;
        int maxStrLen = 10;
        int spaceCaseNumber = 0;

        int len = strEcr.length();
        if (len >= maxStrLen) {
            spaceCaseNumber = 1;
        }
        for (int i = 0; i < len; i++) {
            char cEcr = strEcr.charAt(i);
            //空格
            if (Character.isSpaceChar(cEcr)) {
                spaceCaseNumber++;
            }
        }
        switch (spaceCaseNumber) {
            case 2:
                high = 66;
                break;
            case 3:
                high = 88;
                break;
            default:
                high = 55;
        }
        return high;
    }

    /**
     * 四舍五入，不保留小数点。
     * 显示 #，##，### 数字格式。
     *
     * @param d
     * @return
     */
    public static String lakhFormattedComma(Double d) {
        String dStr = NumberUtil.round(d, 0).toString();
        String out = "";
        int len = dStr.length();
        if (len < 3) {
            return dStr;
        }

        out = dStr.substring(len - 3);
        for (int i = len - 5; i >= 0; i = i - 2) {
            out = dStr.substring(i, i + 2) + "," + out;
            if (i == 1) {
                out = dStr.substring(0, 1) + "," + out;
            }
        }
        return out;
    }


    public static void main(String[] args) {

        //boolean strResult = NumberUtil.isNumber("9339.134444");

        //System.out.printf(NumberUtil.toStr(NumberUtil.round("9339.734444",0)));
        // String str =" 12e 3 4 ";

        //System.out.printf( StrUtil.trim(str));

        // String str = sswr("Rahini");
        //System.out.printf(str);

        // rowHigh("2");

        //"Prithika Sathishkumar"
        //int len = "SANDEEP SINGH".length();
        //
        //String str = StrUtil.replaceIgnoreCase("SA NDEE PSINGH", " ", "\n");
        //
        //boolean b = Character.isUpperCase('K');
        //System.out.println(b);
        //
        //
        //System.out.println(str);
        //String s = StrUtil.padPre("abcd", 1, "ddd");
        //System.out.println(s);

        //double d = rowHigh("HEMALATHA R");
        //System.out.println( d);

        //System.out.println( Character.isSpaceChar(' '));


        //1,59,085
        //48,661
        //String str1 = lakhFormattedComma(14804.76000000002);
        //String str2 = lakhFormattedComma(99048661.1234);
        //System.out.println(str1);
        //
        //System.out.println(str2);
        //
        //
        //StringBuilder sb = new StringBuilder("DharmendraKumar");
        //sb.insert(12, "<br/>");
        //String str = sb.toString();
        //System.out.println(str);
        String str = uanStrOpt("SANGEETHAELUMALAI");

        System.out.println(str);

    }
}