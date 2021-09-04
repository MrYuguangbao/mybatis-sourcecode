package com.example.mysql2es.mybatis.mapper;

import com.example.mysql2es.mybatis.model.SysPrivilege;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Author: admin
 * @Description: provider注解
 * @BelongsProject: mysql2es
 * @BelongsPackage: com.example.mysql2es.mybatis.mapper
 * @CreateTime: 2020-11-20 19:06:51
 */
public interface PrivilegeMapper {

    @SelectProvider(type = PrivilegeProvier.class, method = "selectById")
    SysPrivilege selectById(Long id);

    List<SysPrivilege> selectPrivilegeByRoleId(Long roleId);

    SysPrivilege selectPrivilegeById(@Param("id") Long id);
}
