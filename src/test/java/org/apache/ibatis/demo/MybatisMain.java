package org.apache.ibatis.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.submitted.blobtest.BlobMapper;
import org.mybatis.example.Blog;
import org.mybatis.example.BlogMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: admin
 * @Description: mybatis主函數
 * @BelongsProject: mybatis
 * @BelongsPackage: org.apache.ibatis.demo
 * @CreateTime: 2021-04-02 19:25:42
 */
public class MybatisMain {


    public static void main(String[] args) throws IOException {
        String config = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(config);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();
        //Blog blog = sqlSession.selectOne("org.mybatis.example.BlogMapper.selectBlog", 1);
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlog(1);
        System.out.println(blog);
    }
}
