package com.example.mysql2es.mybatis.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @Author: admin
 * @Description: 角色权限实体类
 * @BelongsProject: demo
 * @BelongsPackage: com.example.demo.mybatis.model
 * @CreateTime: 2020-11-17 23:37:53
 */
@Data
@ToString
public class SysRolePrivilege {

    private Long id;
    private Long roleId;
    private Long privilegeId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
