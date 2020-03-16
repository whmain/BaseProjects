package com.zhengdao.video;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 视频录制
 */
@SuppressWarnings("deprecation")
public class CameraActivity extends AppCompatActivity implements OnClickListener,
        SensorEventListener, Callback {
    private static final String TAG = "CameraActivity";

    private SurfaceView surfaceView; // 用于绘制缓冲图像的
    private Button luXiang_bt; // 开始录制的按钮
    private Button tingZhi_bt; // 停止录制的按钮
    private TextView time_tv; // 显示时间的文本框
    private MediaRecorder mRecorder;
    private boolean recording; // 记录是否正在录像,fasle为未录像, true 为正在录像
    private File videoFolder; // 存放视频的文件夹
    private File videoFile; // 视频文件
    private Handler handler;
    private int time; // 时间
    private Camera myCamera; // 相机声明
    private SurfaceHolder holder; // 用来访问surfaceview的接口

    /**
     * 录制过程中,时间变化
     */
    private Runnable timeRun = new Runnable() {

        @Override
        public void run() {
            time++;
            time_tv.setText(time + "秒");
            handler.postDelayed(timeRun, 1000);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camera);
        getScreenSize();
        initView();
        initCreateFile();
    }



    /**
     * 文件的创建
     */
    private void initCreateFile()   {
        // 判断sd卡是否存在
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            // 设定存放视频的文件夹的路径
            String path = Environment.getExternalStorageDirectory()
                    .getAbsolutePath()
                    + File.separator
                    + "VideoFolder"
                    + File.separator;
            // 声明存放视频的文件夹的File对象
            videoFolder = new File(path);
            // 如果不存在此文件夹,则创建
            if (!videoFolder.exists()) {
                videoFolder.mkdirs();
            }
            // 设置surfaceView不管理的缓冲区
            surfaceView.getHolder().setType(
                    SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
            // 设置surfaceView分辨率
            //surfaceView.getHolder().setFixedSize(1000, 500);
            luXiang_bt.setOnClickListener(this);
        } else
            Toast.makeText(this, "未找到sdCard!", Toast.LENGTH_LONG).show();
    }

    /**
     * 初始化工作
     */
    private void initView() {
        // 获取控件
        surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
        luXiang_bt = (Button) findViewById(R.id.luXiang_bt);
        tingZhi_bt = (Button) findViewById(R.id.tingZhi_bt);
        time_tv = (TextView) findViewById(R.id.time);
        handler = new Handler();
        holder = surfaceView.getHolder();
        tingZhi_bt.setOnClickListener(this);
        // 添加回调
        holder.addCallback(this);
        findViewById(R.id.change_bt).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCamera();
            }
        });
    }

    /**
     * 将Camera和mediaRecoder释放
     */
    @Override
    protected void onDestroy() {
        handler.removeCallbacks(timeRun);
        if (mRecorder != null) {
            mRecorder.release();
        }
        if (myCamera != null) {
            myCamera.stopPreview();
            myCamera.release();
        }
        super.onDestroy();
    }

    /**
     * 通过系统的CamcorderProfile设置MediaRecorder的录制参数
     * 首先查看系统是否包含对应质量的封装参数，然后再设置，根据具体需要的视频质量进行判断和设置
     */
    private void setProfile() {
        CamcorderProfile profile = null;
        if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_1080P)) {
            profile = CamcorderProfile.get(CamcorderProfile.QUALITY_1080P);
        } else if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_720P)) {
            profile = CamcorderProfile.get(CamcorderProfile.QUALITY_720P);
        } else if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_480P)) {
            profile = CamcorderProfile.get(CamcorderProfile.QUALITY_480P);
        }
        if (profile != null) {
            mRecorder.setProfile(profile);
        }
    }

    /**
     * 控件点击事件的监听
     */
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.luXiang_bt){
            // 录像点击事件
            if (!recording) {
                try {
                    // 获取当前时间,作为视频文件的文件名
                    String nowTime = java.text.MessageFormat.format(
                            "{0,date,yyyyMMdd_HHmmss}",
                            new Object[] { new java.sql.Date(System
                                    .currentTimeMillis()) });
                    // 声明视频文件对象
                    videoFile = new File(videoFolder.getAbsoluteFile()
                            + File.separator + "video" + nowTime + ".mp4");
                    // 关闭预览并释放资源
                    myCamera.unlock();
                    mRecorder = new MediaRecorder();
                    mRecorder.setCamera(myCamera);
                    // 创建此视频文件
                    videoFile.createNewFile();

                    mRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface()); // 预览
                    mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA); // 视频源
                    mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC); // 录音源为麦克风
