package org.chihx.seckill.service;

import org.chihx.seckill.dao.UserDao;
import org.chihx.seckill.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User getById(int id) {
        return userDao.getById(id);
    }
}
