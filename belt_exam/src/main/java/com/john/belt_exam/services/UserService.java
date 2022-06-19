package com.john.belt_exam.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.john.belt_exam.models.LoginUser;
import com.john.belt_exam.models.User;
import com.john.belt_exam.repo.UserRepository;


@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;
    
    public User register(User newUser, BindingResult result) {
    	
        Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
        if (potentialUser.isPresent()) {
        	result.rejectValue("email", "Unique", "This email is taken");
        }
        
        if (!newUser.getPassword().equals(newUser.getConfirm())) {
        	result.rejectValue("confirm", "Matches", "the confirm password must match password");
        }
        
        if (result.hasErrors()) {
        	return null;
        } else {
        	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        	newUser.setPassword(hashed);
        	return userRepo.save(newUser);
        }
      
    }
    
      
    public User login(LoginUser newLogin, BindingResult result) {
        if(result.hasErrors()) {                            
            return null;
        }
        
        Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
        if(!potentialUser.isPresent()) {                        
            result.rejectValue("email", "Unique", "Unknown email!");
            return null;
        }
        User user = potentialUser.get();                        

        if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password!");
        }
        if(result.hasErrors()) {
            return null;
        } else {
            return user;    
        }
    }

	public User findOne(Long id) {
		Optional<User> optionalUser = userRepo.findById(id);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}
		}
	}

