package www.zhaoxinkeji.com.inventotyproject.constant;

public class NetworkConst {
	
//	public static final String DOMAIN = "http://192.168.1.101:8080";
	public static final String TITLE = "http://";
	public static final String POINT = ":";
	public static final String LINE = "/";
	public static final String END = "/InventoryMeassages?";
	public static final String DOMAIN = "http://10.144.133.249:8300/rms/InventoryMeassages?";
	
	public static final String ACTION_LOGIN = DOMAIN+"method=loginCheck";
	public static final String LOGIN = "method=loginCheck";
	public static final String ACTION_GETBILL = DOMAIN+"method=getBill";
	public static final String GETBILL = "method=getBill";
	public static final String ACTION_GETBILLGOODS = DOMAIN+"method=getBillGoods";
	public static final String GETBILLGOODS = "method=getBillGoods";
	public static final String ACTION_UPLOAD = DOMAIN+"method=uploadGoods";
	public static final String UPLOAD = "method=uploadGoods";

}
