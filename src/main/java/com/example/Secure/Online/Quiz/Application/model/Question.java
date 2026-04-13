package com.example.Secure.Online.Quiz.Application.model;

import java.util.ArrayList;

public class Question {

    private int id;
    private String questionText;
    private ArrayList<String> options;
    private String correctAnswers;

//    public Question(){
//    }

    //  If you want your default constructor to provide
    //  "placeholder" data instead of leaving fields null,
    //  you can link them using this():

    public Question(){
        this(0, "New question", new ArrayList<>(), "None");
    }

    public Question(int id, String questionText, ArrayList<String> options, String correctAnswers) {
        this.id = id;
        this.questionText = questionText;
        this.options = options;
        this.correctAnswers = correctAnswers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public String getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(String correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", options=" + options +
                ", correctAnswers='" + correctAnswers + '\'' +
                '}';
    }
}
