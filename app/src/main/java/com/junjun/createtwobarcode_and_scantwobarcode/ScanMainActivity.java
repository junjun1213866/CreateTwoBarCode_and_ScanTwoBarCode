package com.junjun.createtwobarcode_and_scantwobarcode;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import java.util.List;


public class ScanMainActivity extends AppCompatActivity {

    private EditText output_edittext;
    private int REQUEST_CODE_SCAN = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanmainactivity_main);
        this.InitView();
    }

    private void InitView(){
        output_edittext=findViewById(R.id.result);
    }

    public void onClickScanButton(View view) {
        AndPermission.with(ScanMainActivity.this)
                .permission(Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE)
                .onGranted(new Action() {
                    public void onAction(List<String> permissions) {
                        Intent intent = new Intent(ScanMainActivity.this, CaptureActivity.class);
                        /*ZxingConfig是配置类
                         *可以设置是否显示底部布局，闪光灯，相册，
                         * 是否播放提示音  震动
                         * 设置扫描框颜色等
                         * 也可以不传这个参数
                         * */
                        ZxingConfig config = new ZxingConfig();
                        config.setPlayBeep(false);//是否播放扫描声音 默认为true
                        config.setShake(false);//是否震动  默认为true
                        config.setDecodeBarCode(false);//是否扫描条形码 默认为true
                        config.setReactColor(R.color.colorAccent);//设置扫描框四个角的颜色 默认为白色
                        config.setFrameLineColor(R.color.colorAccent);//设置扫描框边框颜色 默认无色
                        config.setScanLineColor(R.color.colorAccent);//设置扫描线的颜色 默认白色
                        config.setFullScreenScan(false);//是否全屏扫描  默认为true  设为false则只会在扫描框中扫描
                        intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
                        startActivityForResult(intent, REQUEST_CODE_SCAN);
                    }
                })
                .onDenied(new Action() {
                    public void onAction(List<String> permissions) {
                        Uri packageURI = Uri.parse("package:" + getPackageName());
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(intent);

                        Toast.makeText(ScanMainActivity.this, "没有权限无法扫描呦", Toast.LENGTH_LONG).show();
                    }
                }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                output_edittext.setVisibility(View.VISIBLE);
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                output_edittext.setText(content);
            }
        }
    }

    public void onClickExitButton(View view){
        Intent intent = new Intent();
        intent.setClass(ScanMainActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
