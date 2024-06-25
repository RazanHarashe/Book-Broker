package com.bookbroker.bookbroker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.bookbroker.bookbroker.models.LoginUser;
import com.bookbroker.bookbroker.models.User;
import com.bookbroker.bookbroker.repositories.UserRepository;

import org.mindrot.jbcrypt.BCrypt;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User register(User newUser, BindingResult result) {
        if(userRepo.findByEmail(newUser.getEmail()) != null) {
            result.rejectValue("email", "Unique", "This email is already registered!");
        }
        if(!newUser.getPassword().equals(newUser.getPasswordConfirmation())) {
            result.rejectValue("passwordConfirmation", "Matches", "The password confirmation does not match!");
        }
        if(result.hasErrors()) {
            return null;
        }
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);
        return userRepo.save(newUser);
    }

    public User login(LoginUser newLogin, BindingResult result) {
        if(result.hasErrors()) {
            return null;
        }
        User user = userRepo.findByEmail(newLogin.getEmail());
        if(user == null) {
            result.rejectValue("email", "NotFound", "Invalid email or password!");
            return null;
        }
        if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Invalid", "Invalid email or password!");
            return null;
        }
        return user;
    }

    public User findById(Long id) {
        return userRepo.findById(id).orElse(null);
    }
}