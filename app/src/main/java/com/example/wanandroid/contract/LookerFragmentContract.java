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
public interface LookerFragmentContract {

    /**
     * 美女图片 V层接口
     */
    interface IView extends BaseView{
        /**
         * 显示加载成功
         * @param lookerBeans   图片集合
         */
        void showSuccessful(List<LookerBean> lookerBeans);
    }


    /**
     * 美女图片 P层接口
     */
    interface Presenter extends BasePresenter<IView> {

        /**
         * 获取美女图片
         * @param isRefresh 是否是刷新
         * @param count 每页数量
         */
        void loadData(boolean isRefresh,String count);
    }
}
