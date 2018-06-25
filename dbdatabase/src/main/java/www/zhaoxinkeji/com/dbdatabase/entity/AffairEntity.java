package www.zhaoxinkeji.com.dbdatabase.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/25
 *     desc   : 事务类型表
 *     version: 1.0
 * </pre>
 */

@Entity
public class AffairEntity {

    private Long id;//事物ID
    private String code;//事物代码
    private String name;//事物名称

    @Generated(hash = 700136121)
    public AffairEntity(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    @Generated(hash = 1339998344)
    public AffairEntity() {
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
        return "AffairEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
