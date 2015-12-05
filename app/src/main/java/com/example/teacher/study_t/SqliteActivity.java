package com.example.teacher.study_t;

import android.os.Bundle;
import android.widget.Toast;

import com.example.teacher.study_t.sqlite.UserHelper;
import com.example.teacher.study_t.sqlite.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class SqliteActivity extends BaseActivity {

    UserHelper userHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        userHelper = new UserHelper(this);

        // データ登録

        // トランザクションの開始
        userHelper.startTransaction();

        List<Boolean> chkLst = new ArrayList<>();

        // データ追加
        chkLst.add(userHelper.insert("matsumoto", "tokyo", "080-1234-5678"));
        chkLst.add(userHelper.insert("suzuki", "saitama", "080-1234-5678"));
        chkLst.add(userHelper.insert("sato", "kanagawa", "080-1234-5678"));

        // 検証
        boolean isCommit = true;
        for(Boolean result : chkLst)
            if(!result){
                isCommit = false;
                break;
            }

        // トランザクションのコミット
        if(isCommit) userHelper.commitTransaction();

        userHelper.endTransaction();

        // データ表示
        try{
           List<UserEntity> userEntityLst =
                   userHelper.select("id = 1");
           for(UserEntity entity : userEntityLst){

               Toast.makeText(this, "id:" + entity.getId() +
                               " name:" + entity.getName() +
                               " tel:" + entity.getTel() +
                               " address:" + entity.getAddress(),
                       Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
        }
        userHelper.close();

    }

}
