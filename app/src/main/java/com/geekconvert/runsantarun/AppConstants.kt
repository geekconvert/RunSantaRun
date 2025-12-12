package com.geekconvert.runsantarun

import android.app.Application
import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import com.geekconvert.runsantarun.gameobjects.BitmapBank
import com.geekconvert.runsantarun.gameobjects.GameEngine

object AppConstants {

    lateinit var bitmapBank: BitmapBank
    lateinit var gameEngine: GameEngine

    var SCREEN_WIDTH: Int = 0
    var SCREEN_HEIGHT: Int = 0
    var gravity: Int = 0
    var VELOCITY_WHEN_JUMPED: Int = 0
    var VELOCITY_OBSTACLES: Int = 0
    var playerGrounded: Boolean = true

    lateinit var gameActivityContext: Context

    fun initialization(context: Context) {
        setScreenSize(context)
        gameActivityContext = context
        bitmapBank = BitmapBank(context.resources, context.packageName)
        setGameConstants()
        gameEngine = GameEngine()
    }

    private fun setScreenSize(context: Context) {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val metrics = DisplayMetrics()
        display.getMetrics(metrics)
        SCREEN_WIDTH = metrics.widthPixels
        SCREEN_HEIGHT = metrics.heightPixels
    }

    private fun setGameConstants() {
        gravity = 3
        VELOCITY_WHEN_JUMPED = -40
        VELOCITY_OBSTACLES = 20
        playerGrounded = true
    }

}
