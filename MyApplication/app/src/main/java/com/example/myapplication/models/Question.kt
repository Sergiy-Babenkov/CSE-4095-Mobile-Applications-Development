package com.example.myapplication.models

class Question(
    private var question: String,
    private var answer1: String,
    private var answer2: String,
    private var answer3: String,
    private var answer4: String,
    private var correct: String
) {

    fun getQuestion(): String {
        return question
    }

    fun getAnswer1(): String {
        return answer1
    }

    fun getAnswer2(): String {
        return answer2
    }

    fun getAnswer3(): String {
        return answer3
    }

    fun getAnswer4(): String {
        return answer4
    }

    fun getCorrect(): String {
        return correct
    }
}
