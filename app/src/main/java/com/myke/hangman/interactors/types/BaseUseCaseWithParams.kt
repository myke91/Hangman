package com.myke.hangman.interactors.types

interface BaseUseCaseWithParams<P, R> {
    suspend fun run(params : P) : R
}