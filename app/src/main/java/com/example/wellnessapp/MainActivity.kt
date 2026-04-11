package com.example.wellnessapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Find buttons by use of their id's
        val HealthButton = findViewById<Button>(R.id.health_recipes)
        val NutritionAdviceButton = findViewById<Button>(R.id.nutrition_advice)
        val MeditationButton = findViewById<Button>(R.id.meditation)
        val HydrationButton = findViewById<Button>(R.id.hydration_alert)
        val StartExerciseButton = findViewById<Button>(R.id.start_exercise)
        val DailyMotivationButton = findViewById<Button>(R.id.daily_motivation)
        val WeekyGoalsButton = findViewById<Button>(R.id.weekly_goals)
        val CheckProgressButton = findViewById<Button>(R.id.check_progress)
        val LearnMoreButton = findViewById<Button>(R.id.learnmore)

//        Set on click listener to the buttons as you do the intent to the diffrent pages/activities
        HealthButton.setOnClickListener {
            val intent = Intent(applicationContext, HealthActivity::class.java)
            startActivity(intent)

            showInterstitialAd()
        }
//        =======================================================
        NutritionAdviceButton.setOnClickListener {
            val intent = Intent(applicationContext, NutritionActivity::class.java)
            startActivity(intent)
        }
//          ====================================
        MeditationButton.setOnClickListener {
            val intent = Intent(applicationContext, MeditationActivity::class.java)
            startActivity(intent)
        }
//        ========================================
        HydrationButton.setOnClickListener {
            val intent = Intent(applicationContext, HydrationActivity::class.java)
            startActivity(intent)

            showInterstitialAd()
        }
//        =======================================
        StartExerciseButton.setOnClickListener {
            val intent = Intent(applicationContext, ExerciseActivity::class.java)
            startActivity(intent)
        }
//        ==========================================
        DailyMotivationButton.setOnClickListener {
            val intent = Intent(applicationContext, MotivationActivity::class.java)
            startActivity(intent)
        }
//      ===================================================
        WeekyGoalsButton.setOnClickListener {
            val intent = Intent(applicationContext, WeeklyGoalsActivity::class.java)
            startActivity(intent)

            showInterstitialAd()
        }
//        ===================================================
        CheckProgressButton.setOnClickListener {
            val intent = Intent(applicationContext, ProgressActivity::class.java)
            startActivity(intent)

            showInterstitialAd()
        }

//        ============================================================================
//        Below is an implicit intent. When the button is clicked it takes us tothe default browser
        LearnMoreButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.healthline.com/health/how-to-maintain-a-healthy-lifestyle"))
            startActivity(intent)
        }

//       Implementation of the banner ads
        MobileAds.initialize(this)
        val adView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

//        Load your ad
        loadInterstitialAd()
        showInterstitialAd()
    }

    fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        //Requests interstitial ads
        InterstitialAd.load(
            this,
            "ca-app-pub-8714517234726413/2875455284", // Test ID
            adRequest,
            object : InterstitialAdLoadCallback() {

                override fun onAdLoaded(ad: InterstitialAd) {
                    mInterstitialAd = ad
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    mInterstitialAd = null
                }
            }
        )
    }
    //Function checks if ad already running not to run another one and overlap - which is wrong
    fun showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        }
    }
}