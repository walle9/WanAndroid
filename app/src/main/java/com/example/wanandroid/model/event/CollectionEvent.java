package com.example.wanandroid.model.event;

/**
 * @ProjectName: WanAndroid
 * @Package: com.example.wanandroid.model.event
 * @ClassName: CollectionEvent
 * @Description: java类作用描述
 * @Author: walle
 * @CreateDate: 2019/11/4 10:51
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/11/4 10:51
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class CollectionEvent {
   private boolean coolect;
   private int articleId;

    public CollectionEvent(boolean coolect, int articleId) {
        this.coolect = coolect;
        this.articleId = articleId;
    }


    public boolean isCoolect() {
        return coolect;
    }

    public void setCoolect(boolean coolect) {
        this.coolect = coolect;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }
}
