package www.zhaoxinkeji.com.dbdatabase.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;


/**
 * <pre>
 *     author : LinRuo
 *     time   : 2018/6/24
 *     desc   : 基础数据下载表
 *     version: 1.0
 * </pre>
 */
@Entity
public class BaseDataEntity {

    private Long id;//仓库ID
    private String code;//仓库编号
    private String name;//仓库名称

    @Generated(hash = 1804947092)
    public BaseDataEntity(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    @Generated(hash = 76996034)
    public BaseDataEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BaseDataEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}


