package com.example.ecr.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data

public class ChallanData {

    private Long id;
    private String trrnNumber;
    private String canNumber;
}
