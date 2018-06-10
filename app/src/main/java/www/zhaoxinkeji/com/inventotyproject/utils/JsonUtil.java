package www.zhaoxinkeji.com.inventotyproject.utils;


import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 27631 on 2017/6/21.
 */

public class JsonUtil {

    private static Gson gson;

    public static String toJson(HashMap<String,String>  map){
        if(gson==null) {
            gson = new Gson();
        }
        String json = gson.toJson(map);
        return "&param="+json;
    }

    public static String map2Json(HashMap<String,String> paramsMap){
        String params="&param={";
        for (Map.Entry<String, String> entry:paramsMap.entrySet()) {
            params+=("\""+entry.getKey()+"\""+":"+"\""+entry.getValue())+"\""+",";
        }
        return params+"}";
    }
    public static String map2Json1(HashMap<String,String> paramsMap){
        String params="{";
        for (Map.Entry<String, String> entry:paramsMap.entrySet()) {
            params+=("\""+entry.getKey()+"\""+":"+"\""+entry.getValue())+"\""+",";
        }
        return params;
    }

    public static String list2Json(HashMap<String,String> paramsMap){
        String params="{";
        for (Map.Entry<String, String> entry:paramsMap.entrySet()) {
            params+=("\""+entry.getKey()+"\""+":"+"\""+entry.getValue())+"\""+",";
        }
        return params.substring(0,params.length()-1)+"}";
    }
}
