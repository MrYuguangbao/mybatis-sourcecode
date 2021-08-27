package org.mybatis.example;

import java.io.Serializable;

/**
 * @Author: admin
 * @Description: 自定義Blog裏
 * @BelongsProject: mybatis
 * @BelongsPackage: org.mybatis.example
 * @CreateTime: 2021-04-02 20:12:04
 */
public class Blog implements Serializable {

  private int id;
  private String title;
  private String content;

  @Override
  public String toString() {
    return "Blog{" +
      "id=" + id +
      ", title='" + title + '\'' +
      ", content='" + content + '\'' +
      '}';
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
