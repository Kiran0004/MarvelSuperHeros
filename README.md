# Marvel App

This project is a sample app created to display Marvel comics list and also we can filter the results with search bar.

Upon selection of any movie we can find more details about it and also characters of it as well.

## Libraries and Tools Used

- Kotlin
- MVVM Architecute
- AndroidX
    - App Compat
    - RecyclerView
- Material Components
- Android Architecture Components
    - LiveData and ViewModel
    - Paging
	- Room
- Retrofit
- Kotlin Coroutines
- Picasso
- Mockito,Roboelectric (Unit testing)


## Project Structure

This project is built using Clean Architecture and is structured in the following way:

**ui** - contains Activities/Fragments and their Adapters for the presentation layer

**network** - contains API details and netowrk handling

**model** -  contains data models and repositories for getting data

**databse** - contains implementation details for network and db layer (Retrofit/Room/SQLDelight/Realm)

etc...

Installing

Steps to run the project using the command line:

Get the project locally:
git clone https://github.com/Kiran0004/MarvelComics.git
Navigate to the /app folder and execute assemblDebug command from Gradle Wrapper:
./gradlew assembleDebug After the build, app-debug.apk can be found inside your project dir using this path app/build/outputs/apk/debug/
Using adb install project directly to a device or emulator using the command below:
adb install app/build/outputs/apk/debug/app-debug.apk
You can also use Android Studio for that purpose either: VSC -> Git -> Clone Insert URL https://github.com/Kiran0004/MarvelComics.git

You can replace API keys from Constants.kt file with your own keys.


## Marvel API

The API keys should be in a **constants.kt** file

You can create your own API keys on [Marvel's developer site] (https://developer.marvel.com/documentation/getting_started)

Read more about their API Authorization [here](https://developer.marvel.com/documentation/authorization).


