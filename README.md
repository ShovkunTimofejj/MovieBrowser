# 🎬 MovieBrowse

**MovieBrowse** is an Android application for browsing popular movies with the ability to add and remove them from favorites. It is implemented using modern Android development stack and architectural approaches.

---

## ⚙️ Technologies

The project is built using the following technologies:

- 🟨 **Kotlin** — primary development language  
- 🧱 **Jetpack Compose** — declarative UI framework  
- 🔗 **Retrofit** — network requests to the [TMDb API](https://www.themoviedb.org/)  
- 🗂️ **Room** — local storage for favorite movies  
- 🧠 **ViewModel** — screen state management  
- 🌊 **StateFlow** and **Coroutines** — reactivity and asynchronous programming  
- 🧭 **Navigation Compose** — navigation between screens  

---

## 📱 How the app works

- 📌 **Main screen**: Displays a list of popular movies fetched from the TMDb API.
- 📄 **Movie detail view**: Tap on a movie to open a screen with its description, release date, and image.
- ⭐ **Add to favorites**:
  - Tap the ⭐ icon on the movie card on the main screen.
  - Or tap the ❤️ icon on the movie detail screen.
- ❌ **Remove from favorites**:
  - Tap the ⭐ icon again on the main screen.
  - Or tap the ❤️ icon on the movie detail screen.
  - You can also remove a movie directly from the "Favorites" screen.
- 📂 **Favorites screen**: Shows all the movies added to favorites. You can also open the movie detail or remove it from the list here.

All favorite movies are stored locally via Room and are available even after restarting the app.

---

## 🧑‍💻 Author

**Tymofii Shovkun**  
[GitHub](https://github.com/ShovkunTimofejj) • [LinkedIn](https://www.linkedin.com/in/tymofii-shovkun/)

---

## 📄 Note

Movie data provided by [The Movie Database (TMDb)](https://www.themoviedb.org/documentation/api).
