package com.example.mysql2es.mybatis.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @Author: admin
 * @Description: 角色实体类
 * @BelongsProject: demo
 * @BelongsPackage: com.example.demo.mybatis.model
 * @CreateTime: 2020-11-17 23:28:39
 */
@Data
@ToString
public class SysRoleExtends {

    private Long id;
    private String roleName;
    private Integer enabled;
    private Long createBy;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private SysUser user;

}
