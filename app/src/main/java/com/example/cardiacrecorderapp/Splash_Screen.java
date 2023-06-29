package com.example.cardiacrecorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash_Screen extends AppCompatActivity {

    VideoView videoView;

    private ProgressBar progressBar;
    private  int progress;
    FirebaseUser firebaseUser ;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        videoView = findViewById(R.id.VideoView);
        Uri video =Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.splash_screen_vid);
        videoView.setVideoURI(video);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });
        videoView.start();

        progressBar=(ProgressBar) findViewById(R.id.progressBarID);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
            }
        });
        thread.start();
    }
    public  void doWork(){
        for(progress=20;progress<=100;progress=progress+20){
            try {
                Thread.sleep(500);
                progressBar.setProgress(progress);
            }
            catch (InterruptedException e){
                e.printStackTrace();

            }
        }

    }
    public void startApp(){

        if (firebaseAuth != null )
        {
            Intent intent = new Intent(Splash_Screen.this, Sign_In.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent intent = new Intent(Splash_Screen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}