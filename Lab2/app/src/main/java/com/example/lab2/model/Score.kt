package com.example.lab2.model

class Score {

    private var score: Int = 0

    fun inc(): Int {
        score += 1
        return score
    }

    fun dec(): Int {
        score -= 1
        return score
    }

    fun skip(): Int{
        score -= 2
        return score
    }
    fun reset(): Int
    {
        score = 0
        return score
    }


}