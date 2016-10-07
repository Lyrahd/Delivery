

package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by benjarmanalili on 16/07/2016.
 */

public class SplashScreen extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    Intent intent = new Intent(SplashScreen.this,LoginScreen.class );
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }
}