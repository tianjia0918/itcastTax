package itcast.core.Constant;

import java.util.HashMap;

public class Constant {
	//系统用户
	public static String user="SYS_USER";
	
	//权限
    public static String PRIVILEGE_XZGL="xzgl";
    public static String PRIVILEGE_HQFW="hqfw";
    public static String PRIVILEGE_ZXXX="zxxx";
    public static String PRIVILEGE_NSFW="nsfw";
    public static String PRIVILEGE_SPACES="spaces";
    
    public static HashMap<String, String> PRIVILEGE_MAP;
      static{
        PRIVILEGE_MAP =new HashMap<String, String>();
        PRIVILEGE_MAP.put(PRIVILEGE_XZGL,"行政管理");
	    PRIVILEGE_MAP.put(PRIVILEGE_HQFW,"后勤服务");
	    PRIVILEGE_MAP.put(PRIVILEGE_ZXXX,"在线学习");
	    PRIVILEGE_MAP.put(PRIVILEGE_NSFW,"纳税服务");
	    PRIVILEGE_MAP.put(PRIVILEGE_SPACES,"我的空间");
      }
	    
}
