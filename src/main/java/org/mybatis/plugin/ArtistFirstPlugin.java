package org.mybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Statement;
import java.util.Properties;

/**
 * @since: mybatis
 * @BelongsPackage: org.mybatis.plugin
 * @Description: 自定义插件
 * @Author: admin
 * @CreateTime: 2021-05-26 20:11:36
 */
@Intercepts({
  @Signature(type = StatementHandler.class, method = "parameterize", args = java.sql.Statement.class)
})
public class ArtistFirstPlugin implements Interceptor {


  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    System.out.println("----ArtistFirstPlugin---- intercept: " + invocation.getMethod());
    /*Object target = invocation.getTarget();
    System.out.println("-----当前拦截到的对象：" + target);
    MetaObject metaObject = SystemMetaObject.forObject(target);
    Object param = metaObject.getValue("parameterHandler.parameterObject.id");
    System.out.println("----修改参数");
    metaObject.setValue("parameterHandler.parameterObject.id", 2);*/
    Object proceed = invocation.proceed();
    return proceed;
  }

  /**
   * 生成代理对象
   * @param target
   * @return
   */
  @Override
  public Object plugin(Object target) {
    System.out.println("----ArtistFirstPlugin---- plugin,mybatis要代理的对象: " + target);
    return Plugin.wrap(target, this);
  }

  @Override
  public void setProperties(Properties properties) {
    System.out.println("插件的配置信息：" + properties);
  }
}
