package com.geekconvert.runsantarun.gameobjects

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.graphics.scale
import com.geekconvert.runsantarun.AppConstants
import com.geekconvert.runsantarun.R

class BitmapBank(res: Resources, packageName: String) {

    var background: Bitmap
    var path: Bitmap
    lateinit var player : Array<Bitmap>
    lateinit var playerJump : Array<Bitmap>
    lateinit var playerDead : Array<Bitmap>
    var box: Bitmap
    var crystal: Bitmap
    var iceBox: Bitmap
    var snowMan: Bitmap
    var stone: Bitmap
    var tapToStart: Bitmap

    init {
        background = BitmapFactory.decodeResource(res, R.drawable.background)
        background = scaleImage(background)

        path = BitmapFactory.decodeResource(res, R.drawable.path)

        setupPlayerBitmaps(res)
        setupPlayerJumpBitmaps(res)
        setupPlayerDeadBitmaps(res)

        box = BitmapFactory.decodeResource(res, R.drawable.box)
        crystal = BitmapFactory.decodeResource(res, R.drawable.crystal)
        iceBox = BitmapFactory.decodeResource(res, R.drawable.icebox)
        snowMan = BitmapFactory.decodeResource(res, R.drawable.snowman)
        stone = BitmapFactory.decodeResource(res, R.drawable.stone)

        tapToStart = BitmapFactory.decodeResource(res, R.drawable.tap_to_start)
    }

    fun setupPlayerBitmaps(res: Resources){
        val ids = arrayOf(
            R.drawable.run1, R.drawable.run2, R.drawable.run3, R.drawable.run4,
            R.drawable.run5, R.drawable.run6, R.drawable.run7, R.drawable.run8,
            R.drawable.run9, R.drawable.run10, R.drawable.run11
        )
        player = Array(11) { index ->
            BitmapFactory.decodeResource(res, ids[index])
        }
    }

    fun setupPlayerJumpBitmaps(res: Resources){
        val ids = arrayOf(
            R.drawable.jump1, R.drawable.jump2, R.drawable.jump3, R.drawable.jump4,
            R.drawable.jump5, R.drawable.jump6, R.drawable.jump7, R.drawable.jump8,
            R.drawable.jump9, R.drawable.jump10, R.drawable.jump11, R.drawable.jump12,
            R.drawable.jump13, R.drawable.jump14, R.drawable.jump15, R.drawable.jump16
        )

        playerJump = Array(16) { index ->
            BitmapFactory.decodeResource(res, ids[index])
        }
    }

    fun setupPlayerDeadBitmaps(res: Resources){
        val ids = arrayOf(
            R.drawable.dead1, R.drawable.dead2, R.drawable.dead3, R.drawable.dead4,
            R.drawable.dead5, R.drawable.dead6, R.drawable.dead7, R.drawable.dead8,
            R.drawable.dead9, R.drawable.dead10, R.drawable.dead11, R.drawable.dead12,
            R.drawable.dead13, R.drawable.dead14, R.drawable.dead15, R.drawable.dead16,
            R.drawable.dead17
        )

        playerDead = Array(17) { index ->
            BitmapFactory.decodeResource(res, ids[index])
        }
    }

    // Return background width
    fun getBackgroundWidth(): Int {
        return background.width
    }

    // Return background height
    fun getBackgroundHeight(): Int {
        return background.height
    }

    // Return Path width
    fun getPathWidth(): Int {
        return path.getWidth()
    }

    // Return Path height
    fun getPathHeight(): Int {
        return path.getHeight()
    }

    // Return player width
    fun getPlayerWidth(): Int {
        return player[0].getWidth()
    }

    // Return player height
    fun getPlayerHeight(): Int {
        return player[0].getHeight()
    }


    // Return player dead bitmap
    fun getPlayerDead(pDFrame: Int): Bitmap {
        return playerDead[pDFrame]
    }

    // Return player dead width
    fun getPlayerDeadWidth(): Int {
        return playerDead[0].getWidth()
    }

    // Return player dead height
    fun getPlayerDeadHeight(): Int {
        return playerDead[0].getHeight()
    }

    // Return Box width
    fun getBoxWidth(): Int {
        return box.getWidth()
    }

    // Return Box height
    fun getBoxHeight(): Int {
        return box.getHeight()
    }

    // Return Crystal width
    fun getCrystalWidth(): Int {
        return crystal.getWidth()
    }

    // Return Crystal height
    fun getCrystalHeight(): Int {
        return crystal.getHeight()
    }

    // Return IceBox width
    fun getIceBoxWidth(): Int {
        return iceBox.getWidth()
    }

    // Return IceBox height
    fun getIceBoxHeight(): Int {
        return iceBox.getHeight()
    }

    // Return Snowman width
    fun getSnowmanWidth(): Int {
        return snowMan.getWidth()
    }

    // Return Snowman height
    fun getSnowmanHeight(): Int {
        return snowMan.getHeight()
    }

    //Return Stone width
    fun getStoneWidth(): Int {
        return stone.getWidth()
    }

    // Return Stone height
    fun getStoneHeight(): Int {
        return stone.getHeight()
    }

    //Return Tap to Start width
    fun getTapToStartWidth(): Int {
        return tapToStart.getWidth()
    }

    // Return Tap to Start height
    fun getTapToStartHeight(): Int {
        return tapToStart.getHeight()
    }

    fun scaleImage(bitmap: Bitmap): Bitmap {
        val widthHeightRatio: Float = getBackgroundWidth().toFloat() / getBackgroundHeight().toFloat()
        /*
        We'll multiply widthHeightRatio with screenHeight to get scaled width of the bitmap. Then call createScaledBitmap() to create a new bitmap,
        scaled from an existing bitmap, when possible.
         */
        val backgroundScaledWidth = widthHeightRatio.toInt() * AppConstants.SCREEN_HEIGHT
        return bitmap.scale(backgroundScaledWidth, AppConstants.SCREEN_HEIGHT, false)
    }
}