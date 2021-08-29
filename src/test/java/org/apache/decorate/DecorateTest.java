package org.apache.decorate;

/**
 * @since: mybatis
 * @BelongsPackage: org.apache.decorate
 * @Description: 装饰模式
 * @Author: admin
 * @CreateTime: 2021-08-29 10:30:58
 */
public class DecorateTest {

  public static void main(String[] args) {
    Person person = new Person("design pattern");
    ConcreteFinery1 f1 = new ConcreteFinery1();
    ConcreteFinery2 f2 = new ConcreteFinery2();
    ConcreteFinery3 f3 = new ConcreteFinery3();
    f1.decorate(person);
    f2.decorate(f1);
    f3.decorate(f2);
    f3.show();
  }

}
