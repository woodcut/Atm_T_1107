package com.tom.atm;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class PictureActivity extends AppCompatActivity {

    private static final int REQUES_STORAGE = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        int permission = ActivityCompat.checkSelfPermission(this,
                READ_EXTERNAL_STORAGE);
        if (permission== PackageManager.PERMISSION_GRANTED){
            pictureReader();
        }else {
            ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE},
                    REQUES_STORAGE);
        }
    }

    private void pictureReader() {
        ContentResolver cr = getContentResolver();
        cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null,null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUES_STORAGE &&
                grantResults[0]==PackageManager.PERMISSION_GRANTED){
            pictureReader();
        }

    }
}
