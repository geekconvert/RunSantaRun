package com.geekconvert.runsantarun

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.media.MediaPlayer
import java.util.Random


class GameEngine {

    companion object {
        var gameState: Int = 0
    }

    var backgroundImage: BackgroundImage = BackgroundImage()
    var path: Path = Path()
    var player: Player = Player()
    var playerDead: PlayerDead = PlayerDead()
    var random: Random = Random()
    var score: Int = 0
    var scorePaint: Paint = Paint()
    var pFrame: Int = 0
    var pJFrame: Int = 0
    var pDFrame: Int = 0
    var obstaclesList: ArrayList<Obstacles> = arrayListOf()
    var obstacles: Obstacles = Obstacles("")
    var obstacles1: Obstacles = Obstacles("Box")
    var obstacles2: Obstacles = Obstacles("Crystal")
    var obstacles3: Obstacles = Obstacles("IceBox")
    var obstacles4: Obstacles = Obstacles("Snowman")
    var obstacles5: Obstacles = Obstacles("Stone")
    var obs: Bitmap? = null
    var obsSpawned: Boolean = false
    var points: Int = 0
    val TEXT_SIZE: Int = 80
    var collision: Boolean = false
    var hit: MediaPlayer
    var hitSound: Boolean = false

    constructor() {
        // 0 = Not started
        // 1 = Playing
        // 2 = GameOver
        gameState = 0;
        obsSpawned = false
        pFrame = 0
        pJFrame = 0
        pDFrame = 0
        score = 0
        scorePaint.setColor(Color.RED)
        scorePaint.setTextSize(TEXT_SIZE.toFloat())
        scorePaint.setTextAlign(Paint.Align.LEFT)
        obstaclesList.add(obstacles1)
        obstaclesList.add(obstacles2)
        obstaclesList.add(obstacles3)
        obstaclesList.add(obstacles4)
        obstaclesList.add(obstacles5)
        points = 0;
        hitSound = false;
        hit = MediaPlayer.create(AppConstants.gameActivityContext, R.raw.hit);
    }

    fun updateAndDrawBackgroundImage(canvas: Canvas) {
        if (!collision) {
            backgroundImage.setX(backgroundImage.getX() - backgroundImage.getVelocity())
            if (backgroundImage.getX() < -AppConstants.bitmapBank.getBackgroundWidth()) {
                backgroundImage.setX(0);
            }
        }
        canvas.drawBitmap(AppConstants.bitmapBank.background, backgroundImage.getX().toFloat(), backgroundImage.getY().toFloat(), null)
        if (backgroundImage.getX() < -(AppConstants.bitmapBank.getBackgroundWidth() - AppConstants.SCREEN_WIDTH)) {
            canvas.drawBitmap(
                AppConstants.bitmapBank.background,
                (backgroundImage.getX() + AppConstants.bitmapBank.getBackgroundWidth()).toFloat(),
                backgroundImage.getY().toFloat(), null
            );
        }
    }

    fun updateAndDrawPath(canvas: Canvas) {
        if (!collision) {
            path.pathX = (path.pathX - path.pathVelocity);
            if (path.pathX < -AppConstants.bitmapBank.getPathWidth()) {
                path.pathX = (0);
            }
        }
        canvas.drawBitmap(AppConstants.bitmapBank.path, path.pathX.toFloat(), path.pathY.toFloat(), null);
        if (path.pathX < -(AppConstants.bitmapBank.getPathWidth() - AppConstants.SCREEN_WIDTH)) {
            canvas.drawBitmap(
                AppConstants.bitmapBank.path,
                (path.pathX + AppConstants.bitmapBank.getPathWidth()).toFloat(), path.pathY.toFloat(), null
            );
        }
    }

