package itcast.nsfw.complain.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Complain implements java.io.Serializable {

	// Fields

	private String compId; 
	private String compCompany; //投诉人单位
	private String compName; //投诉人姓名
	private String compPhone; //投诉人姓名
	private String compTitle; //投诉标题
	private Timestamp compTime; //投诉时间
	private String compContent; //投诉内容
	private Boolean compIsNm; //是否匿名投诉
	private String becompDept; //被投诉部门
	private String becompName; //被投诉人姓名
	private String state; //投诉受理状态
	private Set replies = new HashSet(); //回复
	
	public static String complain_state_undone="0";//未受理
	public static String complain_state_done="1";  //已受理
	public static String complain_state_invalid="2"; //已失效
	public static Map<String,String> complain_state_map;
	
	static {
		complain_state_map=new HashMap<String, String>();
		complain_state_map.put(complain_state_undone, "未受理");
		complain_state_map.put(complain_state_done, "已受理");
		complain_state_map.put(complain_state_invalid, "已失效");
		
	}

	// Constructors

	/** default constructor */
	public Complain() {
	}

	/** full constructor */
	public Complain(String compCompany, String compName, String compPhone,
			String compTitle, Timestamp compTime, String compContent,
			Boolean compIsNm, String becompDept, String becompName,
			String state, Set replies) {
		this.compCompany = compCompany;
		this.compName = compName;
		this.compPhone = compPhone;
		this.compTitle = compTitle;
		this.compTime = compTime;
		this.compContent = compContent;
		this.compIsNm = compIsNm;
		this.becompDept = becompDept;
		this.becompName = becompName;
		this.state = state;
		this.replies = replies;
	}

	// Property accessors

	public String getCompId() {
		return this.compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getCompCompany() {
		return this.compCompany;
	}

	public void setCompCompany(String compCompany) {
		this.compCompany = compCompany;
	}

	public String getCompName() {
		return this.compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompPhone() {
		return this.compPhone;
	}

	public void setCompPhone(String compPhone) {
		this.compPhone = compPhone;
	}

	public String getCompTitle() {
		return this.compTitle;
	}

	public void setCompTitle(String compTitle) {
		this.compTitle = compTitle;
	}

	public Timestamp getCompTime() {
		return this.compTime;
	}

	public void setCompTime(Timestamp compTime) {
		this.compTime = compTime;
	}

	public String getCompContent() {
		return this.compContent;
	}

	public void setCompContent(String compContent) {
		this.compContent = compContent;
	}

	public Boolean getCompIsNm() {
		return this.compIsNm;
	}

	public void setCompIsNm(Boolean compIsNm) {
		this.compIsNm = compIsNm;
	}

	public String getBecompDept() {
		return this.becompDept;
	}

	public void setBecompDept(String becompDept) {
		this.becompDept = becompDept;
	}

	public String getBecompName() {
		return this.becompName;
	}

	public void setBecompName(String becompName) {
		this.becompName = becompName;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set getReplies() {
		return this.replies;
	}

	public void setReplies(Set replies) {
		this.replies = replies;
	}

}