package org.apache;

import org.junit.jupiter.api.Test;
import org.mybatis.dynamicproxy.DynamicProxyDemo;
import org.mybatis.dynamicproxy.DynamicProxyImpl;
import org.mybatis.dynamicproxy.IDynamicProxy;

/**
 * @since: mybatis
 * @BelongsPackage: org.apache
 * @Description: 动态代理测试
 * @Author: admin
 * @CreateTime: 2021-08-27 22:38:38
 */
public class DynamicProxyTest {

  @Test
  public void dp() {
    DynamicProxyDemo dp = new DynamicProxyDemo();
    IDynamicProxy proxy = (IDynamicProxy) dp.bind(new DynamicProxyImpl());
    proxy.show();
  }


}
