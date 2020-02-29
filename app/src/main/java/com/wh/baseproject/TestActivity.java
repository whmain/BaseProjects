package com.wh.baseproject;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.databinding.DataBindingUtil;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wh.baseproject.databinding.ActivityLoginBinding;
import com.wh.baseproject.http.RetrofitManager;
import com.wh.baseproject.http.api.TestApiService;
import com.wh.baseproject.http.common.ResponseObserver;
import com.wh.baseproject.http.model.Artlist;
import com.wh.baseproject.http.model.BaseResponse;
import com.wh.baseproject.http.utils.RxUtil;
import com.wh.library.utils.WhiteListUtil;

import org.jetbrains.annotations.Nullable;

/**
 * @author PC-WangHao on 2019/09/26 18:45.
 * E-Mail: wh_main@163.com
 * Description:
 */
public class TestActivity extends RxAppCompatActivity {
    private ActivityLoginBinding binding;
    Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        btn = findViewById(R.id.btn1);
        binding.btn1.setOnClickListener(v -> RetrofitManager.getInstance().create(TestApiService.class)
                .getArticle()
                .compose(RxUtil.rxSchedulerHelper(TestActivity.this,true))
                .subscribe(new ResponseObserver<BaseResponse<Artlist>>() {
                    @Override
                    public void onSuccess(BaseResponse<Artlist> response) {
                        btn.setText("fadfdaf");
                        Log.d("fdafaf","    "+isFinishing() + " "+ isDestroyed()+"      "+ btn);

                    }
                }));

        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!WhiteListUtil.isIgnoringBatteryOptimizations(TestActivity.this)){
                        WhiteListUtil.requestIgnoreBatteryOptimizations(TestActivity.this);
                    }
                }
            }
        });

        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WhiteListUtil.setSystemWhiteList(TestActivity.this);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("fdafas","页面销毁");
    }
}
