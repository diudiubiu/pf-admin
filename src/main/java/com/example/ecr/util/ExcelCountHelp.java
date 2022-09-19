package com.example.ecr.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.ecr.control.ExcelOperationControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.util.List;

public class ExcelCountHelp {
    static Logger log = LoggerFactory.getLogger(ExcelCountHelp.class);

    public static void main(String[] args) {
        final double d0e12 = 0.12;
        final double d0e0833 = 0.0833;
        final double d0e005 = 0.005;
        double dd, dg, dh, di, df;
        double dac2 = 0;
        double ac1 = 0, ac2 = 0, ac10 = 0, ac21 = 0;
        BufferedInputStream in = FileUtil.getInputStream("/Users/chenchencui/Downloads/ecr/August 2021.xls");
        ExcelReader reader = ExcelUtil.getReader(in);
        List<List<Object>> readAll = reader.read();
        //循环读取excel，每行数据，循环一次一行。
        for (List<Object> objects : readAll) {
            //excel中获取- D -列数据。
            String dStr = objects.get(4 - 1).toString();
            String fStr = objects.get(6 - 1).toString();
            //判断是否为数字。
            if (NumberUtil.isNumber(dStr) && NumberUtil.isNumber(fStr)) {
                dd = Double.parseDouble(dStr);
                //dg = NumberUtil.mul(dd, d0e12);//乘
                //dh = NumberUtil.mul(dd, d0e0833);//乘
                //di = NumberUtil.sub(dg, dh);//减
                dg = dd * d0e12;//乘
                dh = dd * d0e0833;//乘
                di = dg - dh;//减
                df = Double.parseDouble(dStr);
                //todo challan Pdf中 ac1 = g+i
                //汇总 ac1 = 四舍五入（g）+（i）
                ac1 += NumberUtil.round(dg, 0).doubleValue() + NumberUtil.round(di, 0).doubleValue();
                //汇总 dac2 = 四舍五入2位小数（f*0.005）
                dac2 += NumberUtil.round(NumberUtil.mul(df, d0e005),2).doubleValue();
                //汇总 ac10 = 四舍五入（h）
                ac10 += NumberUtil.round(dh, 0).doubleValue();
                //汇总 ac10 = 四舍五入（f）
                ac21 +=NumberUtil.round(NumberUtil.mul(df, d0e005), 0).doubleValue();
                //log.info("\nd:{}\ng:{}\nh:{}\ni:{}", dd, dg, dh, di);
            }
        }
        //ac2 = 四舍五入（dac2）
        ac2 = NumberUtil.round(dac2, 0).doubleValue();
        //log.info("\nac1:{}\nac2:{}\nac10:{}\nac21:{}", ac1, ac2, ac10, ac21);
        log.info("\nac1:{}\nac2:{}\nac10:{}\nac21:{}",
                ExcelOperationHelp.lakhFormattedComma(ac1),
                ExcelOperationHelp.lakhFormattedComma(ac2),
                ExcelOperationHelp.lakhFormattedComma(ac10),
                ExcelOperationHelp.lakhFormattedComma(ac21));


    }
}
//ac1:83,355
//        ac2:2,660
//        ac10:44,316
//        ac21:2,662

