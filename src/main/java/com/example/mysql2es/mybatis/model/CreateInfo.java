package com.example.mysql2es.mybatis.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: admin
 * @Description: 创建信息类
 * @BelongsProject: mysql2es
 * @BelongsPackage: com.example.mysql2es.mybatis.model
 * @CreateTime: 2020-11-21 21:49:32
 */
@Data
@ToString
public class CreateInfo implements Serializable {

    private static final long serialVersionUID = 5352292228353517989L;
    private String createBy;
    private Date createTime;

}
