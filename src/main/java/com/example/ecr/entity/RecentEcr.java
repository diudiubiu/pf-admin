package com.example.ecr.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class RecentEcr {

    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private RecentChallans recentChallans;
    private String establishmentId;    //MRNOI2513599000 //DSNHP2111338000

    //private Long sNo;
    private String trrn;

    private String wageMonth;
    private String ecrType;
    private String salaryDisbDate;
    private String contrRate;
    private String uploadDate;
    private String status;
    private boolean uploadStatus;

    //txt下载
    private String ecrFilePath;
    //pdf下载
    private String ecrStatementPath;

    //private boolean hasUploadFile;


}
