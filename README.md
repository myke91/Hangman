## Hangman - Android
A simple [Hangman](https://en.wikipedia.org/wiki/Hangman_(game)) game made in Kotlin with compose.

This project was build using MVVM with Clean Architecture and using Jetpack Compose.

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


## Libraries
- Navigation compose [navigation compose](https://developer.android.com/jetpack/compose/navigation) was used for navigating
- [Hilt](https://developer.android.com/jetpack/compose/libraries#hilt) was used for dependency injection to prevent managing dependencies manually as the application scales.
- [Datastore](https://developer.android.com/topic/libraries/architecture/datastore) was used to persist words that have already been played
- [Room](https://developer.android.com/jetpack/androidx/releases/room) was used for entity persistence
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-basics.html#your-first-coroutine) was used for reactive programming and concurrency
- Android lifecycle aware components (with [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle))
- Data surviving configuration changes (with [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel))
- Type safe dependency management (with [Kotlin DSL](https://kotlinlang.org/docs/type-safe-builders.html))

## Getting started
Clone this repository preferably with Android Studio 
Build project and then run application. In the situation where you are asked about trusting the application please do select yes 
Please make sure you are on the *master* branch as that has all the latest changes.


## Improvements
- Implement test stubs and increase test coverage
- Receive words to play from backend api instead of using a hardcoded list


## Challenges
- Hilt ViewModel injection in the instrumentation tests
