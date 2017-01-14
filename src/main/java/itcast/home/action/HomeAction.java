package itcast.home.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import itcast.core.Constant.Constant;
import itcast.core.util.QueryHelper;
import itcast.nsfw.complain.entity.Complain;
import itcast.nsfw.info.service.InfoService;
import itcast.nsfw.user.entity.User;
import itcast.nsfw.user.service.UserService;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import itcast.nsfw.info.entity.Info;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {
	
	@Resource
	private UserService userService;
	@Resource
	private itcast.nsfw.complain.service.compService compService;
	@Resource
	private InfoService infoService;
	private List<Complain> complainList;
	private List<Info> infoList;
	private Complain comp;
	private User user;
	
	public String execute(){ 
		//信息列表展示
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.Info_type_map);
		
		infoList=infoService.findObjects();
		
		//ActionContext.getContext().getContextMap().put("infoList", infoList);
		//我的投诉信息列表展示
		  User user = (User) ServletActionContext.getRequest().getSession().getAttribute(Constant.user);
		  ActionContext.getContext().getContextMap().put("complainStateMap", Complain.complain_state_map);
		  complainList=compService.findObjects();
		
    	  return "home";
     }
	
	public String complainAddUI(){
		
		return "complainAddUI";
	}
	
	public void complainAdd(){
	try {
		  if(comp!=null){
			
			//设置默认投诉状态为待受理,设置投诉时间
			   comp.setState("0");
			   Timestamp compTime=new Timestamp( new Date().getTime());
			   comp.setCompTime(compTime);
			   compService.save(comp);
			   
			   HttpServletResponse response = ServletActionContext.getResponse();  
	           response.setContentType("text/html");  
	           ServletOutputStream outputStream;
	
			   outputStream = response.getOutputStream();
			   outputStream.write("success".getBytes("utf-8"));  
	           outputStream.close(); 
		  }  
		} catch (Exception e) {			
			e.printStackTrace();
   
		}
		
	}
	
	
	public void getUserJson(){
		String dept=ServletActionContext.getRequest().getParameter("dept");
		if(dept!=""){
			QueryHelper queryHelper=new QueryHelper(User.class, "u");
			queryHelper.addCondition("u.dept=?", dept);
			List<User> userlist=userService.findObjects(queryHelper);
			
			//创建Json对象
			JSONObject jso = new JSONObject();
			jso.put("msg", "success");
			jso.accumulate("userList", userlist);
			
			//3.输出用户列表以json格式字符串形式输出
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/json");
			ServletOutputStream outputStream;
			
			try {
				outputStream = response.getOutputStream();
				outputStream.write(jso.toString().getBytes("utf-8"));
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public Complain getComp() {
		return comp;
	}

	public void setComp(Complain comp) {
		this.comp = comp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Complain> getComplainList() {
		return complainList;
	}

	public void setComplainList(List<Complain> complainList) {
		this.complainList = complainList;
	}

	public List<Info> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<Info> infoList) {
		this.infoList = infoList;
	}




}
