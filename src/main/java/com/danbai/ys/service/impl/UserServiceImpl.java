package com.danbai.ys.service.impl;

import com.danbai.ys.entity.User;
import com.danbai.ys.mapper.UserMapper;
import com.danbai.ys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author danbai
 * @date 2019/10/13
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser(User user) {
        return userMapper.selectOne(user);
    }

    @Override
    public boolean addUser(User user) {
        if (userMapper.insert(user) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("email", email);
        return userMapper.selectOneByExample(example);
    }

    @Override
    public boolean upheadimg(String username, String url) {
        User user = new User();
        user.setUsername(username);
        User user1 = userMapper.selectOne(user);
        user1.setHeadurl(url);
        if (userMapper.updateByPrimaryKey(user1) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int contUser() {
        Example example = new Example(User.class);
        return userMapper.selectCountByExample(example);
    }
}