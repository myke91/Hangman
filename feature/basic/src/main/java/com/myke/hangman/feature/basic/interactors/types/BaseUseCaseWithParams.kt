package com.myke.hangman.feature.basic.interactors.types

interface BaseUseCaseWithParams<P, R> {
    suspend fun run(params : P) : R
}