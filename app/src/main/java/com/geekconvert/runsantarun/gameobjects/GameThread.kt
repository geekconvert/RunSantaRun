package com.geekconvert.runsantarun.gameobjects

import android.util.Log
import android.view.SurfaceHolder
import com.geekconvert.runsantarun.AppConstants


class GameThread(val surfaceHolder: SurfaceHolder) : Thread() {

    var isRunning: Boolean = false // Flag to detect whether the thread is running or not
    var startTime: Long = 0
    var loopTime: Long = 0 // Loop start time and loop duration
    var DELAY: Long = 33 // Delay in milliseconds between screen refreshes

    init {
        isRunning = true
    }

    override fun run() {
        // Looping until the boolean is false
        while (isRunning) {
            startTime = System.currentTimeMillis()
            // lock the canvas
            val canvas = surfaceHolder.lockCanvas(null)
            if (canvas != null) {
                synchronized(surfaceHolder) {
                    AppConstants.gameEngine.updateAndDrawBackgroundImage(canvas);
                    AppConstants.gameEngine.updateAndDrawPath(canvas);
                    AppConstants.gameEngine.updateAndDrawPlayer(canvas);
                    AppConstants.gameEngine.updateAndDrawObstacles(canvas);
                    AppConstants.gameEngine.tapToStart(canvas);
                    // unlock the canvas
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            //loop time
            loopTime = System.currentTimeMillis() - startTime
            // Pause here to make sure we update the right amount per second
            if(loopTime < DELAY){
                try {
                    sleep(DELAY - loopTime)
                } catch (e: InterruptedException) {
                    Log.e("Interrupted", "Interrupted while sleeping")
                }
            }
        }
    }

    // Sets the thread state, false = stopped, true = running
    fun setIsRunning(state: Boolean) {
        isRunning = state
    }
}