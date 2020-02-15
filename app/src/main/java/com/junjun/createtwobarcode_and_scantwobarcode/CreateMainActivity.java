package com.junjun.createtwobarcode_and_scantwobarcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.Bitmap;

import androidx.appcompat.app.AppCompatActivity;

import com.yzq.zxinglibrary.encode.CodeCreator;

public class CreateMainActivity extends AppCompatActivity {

    private EditText input_EditText;
    Bitmap bitmap = null;
    private ImageView output_ImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createmainactivity_main);
        this.initView();
    }

    private void initView(){
        input_EditText=findViewById(R.id.input_edittext);
        output_ImageView=findViewById(R.id.output_image);
    }

    public void onClickSureButton(View view){
        String str_name=input_EditText.getText().toString();

        if(str_name.length()==0) {
            Toast.makeText(CreateMainActivity.this,"请正确输入信息！",Toast.LENGTH_SHORT).show();
        }
        else {
            CodeCreator TwoBarCode=new CodeCreator();
            bitmap = TwoBarCode.createQRCode(str_name, 400, 400, null);
            Toast.makeText(CreateMainActivity.this,"您已经点击了确定!",Toast.LENGTH_SHORT).show();
            if (bitmap != null) {
                output_ImageView.setVisibility(View.VISIBLE);
                output_ImageView.setImageBitmap(bitmap);
            }
            else{
                Toast.makeText(CreateMainActivity.this,"生成出错！",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onClickExitButton(View view){
        Intent intent = new Intent();
        intent.setClass(CreateMainActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
