package org.apache;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.mybatis.example.Blog;
import org.mybatis.example.BlogMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since: mybatis 1.0
 * @BelongsPackage: org.apache
 * @Description: 缓存测试
 * @Author: admin
 * @CreateTime: 2021-05-25 14:27:25
 */
public class CacheTest {

  @Test
  public void testFirstLevelCache() throws IOException {
    InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    Map<String, Object> map = new HashMap<>(6);
    map.put("tName", "blog");
    map.put("id", 1);
    try{
      //PageHelper.startPage(1, 3);
      BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
      Blog blog = mapper.selectBlog(map);
      System.out.println(blog);
      /*List<Blog> blogs = mapper.selectAllBlog();
      for (Blog blog : blogs) {
        System.out.println(blog);
      }*/

    } finally {
    }
  }

  @Test
  public void testSecondLevelCache() throws IOException {
    InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
    SqlSession session1 = sqlSessionFactory.openSession(true);
    SqlSession session2 = sqlSessionFactory.openSession(true);
    Map<String, Object> map = new HashMap<>(6);
    map.put("tName", "blog");
    map.put("id", 1);
    try{
      BlogMapper mapper1 = session1.getMapper(BlogMapper.class);
      BlogMapper mapper2 = session2.getMapper(BlogMapper.class);
      mapper1.selectBlog(map);
      session1.close();
      mapper2.selectBlog(map);
      session2.close();
    } finally {
    }
  }

}
