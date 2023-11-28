package com.mobicode.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private ProgressBar pgbProgreso;
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            pgbProgreso.incrementProgressBy(msg.arg1);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this,R.raw.jet);
        mediaPlayer.seekTo(0);
        mediaPlayer.start();

        Thread timer = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    for (int i = 0; i < 10 ; i++) {
                        sleep(500);
                        Message message = Message.obtain();
                        message.arg1 = 10;
                        handler.sendMessage(message);
                    }

                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent i = new Intent(); //Para abrir ventana prinicipal
                    i.setClassName("com.mobicode.splashscreen","com.mobicode.splashscreen.MainActivity");
                    startActivity(i);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.release();
        finish();
    }
}