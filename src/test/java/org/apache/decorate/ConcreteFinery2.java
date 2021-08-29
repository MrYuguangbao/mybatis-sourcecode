package org.apache.decorate;

/**
 * @since: mybatis
 * @BelongsPackage: org.apache.decorate
 * @Description: 具体服装2
 * @Author: admin
 * @CreateTime: 2021-08-29 10:39:22
 */
public class ConcreteFinery2 extends Finery{

  @Override
  public void show() {
    super.show();
    System.out.println("--具体服装2--");
  }
}
