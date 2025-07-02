
# 🕺 Kotlin Pose Estimation App

A modern Android app built with Kotlin, Jetpack Compose, and MediaPipe to detect and visualize human body pose landmarks in real time. The app captures live video from the camera and overlays stick-figure landmarks directly on top of the preview. This is ideal for fitness tracking, gesture recognition, or any human activity AI projects. It is lightweight, works fully on-device, and uses MediaPipe’s highly-optimized pose detection pipeline. 🚀  

## ✨ Features

- Real-time pose landmark detection  
- Stick-figure overlay on camera preview  
- Kotlin + Jetpack Compose for modern Android development  
- Uses MediaPipe’s world-class on-device ML models  
- Fully offline, no cloud required 🤖  
- Extendable for activity classification or gesture recognition  

## 🚀 How to Run

1. **Install MediaPipe dependencies**  
   Follow [MediaPipe Android installation instructions](https://google.github.io/mediapipe/getting_started/android.html) to add the required AAR and dependencies to your Android Studio project.

2. **Clone the repo**  
   ```bash
   git clone https://github.com/yourusername/kotlin-pose-estimation-app.git
   ```
3. **Open in Android Studio**  
   - Sync Gradle  
   - Plug in your physical Android device  
   - Grant camera permissions when prompted  
   - Click **Run** — watch live pose landmarks on the preview!  

## 🛠 Customization Ideas

- Change landmark colors or size  
- Add gesture detection (e.g., recognize a thumbs-up)  
- Integrate rep counters for exercises  
- Export pose data to CSV or JSON  
- Add yoga pose scoring  

## 🌟 Next Features

- Real-time exercise evaluation  
- Calorie estimation based on pose  
- Cloud synchronization for pose data  
- Social workout sharing  

## 🧩 Project Structure

- `MainActivity.kt`  
   Handles camera feed, initializes MediaPipe, processes pose landmarks, and draws overlays.  
- `build.gradle`  
   Configured to include the correct MediaPipe dependencies.  
- `AndroidManifest.xml`  
   Configured with proper permissions (`CAMERA`, etc).  

## 📷 Screenshots

You can add screenshots here after you build and test on a device, for example:  
- Home screen showing the camera preview  
- Pose detection running with landmarks drawn  
- Example gestures  
