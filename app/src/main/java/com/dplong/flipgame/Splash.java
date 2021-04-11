package com.dplong.flipgame;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent mainIntent = new Intent(Splash.this, FlipGameActivity.class);
        Splash.this.startActivity(mainIntent);
        Splash.this.finish();
    }
}