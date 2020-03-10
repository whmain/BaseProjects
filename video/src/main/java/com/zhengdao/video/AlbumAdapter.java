package com.zhengdao.video;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.io.File;
import java.util.List;

/**
 * @author WangHao on 2020/3/10.
 * E-Mail: wh_main@163.com
 * Description:
 */
public class AlbumAdapter extends BaseQuickAdapter<VideoBean, BaseViewHolder> {
    private Context mcontext;
    public AlbumAdapter( @Nullable List<VideoBean> data,Context mcontext) {
        super(R.layout.ll, data);
        this.mcontext = mcontext;
    }


    @Override
    protected void convert(BaseViewHolder helper, VideoBean item) {
        ImageView iv = helper.getView(R.id.iv_icon);
        Glide.with( mcontext )
                .load( Uri.fromFile( new File( item.getUrl() ) ) )
                .into( iv);
    }
}
