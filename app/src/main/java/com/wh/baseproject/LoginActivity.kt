package com.wh.baseproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wh.baseproject.databinding.ActivityLoginBinding
import com.zhengdao.video.AlbumActivity
import com.zhengdao.video.Camera2Activity
import com.zhengdao.video.CameraActivity
import com.zhengdao.video.VideoActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        var binding =
            DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.btn1.setOnClickListener { startActivity(Intent(this, VideoActivity::class.java)) };
        binding.btn2.setOnClickListener { startActivity(Intent(this, Camera2Activity::class.java)) };
        binding.btn3.setOnClickListener { startActivity(Intent(this, AlbumActivity::class.java)) };
        binding.btn4.setOnClickListener { startActivity(Intent(this, CameraActivity::class.java)) };
    }
}
