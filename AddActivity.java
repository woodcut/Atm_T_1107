package com.tom.atm;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private static final String TAG = "AddActivity";
    private EditText edDate;
    private EditText edInfo;
    private EditText edAmount;
    private MyDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        findViews();
        dbHelper = MyDBHelper.getInstance(this);
    }

    private void findViews() {
        edDate = (EditText) findViewById(R.id.ed_date);
        edInfo = (EditText) findViewById(R.id.ed_info);
        edAmount = (EditText) findViewById(R.id.ed_amount);
    }

    public void add(View v){
        String cdate = edDate.getText().toString();
        String info = edInfo.getText().toString();
        int amount = Integer.parseInt(edAmount.getText().toString());
//        String insertSQL = "insert into exp(cdate,info,amount) values(" +
//                "'"+cdate+"', '"+info+"', "+amount+")";
//        dbHelper.getWritableDatabase().execSQL(insertSQL);
        ContentValues values = new ContentValues();
        values.put("cdate", cdate);
        values.put("info", info);
        values.put("amount", amount);
        long id = dbHelper.getWritableDatabase()
                .insert("exp", null, values);
        Log.d(TAG, "id:"+id);
    }
}
