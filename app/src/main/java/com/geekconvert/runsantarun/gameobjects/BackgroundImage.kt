package com.geekconvert.runsantarun.gameobjects

class BackgroundImage {

    var backgroundImageX: Int = 0
    var backgroundImageY: Int = 0
    var backgroundImageVelocity: Int = 0

    constructor() {
        backgroundImageX = 0
        backgroundImageY = 0
        backgroundImageVelocity = 3
    }

    //Getter method for getting the X-coordinate
    fun getX(): Int {
        return backgroundImageX
    }

    //Getter method for getting the Y-coordinate
    fun getY(): Int {
        return backgroundImageY
    }

    //Setter method for setting the X-coordinate
    fun setX(backgroundImageX: Int) {
        this.backgroundImageX = backgroundImageX
    }

    //Getter method for getting the velocity
    fun getVelocity(): Int {
        return backgroundImageVelocity
    }
}