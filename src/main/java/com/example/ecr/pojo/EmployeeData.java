package com.example.ecr.pojo;

import cn.hutool.core.util.StrUtil;
import com.example.ecr.util.ExcelOperationHelp;
import lombok.Data;

/**
 * @author
 */
@Data
public class EmployeeData {

    private String wageMonth;
    private String returnMonth;
    private String salaryDisbursementDate;
    private String uploadedDateTime;
    private String exemptionStatus;
    private String trrnNumber;
    private String remarks;
    private String ecrId;
    private String aadhaarNotSeededMember;
    private String totalRefundAdvance;
    private String employeeEPFShare;
    private String employerEPSShare;
    private String employerEPFShare;
    private String abryBenefitRemarks;

    public String getEmployeeEPFShare() {
        return employeeEPFShare;
    }

    public void setEmployeeEPFShare(String employeeEPFShare) {
        this.employeeEPFShare = ExcelOperationHelp.lakhFormattedComma(Double.valueOf(employeeEPFShare));
    }

    public String getEmployerEPSShare() {
        return employerEPSShare;
    }

    public void setEmployerEPSShare(String employerEPSShare) {
        this.employerEPSShare = ExcelOperationHelp.lakhFormattedComma(Double.valueOf(employerEPSShare));
    }

    public String getEmployerEPFShare() {
        return employerEPFShare;
    }

    public void setEmployerEPFShare(String employerEPFShare) {
        if(StrUtil.isEmpty(employerEPFShare)){
            this.employerEPFShare = employerEPFShare;
        }else{
            this.employerEPFShare = ExcelOperationHelp.lakhFormattedComma(Double.valueOf(employerEPFShare));
        }
    }
}