//
//                    mRecorder
//                            .setOutputFormat(MediaRecorder.OutputFormat.MPEG_4); // 输出格式为mp4
//                    mRecorder.setVideoSize(800, 480); // 视频尺寸
//                    mRecorder.setVideoEncodingBitRate(10*1920*1080); //设置视频编码帧率
//                    mRecorder.setVideoFrameRate(30); // 视频帧频率
//                    mRecorder
//                            .setVideoEncoder(MediaRecorder.VideoEncoder.H264); // 视频编码
//                    mRecorder
//                            .setAudioEncoder(MediaRecorder.AudioEncoder.AAC); // 音频编码
                    mRecorder.setMaxDuration(0); // 设置最大录制时间 无限制
                    setProfile();
                    mRecorder.setOutputFile(videoFile.getAbsolutePath()); // 设置录制文件源
                    int degrees = 90;
                    if (isFront()){
                        degrees += 180;
                    }
                    mRecorder.setOrientationHint(degrees); //处理拍摄时正常，播放时视屏颠倒问题
                    mRecorder.prepare(); // 准备录像
                    mRecorder.start(); // 开始录像
                    time_tv.setVisibility(View.VISIBLE); // 设置文本框可见
                    handler.post(timeRun); // 调用Runable
                    recording = true; // 改变录制状态为正在录制
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            } else
                Toast.makeText(CameraActivity.this, "视频正在录制中...",
                        Toast.LENGTH_LONG).show();
        }else if (viewId == R.id.tingZhi_bt){
            // 停止点击事件
            if (recording) {
                mRecorder.stop();
                mRecorder.release();
                handler.removeCallbacks(timeRun);
                time_tv.setVisibility(View.GONE);
                int videoTimeLength = time;
                time = 0;
                recording = false;
                Toast.makeText(
                        CameraActivity.this,
                        videoFile.getAbsolutePath() + " " + videoTimeLength
                                + "秒", Toast.LENGTH_LONG).show();
            }
            // 开启相机
            if (myCamera == null) {
                myCamera = Camera.open();
                try {
                    myCamera.setDisplayOrientation(90);
                    myCamera.setPreviewDisplay(holder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            myCamera.startPreview(); // 开启预览
            addAlbum();
        }
    }

    private void addAlbum(){
        if (videoFile == null){
            return;
        }
        Uri uri =Uri.fromFile(videoFile);
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,uri);
        sendBroadcast(mediaScanIntent);
    }

    /**
     * 传感器改变调用的方法
     */
    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    /*** 标识当前是前摄像头还是后摄像头  back:0  front:1*/
    private int backOrFtont = 0;

    private boolean isFront(){
        if (backOrFtont == 1){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 切换摄像头
     */
    public void changeCamera() {
        int cameraCount = 0;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();
        for (int i = 0; i < cameraCount; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT && backOrFtont == 0) {
                myCamera.stopPreview();
                myCamera.release();
                myCamera = null;
                myCamera = Camera.open(i);
                try {
                    myCamera.setPreviewDisplay(surfaceView.getHolder());
                    myCamera.setDisplayOrientation(90);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                backOrFtont = 1;
                myCamera.startPreview();
                break;
            } else if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK && backOrFtont == 1) {
                myCamera.stopPreview();
                myCamera.release();
                myCamera = null;
                myCamera = Camera.open(i);
                try {
                    myCamera.setPreviewDisplay(surfaceView.getHolder());
                    myCamera.setDisplayOrientation(90);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myCamera.startPreview();
                backOrFtont = 0;
                break;
            }
        }

    }


    /**
     * suraceView 创建执行的操作
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // 开启相机
        if (myCamera == null) {
            myCamera = Camera.open();
            try {
                myCamera.setDisplayOrientation(90);
                myCamera.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int screenWidth;
    private int screenHeight;

    public void getScreenSize() {
        WindowManager wm = (WindowManager) getSystemService(
                Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();
    }

    /**
     * suraceView 状态改变执行的操作
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        try {

            //摄像头画面显示在Surface上
            if (myCamera != null) {
                Camera.Parameters parameters = myCamera.getParameters();
                List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
                int[] a = new int[sizes.size()];
                int[] b = new int[sizes.size()];
                for (int i = 0; i < sizes.size(); i++) {
                    int supportH = sizes.get(i).height;
                    int supportW = sizes.get(i).width;
                    a[i] = Math.abs(supportW - screenHeight);
                    b[i] = Math.abs(supportH - screenWidth);
                    Log.d(TAG,"supportW:"+supportW+"supportH:"+supportH);
                }
                int minW=0,minA=a[0];
                for( int i=0; i<a.length; i++){
                    if(a[i]<=minA){
                        minW=i;
                        minA=a[i];
                    }
                }
                int minH=0,minB=b[0];
                for( int i=0; i<b.length; i++){
                    if(b[i]<minB){
                        minH=i;
                        minB=b[i];
                    }
                }
                Log.d(TAG,"result="+sizes.get(minW).width+"x"+sizes.get(minH).height);
                List<Integer> list = parameters.getSupportedPreviewFrameRates();
                parameters.setPreviewSize(sizes.get(minW).width,sizes.get(minH).height); // 设置预览图像大小
                parameters.setPreviewFrameRate(list.get(list.size() - 1));
//                连续自动对焦模式，主要用于录制视频过程中，Camera会不断地尝试聚焦，
//                这是录制视频时对焦模式的最好选择，在设置了Camera的参数后就开始自动对焦，但是调用takePicture时不一定已经对焦完成。
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
                myCamera.cancelAutoFocus();// 先要取消掉进程中所有的聚焦功能
                myCamera.setParameters(parameters);
                myCamera.autoFocus(new AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        Log.d("dfasa","自动聚焦："+success);
                    }
                });
                myCamera.setDisplayOrientation(90);
                myCamera.startPreview();
            }
        } catch (Exception e) {
            if (myCamera != null)
                myCamera.release();
            myCamera = null;
        }
    }



    /**
     * suraceView 销毁执行的操作
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // 关闭预览并释放资源
        if (myCamera != null) {
            myCamera.stopPreview();
            myCamera.release();
            myCamera = null;
        }
    }


}
