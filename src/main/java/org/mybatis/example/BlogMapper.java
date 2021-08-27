package org.mybatis.example;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author: admin
 * @Description: mapper接口
 * @BelongsProject: mybatis
 * @BelongsPackage: org.mybatis.example
 * @CreateTime: 2021-04-02 20:10:04
 */
public interface BlogMapper {
    //@Select("SELECT * FROM BLOG where id = #{id}")
    Blog selectBlog(Map<String, Object> map);
    void updateBlog(Map<String, Object> map);
    void insertBlog(Map<String, Object> map);
    void deleteBlog(Map<String, Object> map);

    List<Blog> selectAllBlog();
}
