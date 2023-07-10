# Openpay Test

This project implements MVVM architecture , StatesFlows to communication between viewmodel and view, coroutines to implement async tasks, retrofilt to consume web apis , Room to local data persistence and some functionalities from Firebase as Cloud firestore to storage user location data and Firebase storage to storage user images
## Requirements

- Android API 21 or higher
- Programming Language: Kotlin

## Project Setup

1. Clone the repository to your local machine:
 
  ```sh
  Git clone https://github.com/neryto/OpenpayTest.git
  ```
2. Open the project in Android Studio.

3. Sync the project dependencies.

   ## Overview

This project is a mobile application for Android devices that consumes a REST API service provided by The Movie Database (TMDB) to fetch movie information. The application is developed using the Kotlin programming language and makes use of Android compatibility libraries.

## Features

1. TMDB REST API Consumption:
- Consumes the TMDB REST API service to retrieve movie data.

2. Local Data Persistence:
- The data obtained from the TMDB service is stored locally for offline access.
- The application displays the locally stored data when the device is offline.

3. Main Activity and Fragments:
- The application includes a main activity and utilizes fragments to display different functionalities.

4. Bottom Navigation:
- Upon launching the application, a Bottom Navigation with 5 options is displayed.

 - Screen 1: Profile Screen
   - Displays user information.
   - Allows viewing user rated movies.
     
  <img src="app/screenshots/user_profile.png" alt="Profile screen" width="300" height="700">

 - Screen 2: Movie List
   - Loads a list of all movies.
   - Displays paying now, most popular , top-rated , and upcoming movies.
  
     
   <img src="app/screenshots/list_movies_1.png" alt="List movies screen 1" width="300" height="700">
   <img src="app/screenshots/list_movies_2.png" alt="List movies screen 2" width="300" height="700">
   <img src="app/screenshots/list_movies3.png" alt="List movies screen 3" width="300" height="700">



 - Screen 3: Map of Locations
   - Consumes data from Firebase console (Cloud Firestore).
   - Displays locations on a map.
   - Shows the storage date for each location.


   <img src="app/screenshots/map.png" alt="Map screen 1" width="300" height="700">
   

 - Screen 4: Get user location
   - Get device location and add it to Firebase (Cloud Firestore) every 5 minutes.
   - Show a notification when a location were successfuly regisred.
  
   <img src="app/screenshots/location.png" alt="Location screen 1" width="300" height="700">
   <img src="app/screenshots/location_notification.png" alt="Location notificacion screen" width="300" height="700">
  
- Screen 5: Image Capture and Upload
   - Allows selecting images from the device gallery.
   - Uploads the images to Firebase Storage.
 
    <img src="app/screenshots/gallery.png" alt="Gallery screen 1" width="300" height="700">
   <img src="app/screenshots/gallery_1.png" alt="Gallery screen 2" width="300" height="700">
   <img src="app/screenshots/gallery_2.png" alt="Gallery screen 3" width="300" height="700">

   
5. Implement Firebase Cloud and storage:
- Adds the device location to  Firebase (Cloud Firestore) every 5 minutes.
- Allow staorage a user image from device gallery to Firebase Storage.

    <img src="app/screenshots/firebase _cloud.png" alt="Firebase cloud" width="700" height="500">
    <img src="app/screenshots/firebase_storage.png" alt="Firebase storage" width="700" height="500">




  ## Netx steeps
   - Implement unit test into viewmodels
   - Add doamin layer 

  ## Contact

If you have any questions or a pice of advice to improve this project, you can contact Nery Bustos at nery.h.bustos@gmail.com.

