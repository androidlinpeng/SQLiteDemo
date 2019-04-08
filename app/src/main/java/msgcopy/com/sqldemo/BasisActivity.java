package msgcopy.com.sqldemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static msgcopy.com.sqldemo.DBHelper.getInstance;

public class BasisActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "BasisActivity";
    private EditText etinput;
    private ListView listView;
    private List<String> mList = new ArrayList<String>();
    private DataAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basis);

        etinput = (EditText) findViewById(R.id.et_input);
        listView = (ListView) findViewById(R.id.list);


        mAdapter = new DataAdapter(mList);
        listView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_insert:
                insertData();
                Log.i(TAG,"insertData:");
                break;
            case R.id.btn_delete:
                deleteData();
                Log.i(TAG,"deleteData:");
                break;
            case R.id.btn_update:
                updateData();
                Log.i(TAG,"updateData:");
                break;
            case R.id.btn_query:
                queryData();
                Log.i(TAG,"queryData:");
                break;
        }
    }

    private void queryData() {
        mList.clear();
        String input = etinput.getText().toString();
        DBHelper dbHelper = getInstance(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from music_center", null);
        //Cursor cursor = db.rawQuery("select * from music_center ORDER BY _id DESC LIMIT 0,3", null);
        //Cursor cursor = db.rawQuery("select * from music_center where name=?",new String[]{input});
        //Cursor cursor = db.rawQuery("select * from music_center where name=? or songid=?",new String[]{input,input});
//        Cursor cursor = db.rawQuery("select * from music_center where name like ? or songid like ? ",new String[]{"%"+input+"%","%"+input+"%"});

        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            mList.add(name);
        }
        cursor.close();
        db.close();
        mAdapter.setData(mList);
    }

    private void updateData() {
        String input = etinput.getText().toString();
        DBHelper dbHelper = getInstance(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name","情郎");
        db.update("music_center",values,"name=?",new String[]{input});
        db.close();
    }

    private void deleteData() {
        String input = etinput.getText().toString();
        DBHelper dbHelper = getInstance(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete("music_center","name=?",new String[]{input});
        db.close();
    }
    private void insertData() {
        String input = etinput.getText().toString();
        DBHelper dbHelper = getInstance(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name","我们的歌"+input);
        values.put("songid","1314");
        values.put("artist","王力宏");
        db.insert("music_center",null,values);
        db.close();
    }

    public class DataAdapter extends BaseAdapter{

        private List<String> mlist;

        public DataAdapter(List<String> mlist) {
            if (mlist == null){
                mlist = new ArrayList<String>();
                this.mlist = mlist;
            }else {
                this.mlist = mlist;
            }
        }

        public void setData(List<String> mlist ){
            this.mlist = mlist;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mlist.size();
        }

        @Override
        public Object getItem(int position) {
            return mlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View converView, ViewGroup viewGroup) {
            if (null == converView){
                converView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_music_item,viewGroup,false);
            }
            TextView name = (TextView) converView.findViewById(R.id.name);
            name.setText(mlist.get(position));
            return converView;
        }
    }

}








