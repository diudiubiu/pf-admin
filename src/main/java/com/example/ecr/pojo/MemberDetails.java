package com.example.ecr.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Member Details : 成员信息详情
 *
 * @author Hs
 */
@Data

public class MemberDetails {


    private int SiNo;
    /**
     * 公司
     */
    private String UAN;
    /** name as per 名称依据 */
    /**
     * ecr
     */
    private String ECR;
    /**
     * uan知识库
     */
    private String UANRepository;
    private Double rowHigh;

    /** Wages 工资 */
    /**
     * 总
     */
    private String Gross;
    private String EPF;
    private String EPS;
    private String EDLI;

    /**
     * Contribution Remitted 已汇出的供款
     */
    private String EE;
    private String CREPS;
    private String ER;
    private String NCPDays;
    /**
     * Refunds  退款
     */
    private String Refunds;

    /** PMRPY / ABRY Benefit 效益 */
    /**
     * pension share 养老金份额
     */
    private String PensionShare;
    private String ERPFShare;
    private String EEShare;

    /**
     * PostingLocation of the member 成员位置
     */
    private String PostingLocationOfTheMember;
}
