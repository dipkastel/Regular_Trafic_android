package com.notrika.regular_trafic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.notrika.regular_trafic.Entitie.OperationResult;
import com.notrika.regular_trafic.Entitie.Req_register;
import com.notrika.regular_trafic.Entitie.Res_register;
import com.notrika.regular_trafic.Util.func;
import com.notrika.regular_trafic.Util.sharedPreference;

import retrofit2.Call;
import retrofit2.Callback;


public class SplashActivity extends AppCompatActivity {
    VideoView videoView;
    final long SPLASH_TIME = 1*1000;
    Thread thread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
        playVideo();
        sleep();

    }
    private void registeruser() {
        Req_register req_register = new Req_register();
        req_register.setAppVersionName(BuildConfig.VERSION_NAME);
        req_register.setAppVersionNumber(Double.valueOf(BuildConfig.VERSION_CODE));
        req_register.setDeviceId(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        req_register.setDeviceModel(func.getDeviceName());
        req_register.setDeviceType(1);
        req_register.setNotificationToken("_");
        req_register.setOsVersion(android.os.Build.VERSION.SDK_INT);
        retrofit2.Call<OperationResult<Res_register>> call = ((application)getApplication()).getClient().deviceRegister(req_register);
        call.enqueue(new Callback<OperationResult<Res_register>>() {
            @Override
            public void onResponse(Call<OperationResult<Res_register>> call, retrofit2.Response<OperationResult<Res_register>> response) {
                new sharedPreference(SplashActivity.this).setToken(response.body().getData().getToken());
                ((application)getApplication()).RefreshRetrofit();
                if(response.isSuccessful()){
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                }
            }

            @Override
            public void onFailure(Call<OperationResult<Res_register>> call, Throwable t) {
                registeruser();
            }
        });
    }
    private void playVideo() {
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash_video);
        videoView.setVideoURI(video);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
            }

        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0, 0);
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });
        videoView.start();
    }

    private void sleep() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(SPLASH_TIME);
                    registeruser();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    private void init() {
        videoView = (VideoView) findViewById(R.id.videoView);
    }
}
