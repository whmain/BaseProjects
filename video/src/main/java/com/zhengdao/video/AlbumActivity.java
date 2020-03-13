package com.zhengdao.video;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhengdao.video.videocompressor.VideoCompress;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AlbumActivity extends AppCompatActivity {
    private RecyclerView rv;
    private List<VideoBean> list;
    private AlbumAdapter adapter;
    private TextView tv_hint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        rv = findViewById(R.id.list);
        tv_hint = findViewById(R.id.tv_hint);
        // 设置布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        list= new ArrayList<>();
        adapter = new AlbumAdapter(list,this);
        rv.setAdapter(adapter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                list.addAll(AlbumUtils.getList(AlbumActivity.this));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "VideoFolder" + File.separator+"Compress";
                final String destPath = path + File.separator+list.get(position).getName()+"_compress"+".mp4";
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            String filePath = SiliCompressor.with(AlbumActivity.this).compressVideo(list.get(position).getUrl(), path);
//                            Log.d("fdafadfa",filePath);
//                        } catch (URISyntaxException e) {
//                            e.printStackTrace();
//                            Log.d("fdafadfa",e.getMessage());
//                        }
//                    }
//                }).start();


                VideoCompress.compressVideoLow(list.get(position).getUrl(), destPath, new VideoCompress.CompressListener() {
                    @Override
                    public void onStart() {
                        tv_hint.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onSuccess() {
                        tv_hint.setText("完成");

                    }

                    @Override
                    public void onFail() {
                        tv_hint.setText("失败");
                    }

                    @Override
                    public void onProgress(float percent) {
                        tv_hint.setText("经度:"+percent);
                        Log.d("fadfas",percent+"");
                    }
                });
            }
        });
    }




}
