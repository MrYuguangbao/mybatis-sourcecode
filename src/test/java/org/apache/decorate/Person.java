package org.apache.decorate;

/**
 * @since: mybatis
 * @BelongsPackage: org.apache.decorate
 * @Description:
 * @Author: admin
 * @CreateTime: 2021-08-29 10:31:50
 */
public class Person implements Appearance {

    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public void show() {
      System.out.println("装扮的:" + name);
    }
}
