package com.example.teacher.study_t;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * 非同期処理
 */
public class AsyncActivity extends ActionBarActivity {

    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);

        /**
         *  AndroidではThreadクラスの非同期は基本使わず
         *  AndroidのSDK付属のAsyncTaskを使う
         */

        /**
         * AsyncTaskサンプル　※こちらをAndroidでは採用
         */

        // HelloAsyncTaskのインスタンス生成
        HelloAsyncTask task = new HelloAsyncTask();
        // Async処理実行（非同期処理→メイン処理）
        task.execute("Hello World !!!!");

        /**
         * Threadサンプル　※ Androidでは基本使わない
         */
/*
        // Handlerのインスタンス生成
        mHandler = new Handler();
        // スレッドインスタンス生成しHelloスレッドを設定
        Thread thread = new Thread(new HelloThread());
        // スレッド開始
        thread.start();
*/
    }

    /**
     * AsyncTaskクラス　※Toastテスト用
     */
    class HelloAsyncTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            // ワーカー処理
            // 渡されたパラメータを取得
            String param = params[0];
            // なにかしらのバックで実行する時間のかかる処理
            int sum = 0;
            for(int i = 0; i < 100 ; i++){
                sum += i;
            }
            param += sum;
            return param;
        }
        @Override
        protected void onPostExecute(String result){
            // メインスレッド
            Toast.makeText(AsyncActivity.this,
                    result,Toast.LENGTH_LONG).show();
        }
    }

    /**
     * スレッドクラス　※Toastテスト用
     */
    class HelloThread implements Runnable{
        @Override
        public void run() {
            // ワーカースレッド内

            // なにかしらのバックで実行する時間のかかる処理
            int sum = 0;
            for(int i = 0; i < 100000 ; i++){
                sum += i;
            }

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    // メインスレッド内
                    Toast.makeText(AsyncActivity.this,
                            "async",Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_async, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
