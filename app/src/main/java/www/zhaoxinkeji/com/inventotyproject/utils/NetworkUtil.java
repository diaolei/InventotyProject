package www.zhaoxinkeji.com.inventotyproject.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtil {
	
	public static String doPost(String urlPath,String json){
		String result="";
		try {
			URL url=new URL(urlPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			conn.setChunkedStreamingMode(0);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			conn.getOutputStream().write(json.getBytes());
			if (conn.getResponseCode()==200) {
				InputStream is = conn.getInputStream();
				BufferedReader reader=new BufferedReader(new InputStreamReader(is));
				String line=null;
				while ((line=reader.readLine())!=null) {
					result+=line;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String doGet(String urlPath){
		String result="";
		try {
			URL url=new URL(urlPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setChunkedStreamingMode(0);
			conn.setRequestMethod("GET");
			InputStream inputStream = conn.getInputStream();
			BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
			String line=null;
			while ((line=reader.readLine())!=null) {
				result+=line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
