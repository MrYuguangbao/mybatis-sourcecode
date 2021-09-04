package com.example.mysql2es.mybatis.plugins;

import com.github.pagehelper.Dialect;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Properties;

/**
 * @version 1.0
 * @project: mybatis
 * @package: com.example.mysql2es.mybatis.plugins
 * @desc: mysql方言实现类
 * @author:admin
 * @createTime 9月 04 2021 09:51:20
 */
public class MySQLDialect implements Dialect {
  @Override
  public boolean skip(MappedStatement ms, Object parameterObject, RowBounds rowBounds) {
    if (rowBounds != RowBounds.DEFAULT) {
      return false;
    }
    return true;
  }

  @Override
  public boolean beforeCount(MappedStatement ms, Object parameterObject, RowBounds rowBounds) {
    if (rowBounds instanceof PageRowBounds) {
      return true;
    }
    return false;
  }

  @Override
  public String getCountSql(MappedStatement ms, BoundSql boundSql, Object parameterObject, RowBounds rowBounds, CacheKey countKey) {
    return "select count(*) from (" + boundSql.getSql() + ") temp";
  }

  @Override
  public boolean afterCount(long count, Object parameterObject, RowBounds rowBounds) {
    ((PageRowBounds) rowBounds).setTotal(count);
    return true;
  }

  @Override
  public boolean beforePage(MappedStatement ms, Object parameterObject, RowBounds rowBounds) {
    if (rowBounds != RowBounds.DEFAULT) {
      return true;
    }
    return false;
  }

  @Override
  public String getPageSql(MappedStatement ms, BoundSql boundSql, Object parameterObject, RowBounds rowBounds, CacheKey pageKey) {
    pageKey.update("RowBounds");
    return boundSql.getSql() + " limit " + rowBounds.getOffset() + "," + rowBounds.getLimit();
  }

  @Override
  public Object afterPage(List pageList, Object parameterObject, RowBounds rowBounds) {
    return pageList;
  }

  @Override
  public void setProperties(Properties properties) {

  }

  @Override
  public void afterAll() {

  }

  @Override
  public Object processParameterObject(MappedStatement ms, Object parameterObject, BoundSql boundSql, CacheKey pageKey) {
    return null;
  }
}
