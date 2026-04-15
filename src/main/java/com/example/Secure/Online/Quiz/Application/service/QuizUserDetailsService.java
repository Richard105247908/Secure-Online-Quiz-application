package com.example.Secure.Online.Quiz.Application.service;

import com.example.Secure.Online.Quiz.Application.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class QuizUserDetailsService implements UserDetailsService {

   private final HashMap<String, User>users= new HashMap<>();
   private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

   @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
       User user = users.get(username);
       if (user == null) {
           throw new UsernameNotFoundException("User not found");
       }

       return org.springframework.security.core.userdetails.User.builder()
               .username(user.getUsername())
               .password(user.getPassword())
               .roles("USER")
               .build();
   }

   public void registerUser(String username, String password, String role) throws Exception {
       if (users.containsKey(username)) {
           throw new Exception("User already exists");
       }else {
           String encodedPassword = passwordEncoder.encode(password);
           users.put(username, new User(username, encodedPassword,role));
       }
   }

}
