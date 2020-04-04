package com.example.demo.impl;

import com.example.demo.domain.User;
import com.example.demo.repositories.IUserRepository;
import com.example.demo.sql.GetUserByuserName;
import com.example.demo.sql.InsertUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository  implements IUserRepository {
    @Autowired
    private InsertUser insertUser;

    @Autowired
    GetUserByuserName userByuserName;
    @Override
    public String SaveUser(User user) {

        return insertUser.InsertBackLog(user);
    }

    @Override
    public User GetUserByUserName(String UserName) {
    return userByuserName.getUserbyUserName(UserName);
    }
}
