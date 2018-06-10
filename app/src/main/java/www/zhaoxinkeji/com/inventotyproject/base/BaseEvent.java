package www.zhaoxinkeji.com.inventotyproject.base;

/**
 * Author: ChenJia
 * Date : 2018/4/4
 * Description : eventbus 公共封装  发送的消息
 */
public class BaseEvent {
    int tag;
    Object content;

    public BaseEvent(int tag, Object content) {
        this.tag = tag;
        this.content = content;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
