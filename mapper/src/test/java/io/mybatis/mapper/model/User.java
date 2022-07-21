/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.mybatis.mapper.model;

import io.mybatis.provider.Entity;

@Entity.Table(value = "user",
  props = {
    @Entity.Prop(name = "deleteByExample.allowEmpty", value = "false", type = Boolean.class),
    @Entity.Prop(name = "updateByExample.allowEmpty", value = "false", type = Boolean.class),
    @Entity.Prop(name = "updateByExampleSelective.allowEmpty", value = "false", type = Boolean.class),
    @Entity.Prop(name = "dynamicTable", value = "user${suffix}", type = String.class),
    @Entity.Prop(name = "dynamicTable.return", value = "true", type = Boolean.class)
    }
)
public class User {
  @Entity.Column(id = true)
  private Long   id;
  @Entity.Column("name")
  private String userName;
  @Entity.Column
  private String sex;

  @Entity.Transient
  private String suffix;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getSuffix() {
    return suffix;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", name='" + userName + '\'' +
      ", sex='" + sex + '\'' +
      '}';
  }
}
