# Zachs-Wonder-Emporium
Udacity Capstone Project

## Build & Run Instructions
This project expects a Google Maps API Key in the `local.properties` file in the format `MAPS_API_KEY=<api_key>`

## Documentation on the Project Milestones

## Android UI/UX

### Build a navigable interface consisting of multiple screens of functionality and data

_**Requirement**_: Application includes at least three screens with distinct features using either the Android Navigation Controller or Explicit Intents
  - My application contains 5 screens, which can be navigated between by using the bottom navigation view

_**Requirement**_: The Navigation Controller is used for Fragment-based navigation and intents are utilized for Activity-based navigation
  - My application is a single activity application so uses fragment-based navigation

_**Requirement**_: An application bundle is built to store data passed between Fragments and Activities
  - A bundle is used to display the Point Of Interest detail on the Detail Screen. The selected Point Of Interest in the Time Screen is passed to the Detail Screen via a bundle and also using navigation safe args.

<p>
  <img src="/documentation/Explore_Screen.png" width="18%">
  <img src="/documentation/My_Plan_Screen.png" width="18%">
  <img src="/documentation/Add_To_Plan_Screen.png" width="18%">
  <img src="/documentation/Times_Screen.png" width="18%">
  <img src="/documentation/Detail_Screen.png" width="18%">
</p>

### Construct interfaces that adhere to Android standards and display appropriately on screens of different size and resolution

_**Requirement**_: Application UI effectively utilizes `ConstraintLayout` to arrange UI elements effectively and efficiently across application features, avoiding nesting layouts and maintaining a flat UI structure where possible.
  - My layouts are all built using `ConstraintLayout`.

_**Requirement**_: Data collections are displayed effectively, taking advantage of visual hierarchy and arrangement to display data in an easily consumable format
  - My application displays Points Of Interest in a map format so the user can see their location with respect to Points Of Interest when in the theme park. It also displays them in the Times screen in an easy to consume format within a recycler view. The user can easily see the Points Of Interest and their relevant wait times/show times/opening times, which is the most key information for a user.

_**Requirement**_: Resources are stored appropriately using the internal `res` directory to store data in appropriate locations including `string` values, `drawables`, `colors`, `dimensions`, and more
  - The `res` folder is used appropriately storing string values, drawables that are required for the project, the font that is used by the project, dimensions that are used in layouts and also json files to style the `MapView`.

_**Requirement**_: Every element within ConstraintLayout should include the id field and at least 1 vertical constraint
  - Yes this is satsified

_**Requirement**_: Data collections should be loaded into the application using ViewHolder pattern and appropriate View, such as RecyclerView
  - I have 3 recycler views within the project on different screens, which all are loaded using the ViewHolder pattern.

### Animate UI components to better utilize screen real estate and create engaging content

_**Requirement**_: Application contains at least 1 feature utilizing MotionLayout to adapt UI elements to a given function. This could include animating control elements onto and off screen, displaying and hiding a form, or animation of complex UI transitions..
  - The Details Screen uses `MotionLayout` to animate the text on the page, creating a more engaging experience.

_**Requirement**_: MotionLayout behaviors are defined in a MotionScene using one or more Transition nodes and ConstraintSet blocks
  - Yes this is satisfied
  
_**Requirement**_: Constraints are defined within the scenes and house all layout params for the animation
  - Yes this is satisfied

## Local and Network Data

### Connect to and consume data from a remote data source such as a RESTful API

_**Requirement**_: The Application connects to at least 1 external data source using Retrofit or other appropriate library/component and retrieves data for use within the application
  - I have used Firebase Firestore as I didn't have access to a REST API for this project.

_**Requirement**_: Data retrieved from the remote source is held in local models with appropriate data types that are readily handled and manipulated within the application source. Helper libraries such as Moshi may be used to assist with this requirement
  - The data gathered from Firebase Firestore is mapped to a `LiveTime` object via the Firebase `toObject(..)` method.
  
_**Requirement**_: The application performs work and handles network requests on the appropriate threads to avoid stalling the UI
  - Yes coroutines are utilised to move network and database operations to a background thread by using `viewModelScope.launch (Dispatchers.IO) {...}`

### Load network resources, such as Bitmap Images, dynamically and on-demand

_**Requirement**_: The Application loads remote resources asynchronously using an appropriate library such as Glide or other library/component when needed
  - I have used Glide in the project to load icons and images from the remote resources

_**Requirement**_: Images display placeholder images while being loaded and handle failed network requests gracefully
  - Placeholder and Error images have been utilised
  
_**Requirement**_: All requests are performed asynchronously and handled on the appropriate threads
  - Yes this is satisfied

### Store data locally on the device for use between application sessions and/or offline use

_**Requirement**_: The application utilizes storage mechanisms that best fit the data stored to store data locally on the device. Example: SharedPreferences for user settings or an internal database for data persistence for application data. Libraries such as Room may be utilized to achieve this functionality
  - Data is persisted using a Room Database with 3 tables

_**Requirement**_: Data stored is accessible across user sessions
  - All necessary data is stored in Room
  
_**Requirement**_: Data storage operations are performed on the appropriate threads as to not stall the UI thread
  - Yes coroutines are utilised to move network and database operations to a background thread by using `viewModelScope.launch (Dispatchers.IO) {...}`. In some cases LiveData is used also.

_**Requirement**_: Data is structured with appropriate data types and scope as required by application functionality
  - Yes this is satisfied

## Android System and Hardware Integration

### Architect application functionality using MVVM

_**Requirement**_: Application separates responsibilities amongst classes and structures using the MVVM Pattern
  - The MVVM pattern is used throughout the project

_**Requirement**_: Application adheres to architecture best practices, such as the observer pattern, to prevent leaking components, such as Activity Contexts, and efficiently utilize system resources
  - Yes the observer pattern is used with the `ViewModel` objects throughout. The `Koin` library has also been used to provide Dependency Injection

### Implement logic to handle and respond to hardware and system events that impact the Android Lifecycle

_**Requirement**_: Beyond MVVM, the application handles system events, such as orientation changes, application switching, notifications, and similar events gracefully.
  - The fragments use the ViewModel to observe data. Location permissions are handled and also checking for the users device location settings being switched on. These requests use the `registerActivityForResult` with a `ActivityResultContracts` as specified in the documentation.

### Utilize system hardware to provide the user with advanced functionality and features.

_**Requirement**_: Application utilizes at least 1 hardware component to provide meaningful functionality to the application as a whole
  - Yes the app uses the user's location so that they can see their location relative to Points of Interest in the theme park they may wish to visit.

_**Requirement**_: Permissions to access hardware features are requested at the time of use for the feature
  - Yes the app requests location permissions when the user navigates to the Explore Screen as this is the feature that uses it. This also happens to be the initial screen.

_**Requirement**_: Behaviors are accessed only after permissions are granted
  - Yes the users current location will only be displayed once they have granted the app permission

