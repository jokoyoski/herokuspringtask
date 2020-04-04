package com.example.demo.services;

import com.example.demo.domain.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.PasswordNotEqualException;
import com.example.demo.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public String saveUser(User user) throws Exception {
var password=user.getPassword();
var confirmPassword=user.getConfirmPassword();
var username=user.getUsername();

        if(password==null || username==null||confirmPassword==null){
            throw new Exception("Bad Information loaded");
        }

        if(!user.getPassword().equals(user.getConfirmPassword())){
            throw new PasswordNotEqualException("Password Not Equal");
        }

        var userInfo=userRepository.GetUserByUserName(user.getUsername());
        if(userInfo.getUsername()!=null){
            throw new Exception("User  already Exist");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepository.SaveUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       User user =userRepository.GetUserByUserName(s);
       if(user==null) new UsernameNotFoundException("user not found");
       return user;
    }
}
