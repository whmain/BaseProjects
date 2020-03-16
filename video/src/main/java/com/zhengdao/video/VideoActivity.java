package com.zhengdao.video;

import android.os.Bundle;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.dueeeke.videocontroller.StandardVideoController;
import com.zhengdao.video.databinding.ActivityVideoBinding;

import java.io.File;

public class VideoActivity extends AppCompatActivity {
    ActivityVideoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =DataBindingUtil.setContentView(this,R.layout.activity_video);

        String url = Environment.getExternalStorageDirectory()
                .getAbsolutePath()
                + File.separator
                + "VideoFolder"
                + File.separator
                +"Compress"
                + File.separator;
        url = url + "video20200316_120424_compress.mp4";
//        url = url + "VID_20200312_145859_compress.mp4";
        binding.player.setUrl(url);
//        binding.player.setUrl("http://video7.house365.com/stream/2020/03/02/15831335555e5cb3738dbcb.mp4");
        StandardVideoController controller = new StandardVideoController(this);

        controller.addDefaultControlComponent("fa",false);
        binding.player.setVideoController(controller);
        binding.player.setRotation(180);
//        binding.player.setPlayerFactory(IjkPlayerFactory.create());
//        binding.player.setVolume();
        binding.player.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.player.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.player.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.player.release();
    }


    @Override
    public void onBackPressed() {
        if (!binding.player.onBackPressed()) {
            super.onBackPressed();
        }
    }
}

