package matt85kim53.is.athis.backgroundservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class MyBackgroundService extends Service {

    private static final String TAG = MyBackgroundService.class.getSimpleName();

    //백그라운드 서비스가 인스턴트화 되면 이 메소드가 실행된다
    //** 서비스가 만들어질 때 한번만 실행 된다. 그러니깐 서비스가 최조 만들어질 때 만 사용되고 그 후에는 필요없다.
    //onCreate 메소드는 꼭 필요한 메소드가 아니기 때문에 없어도 된다.
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: , Thread name :" + Thread.currentThread().getName());
    }

    //처음에 override method 할 시 Service에서는 onBind method 가 나오지만 추가로 override 할 메소드 가 있다.
    //이 메소드 중에서 가장 중요한 메소드
    //백그라운드 서비스에서 실행하려면 onBind return 값이 항상 null 이여야함.
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: , Thread name: " + Thread.currentThread().getName());
        return null;
    }


    //백그라운드 서비스를 실행 할때 이 메소드가 먼저 실행된다.
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand:, Thread name :" + Thread.currentThread().getName());

        //Service 는 재시작 될 것이고 Intent 는 null 이 됨.

        //Do something here.... [음악, 파일 업로드 등..]
        //Dummy Long Operation 작업 예시
        /*try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        new MyAsyncTask().execute();  //Background Thread

        return START_STICKY;
    }

    //Optional
    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: , Thread name :" + Thread.currentThread().getName());
        super.onDestroy();
    }


    public class MyAsyncTask extends AsyncTask<Void,String,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG, "onPreExecute:  , Thread Name : " + Thread.currentThread().getName());
        }

        //perform  tasks in background or worker thread
        @Override
        protected Void doInBackground(Void... voids) {
            Log.i(TAG, "doInBackground:  , Thread Name : " + Thread.currentThread().getName());
            //Dummy long operation
            int ctr = 1;
            while(ctr <= 12){
                publishProgress("Time Elapsed" + ctr + "secs");  // AsyncTask 의 Second Parameter를 String 으로 한 이유는 values 에 있는 String 값을 onProgressUpdate 로 전달 하기 위해서이다.
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ctr++;
            }
            return null;
        }

        //onProgressUpdate uses [MAIN THREAD]
        //UI Element 는 항상 Main Thread를 통하여 접근 해야한다.
        @Override
        protected void onProgressUpdate(String... values) {
            Log.i(TAG, "onProgressUpdate:  , Counter Value = " + values  + "  Thread Name : " + Thread.currentThread().getName());
            super.onProgressUpdate(values);
            Toast.makeText(MyBackgroundService.this, values[0], Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            Log.i(TAG, "onPostExecute:  , Thread Name : " + Thread.currentThread().getName());
            super.onPostExecute(aVoid);
            //Stops the execution of the service
            //dont have to click the button
            stopSelf();
        }


    }

}
