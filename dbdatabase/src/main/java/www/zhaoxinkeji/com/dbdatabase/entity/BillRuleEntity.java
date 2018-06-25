package www.zhaoxinkeji.com.dbdatabase.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/25
 *     desc   : 单据转换表
 *     version: 1.0
 * </pre>
 */
@Entity
public class BillRuleEntity {

    private Long id;//单据ID
    private String code;//单据代码
    private String name;//单据名称

    @Generated(hash = 957558600)
    public BillRuleEntity(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    @Generated(hash = 68998699)
    public BillRuleEntity() {
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
        return "BillRuleEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
