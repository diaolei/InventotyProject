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
}
