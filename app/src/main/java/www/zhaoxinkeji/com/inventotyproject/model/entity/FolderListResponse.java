package www.zhaoxinkeji.com.inventotyproject.model.entity;

import java.util.List;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2018/04/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class FolderListResponse<T> {

    private List<T> folders;

    public List<T> getFolders() {
        return folders;
    }

    public void setFolders(List<T> folders) {
        this.folders = folders;
    }

    @Override
    public String toString() {
        return "FolderListResponse{" +
                "folders=" + folders +
                '}';
    }
}

