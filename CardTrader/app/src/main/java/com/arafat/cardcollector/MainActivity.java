package com.arafat.cardcollector;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickLogin(View view) {
        EditText txtDescription =
                (EditText) findViewById(R.id.usernameField);
        String userInput = txtDescription.getText().toString();
        txtDescription =
                (EditText) findViewById(R.id.passwordField);
        String passwordInput = txtDescription.getText().toString();

        Intent intent = new Intent(MainActivity.this, MainBoardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }



    public void onClickSignUp(View view) {
        Intent intent = new Intent(MainActivity.this, signup.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}