package com.junjun.createtwobarcode_and_scantwobarcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        public void onClickCreateButton(View view){
            Intent create_intent = new Intent();
            create_intent.setClass(MainActivity.this, CreateMainActivity.class);
            startActivity(create_intent);
        }
        public void onClickScanButton(View view){
            Intent create_intent = new Intent();
            create_intent.setClass(MainActivity.this, ScanMainActivity.class);
            startActivity(create_intent);
        }

    }
