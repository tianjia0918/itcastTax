package itcast.nsfw.info.entity;

import java.sql.Timestamp;
import java.util.HashMap;

public class Info {
	private String infoId;
	private String type;
	private String source;
	private String title;
	private String content;
	private String memo;
	private String creator;
	private Timestamp createTime;
	private String state;
	//状态设置常量
	public static String Info_state_public="1";  //发布
	public static String Info_state_stop="0";   //停用
	
	//type设置常量map
	public static String Info_type_tzgg="tzgg";
	public static String Info_type_zcsd="zcsd";
	public static String Info_type_nszd="nszd";
	
	public static HashMap<String, String> Info_type_map;
	static {
		Info_type_map=new HashMap<String, String>();
		Info_type_map.put(Info_type_tzgg, "通知公告");
		Info_type_map.put(Info_type_zcsd, "政策速递");
		Info_type_map.put(Info_type_nszd, "纳税指导");
	}
	
	
		
	public Info() {
	}
	public Info(String infoId, String type, String source, String title,
			String content, String memo, String creator, Timestamp createTime,
			String state) {
		this.infoId = infoId;
		this.type = type;
		this.source = source;
		this.title = title;
		this.content = content;
		this.memo = memo;
		this.creator = creator;
		this.createTime = createTime;
		this.state = state;
	}
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
