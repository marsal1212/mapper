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

package io.mybatis.mapper.fn;

import io.mybatis.mapper.BaseMapperTest;
import io.mybatis.mapper.UserMapper;
import io.mybatis.mapper.model.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class UserFnDynamicTableMapperTest extends BaseMapperTest {

  @Test
  public void testUpdateByPrimaryKeySelectiveWithForceFields() {
    SqlSession sqlSession = getSqlSession();
    try {
      UserMapper mapper = sqlSession.getMapper(UserMapper.class);
      User qo = new User();
      qo.setId(1L);
      qo.setSuffix("_test");
      User user = mapper.selectOne(qo).get();
      user.setUserName(null);
      user.setSuffix("_test");
      int count = mapper.updateByPrimaryKeySelectiveWithForceFields(user, Fn.of(User::getUserName));
      Assert.assertEquals(1, count);
      user = mapper.selectOne(qo).get();
      Assert.assertEquals("男", user.getSex());
      Assert.assertNull(user.getUserName());
    } finally {
      //不要忘记关闭sqlSession
      sqlSession.close();
    }
  }

  @Test
  public void testSelectColumnsOne() {
    SqlSession sqlSession = getSqlSession();
    try {
      UserMapper mapper = sqlSession.getMapper(UserMapper.class);
      User user = new User();
      user.setSuffix("_test");
      user.setId(1L);
      Optional<User> optionalUser = mapper.selectColumnsOne(user, Fn.of(User::getUserName));
      Assert.assertTrue(optionalUser.isPresent());
      Assert.assertEquals(optionalUser.get().getSuffix(),"_test");
      Assert.assertNull(optionalUser.get().getId());
      Assert.assertNull(optionalUser.get().getSex());
      Assert.assertNotNull(optionalUser.get().getUserName());
    } finally {
      //不要忘记关闭sqlSession
      sqlSession.close();
    }
  }

  @Test
  public void testSelectColumns() {
    SqlSession sqlSession = getSqlSession();
    try {
      UserMapper mapper = sqlSession.getMapper(UserMapper.class);
      User user = new User();
      user.setSex("男");
      user.setSuffix("_test");
      List<User> users = mapper.selectColumns(user, Fn.of(User::getUserName));
      users.forEach(u -> {
        Assert.assertEquals(u.getSuffix(),"_test");
        Assert.assertNull(u.getId());
        Assert.assertNull(u.getSex());
        Assert.assertNotNull(u.getUserName());
      });
    } finally {
      //不要忘记关闭sqlSession
      sqlSession.close();
    }
  }

}


