package com.example.mysql2es.mybatis.test;

import com.example.mysql2es.mybatis.mapper.PrivilegeMapper;
import com.example.mysql2es.mybatis.model.SysPrivilege;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @Author: admin
 * @Description: provider注解测试
 * @BelongsProject: mysql2es
 * @BelongsPackage: com.example.mysql2es.mybatis.test
 * @CreateTime: 2020-11-20 19:12:40
 */
public class PrivilegeMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() {
        SqlSession sqlSession = getSqlsession();
        try {
            PrivilegeMapper mapper = sqlSession.getMapper(PrivilegeMapper.class);
            SysPrivilege sysPrivilege = mapper.selectById(1L);
            System.out.println(sysPrivilege);
            Assert.assertNotNull(sysPrivilege);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

  @Test
  public void testSelectPrivilegeById() {
    SqlSession sqlSession = getSqlsession();
    try {
      PrivilegeMapper mapper = sqlSession.getMapper(PrivilegeMapper.class);
      SysPrivilege sysPrivilege = mapper.selectPrivilegeById(1L);
      System.out.println(sysPrivilege);
      Assert.assertNotNull(sysPrivilege);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      sqlSession.rollback();
      sqlSession.close();
    }
  }

    @Test
    public void testSelectPrivilegeByRoleId() {
        SqlSession sqlSession = getSqlsession();
        try {
            PrivilegeMapper mapper = sqlSession.getMapper(PrivilegeMapper.class);
            List<SysPrivilege> sysPrivilege = mapper.selectPrivilegeByRoleId(1L);
            for (SysPrivilege privilege : sysPrivilege) {
                System.out.println(privilege);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

}
