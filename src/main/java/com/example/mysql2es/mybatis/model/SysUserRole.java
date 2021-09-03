package com.example.mysql2es.mybatis.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @Author: admin
 * @Description: 用户角色实体类
 * @BelongsProject: demo
 * @BelongsPackage: com.example.demo.mybatis.model
 * @CreateTime: 2020-11-17 22:42
 */
@Data
@ToString
public class SysUserRole {

    private Long id;
    private Long userId;
    private Long roleId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
