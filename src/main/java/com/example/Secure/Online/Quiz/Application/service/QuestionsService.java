package com.example.Secure.Online.Quiz.Application.service;

import com.example.Secure.Online.Quiz.Application.model.Quiz;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class QuestionsService {

    private HashMap<Integer, Quiz> questions = new HashMap<>();
    private int nextId = 1;

    public ArrayList<Quiz> getQuizzes() {
        ArrayList<Quiz> valueList = new ArrayList<>(questions.values());
        return valueList;
    }

    public Quiz getQuizById(int id) {
        return questions.get(Integer.valueOf(id));
    }
    
}
