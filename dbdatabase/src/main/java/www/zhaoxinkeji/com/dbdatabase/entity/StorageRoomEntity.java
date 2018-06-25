package www.zhaoxinkeji.com.dbdatabase.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/06/25
 *     desc   : 库房表
 *     version: 1.0
 * </pre>
 */

@Entity
public class StorageRoomEntity {

    private Long id;//库房ID
    private String code;//库房代码
    private String name;//库房名称

    @Generated(hash = 1800796155)
    public StorageRoomEntity(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    @Generated(hash = 1968551296)
    public StorageRoomEntity() {
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
        return "StorageRoomEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
