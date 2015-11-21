package com.example.teacher.study_t;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.teacher.study_t.sqlite.helper.CreateProductHelper;

public class SqliteActivity extends ActionBarActivity {

    private CreateProductHelper mCreateProductHelper = null;
    private SQLiteDatabase mSQLiteDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        mCreateProductHelper = new CreateProductHelper(this);

        // インスタンス生成 ※書き込み用(insert / update / delete)
        mSQLiteDatabase = mCreateProductHelper.getWritableDatabase();

        // データ登録
        try{
            // トランザクションの開始
            mSQLiteDatabase.beginTransaction();

            ContentValues value = new ContentValues();
            value.put("name", "matsumoto");
            value.put("add", "tokyo");
            value.put("tel", "080-1234-5678");
            mSQLiteDatabase.insert("Users",null,value);

            // トランザクションのコミット
            mSQLiteDatabase.setTransactionSuccessful();

        }catch(Exception e){

        }finally {
            mSQLiteDatabase.endTransaction();
        }

        // データ更新


        // データ削除


        // データ表示
        mSQLiteDatabase = mCreateProductHelper.getReadableDatabase();

        try{
            String columns[] = {"name","tel"};
            Cursor cursor = mSQLiteDatabase.query("Users",columns,null,null,null,null,null);

            while(cursor.moveToNext()){
                //String id = cursor.getString(0);
                //String name = cursor.getString(1);
                //String add = cursor.getString(2);
                //String tel = cursor.getString(3);

               // Toast.makeText(this,"id:" + id + " name:" + name,
                //        Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            String test = "";
        }
        mSQLiteDatabase.close();

    }

}