    fun updateAndDrawPlayer(canvas: Canvas){
        if(gameState == 1){
            if(!collision && !AppConstants.playerGrounded){
                player.velocity = (player.velocity + AppConstants.gravity)
                player.pY = (player.pY + player.velocity)
                canvas.drawBitmap(AppConstants.bitmapBank.playerJump[pJFrame], player.pX.toFloat(), player.pY.toFloat(), null)
                pJFrame++
                if (pJFrame > 15) {
                    pJFrame = 0
                }
                if (player.pY >= player.pYInitial) {
                    player.pY = (player.pYInitial)
                    AppConstants.playerGrounded = true
                }
            }else if (collision && !AppConstants.playerGrounded) {
                playerDead.velocity = (playerDead.velocity + AppConstants.gravity);
                playerDead.pY = (playerDead.pY + playerDead.velocity);
                canvas.drawBitmap(AppConstants.bitmapBank.getPlayerDead(pDFrame), playerDead.pX.toFloat(), playerDead.pY.toFloat(), null);
                pDFrame++;
                if (pDFrame == 17) {
                    gameState = 2;
                    val context = AppConstants.gameActivityContext;
                    val intent = Intent(context, GameOver::class.java);
                    intent.putExtra("points", points);
                    context.startActivity(intent);
                    (context as Activity).finish();
                }
                if (playerDead.pY >= playerDead.pYInitial) {
                    playerDead.pY = (playerDead.pYInitial);
                    AppConstants.playerGrounded = true;
                }
                if(!hitSound) {
                    hit.start();
                    hitSound = true;
                }
            }else if (!collision && AppConstants.playerGrounded) {
                canvas.drawBitmap(AppConstants.bitmapBank.player[pFrame], player.pX.toFloat(), player.pY.toFloat(), null);
                pFrame++;
                if (pFrame > 10) {
                    pFrame = 0;
                }
            }else if (collision && AppConstants.playerGrounded) {
                canvas.drawBitmap(AppConstants.bitmapBank.getPlayerDead(pDFrame), playerDead.pX.toFloat(), playerDead.pY.toFloat(), null);
                pDFrame++;
                if (pDFrame == 16) {
                    gameState = 2;
                    val context = AppConstants.gameActivityContext;
                    val intent = Intent(context, GameOver::class.java);
                    intent.putExtra("points", points);
                    context.startActivity(intent);
                    (context as Activity).finish();
                }
                if(!hitSound) {
                    hit.start();
                    hitSound = true;
                }
            }
            if (obstacles.cX <= player.pX + AppConstants.bitmapBank.getPlayerWidth() && obstacles.cX + obstacles.width >= player.pX
                && obstacles.cY >= player.pY
                && obstacles.cY <= player.pY + AppConstants.bitmapBank.getPlayerHeight()) {
                collision = true;
                obstacles.reset();
            }
            canvas.drawText("Pt: " + points, 0.toFloat(), TEXT_SIZE.toFloat(), scorePaint);
        }
    }

    fun updateAndDrawObstacles(canvas: Canvas){
        if(gameState == 1){
            if(!obsSpawned){
                val randIndex = random.nextInt(5)
                obstacles = obstaclesList[randIndex]
                obsSpawned = true
            }
            if (!collision) {
                obstacles.cX -= obstacles.velocity;
                if (obstacles.type =="Box") {
                    obs = AppConstants.bitmapBank.box;
                }
                if (obstacles.type =="Crystal") {
                    obs = AppConstants.bitmapBank.crystal;
                }
                if (obstacles.type =="IceBox") {
                    obs = AppConstants.bitmapBank.iceBox;
                }
                if (obstacles.type =="Snowman") {
                    obs = AppConstants.bitmapBank.snowMan;
                }
                if (obstacles.type =="Stone") {
                    obs = AppConstants.bitmapBank.stone;
                }
                obs?.let { canvas.drawBitmap(it, obstacles.cX.toFloat(), obstacles.cY.toFloat(), null) };
                if (obstacles.cX <= -AppConstants.bitmapBank.getBoxWidth()) {
                    obstacles.reset();
                    points++;
                    obsSpawned = false;
                }
            }
        }
    }

    fun tapToStart(canvas: Canvas) {
        if (GameEngine.gameState == 0) {
            canvas.drawBitmap(
                AppConstants.bitmapBank.tapToStart, (AppConstants.SCREEN_WIDTH / 2 - AppConstants.bitmapBank.getTapToStartWidth() / 2).toFloat(),
                (AppConstants.SCREEN_HEIGHT / 2 - AppConstants.bitmapBank.getTapToStartHeight() / 2).toFloat(), null
            );
        }
    }
}