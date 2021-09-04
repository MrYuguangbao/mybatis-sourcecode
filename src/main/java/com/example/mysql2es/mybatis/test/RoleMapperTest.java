package com.example.mysql2es.mybatis.test;

import com.example.mysql2es.mybatis.mapper.RoleMapper;
import com.example.mysql2es.mybatis.model.Enabled;
import com.example.mysql2es.mybatis.model.SysPrivilege;
import com.example.mysql2es.mybatis.model.SysRole;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: admin
 * @Description: 角色测试类
 * @BelongsProject: mysql2es
 * @BelongsPackage: com.example.mysql2es.mybatis.test
 * @CreateTime: 2020-11-20 18:30:13
 */
public class RoleMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() {
        SqlSession sqlSession = getSqlsession();
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = mapper.selectById1(1L);
            System.out.println(sysRole);
            //sysRole.setEnabled(Enabled.disabled);
            //mapper.updateById(sysRole);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlsession();
        try {
          RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
          List<SysRole> sysRoles = mapper.selectAll(new RowBounds(0, 1));
          sysRoles.forEach(v -> {
              System.out.println(v.getRoleName());
          });
          System.out.println("--------------------- 第一次使用PageRowBounds ---------------------");
          PageRowBounds pageRowBounds = new PageRowBounds(0, 1);
          sysRoles = mapper.selectAll(pageRowBounds);
          System.out.println("数据总数：" + pageRowBounds.getTotal());
          sysRoles.forEach(v -> {
            System.out.println(v.getRoleName());
          });
          System.out.println("--------------------- 第二次使用PageRowBounds ---------------------");
          pageRowBounds = new PageRowBounds(1, 1);
          sysRoles = mapper.selectAll(pageRowBounds);
          System.out.println("数据总数：" + pageRowBounds.getTotal());
          sysRoles.forEach(v -> {
            System.out.println(v.getRoleName());
          });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2() {
        SqlSession sqlSession = getSqlsession();
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setRoleName("testrolename");
            sysRole.setEnabled(Enabled.enabled.getValue());
            /*sysRole.setCreateBy(1L);
            sysRole.setCreateTime(new Date());*/
            sysRole.setUpdateTime(LocalDateTime.now());
            int insertCount = mapper.insert2(sysRole);
            Assert.assertEquals(1, insertCount);
            System.out.println("生成的主键是:"+sysRole.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //sqlSession.rollback();
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3() {
        SqlSession sqlSession = getSqlsession();
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setRoleName("testrolename1");
            sysRole.setEnabled(1);
            /*sysRole.setCreateBy(1L);
            sysRole.setCreateTime(LocalDateTime.now());*/
            sysRole.setUpdateTime(LocalDateTime.now());
            int insertCount = mapper.insert3(sysRole);
            Assert.assertEquals(1, insertCount);
            System.out.println("生成的主键是:"+sysRole.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllRoleAndPrivileges() {
        SqlSession sqlSession = getSqlsession();
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> sysRoles = mapper.selectAllRoleAndPrivileges();
            for (SysRole sysRole : sysRoles) {
                System.out.println("角色名:" + sysRole.getRoleName());
                for (SysPrivilege sysPrivilege : sysRole.getPrivilegeList()) {
                    System.out.println("-- 权限名:"+sysPrivilege.getPrivilegeName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserIdChoose() {
        SqlSession sqlSession = getSqlsession();
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> sysRoles = mapper.selectRoleByUserIdChoose(1L);
            for (SysRole sysRole : sysRoles) {
                System.out.println("-角色名:" + sysRole.getRoleName());
                /*if (sysRole.getId().equals(1L)) {
                    Assert.assertNotNull(sysRole.getPrivilegeList());
                } else if (sysRole.getId().equals(2L)) {
                    Assert.assertNull(sysRole.getPrivilegeList());
                    continue;
                }*/
                for (SysPrivilege sysPrivilege : sysRole.getPrivilegeList()) {
                    System.out.println("------权限名:" + sysPrivilege.getPrivilegeName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserId() {
        SqlSession sqlSession = getSqlsession();
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> sysRoles = mapper.selectRoleByUserId(1L);
            Assert.assertNotNull(sysRoles);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void testL2Cache() {
        SqlSession sqlSession = getSqlsession();
        SysRole role1 = null;
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            role1 = mapper.selectRoleById(1L);
            role1.setRoleName("new rolename");
            SysRole role2 = mapper.selectRoleById(1L);
            Assert.assertEquals("new rolename", role2.getRoleName());
            Assert.assertEquals(role1, role2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        System.out.println("---开启新的sqlsession");
        sqlSession = getSqlsession();
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role2 = mapper.selectRoleById(1L);
            Assert.assertEquals("new rolename", role2.getRoleName());
            Assert.assertEquals(role1, role2);
            SysRole role3 = mapper.selectRoleById(1L);
            Assert.assertEquals(role2, role3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }


    }
}
