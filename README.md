# Tasks App <a name="about-project"></a>

A simple Android task management application built with Kotlin and Jetpack Compose. The application demonstrates modern Android development concepts including state management, navigation, reusable composables, and dynamic UI updates.

## 🛠️ Built With

- **Language:** Kotlin
- **UI Toolkit:** Jetpack Compose
- **Navigation:** Navigation Compose
- **Architecture:** Android Activity + Composable Functions
- **State Management:** Compose State (`remember`, `mutableStateOf`, `rememberSaveable`)
- **Design System:** Material Design 3
- **Development Environment:** Android Studio
- **SDK:** Android SDK

---

### Key Features <a name="key-features"></a>

- ✅ Display tasks using a modern Jetpack Compose interface
- ✅ Add new tasks dynamically
- ✅ Mark tasks as completed or pending
- ✅ Delete tasks individually
- ✅ Display task priorities (LOW, MEDIUM, HIGH)
- ✅ Strike-through completed tasks automatically
- ✅ Real-time completion statistics
- ✅ Progress percentage calculation
- ✅ Navigation between multiple screens using Navigation Compose
- ✅ Dedicated Add Task screen
- ✅ Dedicated About screen
- ✅ Reusable `TaskRow` and `TaskList` composables
- ✅ Task model implemented using Kotlin data classes
- ✅ State preserved using Compose state management
- ✅ Debug logging through Logcat

---

## 💻 Getting Started <a name="getting-started"></a>

To get a local copy up and running, follow these steps.

### Prerequisites

Before running this project, make sure you have:

- Android Studio installed
- Android SDK configured
- Kotlin support enabled
- Android Emulator or physical Android device
- JDK 11 or later

### Setup

Clone this repository:

```sh
git clone https://github.com/AbigiyaTY/Tasks.git
```

Navigate to the project directory:

```sh
cd Tasks
```

### Install

Open the project in Android Studio:

```sh
File → Open → Select Project Folder
```

Allow Android Studio to download and synchronize all required Gradle dependencies.

### Usage

This project demonstrates:

- Kotlin data classes
- Enum classes
- State management with Compose
- Navigation Compose
- Material Design 3
- Dynamic UI updates through recomposition
- LazyColumn for displaying lists
- Reusable composables
- Event-driven UI interactions
- Navigation between screens

### Application Workflow

#### Home Screen

The home screen displays:

- List of tasks
- Completion statistics
- Progress percentage
- Add Task button
- About button

#### Add Task Screen

Users can:

- Enter a task title
- Save the task
- Cancel and return to the home screen

#### About Screen

Displays basic application information and navigation back to the home screen.

### Run the App

Run the application using Android Studio:

```sh
Run > Run 'app'
```

Or use the keyboard shortcut:

```sh
Shift + F10
```

The application will launch on the selected emulator or Android device.

---

## 📱 Features Demonstrated

### Task Management

- Create tasks
- Complete tasks
- Reopen completed tasks
- Delete tasks
- Track completion progress

### Navigation

Implemented using Navigation Compose:

```text
Home Screen
      │
      ├── Add Task Screen
      │       └── Save / Cancel
      │
      └── About Screen
              └── Back
```

### State Management

The application demonstrates:

- `remember`
- `rememberSaveable`
- `mutableStateOf`
- State-driven UI updates
- Immutable list transformations using:
  - `map()`
  - `filter()`

### Debugging

The project includes examples of:

- Logcat logging
- State inspection
- Navigation debugging
- Event tracing


## 🤝 Contributing <a name="contributing"></a>

Contributions, issues, and feature requests are welcome!

Feel free to fork this repository and submit a pull request.
