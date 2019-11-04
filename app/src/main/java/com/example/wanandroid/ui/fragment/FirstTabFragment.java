package com.example.wanandroid.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseMvpFragment;
import com.example.wanandroid.contract.FirstTabFragmentContract;
import com.example.wanandroid.model.bean.HomeBannerBean;
import com.example.wanandroid.model.bean.HomeListBean;
import com.example.wanandroid.model.event.CollectionEvent;
import com.example.wanandroid.model.event.LoginEvent;
import com.example.wanandroid.presenter.FirstTabFragmentPresenter;
import com.example.wanandroid.ui.activity.ArticleActivity;
import com.example.wanandroid.ui.activity.LoginActivity;
import com.example.wanandroid.ui.activity.SimpleWebActivity;
import com.example.wanandroid.ui.adapter.HomeAdapter;
import com.example.wanandroid.utils.Constants;
import com.example.wanandroid.utils.SPUtils;
import com.orhanobut.logger.Logger;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.view.fragment
 * @ClassName: FirstTabFragment
 * @Description: 首页
 * @Author: walle
 * @CreateDate: 2019/9/3 10:15
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/9/3 10:15
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class FirstTabFragment extends BaseMvpFragment<FirstTabFragmentPresenter> implements FirstTabFragmentContract.IView {

    private ImageButton mIbHomeSearch;
    private SmartRefreshLayout mRefreshLayoutHomeRefresh;
    private RecyclerView mRvHomeList;
    private HomeAdapter mAdapter;
    private static final int PAGE_START = 0;
    private int currPage;
    private Banner mBanner;
    private ArrayList<String> mImages = new ArrayList<>();

    public static SupportFragment newInstance() {
        Bundle args = new Bundle();
        FirstTabFragment fragment = new FirstTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        super.initView();
        mIbHomeSearch = findViewById(R.id.ib_home_search);
        mRefreshLayoutHomeRefresh = findViewById(R.id.refreshLayout_home_refresh);
        mRvHomeList = findViewById(R.id.rv_home_list);

        mRvHomeList.addItemDecoration(new DividerItemDecoration(_mActivity,
                DividerItemDecoration.VERTICAL));
        mRvHomeList.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new HomeAdapter(_mActivity);
        mRvHomeList.setAdapter(mAdapter);
        mAdapter.openLoadAnimation();
        mAdapter.setPreLoadNumber(2);
        mAdapter.setEnableLoadMore(false);
        setOnClick(mIbHomeSearch);

        mRefreshLayoutHomeRefresh.setOnRefreshListener(refreshLayout -> {
            currPage = PAGE_START;
            basePresenter.loadHomeArticleList(currPage);
            basePresenter.loadBanner();
        });

        mAdapter.setOnLoadMoreListener(() -> basePresenter.loadHomeArticleList(currPage),
                mRvHomeList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            HomeListBean.DatasBean adapterItem = (HomeListBean.DatasBean) adapter.getItem(position);
            if (null != adapterItem && !TextUtils.isEmpty(adapterItem.mTitle) && !TextUtils.isEmpty(adapterItem.mLink) && adapterItem.mId != -1) {
                ArticleActivity.actionStartActivity(_mActivity, adapterItem.mId,adapterItem.mCollect, adapterItem.mTitle, adapterItem.mLink);
            } else {
                showTipMsg(_mActivity.getResources().getString(R.string.get_home_articlelist_failed));
            }
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            HomeListBean.DatasBean adapterItem =
                    (HomeListBean.DatasBean) adapter.getItem(position);
            if (null != adapterItem) {
                switch (view.getId()) {
                    case R.id.shine_button:
                        //item收藏
                        setCollect((ShineButton) view, adapterItem.mId);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void setCollect(ShineButton view, int articleId) {

        if (SPUtils.newInstance(Constants.SP_NAME_USERINFO).get(Constants.KEY_USERINFO_ISLOGIN,
                false)) {
            if (view.isChecked()) {
                //点击状态先变成选中了
                basePresenter.collect(articleId, view);
            } else {
                basePresenter.uncollect(articleId, view);
            }

        } else {
            if (view.isChecked()) {
                //还原点击前的状态
                view.setChecked(false);
            } else {
                view.setChecked(true);
            }
            LoginActivity.actionStartActivity(_mActivity);
        }
    }

    @Override
    protected void initData() {
        mRefreshLayoutHomeRefresh.autoRefresh();
    }

    @Override
    public void showgetHomeArticleListSuccessful(HomeListBean homeListBean) {

        if (currPage == PAGE_START) {
            mRefreshLayoutHomeRefresh.finishRefresh();
            mAdapter.replaceData(homeListBean.mDatas);
        } else {
            mAdapter.addData(homeListBean.mDatas);
            mAdapter.loadMoreComplete();
        }

        if (homeListBean.mOver) {
            mAdapter.loadMoreEnd();
        } else {
            currPage++;
        }

        if (mAdapter.getData().isEmpty() && mAdapter.getEmptyViewCount() == 0) {
            mAdapter.setEmptyView(R.layout.empty_view, mRefreshLayoutHomeRefresh);
        }


    }

    @Override
    public void showgetHomeArticleListFailed() {

        if (currPage == PAGE_START) {
            mRefreshLayoutHomeRefresh.finishRefresh(false);
            mAdapter.loadMoreFail();
        } else {
            mAdapter.loadMoreFail();
        }

        if (mAdapter.getEmptyViewCount() == 0) {
            mAdapter.setEmptyView(R.layout.empty_view, mRefreshLayoutHomeRefresh);
        }
    }

    @Override
    public void showHomeBannerSuccessful(List<HomeBannerBean> homeBannerBeans) {
        setBanner(homeBannerBeans);
    }

    private void setBanner(List<HomeBannerBean> homeBannerBeans) {
        if (mBanner == null) {
            createHeaderBanner();
            mAdapter.addHeaderView(mBanner, 0);

        }

        if (!homeBannerBeans.isEmpty()) {
            mImages.clear();
            for (HomeBannerBean homeBannerBean : homeBannerBeans) {
                mImages.add(homeBannerBean.mImagePath);
            }
            mBanner.setImages(mImages);
            mBanner.start();
        }

    }

    private void createHeaderBanner() {
        mBanner = (Banner) LayoutInflater.from(_mActivity).inflate(R.layout.home_banner, null);
        int height = (int) (getResources().getDisplayMetrics().widthPixels * (9F / 16F));
        mBanner.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                height));
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(FirstTabFragment.this).load(path).into(imageView);
            }
        });
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                // TODO: 2019/10/18  跳转到具体的页面
            }
        });
        mBanner.setImages(mImages);
        mBanner.start();
    }

    @Override
    public void showgetHomeBannerFailed() {
        // TODO: 2019/10/19 加载banner失败
    }

    @Override
    public void showCollectSuccessful(String msg) {
        //暂不处理成功
    }

    @Override
    public void showCollectFailed(ShineButton shineButton) {
        if (shineButton.isChecked()) {
            shineButton.setChecked(false);
        }
    }

    @Override
    public void showUncollectSuccessful(String msg) {
        //暂不处理成功
    }

    @Override
    public void showUncollectFailed(ShineButton shineButton) {
        if (!shineButton.isChecked()) {
            shineButton.setChecked(true);
        }
    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if (null != mBanner) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        if (null != mBanner) {
            mBanner.stopAutoPlay();
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.ib_home_search) {
            // TODO: 2019/10/8 跳转到搜索页面
        }


    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent loginEvent) {
        if (!isHidden()) {
            mRefreshLayoutHomeRefresh.autoRefresh();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectionEvent(CollectionEvent collectionEvent) {
        int articleId = collectionEvent.getArticleId();
        List<HomeListBean.DatasBean> data = mAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            HomeListBean.DatasBean datasBean = data.get(i);
            if (datasBean.mId==articleId) {
                datasBean.mCollect = collectionEvent.isCoolect();
                mAdapter.notifyItemChanged(i +mAdapter.getHeaderLayoutCount());
            }
        }
    }
}
