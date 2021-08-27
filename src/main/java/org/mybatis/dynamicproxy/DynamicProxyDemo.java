package org.mybatis.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @since: mybatis
 * @BelongsPackage: org.mybatis.dynamicproxy
 * @Description: 动态代理
 * @Author: admin
 * @CreateTime: 2021-08-27 22:31:37
 */
public class DynamicProxyDemo implements InvocationHandler {

  // 真实对象
  private Object target = null;

  /**
   * 建立真实对象和代理对象的代理关系，并返回代理对象
   * @param target 真实对象
   * @return       代理对象
   */
  public Object bind(Object target) {
      this.target = target;
      return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("进入代理逻辑.......");
    System.out.println("调用真实对象的方法之前......");
    Object result = method.invoke(target, args);
    System.out.println("调用真实对象的方法之后......");
    return result;
  }
}
