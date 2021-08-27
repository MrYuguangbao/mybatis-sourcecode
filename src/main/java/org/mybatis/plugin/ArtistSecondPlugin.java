package org.mybatis.plugin;


import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * @since: mybatis
 * @BelongsPackage: org.mybatis.plugin
 * @Description: 自定义插件
 * @Author: admin
 * @CreateTime: 2021-05-26 20:55:16
 */
/*@Intercepts({
  @Signature(type = StatementHandler.class, method = "parameterize", args = java.sql.Statement.class)
})*/
public class ArtistSecondPlugin implements Interceptor {


  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    System.out.println("---- ArtistSecondPlugin...intercept:" + invocation.getMethod());
    return invocation.proceed();
  }

  @Override
  public Object plugin(Object target) {
    System.out.println("--- ArtistSecondPlugin...plugin:" + target);
    return Plugin.wrap(target, this);
  }

  @Override
  public void setProperties(Properties properties) {
  }
}
