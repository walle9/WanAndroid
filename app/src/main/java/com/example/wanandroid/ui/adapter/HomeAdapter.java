package com.example.wanandroid.ui.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wanandroid.R;
import com.example.wanandroid.model.bean.HomeListBean;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

/**
 * @ProjectName: WanAndroid
 * @Package: com.example.wanandroid.view.adapter
 * @ClassName: HomeAdapter
 * @Description: java类作用描述
 * @Author: walle
 * @CreateDate: 2019/10/8 21:15
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/10/8 21:15
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class HomeAdapter extends BaseQuickAdapter<HomeListBean.DatasBean, BaseViewHolder> {
    private Activity mActivity;

    public HomeAdapter(Activity activity) {
        super(R.layout.item_homearticle);
        mActivity = activity;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomeListBean.DatasBean item) {

        LinearLayout llTagNew = helper.getView(R.id.ll_item_home_article_tag_new);
        llTagNew.setVisibility((item.mFresh) ? View.VISIBLE : View.GONE);

        String author = mActivity.getResources().getString(R.string.unknown);
        if (!TextUtils.isEmpty(item.mAuthor)) {
            author = item.mAuthor;
        } else if (!TextUtils.isEmpty(item.mShareUser)) {
            author = item.mShareUser;
        }
        helper.setText(R.id.tv_item_home_article_author, author);

        TextView tvArticleTag = helper.getView(R.id.tv_item_home_article_tag);
        List<HomeListBean.DatasBean.TagsBean> tags = item.mTags;
        if (null != tags && !tags.isEmpty()) {
            tvArticleTag.setText(tags.get(0).mName);
            tvArticleTag.setVisibility(View.VISIBLE);
        } else {
            tvArticleTag.setVisibility(View.GONE);
        }

        helper.setText(R.id.tv_item_home_article_time, TextUtils.isEmpty(item.mNiceDate) ?
                mActivity.getResources().getString(R.string.unknown) : item.mNiceDate);

        CardView cvArticleIcon = helper.getView(R.id.cv_item_home_article_icon);
        ImageView ivArticleIcon = helper.getView(R.id.iv_item_home_article_icon);
        String envelopePic = item.mEnvelopePic;
        if (TextUtils.isEmpty(envelopePic)) {
            cvArticleIcon.setVisibility(View.GONE);
        } else {
            Glide.with(mContext).load(envelopePic).placeholder(R.drawable.image_holder).error(R.drawable.image_holder).into(ivArticleIcon);
            cvArticleIcon.setVisibility(View.VISIBLE);
        }

        helper.setText(R.id.tv_item_home_article_title, item.mTitle);
        helper.setText(R.id.tv_item_home_article_desc, TextUtils.isEmpty(item.mDesc) ? "" :
                item.mDesc);
        helper.setText(R.id.tv_item_home_article_chaptername, item.mChapterName);
        helper.setText(R.id.tv_item_home_article_superchaptername, item.mSuperChapterName);

        ShineButton shineButton = helper.getView(R.id.shine_button);
        shineButton.init(mActivity);
        shineButton.setChecked(item.mCollect);
        helper.addOnClickListener(R.id.shine_button);


    }

}
