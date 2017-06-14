package io.github.xunuosi.tb.views.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;

import javax.inject.Inject;

import io.github.xunuosi.tb.R;


/**
 * Created by xns on 2017/6/1.
 * 列表形式简单Adapter
 */

public class SimpleAdapter<T> extends UltimateViewAdapter {

    private List<T> mList;
    private LayoutInflater mInflater;
    private itemOnClickListener mListener;

    @Inject
    public SimpleAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setData(List<T> list) {
        if (mList == null) {
            mList = list;
        } else {
            mList.clear();
            mList.addAll(list);
        }
    }

    public void refresh() {
        if (mList != null && mList.size() != 0) {
            notifyDataSetChanged();
        }
    }

    public void setListener(itemOnClickListener listener) {
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public RecyclerView.ViewHolder newHeaderHolder(View view) {
        return null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.text_item, parent, false);
        return new TextViewHolder(view);
    }

    @Override
    public int getAdapterItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        T bean = mList.get(position);
        TextViewHolder mHolder = (TextViewHolder) holder;
        mHolder.mTextView.setText(bean.toString());
        mHolder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public interface itemOnClickListener{
        void onItemClick(int position);
    }

    private class TextViewHolder extends UltimateRecyclerviewViewHolder {
        private TextView mTextView;

        public TextViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tv_item_text);
        }
    }
}
