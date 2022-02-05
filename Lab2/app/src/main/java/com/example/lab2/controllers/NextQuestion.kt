package com.example.lab2.controllers

import com.example.lab2.model.AllQuestions



class NextQuestion {

    private val allQuestions: AllQuestions = AllQuestions()

    private var question: Int = 0
    private val totalqs: Int = allQuestions.allQuestions.size

    public fun getIndex (): Int {
        return (question) % totalqs
    }

    public fun linearNextQuestion(): Int {
        question = (question+1) % totalqs
        return allQuestions.allQuestions[question].index
    }
}