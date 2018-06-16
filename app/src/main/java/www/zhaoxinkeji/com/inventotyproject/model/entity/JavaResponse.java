package www.zhaoxinkeji.com.inventotyproject.model.entity;

/**
 * <pre>
 *     author : LinRuo
 *     time   : 2018/3/20/17:28
 *     desc   : java服务器统一的返回格式
 *     version: 1.0
 * </pre>
 */
public class JavaResponse<T> {

    private int errcode;//状态码
    private String errmsg;//提示信息
    private T data;//返回数据

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JavaResponse{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * API是否请求失败
     *
     * @return 失败返回true, 成功返回false
     */
    public boolean isSuccess() {
        return errcode == 0;
    }
}
