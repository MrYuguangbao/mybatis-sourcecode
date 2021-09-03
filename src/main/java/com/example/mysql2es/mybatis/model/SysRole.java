package com.example.mysql2es.mybatis.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: admin
 * @Description: 角色实体类
 * @BelongsProject: demo
 * @BelongsPackage: com.example.demo.mybatis.model
 * @CreateTime: 2020-11-17 23:28:39
 */
@Data
@ToString
public class SysRole implements Serializable {
    private static final long serialVersionUID = -5113191526729728151L;
    private Long id;
    private String roleName;
    private Integer enabled;
    private Long createBy;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    //private Enabled enabled;
    // 使用CreateInfo代替存储
    private CreateInfo createInfo;

    /**
     * 角色的权限集合
     */
    private List<SysPrivilege> privilegeList;

}
