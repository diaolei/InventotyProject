package www.zhaoxinkeji.com.dbdatabase.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;


/**
 * <pre>
 *     author : LinRuo
 *     time   : 2018/6/24
 *     desc   : 产品表
 *     version: 1.0
 * </pre>
 */
@Entity
public class ProductEntity {

    private Long id;//产品ID
    private String code;//产品代码
    private String name;//产品名称
    private String type;//产品型号

    @Generated(hash = 1225127217)
    public ProductEntity(Long id, String code, String name, String type) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.type = type;
    }

    @Generated(hash = 27353230)
    public ProductEntity() {
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

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}


