package com.star.bean;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author duxing
 * @version V1.0
 * @Package com.star.bean
 * @date 2020/04/01 22:56
 */
@Data
public class Student {
    private static final long serialVersionUID = -339516038496531943L;
    private Long id;
    private String username;
    private String password;
    private String icon;
    private String email;
    private String nickName;
    private String note;
    private Timestamp createTime;
    private Timestamp loginTime;
    private Integer status;


}    