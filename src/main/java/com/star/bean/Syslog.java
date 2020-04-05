package com.star.bean;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author duxing
 * @version V1.0
 * @Package com.star.bean
 * @date 2020/04/05 15:41
 */
@Data
public class Syslog {
    private Integer id;
    private String username;
    private String operation;
    private Integer time;
    private String method;
    private String params;
    private String ip;
    private Timestamp createTime;

}    