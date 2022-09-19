package com.example.ecr.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class OriginalExcel {
    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    private Long id;

    private String excelPath;

    //private String excelName;

    private Date uploadDate;

    //excel总行数
    private int subScribers;

    private boolean txtState;
    private boolean ecrState;
    private boolean paymentState;
    private boolean challansState;

}
