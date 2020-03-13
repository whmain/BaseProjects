package com.zhengdao.video;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class Camera2Activity extends AppCompatActivity {
    private SurfaceView surfaceView;
    private Button btnStart,btnStop;
    private CameraUtils  cameraUtils;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2);
        surfaceView = findViewById(R.id.surfaceview);
        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);
        cameraUtils = new CameraUtils();
        cameraUtils.create(surfaceView, this);
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "VideoFolder2" ;

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = java.text.MessageFormat.format(
                        "{0,date,yyyyMMdd_HHmmss}",
                        new Object[] { new java.sql.Date(System
                                .currentTimeMillis()) });
                cameraUtils.startRecord(path, name);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraUtils.stopRecord();
            }
        });
    }

    String TAG = "Camera2Activity";
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        cameraUtils.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        cameraUtils.destroy();
    }
}
