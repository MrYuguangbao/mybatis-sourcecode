package com.example.mysql2es.mybatis.model;

/**
 * @Author: admin
 * @Description: 状态枚举类
 * @BelongsProject: mysql2es
 * @BelongsPackage: com.example.mysql2es.mybatis.model
 * @CreateTime: 2020-11-21 23:07:47
 */
public enum Enabled {
    enabled(1),
    disabled(0);

    private final int value;

    private Enabled(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
