package com.geekconvert.runsantarun

class PlayerDead : Player {

    constructor() {
        pX = AppConstants.SCREEN_WIDTH / 3 - AppConstants.bitmapBank.getPlayerDeadWidth();
        pYInitial = AppConstants.SCREEN_HEIGHT - AppConstants.bitmapBank.getPathHeight() - AppConstants.bitmapBank.getPlayerDeadHeight();
        pY = AppConstants.SCREEN_HEIGHT - AppConstants.bitmapBank.getPathHeight() - AppConstants.bitmapBank.getPlayerDeadHeight();
        pFrame = 0;
        velocity = 0;
    }
}