# Hangman - Android
A simple [Hangman](https://en.wikipedia.org/wiki/Hangman_(game)) game made in Kotlin with compose.

This project was build using MVVM and using Jetpack Compose.

Navigation compose [navigation compose](https://developer.android.com/jetpack/compose/navigation) was used for navigating

[Hilt](https://developer.android.com/jetpack/compose/libraries#hilt) was used
for dependency injection to prevent managing dependencies manually as the application scales.


## Requirements
As a user, when I'm in a game session,
- I want to see my score history,
- I want to start a new game, 
- I want to be challenged with unique words only, 
- I want to know how many unique words I can still guess. 

As a user, when I'm playing the match,
- I want to know how many chances I still have,
- I want to be able to try out letters in any order I want,
- I want to know how many points I made when I completed a word,
- I want that my game state is persisted.

## Improvements
- Implement test stubs and increase test coverage
- Receive words to play from backend api instead of using a hardcoded list
