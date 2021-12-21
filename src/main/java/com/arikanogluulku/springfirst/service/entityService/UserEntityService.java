package com.arikanogluulku.springfirst.service.entityService;

import com.arikanogluulku.springfirst.dao.UserDao;
import com.arikanogluulku.springfirst.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityService {
    @Autowired
    private UserDao userDao;

    public List<User> findAll(){
        return userDao.findAll();
    }

    public User findByUserName(String userName){
        return userDao.findByUserName(userName);
    }

    public User findByPhone(String phone){
        return userDao.findByPhone(phone);
    }
    public User save ( User user){
        user = userDao.save(user);
        return user;
    }
    public void deleteUser(String userName , String phone){
       userDao.removeByUserNameAndAndPhone(userName,phone);
    }
}
