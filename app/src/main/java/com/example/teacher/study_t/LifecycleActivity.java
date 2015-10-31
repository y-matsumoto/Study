package com.example.teacher.study_t;

import android.os.Bundle;
import android.widget.Toast;

/**
 * AndroidのActivityライフサイクルサンプル
 * 各onメソッドの処理の実行順番確認用
 */
public class LifecycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "onCreate実行",
                Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStart (){
        super.onStart();
        Toast.makeText(this, "onStart実行",
                Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onRestart (){
        super.onRestart();
        Toast.makeText(this, "onRestart実行",
                Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onResume (){
        super.onResume();
        Toast.makeText(this, "onResume実行",
                Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onPause (){
        super.onPause();
        Toast.makeText(this, "onPause実行",
                Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onStop(){
        super.onStop();
        Toast.makeText(this, "onStop実行",
                Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "ondestroy実行",
                Toast.LENGTH_LONG).show();
    }
}
