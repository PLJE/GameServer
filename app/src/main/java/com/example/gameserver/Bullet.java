package com.example.gameserver;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.gameserver.GameView.screenRatioX;
import static com.example.gameserver.GameView.screenRatioY;


public class  Bullet {
    int x, y, width, height;
    Bitmap bullet;

    Bullet (Resources res) {

        bullet = BitmapFactory.decodeResource(res, R.drawable.bullet);

        width = bullet.getWidth();
        height = bullet.getHeight();

        width /= 10;
        height /= 10;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        bullet = Bitmap.createScaledBitmap(bullet, width, height, false);

    }

    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }
}