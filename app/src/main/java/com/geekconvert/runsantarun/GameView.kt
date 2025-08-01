package com.geekconvert.runsantarun

import android.content.Context
import android.media.MediaPlayer
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView(context: Context): SurfaceView(context), SurfaceHolder.Callback {

    var gameThread: GameThread? = null
    var mediaPlayer: MediaPlayer? = null

    init {
        mediaPlayer = MediaPlayer.create(context, R.raw.jump)
        initView()
    }

    fun initView() {
        val surfaceHolder = holder
        holder.addCallback(this)
        isFocusable = true
        gameThread = GameThread(surfaceHolder)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        if(gameThread?.isRunning == false){
            gameThread = GameThread(holder)
            gameThread?.start()
        }else {
            gameThread?.start()
        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        if (gameThread?.isRunning == true) {
            gameThread?.isRunning = false
            var retry = true
            while (retry) {
                try {
                    gameThread!!.join()
                    retry = false
                } catch (e: InterruptedException) {
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = event?.action

        // Tap is detected
        if (action == MotionEvent.ACTION_DOWN) {
            if (GameEngine.gameState == 0) {
                GameEngine.gameState = 1
            }
            if (AppConstants.playerGrounded) {
                AppConstants.gameEngine.player.velocity = (AppConstants.VELOCITY_WHEN_JUMPED)
                AppConstants.playerGrounded = false
            }
            mediaPlayer!!.start()
        }
        return true
    }
}