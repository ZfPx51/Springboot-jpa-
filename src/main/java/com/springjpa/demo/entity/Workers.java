package com.springjpa.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "workers")
public class Workers {
    @Id    //主键id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    @Column(name = "id")//数据库字段名
    private String userId;

    private String collectUser;

    private String address;

    private float weight;

    private String userTel;


    private Integer point;

    private String rubbishType;

    private String collectType;

    private String unit;

    private String build;
    @Column(name = "createtime")//数据库字段名
    private String createTime;

    private String identityInfo;

    private Date putTime;

    private String village;

    private String userName;

    private String imgPath;
}
