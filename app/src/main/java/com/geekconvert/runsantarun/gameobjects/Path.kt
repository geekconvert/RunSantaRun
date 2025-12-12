package com.geekconvert.runsantarun.gameobjects

import com.geekconvert.runsantarun.AppConstants

class Path {

    var pathX: Int = 0
    var pathY: Int = 0
    var pathVelocity: Int = 0

    constructor(){
        pathX = 0;
        pathY = AppConstants.SCREEN_HEIGHT - AppConstants.bitmapBank.getPathHeight();
        pathVelocity = 15;
    }
}