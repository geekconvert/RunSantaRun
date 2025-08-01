package com.geekconvert.runsantarun

import android.graphics.Canvas
import java.util.Random

class Obstacles {

    var cX: Int = 0
    var cY: Int = 0
    var type: String
    var velocity: Int = 0
    var width: Int = 0
    var random: Random = Random()

    constructor(type: String) {
        this.type = type
        cX = AppConstants.SCREEN_WIDTH + 1000
        cY = AppConstants.SCREEN_HEIGHT - AppConstants.bitmapBank.getPathHeight()

        if (type == ("Box")) {
            cY -= AppConstants.bitmapBank.getBoxHeight();
            this.velocity = AppConstants.VELOCITY_OBSTACLES + 14 + random.nextInt(5);// 14-18
            width = AppConstants.bitmapBank.getBoxWidth();
        } else if (type == ("Crystal")) {
            cY -= AppConstants.bitmapBank.getCrystalHeight();
            this.velocity = AppConstants.VELOCITY_OBSTACLES + 12 + random.nextInt(5); // 12-16
            width = AppConstants.bitmapBank.getCrystalWidth();
        } else if (type == ("IceBox")) {
            cY -= AppConstants.bitmapBank.getIceBoxHeight();
            this.velocity = AppConstants.VELOCITY_OBSTACLES + 11 + random.nextInt(5); // 11-15
            width = AppConstants.bitmapBank.getIceBoxWidth();
        } else if (type == ("Snowman")) {
            cY -= AppConstants.bitmapBank.getSnowmanHeight();
            this.velocity = AppConstants.VELOCITY_OBSTACLES + 10 + random.nextInt(6); // 10-15
            width = AppConstants.bitmapBank.getSnowmanWidth();
        } else if (type == ("Stone")) {
            cY -= AppConstants.bitmapBank.getStoneHeight();
            this.velocity = AppConstants.VELOCITY_OBSTACLES + 15 + random.nextInt(11); // 15-25
            width = AppConstants.bitmapBank.getStoneWidth();
        }
    }

    fun reset() {
        cX = AppConstants.SCREEN_WIDTH + 300;
        if (type == ("Box")) {
            this.velocity = AppConstants.VELOCITY_OBSTACLES + 14 + random.nextInt(5);// 14-18
        } else if (type == ("Crystal")) {
            this.velocity = AppConstants.VELOCITY_OBSTACLES + 12 + random.nextInt(5); // 12-16
        } else if (type == ("IceBox")) {
            this.velocity = AppConstants.VELOCITY_OBSTACLES + 11 + random.nextInt(5); // 11-15
        } else if (type == ("Snowman")) {
            this.velocity = AppConstants.VELOCITY_OBSTACLES + 10 + random.nextInt(6); // 10-15
        } else if (type == ("Stone")) {
            this.velocity = AppConstants.VELOCITY_OBSTACLES + 15 + random.nextInt(11); // 15-25
        }
    }
}