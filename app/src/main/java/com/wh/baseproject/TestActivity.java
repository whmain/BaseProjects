package com.wh.baseproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wh.baseproject.databinding.ActivityLoginBinding;
import com.wh.baseproject.http.RetrofitManager;
import com.wh.baseproject.http.api.TestApiService;
import com.wh.baseproject.http.common.ResponseObserver;
import com.wh.baseproject.http.model.Artlist;
import com.wh.baseproject.http.model.BaseResponse;
import com.wh.baseproject.http.utils.RxUtil;

/**
 * @author PC-WangHao on 2019/09/26 18:45.
 * E-Mail: wh_main@163.com
 * Description:
 */
public class TestActivity extends RxAppCompatActivity {
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RetrofitManager.getInstance().create(TestApiService.class)
                        .getArticle()
                        .compose(RxUtil.rxSchedulerHelper(TestActivity.this,true))
                        .subscribe(new ResponseObserver<BaseResponse<Artlist>>() {
                            @Override
                            public void onSuccess(BaseResponse<Artlist> response) {
                                Log.d("fdafaf",response.getData().toString());
                            }
                        });
            }
        });
    }
}
