package com.example.mysql2es.mybatis.test;

import com.example.mysql2es.mybatis.mapper.RoleMapper;
import com.example.mysql2es.mybatis.mapper.UserMapper;
import com.example.mysql2es.mybatis.model.SysRole;
import com.example.mysql2es.mybatis.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: admin
 * @Description: 一级缓存测试
 * @BelongsProject: mysql2es
 * @BelongsPackage: com.example.mysql2es.mybatis.test
 * @CreateTime: 2020-11-21 23:45:30
 */
public class CacheTest extends BaseMapperTest {

    /**
     * 测试一级缓存
     */
    @Test
    public void testL1Cache(){
        SqlSession sqlSession = getSqlsession();
        SysUser sysUser1 = null;
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            sysUser1 = mapper.selectById(1L);
            sysUser1.setUserName("new username");

            SysUser sysUser2 = mapper.selectById(1L);
            Assert.assertEquals("new username", sysUser2.getUserName());
            Assert.assertEquals(sysUser1, sysUser2);

        } finally {
            sqlSession.close();
        }
        System.out.println("---开启新的sqlsession");
        sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser2 = mapper.selectById(1L);
            System.out.println(sysUser2.getUserName());
            Assert.assertEquals(sysUser1, sysUser2);

            int del = mapper.deleteById(2L);

            SysUser sysUser3 = mapper.selectById(1L);
            Assert.assertNotEquals(sysUser2, sysUser3);

        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    /**
     * 测试二级缓存
     */
    @Test
    public void testL2Cache(){
        SqlSession sqlSession = getSqlsession();
        SysRole role1 = null;
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            role1 = mapper.selectById(1L);
            role1.setRoleName("new role name");
            SysRole role2 = mapper.selectById(1L);
            Assert.assertEquals("new role name", role2.getRoleName());
            Assert.assertEquals(role1, role2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        System.out.println("开启新的sqlSession");
        sqlSession = getSqlsession();
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role2 = mapper.selectById(1L);
            Assert.assertEquals("new role name", role2.getRoleName());
            Assert.assertEquals(role1, role2);

            SysRole role3 = mapper.selectById(1L);
            Assert.assertEquals(role2, role3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }


    }

    /**
     * 测试二级缓存产生脏数据
     */
    @Test
    public void testL2CacheDirtyData(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            System.out.println("根据用户id查询出用户以及对应的角色：");
            SysUser user = mapper.selectUserAndRoleById(1001L);
            System.out.println("第一次sqlSession查询user:" + user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        System.out.println("开启新的sqlsession");
        sqlSession = getSqlsession();
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = mapper.selectById(2L);
            sysRole.setRoleName("脏数据2");
            System.out.println("修改角色数据：");
            mapper.updateById(sysRole);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("开启另一个新的sqlsession");
        sqlSession = getSqlsession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectUserAndRoleById(1001L);
            System.out.println("第三次sqlSession查询user:" + user);
            SysRole role = roleMapper.selectById(2L);
            System.out.println("role:" + role);

            role.setRoleName("普通用户");
            roleMapper.updateById(role);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }

    }

}
