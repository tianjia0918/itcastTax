package itcast.login.action;

import java.util.List;

import javax.annotation.Resource;

import itcast.core.Constant.Constant;
import itcast.nsfw.user.entity.User;
import itcast.nsfw.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	@Resource
	private UserService userService;
	private User user;
	private String loginResult;
	
	//进入登陆页面
    public String toLoginUI(){
    	System.out.println("----------loginUI---------------");
    	return "loginUI";
    }
    //登陆
    public String login(){		
	    if(user!=null){
	    	if(StringUtils.isNotBlank(user.getAccount())&&StringUtils.isNotBlank(user.getPassword())){
	    		List list= userService.findUserByAccountAndPassword(user.getAccount(), user.getPassword());
	    		if(list != null && list.size() > 0){
	    			User user=(User) list.get(0);
	    			//设置到session域对象中
	    			ServletActionContext.getRequest().getSession().setAttribute(Constant.user, user);
	    			return "home"; 		
	    		}else{
	    			setLoginResult("用户名或密码不正确");
	    		}
	    		
	    		
	    	}else{
	    		setLoginResult("用户名和密码均不能为空");
	    	}			
		}else{			
			setLoginResult("请输入用户名和密码");
		}
	    
	    return toLoginUI();
	    
    }
    //无权限返回页面
    public String noPermissionUI(){
    	ServletActionContext.getRequest().getSession().removeAttribute("SYS_USER");
    	  return "noPermissionUI";
    }
    //注销，退出登陆
 
    public String LoginOut(){
    	
    	ServletActionContext.getRequest().getSession().removeAttribute("SYS_USER");
    	
    	  return toLoginUI();
    } 
    

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
    
}
