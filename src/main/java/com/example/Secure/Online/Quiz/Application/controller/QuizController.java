package com.example.Secure.Online.Quiz.Application.controller;

import com.example.Secure.Online.Quiz.Application.model.Quiz;
import com.example.Secure.Online.Quiz.Application.service.QuestionsService;
import com.example.Secure.Online.Quiz.Application.service.QuizUserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class QuizController {


    private final QuizUserDetailsService userDetailsService;
    private final QuestionsService questionsService;
    private final AuthenticationManager authenticationManager;

    public QuizController(QuizUserDetailsService userDetailsService, AuthenticationManager authenticationManager, QuestionsService questionsService) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.questionsService = questionsService;

    }

    @GetMapping("/home")
    public String homepage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //Get username
        String username = authentication.getName();
        System.out.println("");
        model.addAttribute("username", username);

//Get role
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_USER");

        if (role.equals("ROLE_ADMIN")) {
            List<Quiz> quizzes = questionsService.getQuizzesList();

            model.addAttribute("quizzes", quizzes);
            return "QuizList";
        } else {
            List<Quiz> quizzes = questionsService.getQuizzesList();

            model.addAttribute("quizzes", quizzes);
            return "Quiz";
        }

    }
    @GetMapping("/login")
public String login(){
        return "Login";
    }

    @GetMapping("/register")
    public String register(){
        return "Register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role
    ){
        try {
            userDetailsService.registerUser(username,password,role);
        } catch (Exception userExistsAlready) {
            return "redirect:/register?error";
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/login?success";
    }

    @GetMapping("/addQuiz")
    public String showAddQuizForm(Model model){
        model.addAttribute("quiz",new Quiz());
        return "addQuiz";
    }

    @PostMapping("/addQuiz")
    public String addQuiz(@ModelAttribute Quiz quiz, Model model, Authentication authentication){
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_USER");

        if (role.equals("ROLE_ADMIN")) {
            quiz.setId(questionsService.getNextId());
            // Add the quiz to the service
            questionsService.addQuiz(quiz);

            // Add a success message to the model
            model.addAttribute("success", "Quiz added successfully!");

            // Redirect to the quiz list page
            return "redirect:/home";
        } else {
            // Add an error message to the model
            model.addAttribute("error", "You do not have permission to add a quiz.");

            // Redirect to the add quiz page
            return "redirect:/addQuiz?error";
        }

    }

}
