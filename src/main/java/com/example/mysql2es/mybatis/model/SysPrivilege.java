package com.example.mysql2es.mybatis.model;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: admin
 * @Description: 权限实体类
 * @BelongsProject: demo
 * @BelongsPackage: com.example.demo.mybatis.model
 * @CreateTime: 2020-11-17 23:34:58
 */
@Data
@ToString
public class SysPrivilege implements Serializable {

    private Long id;
    private String privilegeName;
    private String privilegeUrl;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
