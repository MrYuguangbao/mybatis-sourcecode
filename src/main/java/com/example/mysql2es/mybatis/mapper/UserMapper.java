package com.example.mysql2es.mybatis.mapper;

import com.example.mysql2es.mybatis.model.SysRole;
import com.example.mysql2es.mybatis.model.SysRoleExtends;
import com.example.mysql2es.mybatis.model.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: admin
 * @Description: 用户Mapper
 * @BelongsProject: mysql2es
 * @BelongsPackage: com.example.mysql2es.mybatis.mapper
 * @CreateTime: 2020-11-18 19:36:21
 */
public interface UserMapper {

    /**
     * 通过ID查询用户
     * @param id
     * @return
     */
    SysUser selectById(Long id);

    /**
     * 条件查询
     * @param sysUser
     * @return
     */
    List<SysUser> selectByUser(SysUser sysUser);

    /**
     * 查询全部用户
     * @return
     */
    List<SysUser> selectAll();

    /**
     * 根据用户id获取角色信息
     * @param userId
     * @return
     */
    List<SysRole> selectRoleByUserId(Long userId);

    /**
     * 根据用户id获取角色信息（扩展）
     * @param userId
     * @return
     */
    List<SysRoleExtends> selectRoleByUserIdExtends(Long userId);

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    int insert(SysUser sysUser);

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    int insertBySelective(SysUser sysUser);

    /**
     * 新增用户2 - 使用JDBC方式获取自增主键
     * @param sysUser
     * @return
     */
    int insert2(@Param("sysUser") SysUser sysUser);

    /**
     * 新增用户3 - 使用selectKey获取自增主键
     * @param sysUser
     * @return
     */
    int insert3(SysUser sysUser);

    /**
     * 根据主键更新
     * @param sysUser
     * @return
     */
    int updateById(SysUser sysUser);

    /**
     * 根据enabled和用户id查询用户的所有角色
     * @param userId
     * @param enabled
     * @return
     */
    List<SysRole> selectRolesByIdAndEnable(@Param("userId") Long userId, @Param("enabled") Integer enabled);

    /**
     * 根据enabled和用户id查询用户的所有角色
     * @param sysUser
     * @param sysRole
     * @return
     */
    List<SysRole> selectRolesByIdAndEnable2(SysUser sysUser,SysRole sysRole);

    /**
     * 根据id集合查询用户-参数是list集合
     * @param idList
     * @return
     */
    List<SysUser> selectUsersByIdlist(@Param("myList") List<Long> idList);

    /**
     * 根据id集合查询用户-参数是数组
     * @param idList
     * @return
     */
    List<SysUser> selectUsersByIdlist2(@Param("myArray") Long[] idList);

    /**
     * 批量新增用户
     * @param userList
     * @return
     */
    int insertUserBatch(List<SysUser> userList);

    /**
     * 通过map更新列
     * @param map
     * @return
     */
    int updateByMap(Map<String, Object> map);

    /**
     * 在where中使用if
     * @param sysUser
     * @return
     */
    List<SysUser> selectUserByIf1(SysUser sysUser);

    /**
     * 根据主键更新
     * @param sysUser
     * @return
     */
    int updateByIdSelective(SysUser sysUser);

    void selectByIdOrUserName(SysUser sysUser);

    SysUser selectByIdOrUserName1(SysUser sysUser);

    SysUser selectUserAndRoleById(Long id);

    SysUser selectUserAndRoleById2(Long id);

    SysUser selectUserAndRoleByIdResultMap(Long id);

    SysUser selectUserAndRoleByIdAssociation(Long id);

    SysUser selectUserAndRoleById3(Long id);

    SysUser selectUserAndRoleById4(Long id);

    /**
     * association标签的嵌套查询
     * @param id
     * @return
     */
    SysUser selectUserAndRoleByIdSelect(Long id);

    List<SysUser> selectUserRoleListMap6121();

    List<SysUser> selectAllUserAndRoles();
    SysUser selectUserAndRoleByIdCol(Long id);

    SysUser selectAllUserAndRole0104(Long id);

    /**
     * collection集合的嵌套查询
     * @param id
     * @return
     */
    SysUser selectAllUserAndRoleSelect(Long id);

    @Delete("delete from sys_user where id = #{id}")
    int deleteById(Long id);



    @Results(id = "userResultMap1", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPassword", column = "user_password"),
            @Result(property = "userEmail", column = "user_email"),
            @Result(property = "userInfo", column = "user_info"),
            @Result(property = "headImg", column = "head_img"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    @Select("select * from sys_user")
    List<SysUser> selectAll2();

    List<SysUser> selectByWhere(SysUser sysUser);

    int updateBySet(SysUser sysUser);

    /**
     * 使用foreach标签的时候：1.collection默认值是list或array;2.亦可通过@Param注解指定名称(推荐)
     * @param idList
     * @return
     */
    List<SysUser> selectByForeach(@Param("idList") List<SysUser> idList);

    int insetMulti(List<SysUser> userList);

    int updateForeach(@Param("mapParam") Map<String, Object> map);

}
