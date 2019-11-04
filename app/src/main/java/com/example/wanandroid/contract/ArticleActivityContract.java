package com.example.wanandroid.contract;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.model.bean.LoginBean;
import com.sackcentury.shinebuttonlib.ShineButton;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.contract
 * @ClassName: LoginActivityContract
 * @Description: 登录控制层
 * @Author: walle
 * @CreateDate: 2019年10月23日00:50:36
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019年10月23日00:50:43
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface ArticleActivityContract {

    interface IView extends BaseView{
        /**
         * 收藏站内文章成功
         */
        void showCollectSuccessful(String msg);

        /**
         * 收藏站内文章失败
         */
        void showCollectFailed(ShineButton shineButton);

        /**
         * 取消收藏成功
         */
        void showUncollectSuccessful(String msg);

        /**
         * 取消收藏失败
         */
        void showUncollectFailed(ShineButton shineButton);
    }

    interface Presenter extends BasePresenter<IView> {
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
