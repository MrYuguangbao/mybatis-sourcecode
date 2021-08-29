package org.apache.decorate;

/**
 * @since: mybatis
 * @BelongsPackage: org.apache.decorate
 * @Description: 具体服装1
 * @Author: admin
 * @CreateTime: 2021-08-29 10:38:26
 */
public class ConcreteFinery1 extends Finery {

  @Override
  public void show() {
    super.show();
    System.out.println("--具体服装1--");
  }
}
