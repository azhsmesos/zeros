package com.github.ticket.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysUser {

    private Integer id;

    private String username;

    private String telephone;

    private String mail;

    private String password;

    private Integer deptId;

    private Integer status;

    private String remark;

    private String operator;

    private Date operateTime;

    private String operateIp;
}
