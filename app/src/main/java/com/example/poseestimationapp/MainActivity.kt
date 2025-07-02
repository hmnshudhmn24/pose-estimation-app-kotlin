
package com.example.poseestimationapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Size
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.mediapipe.components.*
import com.google.mediapipe.formats.proto.LandmarkProto
import com.google.mediapipe.framework.PacketGetter
import com.google.mediapipe.solutioncore.CameraInput
import com.google.mediapipe.solutioncore.SolutionBase
import com.google.mediapipe.solutions.pose.Pose
import com.google.mediapipe.solutions.pose.PoseResult

class MainActivity : ComponentActivity() {
    private lateinit var pose: Pose
    private lateinit var cameraInput: CameraInput
    private lateinit var surfaceView: SurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                200
            )
        }

        pose = Pose(
            this,
            Pose.PoseOptions.builder()
                .setStaticImageMode(false)
                .setModelComplexity(1)
                .setSmoothLandmarks(true)
                .build()
        )

        setContent {
            PoseEstimationApp()
        }
    }

    @Composable
    fun PoseEstimationApp() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text("🕺 Kotlin Pose Estimation", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(12.dp))
                AndroidView(factory = {
                    surfaceView = SurfaceView(this@MainActivity)
                    startCamera()
                    surfaceView
                })
            }
        }
    }

    private fun startCamera() {
        cameraInput = CameraInput(this)
        cameraInput.setNewFrameListener { textureFrame ->
            pose.send(textureFrame)
        }

        pose.setResultListener { result: PoseResult ->
            drawLandmarks(result)
        }

        cameraInput.start(this, pose.glContext, CameraInput.CameraFacing.FRONT, surfaceView.holder)
    }

    private fun drawLandmarks(result: PoseResult) {
        val canvas = surfaceView.holder.lockCanvas() ?: return
        canvas.drawColor(android.graphics.Color.TRANSPARENT)
        val landmarks: List<LandmarkProto.Landmark> = result.poseLandmarks?.landmarkList ?: return
        for (landmark in landmarks) {
            val x = landmark.x * canvas.width
            val y = landmark.y * canvas.height
            val paint = android.graphics.Paint().apply {
                color = android.graphics.Color.RED
                strokeWidth = 10f
            }
            canvas.drawCircle(x, y, 8f, paint)
        }
        surfaceView.holder.unlockCanvasAndPost(canvas)
    }

    override fun onDestroy() {
        super.onDestroy()
        pose.close()
        cameraInput.close()
    }
}
