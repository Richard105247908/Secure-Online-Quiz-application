package com.example.Secure.Online.Quiz.Application.service;

import com.example.Secure.Online.Quiz.Application.model.Question;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class QuestionsService {

private HashMap<Integer, Question>questions=new HashMap<>();
private int nextId = 1;


}
