package com.geekconvert.runsantarun

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var ibStaruButton: ImageView? = null

    companion object {
        var mediaPlayer: MediaPlayer? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.bg_music)
        mediaPlayer?.let {
            it.isLooping = true
            it.start()
        }

        AppConstants.initialization(this.applicationContext)

        ibStaruButton = findViewById(R.id.ibStartGame)
        ibStaruButton?.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            startActivity(intent)
            finish()
        }

        onBackPressedDispatcher.addCallback(this) {
            handleCustomCallback()
        }
    }

    fun handleCustomCallback() {
        mediaPlayer?.let {
            it.stop()
            it.release()
            mediaPlayer = null
        }
    }
}