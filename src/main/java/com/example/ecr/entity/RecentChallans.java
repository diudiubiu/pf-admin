package com.example.ecr.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class RecentChallans {

    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private RecentEcr recentEcr;

    private String AC1;
    private String AC2;
    private String AC10;
    private String AC21;
    private String AC22;
    private String totalAmt;
    private String CRN;
    private boolean uploadStatus;

    private String challanReceiptPath;
    private String paymentReceiptPath;

    //private boolean hasUploadFile;

}
