package com.example.wanandroid.contract;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.base.BaseView;

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
public interface SecondTabFragmentContract {

    /**
     * 天气情况 V层接口
     */
    interface IView extends BaseView{
        /**
         * 显示加载成功的数据
         * @param msg
         */
        void showSuccessful(String msg);
    }


    /**
     * 天气情况 P层接口
     */
    interface Presenter extends BasePresenter<IView> {

        /**
         * 加载数据
         */
        void getGirlList(String count,String page);
    }
}
