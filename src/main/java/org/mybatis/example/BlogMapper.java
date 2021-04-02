package org.mybatis.example;

import org.apache.ibatis.annotations.Select;

/**
 * @Author: admin
 * @Description: mapper接口
 * @BelongsProject: mybatis
 * @BelongsPackage: org.mybatis.example
 * @CreateTime: 2021-04-02 20:10:04
 */
public interface BlogMapper {
    @Select("SELECT * FROM BLOG where id = #{id}")
    Blog selectBlog(int id);
}
