package com.example.mysql2es.mybatis.test;

import com.example.mysql2es.mybatis.mapper.RoleMapper;
import com.example.mysql2es.mybatis.mapper.UserMapper;
import com.example.mysql2es.mybatis.mapper.UsertestMapper;
import com.example.mysql2es.mybatis.model.*;
import com.example.mysql2es.mybatis.proxy.MyMapperProxy;
import org.apache.ibatis.reflection.Jdk;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.*;


/**
 * @Author: admin
 * @Description:
 * @BelongsProject: mysql2es
 * @BelongsPackage: com.example.mysql2es.mybatis.test
 * @CreateTime: 2020-11-18 20:39:04
 */
public class UserMapperTest extends BaseMapperTest {

    @Test
    public void myTest() {
        SqlSession sqlSession = getSqlsession();
        try {
            TypeHandlerRegistry registry = sqlSession.getConfiguration().getTypeHandlerRegistry();
            Collection<TypeHandler<?>> typeHandlers = registry.getTypeHandlers();
            System.out.println("typehandlers size:" + typeHandlers.size());
            for (TypeHandler<?> typeHandler : typeHandlers) {
                System.out.println(typeHandler.getClass().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = mapper.selectById(1L);
            Assert.assertNotNull(sysUser);
            Assert.assertEquals("admin", sysUser.getUserName());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByUser() {
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = new SysUser();
            sysUser.setUserName("administrator");
            sysUser.setUserEmail("mysql@mybatis.org");
            List<SysUser> list = mapper.selectByUser(sysUser);
            list.forEach(e -> {
                System.out.println(e);
            });
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
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> sysUser = mapper.selectAll();
            Assert.assertNotNull(sysUser);
            Assert.assertTrue(!sysUser.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserId(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> sysRole = mapper.selectRoleByUserId(1L);
            Assert.assertNotNull(sysRole);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserIdExtends(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysRoleExtends> sysRole = mapper.selectRoleByUserIdExtends(1L);
            for (SysRoleExtends role : sysRole) {
                System.out.println(role);
            }
            Assert.assertNotNull(sysRole);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("usertest20210101");
            user.setUserPassword("passtest20210101");
            user.setUserEmail("email20210101@mybatis.org");
            user.setUserInfo("infotest20210101");
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            int insert = mapper.insert(user);
            Assert.assertEquals(1, insert);
            Assert.assertNull(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //sqlSession.rollback();
            // 默认不自动提交
            //sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void insertBySelective(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("usertest20210101");
            user.setUserPassword("passtest20210101");
            user.setUserEmail("email20210101@mybatis.org");
            user.setUserInfo("infotest20210101");
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            int insert = mapper.insertBySelective(user);
            Assert.assertEquals(1, insert);
            Assert.assertNull(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //sqlSession.rollback();
            // 默认不自动提交
            //sqlSession.commit();
            sqlSession.close();
        }
    }

    /**
     * 测试新增返回的主键
     */
    @Test
    public void testInsert2(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("usertest1");
            user.setUserPassword("passtest1");
            user.setUserEmail("testemail");
            user.setUserInfo("infotest1");
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            int insert = mapper.insert2(user);
            Assert.assertEquals(1, insert);
            System.out.println("新增返回的主键是:"+user.getId());

            //SysUser user1 = mapper.selectById(user.getId());
            //Assert.assertEquals("default@mybatis.org", user1.getUserEmail());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //sqlSession.rollback();
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("usertest");
            user.setUserPassword("passtest");
            user.setUserEmail("email@mybatis.org");
            user.setUserInfo("infotest");
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            int insert = mapper.insert3(user);
            Assert.assertEquals(1, insert);
            System.out.println("返回的自增主键是："+user.getId());
            Assert.assertNotNull(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //sqlSession.rollback();
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = mapper.selectById(1028L);
            user.setUserName("administrator");
            user.setUserEmail("mysql@mybatis.org");
            user.setUpdateTime(new Date());
            int result = mapper.updateById(user);
            Assert.assertEquals(1, result);
            SysUser updateUser = mapper.selectById(1L);
            System.out.println("更新后的User："+updateUser);
            Assert.assertEquals("administrator", user.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //sqlSession.rollback();
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByIdAndEnable(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> sysRoles = mapper.selectRolesByIdAndEnable(1L, 1);
            Assert.assertNotNull(sysRoles);
            Assert.assertTrue(sysRoles.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByIdAndEnable2(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = new SysUser();
            sysUser.setId(1L);
            SysRole sysRole = new SysRole();
            sysRole.setEnabled(Enabled.enabled.getValue());

            List<SysRole> sysRoles = mapper.selectRolesByIdAndEnable2(sysUser, sysRole);
            for (SysRole role : sysRoles) {
                System.out.println(role);
            }
            //Assert.assertNotNull(sysRoles);
            //Assert.assertTrue(sysRoles.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testASelectAll2(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> sysUsers = mapper.selectAll2();
            //Assert.assertTrue(sysRoles.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void selectUsersByIdlist(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<Long> idList = new ArrayList<>(Arrays.asList(1L,1001L));
            List<SysUser> sysUsers = mapper.selectUsersByIdlist(idList);
            Assert.assertEquals(2, sysUsers.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectUsersByIdlist2(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            Long[] idArray = {1L, 1001L};
            List<SysUser> sysUsers = mapper.selectUsersByIdlist2(idArray);
            Assert.assertEquals(2, sysUsers.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertUserBatch(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                SysUser user = new SysUser();
                user.setUserName("test" + i);
                user.setUserPassword("123password@" + i);
                user.setUserEmail("test" + i + "@mybatis.org");
                userList.add(user);
            }
            int insertResult = mapper.insetMulti(userList);
            System.out.println("批量新增完成之后，返回自增主键值");
            for (SysUser user : userList) {
                System.out.println(user.getId());
            }
            Assert.assertEquals(2, insertResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //sqlSession.rollback();
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByMap(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1032L);
            map.put("user_email", "updateforeach11@mybatis.org");
            map.put("user_password", "mypsd@foreach");
            int updateResult = mapper.updateForeach(map);
            Assert.assertEquals(1, updateResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testMapperProxy(){
        SqlSession sqlSession = getSqlsession();
        try {
            MyMapperProxy mapperProxy = new MyMapperProxy(UserMapper.class, sqlSession);
            UserMapper userMapper = (UserMapper)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class[]{UserMapper.class},
                    mapperProxy);
            List<SysUser> userList = userMapper.selectAll();
            for (SysUser user : userList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserByIf1(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser query = new SysUser();
            System.out.println("只根据用户名查询");
            query.setUserName("ad");
            List<SysUser> sysUsers = mapper.selectUserByIf1(query);
            //Assert.assertTrue(sysUsers.size() > 0);

            /*query = new SysUser();
            System.out.println("只根据email查询");
            query.setUserEmail("admin@mybatis.com");
            sysUsers = mapper.selectUserByIf1(query);
            Assert.assertTrue(sysUsers.size() > 0);

            query = new SysUser();
            query.setUserName("ad");
            query.setUserEmail("test@mybatis.com");
            sysUsers = mapper.selectUserByIf1(query);
            Assert.assertTrue(sysUsers.size() == 0);*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByIdSelective(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser update = new SysUser();
            update.setId(1001L);
            update.setUserEmail("update1@mybatis.org");
            int updateResult = mapper.updateByIdSelective(update);
            Assert.assertEquals(1, updateResult);

            update = mapper.selectById(1001L);
            Assert.assertEquals("test", update.getUserName());
            Assert.assertEquals("update1@mybatis.org", update.getUserEmail());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdOrUserName(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser param = new SysUser();
            param.setId(1L);
            param.setUserName("test@mybatis.com");
            System.out.println("根据id查询");
            mapper.selectByIdOrUserName(param);

            System.out.println("根据name查询");
            param.setId(null);
            mapper.selectByIdOrUserName(param);

            System.out.println("id和name都为空");
            param.setUserName(null);
            mapper.selectByIdOrUserName(param);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByWhere(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("ddd");
            user.setUserEmail("eee");
            System.out.println("userName和userEmail都不为null");
            mapper.selectByWhere(user);

            System.out.println("userName为null");
            user.setUserName(null);
            mapper.selectByWhere(user);

            System.out.println("userEmail为null");
            //user.setUserEmail(null);
            mapper.selectByWhere(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateBySet(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("ddd");
            user.setUserEmail("eee");
            mapper.updateBySet(user);

            System.out.println("userName为null");
            user.setUserName(null);
            mapper.updateBySet(user);

            System.out.println("userEmail为null");
            user.setUserEmail(null);
            mapper.updateBySet(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = mapper.selectUserAndRoleByIdSelect(1001L);
            System.out.println(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByForeach(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            //List<Long> integers = Arrays.asList(1L, 2L, 3L);
            //Long[] integers = new Long[]{1L, 2L, 3L};
            List<SysUser> users = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                SysUser u = new SysUser();
                u.setId(Long.parseLong(i + ""));
                users.add(u);
            }
            List<SysUser> sysUsers = mapper.selectByForeach(users);
            System.out.println(sysUsers.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void testSelectUserAndRoleByIdSelect(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = mapper.selectUserAndRoleByIdSelect(1001L);
            System.out.println("调用equals,clone,hashCode,toString方法");
            //System.out.println(sysUser.toString());
            sysUser.equals(null);
            /*System.out.println("调用sysUser.getRole()");
            Assert.assertNotNull(sysUser.getRole());*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserRoleListMap6121(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> sysUser = mapper.selectUserRoleListMap6121();
            System.out.println(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }



    @Test
    public void testSelectAllUserAndRoles(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            // 6.1.2.1 collection集合的嵌套结果查询
            /*List<SysUser> sysUsers = mapper.selectAllUserAndRoles();
            System.out.println("用户数：" + sysUsers.size());
            for (SysUser sysUser : sysUsers) {
                System.out.println("* 用户名:" + sysUser.getUserName());
                for (SysRole sysRole : sysUser.getRoleList()) {
                    System.out.println("\t@角色名:"+sysRole.getRoleName());
                    for (SysPrivilege sysPrivilege : sysRole.getPrivilegeList()) {
                        System.out.println("----- 权限名:"+sysPrivilege.getPrivilegeName());
                    }
                }
            }*/

            // 6.1.2.2 collection集合的嵌套查询
            SysUser sysUser = mapper.selectAllUserAndRoleSelect(1L);
            System.out.println("* 用户名:" + sysUser.getUserName());
            for (SysRole sysRole : sysUser.getRoleList()) {
                System.out.println("\t@角色名:"+sysRole.getRoleName());
                for (SysPrivilege sysPrivilege : sysRole.getPrivilegeList()) {
                    System.out.println("----- 权限名:"+sysPrivilege.getPrivilegeName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUserAndRole0104(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = mapper.selectAllUserAndRole0104(1L);
            System.out.println(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleByIdCol(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = mapper.selectUserAndRoleByIdCol(1L);
            System.out.println(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDirtyData(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = mapper.selectUserAndRoleById(1001L);
            Assert.assertEquals("普通用户", sysUser.getRole().getRoleName());
            System.out.println("角色名："+sysUser.getRole().getRoleName());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        sqlSession = getSqlsession();
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = mapper.selectById(2L);
            sysRole.setRoleName("脏数据");
            mapper.updateById(sysRole);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        sqlSession = getSqlsession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysUser sysUser = userMapper.selectUserAndRoleById(1001L);
            SysRole sysRole = roleMapper.selectById(2L);

            Assert.assertEquals("普通用户", sysUser.getRole().getRoleName());
            Assert.assertEquals("脏数据", sysRole.getRoleName());
            System.out.println("角色名：" + sysUser.getRole().getRoleName());

            sysRole.setRoleName("普通用户");
            roleMapper.updateById(sysRole);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testJDKLevel(){
        System.out.println(Jdk.parameterExists);
    }

    @Test
    public void testExample() {
        SqlSession sqlSession = getSqlsession();
        try {
            UsertestMapper mapper = sqlSession.getMapper(UsertestMapper.class);
            UsertestExample example = new UsertestExample();

            // 测试selectExample
            /*example.setOrderByClause("id desc");
            example.setDistinct(true);
            UsertestExample.Criteria criteria = example.createCriteria();
            criteria.andIdGreaterThanOrEqualTo(1L);
            criteria.andIdLessThan(3L);
            UsertestExample.Criteria or = example.or();
            or.andNameEqualTo("Yekok");

            List<Usertest> usertests = mapper.selectByExample(example);*/

            // 测试updateByExampleSelective
            /*UsertestExample.Criteria criteria = example.createCriteria();
            criteria.andIdGreaterThan(2L);

            Usertest model = new Usertest();
            model.setName("MBG update");
            mapper.updateByExampleSelective(model, example);*/

            // 测试deleteByExample
            UsertestExample.Criteria criteria = example.createCriteria();
            criteria.andIdGreaterThan(2L);
            mapper.deleteByExample(example);
            System.out.println(mapper.countByExample(example));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

}
