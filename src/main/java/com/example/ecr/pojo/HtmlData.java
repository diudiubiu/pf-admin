package com.example.ecr.pojo;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 页面数据
 *
 * @author
 */
@Data
public class HtmlData {
    /**
     * 对象列表总个数
     * 也是人数
     */
    private int totalNumber;
    /**
     * 总共多少页
     */
    private int pageCount;
    /**
     * false=独立页面
     * true=与左右表格共享页面
     */
    private boolean independentPage;
    //G总
    private String totalEPFContributionRemitted;
    //H总
    private String totalEPSContributionRemitted;
    //I总
    private String totalEPFEPSContributionRemitted;
    /**前端提交所得*/
    private EmployeeData employeeData;
    /**
     * 1:{id,name}
     * 2:{id,name}
     */
    Map<Integer, List<MemberDetails>> pageDate;
    /**后台计算所得*/

}
