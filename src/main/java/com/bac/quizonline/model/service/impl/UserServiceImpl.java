package com.bac.quizonline.model.service.impl;

import com.bac.quizonline.model.entity.Role;
import com.bac.quizonline.model.entity.User;
import com.bac.quizonline.model.mapper.RoleMapper;
import com.bac.quizonline.model.mapper.UserMapper;
import com.bac.quizonline.model.service.UserService;
import com.bac.quizonline.model.service.exception.BlockUserException;
import com.bac.quizonline.model.service.exception.DoubleEmailException;
import com.bac.quizonline.model.service.exception.NotFoundUserException;
import com.bac.quizonline.model.utilities.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public User login(String email, String password) throws NotFoundUserException, BlockUserException {
        User user = User.UserBuilder
                .anUser()
                .withEmail(email)
                .withPassword(password)
                .build();
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final UserMapper userMapper = session.getMapper(UserMapper.class);
            user = userMapper.login(user);
            if (user == null) {
                throw new NotFoundUserException();
            }

            if (!user.getStatus()) {
                throw new BlockUserException();
            }

            return user;
        }
    }

    @Override
    public List<Role> getAllRole() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final RoleMapper roleMapper = session.getMapper(RoleMapper.class);
            return roleMapper.getAllRole();
        }
    }

    @Override
    public User checkExist(String email) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final UserMapper userMapper = session.getMapper(UserMapper.class);
            return userMapper.checkExist(email);
        }
    }

    @Override
    public User register(User user) throws DoubleEmailException {
        final User userInDb = checkExist(user.getEmail());
        if (userInDb != null) {
            throw new DoubleEmailException();
        }

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final UserMapper userMapper = session.getMapper(UserMapper.class);
            int result = userMapper.insert(user);
            if (result == 1) {
                session.commit();
                return user;
            }

            session.rollback();
            return null;
        }
    }
}
