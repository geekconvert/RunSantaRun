package com.geekconvert.runsantarun

import android.os.Bundle
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class GameActivity: AppCompatActivity() {
    var gameView: GameView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppConstants.gameActivityContext = this

        gameView = GameView(this)

        val relativeLayout = RelativeLayout(this)
        relativeLayout.layoutParams = ViewGroup.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
        relativeLayout.addView(gameView)

        setContentView(relativeLayout)
    }

    override fun onDestroy() {
        super.onDestroy()
        MainActivity.mediaPlayer?.let {
            it.stop()
            it.release()
            MainActivity.mediaPlayer = null
        }
    }
}