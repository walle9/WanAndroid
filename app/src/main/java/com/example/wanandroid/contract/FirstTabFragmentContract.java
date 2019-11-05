package com.example.wanandroid.contract;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.model.bean.HomeBannerBean;
import com.example.wanandroid.model.bean.HomeListBean;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.List;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.contract
 * @ClassName: MainContract
 * @Description: 天气情况控制层
 * @Author: walle
 * @CreateDate: 2019/8/19 15:53
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 15:53
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface FirstTabFragmentContract {

    /**
     * 首页数据 V层接口
     */
    interface IView extends BaseView{


        /**
         * 加载首页列表数据成功
         * @param homeListBean 首页列表数据
         */
        void showgetHomeArticleListSuccessful(HomeListBean homeListBean);

        /**
         * 加载首页列表数据失败,处理界面
         */
        void showgetHomeArticleListFailed();


        /**
         * 加载首页Banner数据成功
         * @param homeBannerBeans 首页banner数据集合
         */
        void showHomeBannerSuccessful(List<HomeBannerBean> homeBannerBeans);

        /**
         * 加载首页banner数据失败
         */
        void showgetHomeBannerFailed();

        /**
         * 加载首页top库数据成功
         * @param datasBeans top库数据
         */
        void showgetHomeArticleTopListSuccessful(List<HomeListBean.DatasBean> datasBeans);

        /**
         * 加载首页Top列表数据失败,处理界面
         */
        void showgetHomeArticleTopListFailed();

    }


    /**
     * 首页数据 P层接口
     */
    interface Presenter extends BasePresenter<IView> {

        /**
         * 加载首页列表数据
         * @param page  页码
         */
        void loadHomeArticleList(int page);

        /**
         * 加载首页Top数据
         */
        void loadHomeArticleTopList();

        /**
         * 加载首页banner
         */
        void loadBanner();

        /**
         * 收藏站内文章
         * @param id 文章id
         */
        void collect(int id, ShineButton shineButton);

        /**
         * 取消收藏
         * @param id    文章id
         */
        void uncollect(int id, ShineButton shineButton);
    }
}
