package com.example.mysql2es.mybatis.proxy;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author: admin
 * @Description: mapper的代理类
 * @BelongsProject: mysql2es
 * @BelongsPackage: com.example.mysql2es.mybatis.proxy
 * @CreateTime: 2020-11-19 22:20:17
 */
public class MyMapperProxy<T> implements InvocationHandler {

    private Class<T> mapperInterface;
    private SqlSession sqlSession;
    public MyMapperProxy(Class<T> mapperInterface, SqlSession sqlSession){
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入代理方法，接口全限定名称是:"+mapperInterface.getCanonicalName());
        List<T> list = sqlSession.selectList(mapperInterface.getCanonicalName()+"."+method.getName());
        return list;
    }
}
