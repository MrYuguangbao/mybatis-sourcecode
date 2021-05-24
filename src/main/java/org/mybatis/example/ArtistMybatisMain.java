package org.mybatis.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: mybatis
 * @BelongsPackage: org.mybatis.example
 * @Description: 主函数
 * @Author: admin
 * @CreateTime: 2021-05-24 20:29:19
 */
public class ArtistMybatisMain {

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
    Map<String, Object> map = new HashMap<>();
    map.put("tName", "blog");
    map.put("id", 1);
    Blog blog = mapper.selectBlog(map);
    System.out.println(blog);
  }

}
