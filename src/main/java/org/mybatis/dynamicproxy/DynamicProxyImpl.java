package org.mybatis.dynamicproxy;

/**
 * @since: mybatis
 * @BelongsPackage: org.mybatis.dynamicproxy
 * @Description: 动态代理实现类
 * @Author: admin
 * @CreateTime: 2021-08-27 22:39:41
 */
public class DynamicProxyImpl implements IDynamicProxy{


  @Override
  public void show() {
    System.out.println("--DynamicProxyImpl--");
  }
}
