package com.example.wanandroid.di.component;

import com.example.wanandroid.view.activity.MainActivity;
import com.example.wanandroid.di.module.ActivityModule;
import com.example.wanandroid.di.scope.ActivityScope;
import com.example.wanandroid.view.activity.WeatherActivity;
import com.example.wanandroid.view.fragment.FirstTabFragment;
import com.example.wanandroid.view.fragment.ForthTabFragment;
import com.example.wanandroid.view.fragment.LookerFragment;
import com.example.wanandroid.view.fragment.SecondTabFragment;
import com.example.wanandroid.view.fragment.ThirdTabFragment;

import dagger.Component;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.di.component
 * @ClassName: ActivityComponent
 * @Description: Activity(Fragment)注射器, 依赖AppComponent, 继承BaseMvpActivity(BaseMvpFragment)的activity(fragment)都来此注册
 * @Author: walle
 * @CreateDate: 2019/8/19 15:28
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 15:28
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(WeatherActivity activity);

    void inject(FirstTabFragment fragment);
    void inject(SecondTabFragment fragment);
    void inject(ThirdTabFragment fragment);
    void inject(ForthTabFragment fragment);
    void inject(LookerFragment fragment);

}
