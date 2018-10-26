package matt85kim53.is.athis.backgroundservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyBackgroundService extends Service {

    private static final String TAG = MyBackgroundService.class.getSimpleName();

    //백그라운드 서비스가 인스턴트화 되면 이 메소드가 실행된다
    //** 서비스가 만들어질 때 한번만 실행 된다. 그러니깐 서비스가 최조 만들어질 때 만 사용되고 그 후에는 필요없다.
    //onCreate 메소드는 꼭 필요한 메소드가 아니기 때문에 없어도 된다.
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    //처음에 override method 할 시 Service에서는 onBind method 가 나오지만 추가로 override 할 메소드 가 있다.
    //이 메소드 중에서 가장 중요한 메소드
    //백그라운드 서비스에서 실행하려면 onBind return 값이 항상 null 이여야함.
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return null;
    }


    //백그라운드 서비스를 실행 할때 이 메소드가 먼저 실행된다.
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    //Optional
    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
