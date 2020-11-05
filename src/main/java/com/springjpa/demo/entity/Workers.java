package com.springjpa.demo.entity;

import com.unicorn.core.domain.DefaultIdentifiable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "workers")
public class Workers extends DefaultIdentifiable {

    private String collectUser;

    private String address;

    private String weight;

    private String userTel;

    private String userId;

    private String point;

    private String rubbishType;

    private String collectType;

    private String unit;

    private String build;

    private String createTime;

    private String identityInfo;

    private String putTime;

    private String village;

    private String userName;

    private String imgPath;


}
