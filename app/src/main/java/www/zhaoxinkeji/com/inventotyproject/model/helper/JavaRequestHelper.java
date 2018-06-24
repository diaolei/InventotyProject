package www.zhaoxinkeji.com.inventotyproject.model.helper;

import java.util.HashMap;
import java.util.Map;

public class JavaRequestHelper {

    public static Map<String, Object> testNetSetting(String action, long cid) {
        Map<String, Object> params = new HashMap<>();
        params.put("action", action);
        params.put("cid", cid);
        return params;
    }

    public static Map<String, Object> login(String userName, String password) {
        Map<String, Object> params = new HashMap<>();
        params.put("action", "Login");
        params.put("cid", 1000);
        params.put("userName", userName);
        params.put("password", password);
        return params;
    }

    public static Map<String, Object> baseDataDownload(long userID, long storeID, int baseType) {
        Map<String, Object> params = new HashMap<>();
        params.put("action", "DownloadBaseInfo");
        params.put("cid", 1000);
        params.put("userID", userID);
        params.put("storeID", storeID);
        params.put("baseType", baseType);
        return params;
    }
}
