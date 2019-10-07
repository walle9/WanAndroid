package com.example.wanandroid.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.di.scope
 * @ClassName: ActivityScope
 * @Description: 自定义注解,Activity作用域
 * @Author: walle
 * @CreateDate: 2019/8/19 14:57
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 14:57
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
