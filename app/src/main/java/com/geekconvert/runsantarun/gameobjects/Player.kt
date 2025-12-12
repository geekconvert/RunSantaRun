package com.geekconvert.runsantarun.gameobjects

import com.geekconvert.runsantarun.AppConstants

open class Player {

    var pX: Int = 0
    var pY: Int = 0
    var pYInitial: Int = 0
    var pFrame: Int = 0
    var velocity: Int = 0

    constructor() {
        pX = AppConstants.SCREEN_WIDTH / 3
        pYInitial = AppConstants.SCREEN_HEIGHT - AppConstants.bitmapBank.getPathHeight() - AppConstants.bitmapBank.getPlayerHeight()
        pY = AppConstants.SCREEN_HEIGHT - AppConstants.bitmapBank.getPathHeight() - AppConstants.bitmapBank.getPlayerHeight()
        pFrame = 0
        velocity = 0
    }
}