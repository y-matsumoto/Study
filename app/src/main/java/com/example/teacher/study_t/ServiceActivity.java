package com.example.teacher.study_t;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ServiceActivity extends BaseActivity {

    private EditText mEditStopCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        // インスタンス取得
        mEditStopCount = (EditText)findViewById(R.id.edit_stop_count);

        // startボタンクリック処理
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // service起動

                Intent intent = new Intent(ServiceActivity.this,
                        StartService.class);
                intent.putExtra(StartService.EXTRA_STOP_TIME,
                        mEditStopCount.getText().toString());
                startService(intent);

            }
        });
    }
}
