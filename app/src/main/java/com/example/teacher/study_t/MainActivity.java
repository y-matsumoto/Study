package com.example.teacher.study_t;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Androidサンプル課題トップページ
 */
public class MainActivity extends BaseActivity {

        // フィールドで変数設定
        private ListView mListStudy;
        private SwipeRefreshLayout mSwipeListStudy;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // レイアウトインスタンス生成
            mListStudy = (ListView) findViewById(R.id.listStudy);
            mSwipeListStudy = (SwipeRefreshLayout) findViewById(R.id.swipeListStudy);

            // リストデータ生成
            final List<StudyItem> studyLst = new ArrayList<>();

            // リストデータ組立
            studyLst.add(new StudyItem("ライフサイクル",
                    LifecycleActivity.class));
            studyLst.add(new StudyItem("Service",
                    null));
            studyLst.add(new StudyItem("ContentProvider",
                    null));
            studyLst.add(new StudyItem("Sqlite3",
                    null));

            // リストデータをAdapterへ設定
            StudySampleAdapter adapter = new StudySampleAdapter(this);
            adapter.addLst(studyLst);

            // ListviewのAdapterへ設定
            mListStudy.setAdapter(adapter);

            // Listviewのクリックイベント
            mListStudy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // リストからクリックされたitemを取得
                    StudyItem item = studyLst.get(position);

                    // 画面遷移
                    Intent intent =
                            new Intent(MainActivity.this,
                                    item.getActivityClass());
                    startActivity(intent);
                }
            });

    }

    /**
     * Listview表示用のitemリストクラス
     */
    class StudyItem{
        private String _title;
        private Class<?> _activityClass;

        public String getTitle(){
            return _title;
        }

        public Class<?> getActivityClass(){
            return _activityClass;
        }

        public StudyItem(String title,Class<?> activityClass){
            _title = title;
            _activityClass = activityClass;
        }
    }

    /**
     * Study用のAdapterクラス　※レイアウトを操作したい場合に用意する
     */
    public class StudySampleAdapter extends BaseAdapter {

        private LayoutInflater _layoutInflater;
        private Context _context;
        private List<StudyItem> _list;

        /**
         * コンストラクタ
         * @param context
         */
        public StudySampleAdapter(Context context){
            _context = context;
            _list = new ArrayList();
            _layoutInflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        /**
         * リストデータの追加（単）
         * @param data
         */
        public void add(StudyItem data){
            _list.add(data);
            notifyDataSetChanged();
        }

        /**
         * リストデータの追加（リスト一式）
         * @param dataLst
         */
        public void addLst(List<StudyItem> dataLst){
            for(StudyItem data : dataLst)
                add(data);
        }

        /**
         * リストの個数を返す
         * @return
         */
        @Override
        public int getCount() {
            return _list.size();
        }

        /**
         * 指定位置のリストを返す
         * @param position
         * @return
         */
        @Override
        public Object getItem(int position) {
            return _list.get(position);
        }

        /**
         * リストのID値を返す
         * @param position
         * @return
         */
        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * 画面の表示情報を返す
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if(convertView == null) {
                // インスタンス取得
                convertView = _layoutInflater.inflate(R.layout.study_list_item, null);
                holder = new Holder();
                holder.txtTitle = (TextView) convertView.findViewById(R.id.txt_title);
                convertView.setTag(holder);
            }else{
                holder = (Holder) convertView.getTag();
            }

            // データの取得
            StudyItem item = (StudyItem)getItem(position);

            //　データ設定
            holder.txtTitle.setText(item.getTitle());

            return convertView;
        }

        private class Holder{
            TextView txtTitle;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
