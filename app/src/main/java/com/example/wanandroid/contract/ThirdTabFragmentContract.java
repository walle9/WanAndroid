package com.example.wanandroid.contract;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.model.bean.LookerBean;

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
public interface ThirdTabFragmentContract {

    /**
     * 天气情况 V层接口
     */
    interface IView extends BaseView{
        /**
         * 加载图片成功
         * @param lookerBeans   图片集合
         */
        void showSuccessful(List<LookerBean> lookerBeans,boolean isRefresh);

        /**
         *
         * 没有加载到数据
         * @param isRefresh
         */
        void showHasNoData(boolean isRefresh);
    }


    /**
     * 天气情况 P层接口
     */
    interface Presenter extends BasePresenter<IView> {

        /**
         * 加载数据
         */
        void getGirlList(int count,boolean isRefresh);
    }
}
