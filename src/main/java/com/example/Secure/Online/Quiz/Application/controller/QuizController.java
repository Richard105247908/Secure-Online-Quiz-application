package com.example.Secure.Online.Quiz.Application.controller;

import com.example.Secure.Online.Quiz.Application.service.QuestionsService;
import com.example.Secure.Online.Quiz.Application.service.QuizUserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuizController {


private final QuizUserDetailsService userDetailsService;
private final QuestionsService questionsService;
private final AuthenticationManager authenticationManager;

public QuizController (QuizUserDetailsService userDetailsService, AuthenticationManager authenticationManager, QuestionsService questionsService){
    this.userDetailsService = userDetailsService;
    this.authenticationManager = authenticationManager;
    this.questionsService = questionsService;

}
@GetMapping("/home")
public String homepage(Model model){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
   //
    String username = authentication.getName();
    System.out.println("");
    model.addAttribute("username", username);

    return "greet";
}






}
