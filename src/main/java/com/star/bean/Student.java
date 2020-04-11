package com.star.bean;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
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
    @Min(value = 100,message = "最小的数字为100")
    private Long id;
    private String username;
    private String password;
    private String icon;
    @Email(message =  "友情提示您的邮箱格式错了")
    private String email;
    private String nickName;
    private String note;
    private Timestamp createTime;
    private Timestamp loginTime;
    @Min(0)
    @Max(1)
    private Integer status;


}    