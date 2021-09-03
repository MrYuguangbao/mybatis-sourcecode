package com.example.mysql2es.mybatis.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: admin
 * @Description: 用户实体类
 * @BelongsProject: demo
 * @BelongsPackage: com.example.demo.mybatis.model
 * @CreateTime: 2020-11-17 22:38
 */
@Data
@ToString
public class SysUser implements Serializable {

    private static final long serialVersionUID = 3172480234149932846L;
    private Long id;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userInfo;
    private byte[] headImg;
    private Date createTime;
    private Date updateTime;

    /**
     * 嵌套查询 一对一映射
     */
    private SysRole role;
    /**
     * 嵌套查询 一对多映射
     */
    private List<SysRole> roleList;

}
