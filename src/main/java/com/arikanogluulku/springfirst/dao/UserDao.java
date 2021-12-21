package com.arikanogluulku.springfirst.dao;

import com.arikanogluulku.springfirst.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    User findByPhone(String phone);

    @Transactional
    void removeByUserNameAndAndPhone(String userName, String phone);
}
