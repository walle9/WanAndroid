package com.example.wanandroid.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.di.qualifier
 * @ClassName: ApiUrl
 * @Description: 限定标识符,用于标识区分相同的被注入对象
 * @Author: walle
 * @CreateDate: 2019/8/19 15:02
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 15:02
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiUrl {
    String value() default "";
}
