package com.nsergey.android.test.myfirstapplication;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = MediaPlayer.create(this, R.raw.happybirthday);
        player.start();
        player.setLooping(true);
    }

    public void onBackPressed() {
        player.stop();
        super.onBackPressed();
    }

    public void onPause () {
        player.stop();
        super.onPause();
    }
}
