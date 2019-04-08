package msgcopy.com.sqldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SQLActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etContent;
    private Button insert;
    private Button delete;
    private Button update;
    private Button query;
    private ListView listView;
    private UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new UsersAdapter();
        listView.setAdapter(adapter);

        etContent = (EditText) findViewById(R.id.content);
        insert = (Button) findViewById(R.id.insert);
        delete = (Button) findViewById(R.id.delete);
        update = (Button) findViewById(R.id.update);
        query = (Button) findViewById(R.id.query);
        insert.setOnClickListener(this);
        delete.setOnClickListener(this);
        update.setOnClickListener(this);
        query.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        UserInfo userInfo;
        String content = etContent.getText().toString().trim();
        switch (v.getId()) {
            case R.id.insert:
                if (!TextUtils.isEmpty(content)) {
                    userInfo = new UserInfo.Builder()
                            .name(content)
                            .age((int) (Math.random() * 100))
                            .sex(0)
                            .info("名莲VIP")
                            .build();
                    DBManager.getInstance().insertData(userInfo);
                } else {
                    userInfo = new UserInfo.Builder()
                            .name((int) (Math.random() * 100) + "")
                            .age((int) (Math.random() * 100))
                            .sex(0)
                            .info("名莲VIP")
                            .build();
                    DBManager.getInstance().insertData(userInfo);
                }
                break;
            case R.id.delete:
                if (!TextUtils.isEmpty(content)) {
                    DBManager.getInstance().deleteData(content);
                } else {
                    DBManager.getInstance().deleteAllData();
                }
                break;
            case R.id.update:
                if (!TextUtils.isEmpty(content)) {
                    userInfo = new UserInfo.Builder()
                            .sex(1)
                            .info("名莲VIP")
                            .build();
                    DBManager.getInstance().updateData(content, userInfo);
                }
                break;
            case R.id.query:
                if (!TextUtils.isEmpty(content)) {
                    adapter.update(DBManager.getInstance().queryData(content));
                } else {
                    adapter.update(DBManager.getInstance().queryAllData());
                }
                break;
        }
    }


    private class UsersAdapter extends BaseAdapter {

        private List<UserInfo> datas = new ArrayList<>();

        private void update(List<UserInfo> datas) {
            this.datas = datas;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (null == convertView) {
                convertView = LayoutInflater.from(getApplication()).inflate(R.layout.view_list_item, null);
                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.sex = (TextView) convertView.findViewById(R.id.sex);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.name.setText(datas.get(position).getName());
            if (datas.get(position).getSex() == 0) {
                holder.sex.setText("男");
            } else {
                holder.sex.setText("女");
            }
            return convertView;
        }

        private class ViewHolder {
            TextView name;
            TextView sex;
        }
    }

}
