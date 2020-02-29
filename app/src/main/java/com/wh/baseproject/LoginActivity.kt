package com.wh.baseproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wh.baseproject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        DataBindingUtil.setContentView<ActivityLoginBinding>(this,R.layout.activity_login)
            .btn1.setOnClickListener {   startActivity(Intent(this,TestActivity::class.java)) };


    }
}
