package com.example.wellnessapp

import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * SplashActivity serves as the entry point of the application.
 * It displays a branded splash screen with an animated icon for a fixed duration
 * before navigating to the [MainActivity].
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Enable edge-to-edge display for a modern UI experience.
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        // Handle window insets to ensure content is not obscured by system bars.
        val rootView = findViewById<android.view.View>(R.id.main)
        if (rootView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        // Initialize and start the splash icon animation if it is an AnimatedVectorDrawable.
        val imageView = findViewById<ImageView>(R.id.splashIcon)
        val drawable = imageView.drawable
        if (drawable is AnimatedVectorDrawable) {
            drawable.start()
        }

        // Delay for 5 seconds to show the splash screen, then transition to MainActivity.
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                // Finish SplashActivity so the user cannot return to it via the back button.
                finish()
            }, 5000
        )
    }
}
