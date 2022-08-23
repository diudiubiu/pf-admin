package com.example.ecr.util;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.util.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hs
 */
public class ExcelOperationHelp {

    static Logger log = LoggerFactory.getLogger(ExcelOperationHelp.class);

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
     * @param inStr
     * @return
     */

    public static String uanStrOpt(String inStr) {
        if (inStr.length() <= 8) {
            return inStr;
        }
        String[] strArr = inStr.split("\\ ");
        if (strArr.length == 1) {
            inStr = stringInsertByInterval(inStr, "<br/>", 8);
        }

        String inStr_m8 = "";

        for (int i = 0; i < strArr.length; i++) {

            if (strArr[i].length() > 8) {
                inStr_m8 += stringInsertByInterval(strArr[i], "<br/>", 8);
                if (strArr.length == 2) {
                    inStr_m8 += "<br/>";
                }
            } else {
                inStr_m8 += strArr[i] + "<br/>";
            }
        }
        if (strArr.length == 4) {
            int idx = 0;
            if ((idx = inStr_m8.indexOf("<br/>")) > -1) {
                if (idx < 8) {
                    inStr_m8 = inStr_m8.replaceFirst("<br/>", "&nbsp;");
                }
            }
        }

        if (strArr.length == 3) {
            if(strArr[0].length()==1){
                inStr_m8 = inStr_m8.replaceFirst("<br/>", "&nbsp;");
            }
        }
        inStr_m8 = StrUtil.removeSuffix(inStr_m8, "<br/>");
        //log.info("{},{}", inStr, inStr_m8);

        return inStr_m8;
    }

    public static String uanStris8(String strUan) {
        String str = strTrimExt(strUan);
        return str.length() > 8 ? stringInsertByInterval(str, "<br/>", 8) : str;
    }

    public static void main(String[] args) {

        //int i = StrUtil.split("Rajdhani<br/>aaa", "<br/>").size();

        //Meena Anbazhagan
        // String str1 = ExcelOperationHelp.uanStrOpt("Roja Thanigachalam");
        //String str1 = ExcelOperationHelp.uanStrOpt("Sangeetha R\n");

        // String str1 = ExcelOperationHelp.uanStrOpt("M Sai Kumudha Priya");
        //String str3 = stringInsertByInterval("Sangeetha R", "<br/>", 8);

        String inStr = "Dhanalakshmi Kamalakannan";
        uanStrOpt(inStr);
    }

    public static String stringInsertByInterval(String original, String insertString, int interval) {
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
     * 判断字符串中的空格个数，给rowHigh赋值。
     * 即表格中每行的动态高。
     *
     * @param strEcr
     * @return
     */
    public static double rowHigh(String strEcr) {
        int afewLines = StrUtil.split(uanStrOpt(strEcr), "<br/>").size();
        int high = 54;
        if (afewLines == 1) {
            high = 54;
        }
        if (afewLines == 2) {
            high = 55;
        }
        if (afewLines == 3) {
            high = 66;
        }
        return high;
    }

    /**
     * 四舍五入，不保留小数点。
     * 显示 #，##，### 数字格式。
     *
     * @param value
     * @return
     */
    public static String lakhFormattedComma(double value) {
        if (value < 1000) {
            return format("###", value);
        } else {
            double hundreds = value % 1000;
            int other = (int) (value / 1000);
            return format(",###", other) + ',' + format("000", hundreds);
        }
    }

    private static String format(String pattern, Object value) {
        return new DecimalFormat(pattern).format(value);
    }


}
