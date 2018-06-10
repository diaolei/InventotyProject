package www.zhaoxinkeji.com.dbdatabase.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <pre>
 *     author : LinRuo
 *     time   : 2018/03/21
 *     desc   : 纸条文章表
 *     version: 1.0
 * </pre>
 */

@Entity
public class ArticleEntity {

    private Long articleId;//纸条id

    @Generated(hash = 1722848044)
    public ArticleEntity(Long articleId) {
        this.articleId = articleId;
    }

    @Generated(hash = 1301498493)
    public ArticleEntity() {
    }

    public Long getArticleId() {
        return this.articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

}


