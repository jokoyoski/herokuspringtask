package com.example.demo.web;

import com.example.demo.PAYLOAD.LoginRequest;
import com.example.demo.PAYLOAD.LoginResponse;
import com.example.demo.domain.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.security.SecurityConstants;
import com.example.demo.services.UserServices;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserServices userService;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;
  @Autowired
  private AuthenticationManager authenticationManager;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) throws Exception {

        String userValue=userService.saveUser(user);


        return new ResponseEntity<String>(userValue, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<?> LoginUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) throws Exception {

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()

        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt= SecurityConstants.TOKEN_PREFIX+jwtTokenProvider.generateToken(authentication) ;
       LoginResponse value=new LoginResponse();
       value.setToken(jwt);

        return new ResponseEntity<Object>(value, HttpStatus.CREATED);
    }

}
