package matt85kim53.is.athis.backgroundservicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // Case 1. 서비스를 처음 시작 시 Service의
    // 1. onCreate
    // 2. onStartCommand 순서로 탐
    //--------------------------------
    // Case 2. 서비스 실행 중  다시 서비스를 시작하면
    // 1. onStartCommand 만 호출됨.
    public void startBackgroundService(View view) {

        Intent intent = new Intent(this, MyBackgroundService.class);
        startService(intent);
    }

    //Case 1. 서비스 가 실행 중 stopService가 호출되면
    // 1. onDestory
    public void stopBackgroundService(View view) {
        // To start Service Create an Intent
        Intent intent = new Intent(this, MyBackgroundService.class);
        stopService(intent);
    }


}
