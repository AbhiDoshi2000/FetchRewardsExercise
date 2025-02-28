# Fetch Rewards Coding Exercise

## Description
A native Android app in Kotlin using Jetpack Compose and MVVM architecture that fetches data from a JSON endpoint, filters and sorts it, and displays it grouped by `listId`. The app features an initial screen with four square flashcards (one for each list: List 1, List 2, List 3, List 4), clickable to view detailed, sorted items for each list, with scrollbars, search bars, and a professional purple-gray color scheme. Items are displayed in a vertical list of cards with centered text, allowing single-item selection with a highlighted 2dp purple border and tick icon on the rightmost side. The app supports searching for lists and items, displaying "No items found!" when no matches are found.

## Setup
- Install Android Studio Ladybug (2024.2.1 or later)
- Clone this repository
- Open in Android Studio

## Building and Running
- Sync project with Gradle files
- Build using `Build > Make Project`
- Run on an emulator (API 21+) or connected device

## Architecture
- **Model**: `Item.kt` represents the data structure for items fetched from the JSON, with fields `id`, `listId`, and `name`.
- **View**: `MainActivity.kt` and screens (`FlashcardGridScreen.kt`, `ListDetailScreen.kt`) display the data using Jetpack Compose.
- **ViewModel**: `ItemViewModel.kt` handles data fetching, processing, and state management using LiveData/StateFlow, with API calls implemented using Retrofit and Kotlin Coroutines.

## Assumptions
- Internet connection is available to fetch data from the API.
- The JSON data format (`https://fetch-hiring.s3.amazonaws.com/hiring.json`) remains consistent, with `id`, `listId`, and `name` fields.
- Empty strings and null values for `name` are filtered out as per requirements.
- Currently, items within each list are sorted lexicographically (alphabetically) by the `name` field (e.g., "Item 0", "Item 101", "Item 21"). However, if desired, I can modify the sorting to be numerical based on the number in the `name` (e.g., "Item 0", "Item 21", "Item 101").

## Features
- Fetches data from `https://fetch-hiring.s3.amazonaws.com/hiring.json` using Retrofit.
- Groups items by `listId` (1, 2, 3, 4).
- Sorts items lexicographically by `name` within each `listId` group.
- Filters out items with blank or null `name` values.
- Displays four square flashcards on the initial screen, each representing a list (List 1–4), with a search bar to filter lists by `listId`.
- Navigates to a detailed screen for each list’s sorted items upon clicking a flashcard, featuring:
    - A vertical list of rectangular cards with centered, center-aligned text.
    - A search bar to filter items by `name`.
    - Single-item selection with a highlighted 2dp purple border and tick icon on the rightmost side (deselecting previous selections).
    - Scrollbars on both the flashcard grid and detail screens for long content.
    - A "No items found!" message when search queries yield no matches on both screens.
- Uses a professional purple-gray color scheme with rounded corners for cards.
- Implements MVVM architecture for better structure, maintainability, and scalability.

## Notes
- The app targets Android API 21+ (minimum SDK) and uses the latest tools (compileSdk and targetSdk 35) for Android 15 compatibility as of February 2025.
- The UI is optimized for accessibility, usability, and visual appeal, with consistent theme application across screens.
- Dependencies include Retrofit for networking, Jetpack Compose for UI, Navigation Compose for navigation, and lifecycle components for MVVM.
