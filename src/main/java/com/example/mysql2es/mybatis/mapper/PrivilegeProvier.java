package com.example.mysql2es.mybatis.mapper;

import org.apache.ibatis.jdbc.SQL;

/**
 * @Author: admin
 * @Description: provider类
 * @BelongsProject: mysql2es
 * @BelongsPackage: com.example.mysql2es.mybatis.mapper
 * @CreateTime: 2020-11-20 19:08:04
 */
public class PrivilegeProvier {

    public String selectById(final Long id){
        return new SQL(){
            {
                SELECT("id,privilege_name,privilege_url,create_time,update_time");
                FROM("sys_privilege");
                WHERE("id = #{id}");

            }
        }.toString();

        // 或者直接返回SQL语句字符串
    }

}
