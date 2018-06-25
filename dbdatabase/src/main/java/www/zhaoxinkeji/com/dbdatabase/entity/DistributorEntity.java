package www.zhaoxinkeji.com.dbdatabase.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/25
 *     desc   : 下级经销商表
 *     version: 1.0
 * </pre>
 */
@Entity
public class DistributorEntity {

    private Long id;//经销商ID
    private String code;//经销商代码
    private String name;//经销商名称

    @Generated(hash = 698912726)
    public DistributorEntity(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    @Generated(hash = 1873150230)
    public DistributorEntity() {
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

}
