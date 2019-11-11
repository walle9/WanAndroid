package com.example.wanandroid.contract;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.model.bean.HomeListBean;
import com.example.wanandroid.model.bean.HotKeyBean;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.List;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.contract
 * @ClassName: LoginActivityContract
 * @Description: 搜索控制层
 * @Author: walle
 * @CreateDate: 2019年10月23日00:50:36
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019年10月23日00:50:43
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface SearchActivityContract {

    interface IView extends BaseView{

        /**
         * 获取搜索热词成功
         */
        void showGetHotKeySuccessful(List<HotKeyBean> hotKeyBeans);

        /**
         * 获取搜索热词失败
         */
        void showGetHotKeyFailed();

        /**
         * 获取搜索列表成功
         */
        void showQueryArticleSuccessful(HomeListBean homeListBean);

        /**
         * 获取搜索热词失败
         */
        void showQueryArticleFailed();
    }

    interface Presenter extends BasePresenter<IView> {

        /**
         * 获取搜索热词
         */
        void getHotKeyList();



        void queryArticle(int page,String key);
    }
}
