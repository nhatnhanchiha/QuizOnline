package com.bac.quizonline.model.service;

import com.bac.quizonline.model.entity.Role;
import com.bac.quizonline.model.entity.User;
import com.bac.quizonline.model.service.exception.BlockUserException;
import com.bac.quizonline.model.service.exception.DoubleEmailException;
import com.bac.quizonline.model.service.exception.NotFoundUserException;

import java.util.List;

public interface UserService {
    User login(String email, String password) throws NotFoundUserException, BlockUserException;
    List<Role> getAllRole();
    User checkExist(String email);
    User register(User user) throws DoubleEmailException;
}
