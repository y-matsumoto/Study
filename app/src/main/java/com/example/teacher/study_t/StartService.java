package com.example.teacher.study_t;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Serviceクラス　※タイマーの時刻表示サンプル
 */
public class StartService extends Service {

    //Timer オブジェクト
    private Timer mTimer = null;
    //経過時間
    private int mCountTime;
    //終了時間
    private int mStopTime;

    // stoptime ※呼び出し元の値取得用
    public static final String EXTRA_STOP_TIME = "EXTRA_STOP_TIME";

    // ハンドラー生成
    private Handler mHandler = new Handler() {
        // メッセージ表示
        public void handleMessage(Message msg) {
            Toast.makeText(StartService.this,
                    (String) msg.obj,
                    Toast.LENGTH_SHORT).show();
        }
    };

    // TimerTaskオブジェクト生成
    private TimerTask mTask = new TimerTask() {

        @Override
        public void run() {
            // 10秒毎にカウント
            mCountTime += 10;

            if (mCountTime / 60 == mStopTime) {
                // service終了
                stopSelf();
            } else {
                // handlerへメッセージ転送
                mHandler.sendMessage(
                        Message.obtain(
                                mHandler,
                                0,
                                mCountTime / 60 + "分" + mCountTime % 60 +
                                        "秒経過しました"));
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //トースト表示
        Toast.makeText(this,
                "サービスを起動します",
                Toast.LENGTH_SHORT).show();

        //タイマーと経過時間初期化
        mTimer = new Timer();
        mCountTime = 0;
    }

    @Override
    public void onStart(Intent intent, int startld) {
        super.onStart(intent, startld);

        //トースト表示
        Toast.makeText(this,
                "サービスを開始します",
                Toast.LENGTH_SHORT).show();

        // タイマー設定( 10秒ごとにrunメソッド呼び出し）
        mTimer.schedule(mTask, 10000, 10000);

        // バンドル生成　※呼び出し元の値を取得するため
        Bundle bundle = intent.getExtras();
        mStopTime = Integer.parseInt(bundle.getString(EXTRA_STOP_TIME));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //トースト表示
        Toast.makeText(this,
                "サービスを終了しました",
                Toast.LENGTH_SHORT).show();

        // timer設定解除
        mTimer.cancel();
        mTimer.purge();
    }
}
