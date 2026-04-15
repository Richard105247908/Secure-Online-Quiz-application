package com.example.Secure.Online.Quiz.Application.model;

import java.util.ArrayList;

public class Quiz {

    private int id;
    private String questionText;
    private ArrayList<String> options;
    private String correctAnswer;


//    public Question(){
//    }

    //  If you want your default constructor to provide
    //  "placeholder" data instead of leaving fields null,
    //  you can link them using this():

    public Quiz(){
        this(0, "New question", new ArrayList<>(), "None");
    }

    public Quiz(int id, String questionText, ArrayList<String> options, String correctAnswer) {
        this.id = id;
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
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

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswers(String correctAnswers) {
        this.correctAnswer = correctAnswers;
    }

    // Add this inside your Quiz class
    public String getOptionsAsString() {
        if (options == null || options.isEmpty()) {
            return "";
        }
        return String.join(", ", options);
    }


    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", options=" + options +
                ", correctAnswers='" + correctAnswer + '\'' +
                '}';
    }
}
