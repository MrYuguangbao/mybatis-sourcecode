package com.example.mysql2es.mybatis.plugins;

import com.github.pagehelper.Dialect;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @version 1.0
 * @project: mybatis
 * @package: com.example.mysql2es.mybatis.plugins
 * @desc: 分页插件拦截器
 * @author:admin
 * @createTime 9月 04 2021 09:50:04
 */
@Intercepts(
  @Signature(type = Executor.class,
    method = "query",
    args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
)
public class PageHelpInterceptor implements Interceptor {

  private static final List<ResultMapping> EMPTY_RESULTMAPPING = new ArrayList<>(0);
  private Dialect dialect;
  private Field addParamField;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
      Object[] args = invocation.getArgs();
      MappedStatement ms = (MappedStatement) args[0];
      Object parameter = args[1];
      // 判断是否需要分页
      RowBounds rowBounds = (RowBounds) args[2];
      if (!dialect.skip(ms, parameter, rowBounds)) {
          ResultHandler resultHandler = (ResultHandler) args[3];
          Executor executor = (Executor) invocation.getTarget();
          BoundSql boundSql = ms.getBoundSql(parameter);
          Map<String, Object> addParams = (Map<String, Object>) addParamField.get(boundSql);
          // 判断是否需要count查询
          if (dialect.beforeCount(ms, parameter, rowBounds)) {
              MappedStatement countMs = newMappedStatement(ms, Long.class);
              CacheKey countKey = executor.createCacheKey(ms, parameter, RowBounds.DEFAULT, boundSql);
              String countSql = dialect.getCountSql(ms, boundSql, parameter, rowBounds, countKey);
              BoundSql countBoundsql = new BoundSql(ms.getConfiguration(), countSql, boundSql.getParameterMappings(), parameter);
              for (String key : addParams.keySet()) {
                countBoundsql.setAdditionalParameter(key, addParams.get(key));
              }
              List<Object> countLists = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey, countBoundsql);
              Long count = (Long) countLists.get(0);
              dialect.afterCount(count, parameter, rowBounds);
              if (count == 0) {
                return dialect.afterPage(new ArrayList(), parameter, rowBounds);
              }
          }
        // 判断是否需要分页查询
        if (dialect.beforePage(ms, parameter, rowBounds)) {
          CacheKey pageKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
          String pageSql = dialect.getPageSql(ms, boundSql, parameter, rowBounds, pageKey);
          BoundSql pageBoundsql = new BoundSql(ms.getConfiguration(), pageSql, boundSql.getParameterMappings(), parameter);
          for (String key : addParams.keySet()) {
            pageBoundsql.setAdditionalParameter(key, addParams.get(key));
          }
          List<Object> resultList = executor.query(ms, parameter, RowBounds.DEFAULT, resultHandler, pageKey, pageBoundsql);
          return dialect.afterPage(resultList, parameter, rowBounds);
        }
      }
      return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        String dialectClazz = properties.getProperty("dialect");
        try {
          dialect = (Dialect) Class.forName(dialectClazz).newInstance();
        } catch (Exception e) {
          throw new RuntimeException("使用分页插件，必须设置dialect属性！");
        }
        dialect.setProperties(properties);
        try {
          addParamField = BoundSql.class.getDeclaredField("additionalParameters");
          addParamField.setAccessible(true);
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
    }

  /**
   * 根据现有的MappedStatement创建并返回一个新的返回值类型
   * @param ms
   * @param resultType
   * @return
   */
  public MappedStatement newMappedStatement(MappedStatement ms, Class<?> resultType) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
        ms.getId() + "_Count",
        ms.getSqlSource(),
        ms.getSqlCommandType());
        builder.resource(ms.getResource())
          .fetchSize(ms.getFetchSize())
          .statementType(ms.getStatementType())
          .keyGenerator(ms.getKeyGenerator());
        String[] properties = ms.getKeyProperties();
        if (properties != null && properties.length > 0) {
          StringBuilder keyProperties = new StringBuilder();
          for (String keyProperty : properties) {
            keyProperties.append(keyProperty).append(",");
          }
          keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
          builder.keyProperty(keyProperties.toString());
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        List<ResultMap> resultMaps = new ArrayList<>();
        ResultMap resultMap = new ResultMap.Builder(ms.getConfiguration(), ms.getId(), resultType, EMPTY_RESULTMAPPING).build();
        resultMaps.add(resultMap);
        builder.resultMaps(resultMaps)
          .resultSetType(ms.getResultSetType())
          .cache(ms.getCache())
          .flushCacheRequired(ms.isFlushCacheRequired())
          .useCache(ms.isUseCache());
        return builder.build();
    }

}
