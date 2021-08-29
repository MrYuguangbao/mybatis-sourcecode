package org.apache.decorate;

/**
 * @since: mybatis
 * @BelongsPackage: org.apache.decorate
 * @Description: 服装类
 * @Author: admin
 * @CreateTime: 2021-08-29 10:33:26
 */
public abstract class Finery implements Appearance{

  private Appearance component;

  public void decorate(Appearance component) {
    this.component = component;
  }

  @Override
  public void show() {
    if (component != null) {
      component.show();
    }
  }
}
