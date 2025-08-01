package com.geekconvert.runsantarun

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class GameOver: AppCompatActivity() {

    var tvScore: TextView? = null
    var tvPersonalBest: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_over)

        val points = intent.extras?.getInt("points") ?: 0
        val pref = getSharedPreferences("MyPref", 0)
        var pointsSP = pref.getInt("pointsSP", 0)
        val editor = pref.edit()
        if (points > pointsSP) {
            pointsSP = points
            editor.putInt("pointsSP", pointsSP)
            editor.apply()
        }
        tvScore = findViewById(R.id.tvPoints)
        tvPersonalBest = findViewById(R.id.tvPersonalBest)
        tvScore?.text = points.toString()
        tvPersonalBest?.text = pointsSP.toString()
    }

    fun restart(view: View?) {
        val intent = Intent(this@GameOver, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun exit(view: View?) {
        finish()
    }
}