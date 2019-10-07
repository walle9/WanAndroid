package com.example.wanandroid.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.wanandroid.R;
import com.example.wanandroid.base.MyApplication;
import com.example.wanandroid.model.bean.LookerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.view.adapter
 * @ClassName: LookerAdapter
 * @Description: java类作用描述
 * @Author: walle
 * @CreateDate: 2019/9/6 19:26
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/9/6 19:26
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class LookerAdapter extends RecyclerView.Adapter<LookerAdapter.ViewHolder> {

    private List<LookerBean> mLookerBeans;
    private Context mContext;

    public LookerAdapter(Context context) {
        mLookerBeans = new ArrayList<>();
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(MyApplication.getInstance()).inflate(R.layout.item_girl, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setData(position);
    }

    public void addDate(List<LookerBean> lookerBeans,boolean isRefresh) {
        if (isRefresh) {
            mLookerBeans.clear();
        }
        int size = mLookerBeans.size();
        mLookerBeans.addAll(lookerBeans);
        notifyItemChanged(size);
    }

    @Override
    public int getItemCount() {
        return mLookerBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mItemIvGirl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemIvGirl = itemView.findViewById(R.id.item_iv_girl);

        }

        private void setData(int position) {
            LookerBean lookerBean = mLookerBeans.get(position);
            String url = lookerBean.mUrl;
            Glide.with(mContext).load(url).placeholder(R.drawable.red_miao).into(mItemIvGirl);
        }
    }
}
