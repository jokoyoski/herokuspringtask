package com.example.demo.repositories;

import com.example.demo.domain.User;

public interface IUserRepository {
    String SaveUser(User user);
    User  GetUserByUserName(String UserName);
}
