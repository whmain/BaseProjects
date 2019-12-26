package com.wh.baseproject.vp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.wh.baseproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PC-WangHao on 2019/10/17 15:27.
 * E-Mail: wh_main@163.com
 * Description:
 */
public class RefreshBuyTab extends LinearLayout {

    private Context mContext;
    private List<String> mTextData = new ArrayList<>();
    private List<View> mChildViews = new ArrayList<>();
    private OnItemChangeListener mOnItemChangeListener;

    public RefreshBuyTab(Context context) {
        super(context);
        build(context);
    }

    public RefreshBuyTab(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        build(context);
    }

    public RefreshBuyTab(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        build(context);

    }

    private void build(Context context) {
        this.mContext = context;
        setOrientation(LinearLayout.HORIZONTAL);
    }

    private void addTab(List<String> data) {
        mChildViews.clear();
        for (int i = 0; i < data.size(); i++) {
            View view = View.inflate(mContext, R.layout.item_refresh_buy, null);
            ((TextView)view.findViewById(R.id.tv_text)).setText(mTextData.get(i));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
            final int finalI = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeTab(finalI);
                }
            });
            mChildViews.add(view);
            addView(view, lp);
        }
    }


    private void changeTab(int position) {
        for (int i = 0; i < mChildViews.size(); i++) {
            mChildViews.get(i).findViewById(R.id.tv_text).setSelected(i == position);
            mChildViews.get(i).findViewById(R.id.line_mark).setVisibility(i == position ? View.VISIBLE : View.INVISIBLE);
        }
        if (mOnItemChangeListener != null) {
            mOnItemChangeListener.onItemChange(position);
        }
    }

    public void setData(List<String> data){
        mTextData.clear();
        mTextData.addAll(data);
        addTab(mTextData);
    }

    public void setOnItemChangeListener(OnItemChangeListener listener) {
        this.mOnItemChangeListener = listener;
    }

    public void setCurrentPosition(int position) {
        changeTab(position);
    }

    public void setSelectedByPosition(int position) {
        for (int i = 0; i < mChildViews.size(); i++) {
            mChildViews.get(i).findViewById(R.id.tv_selected).setVisibility(i == position ? View.VISIBLE : View.GONE);
        }
    }

    public void clearSelected(){
        setSelectedByPosition(-1);
    }

    public interface OnItemChangeListener {
        void onItemChange(int position);
    }
}
