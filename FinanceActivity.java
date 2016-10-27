package com.tom.atm;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class FinanceActivity extends AppCompatActivity {

    private MyDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(FinanceActivity.this, AddActivity.class));
            }
        });
        ListView list = (ListView) findViewById(R.id.list);
        dbHelper = MyDBHelper.getInstance(this);
        Cursor c = dbHelper.getReadableDatabase()
                .query("exp", null, null, null, null, null, null);
        String[] from = {"_id", "info", "amount", "cdate"};
//        int[] to = {android.R.id.text1, android.R.id.text2};
        int[] to = {R.id.item_id, R.id.item_info, R.id.item_amount, R.id.item_cdate};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
//                android.R.layout.simple_list_item_2,
                R.layout.finance_row,
                c, from, to, 0);
        list.setAdapter(adapter);
//                .rawQuery("select * from users where userid=?", new String[]{"tom"});
    }

}
