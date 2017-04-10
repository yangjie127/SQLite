package com.yang.user.mysqlitedemo1;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yang.user.mysqlitedemo1.dbhelper.MyDBHelper;
import com.yang.user.mysqlitedemo1.dbhelper.MyDBUtils;
import com.yang.user.mysqlitedemo1.dbhelper.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyDBHelper myDBHelper;
    private SQLiteDatabase db;
    private List<User> mList = new ArrayList<>();
    private Button button1, button2, button3;
    private TextView tv;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
//        myDBHelper = new MyDBHelper(this);
//        db =myDBHelper.getReadableDatabase();
//        db.close();
        MyDBUtils.getInstance().initDBUtils(this);
        button1 = (Button) findViewById(R.id.insert);
        button2 = (Button) findViewById(R.id.delete);
        button3 = (Button) findViewById(R.id.update);
        tv = (TextView) findViewById(R.id.query);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        user.setId(123);
        user.setUserName("yangjie");
        user.setPassword("yangjie");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insert:

                MyDBUtils.getInstance().insert(user);

                mList = MyDBUtils.getInstance().query();

                for (int i = 0; i < mList.size(); i++) {
                    tv.setText("账号： " + mList.get(i).getUserName() + " 密码： " + mList.get(i).getPassword());
                }
                if (mList.size() == 0) {
                    tv.setText("账号： " + "" + " 密码： " + "");
                }
                mList.clear();
                Log.e("yangjie", "  mList = " + mList.size());
                break;
            case R.id.delete:
                MyDBUtils.getInstance().delete(123);

                mList = MyDBUtils.getInstance().query();
                for (int i = 0; i < mList.size(); i++) {
                    tv.setText("账号： " + mList.get(i).getUserName() + " 密码： " + mList.get(i).getPassword());
                }
                if (mList.size() == 0) {
                    tv.setText("账号： " + "" + " 密码： " + "");
                }
                mList.clear();
                Log.e("yangjie", "  mList = " + mList.size());
                break;

            case R.id.update :
                user.setPassword("111111111111");
                MyDBUtils.getInstance().update(123, user);

                mList = MyDBUtils.getInstance().query();
                for (int i = 0; i < mList.size(); i++) {
                    tv.setText("账号： " + mList.get(i).getUserName() + " 密码： " + mList.get(i).getPassword());
                }
                if (mList.size() == 0) {
                    tv.setText("账号： " + "" + " 密码： " + "");
                }
                mList.clear();
                Log.e("yangjie", "  mList = " + mList.size());
                break;
        }
    }
}
