package com.example.teacher.study_t;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;


public class ContentProviderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        Cursor cursor = managedQuery(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null);

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "連絡先がありません。",Toast.LENGTH_LONG).show();
        }else {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));
                Toast.makeText(this, name, Toast.LENGTH_LONG).show();
            }
        }
    }

}
