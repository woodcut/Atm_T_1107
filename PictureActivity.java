package com.tom.atm;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.*;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class PictureActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {

    private static final int REQUES_STORAGE = 999;
    private GridView grid;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        grid = (GridView) findViewById(R.id.grid);
        grid.setOnItemClickListener(this);
        int permission = ActivityCompat.checkSelfPermission(this,
                READ_EXTERNAL_STORAGE);
        if (permission== PackageManager.PERMISSION_GRANTED){
//            pictureReader();
            thumbnailsReader();
        }else {
            ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE},
                    REQUES_STORAGE);
        }
    }

    private void thumbnailsReader() {
//        Cursor c = getContentResolver().query(Thumbnails.EXTERNAL_CONTENT_URI,
//                null, null, null, null);
        String[] from = {Thumbnails.DATA, Media.DISPLAY_NAME};
        int[] to = {R.id.thumb_image, R.id.thumb_txt};
        adapter = new SimpleCursorAdapter(this, R.layout.thumb_item, null, from, to, 0);
        grid.setAdapter(adapter);
        getSupportLoaderManager().initLoader(0, null, this);
    }


//    private void pictureReader() {
//        ContentResolver cr = getContentResolver();
//        Cursor c =
//                cr.query(Media.EXTERNAL_CONTENT_URI, null, null, null,null);
//        while (c.moveToNext()){
//            int id = c.getInt(c.getColumnIndex(Media._ID));
//            String name = c.getString(c.getColumnIndex(Media.DISPLAY_NAME));
//            String data = c.getString(c.getColumnIndex(Media.DATA));
//            Log.d("pictureReader", id+","+name+","+data);
//        }
//
//        Cursor c2 =
//                cr.query(Thumbnails.EXTERNAL_CONTENT_URI, null, null, null, null);
//        while (c2.moveToNext()){
//            int id = c2.getInt(c2.getColumnIndex(Thumbnails._ID));
//            String imgID = c2.getString(c2.getColumnIndex(Thumbnails.IMAGE_ID));
//            String data = c2.getString(c2.getColumnIndex(Thumbnails.DATA));
//            Log.d("Thumb", id+","+imgID+","+data);
//        }
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUES_STORAGE &&
                grantResults[0]==PackageManager.PERMISSION_GRANTED){
//            pictureReader();
            thumbnailsReader();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, Media.EXTERNAL_CONTENT_URI,
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("POSITION", position);
        startActivity(intent);

    }
}
