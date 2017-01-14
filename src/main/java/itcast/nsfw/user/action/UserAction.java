package itcast.nsfw.user.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import itcast.core.util.PageResult;
import itcast.core.util.QueryHelper;
import itcast.nsfw.role.service.RoleService;
import itcast.nsfw.user.entity.UserRole;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import itcast.core.action.BaseAction;
import itcast.nsfw.user.entity.User;
import itcast.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionContext;

public class UserAction extends BaseAction {
	
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	private List<User> userList;
	private User user;
	private  File headImg;
	private String headImgFileName;
	private String headImgContentType;
	private  File userExcel;
	private String userExcelFileName;
	private String userExcelContentType;
	protected String[] selectedRow;	
	private String[] userRoleIds;
	private String strTitle;
	
	private int pageSize;
	private int pageNum;
	private PageResult pageResult;
	
	public static int default_pageSize=3;
	//String account;
			
	//列表页面
	public String listUI() throws Exception{
		
		QueryHelper queryHelper=new QueryHelper(User.class,"u");
		
     if(user!=null){
			
			if(StringUtils.isNotBlank(user.getName())){
				//解码
				user.setName(URLDecoder.decode(user.getName(),"utf-8"));	
				//获取查询语句
				queryHelper.addCondition("u.name like ?" , "%"+user.getName()+"%");				
						
			}			
		}
		
		try {
			pageResult=userService.getPageResult(queryHelper,default_pageSize,getPageNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return "listUI";
	}
	
	//跳转到新增页面
	public String addUI(){
		strTitle=user.getName();
		ActionContext.getContext().getContextMap().put("roleList",roleService.findObjects());
		return "addUI";
	}
	
	//保存新增
	public String add(){
		try {
			if(user!=null){
				if(headImg!=null){
					//保存路径
					String filepath=ServletActionContext.getServletContext().getRealPath("/fileUpload/user");
					//保存文件名
					String fileName=UUID.randomUUID().toString().replace("-","")+headImgFileName.substring(headImgFileName.lastIndexOf("."));
					
						FileUtils.copyFile(headImg, new File(filepath,fileName));
						user.setHeadImg("user/" + fileName);
				   } 
				
				userService.saveUserRole(user,userRoleIds);
				}
			}catch (IOException e) {				
				e.printStackTrace();
			}
		

		return "list";
	}
	
	//跳转到编辑页面
	public String editUI(){		
		
		ActionContext.getContext().getContextMap().put("roleList",roleService.findObjects());
		
		if (user != null && user.getId() != null) {
			strTitle=user.getName();
			user = userService.findObjectById(user.getId());
			
			//回显用户对应的角色
		 List<UserRole> list=userService.findUserRoleIdByUserId(user.getId());
		      userRoleIds =new String[list.size()];
			for(int i=0;i<list.size();i++){
				userRoleIds[i]=list.get(i).getUserRoleIds().getRole().getRoleId();
				System.out.println(userRoleIds[i]);			
			}
			
		}		
		return "editUI";
	}
	
	//保存编辑
	public String edit(){	
		
		try {
			if(user!=null){
				if(headImg!=null){
					//保存路径
					String filepath=ServletActionContext.getServletContext().getRealPath("/fileUpload/user");
					//保存文件名
					String fileName=UUID.randomUUID().toString().replace("-","")+headImgFileName.substring(headImgFileName.lastIndexOf("."));
					
						FileUtils.copyFile(headImg, new File(filepath,fileName));
						user.setHeadImg("user/" + fileName);
				   } 
				
				
				userService.updateUserRole(user,userRoleIds);
				}
			}catch (IOException e) {				
				e.printStackTrace();
			}

		return "list";
	}
	
	//删除
	public String delete(){	
		strTitle=user.getName();
		userService.delete(user.getId());
		return "list";
	}
	
	//批量删除
	public String deleteSelected(){
		strTitle=user.getName();
		if(selectedRow!=null){
			for(String id:selectedRow  ){
				userService.delete(id);				
			}						
		}
			
		return "list";
	}
	
	//导出文件列别
		@Test
		public void exportExcel(){
			try {			
				//System.out.println("----------------haha------------");
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("application/msexcel");
				
				response.setHeader("Content-Disposition", "attachment;filename="+ new String("用户列表.excel".getBytes(), "ISO-8859-1"));
				ServletOutputStream outputStream= response.getOutputStream();
				List<User> userList=userService.findObjects();
				userService.exportExcel(userList, outputStream);
				if(outputStream!=null){
					outputStream.close();				
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}		
		}
	//导入文件列表
		public void importExcel(){
			
				if(userExcel!=null){
					
					userService.importExcel(userExcel, userExcelFileName);
					}			
		}
		
	
	//唯一性校验
		public void vertifyAccount(){		
			if(user!=null){			
				//String account=user.getAccount();
				List<User> list=userService.findUserByAccountAndId(user.getId(), user.getAccount());
				String msg="true";
					if(list!=null){
						msg="false";			
					}
					
				HttpServletResponse response=ServletActionContext.getResponse();
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter pw;
				try {
					pw = response.getWriter();
					pw.write("msg");
					pw.close();
				} catch (IOException e) {
				
					e.printStackTrace();
				}
				
			}
			

		}
	

	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public File getHeadImg() {
		return headImg;
	}
	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}
	public String getHeadImgFileName() {
		return headImgFileName;
	}
	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}
	public String getHeadImgContentType() {
		return headImgContentType;
	}
	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}
	public String getUserExcelFileName() {
		return userExcelFileName;
	}
	public void setUserExcelFileName(String userExcelFileName) {
		this.userExcelFileName = userExcelFileName;
	}
	public File getUserExcel() {
		return userExcel;
	}
	public void setUserExcel(File userExcel) {
		this.userExcel = userExcel;
	}
	public String getUserExcelContentType() {
		return userExcelContentType;
	}
	public void setUserExcelContentType(String userExcelContentType) {
		this.userExcelContentType = userExcelContentType;
	}
	/**
	 * @return the selectedRow
	 */
	public String[] getSelectedRow() {
		return selectedRow;
	}

	/**
	 * @param selectedRow the selectedRow to set
	 */
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}

	public String[] getUserRoleIds() {
		return userRoleIds;
	}

	public void setUserRoleIds(String[] userRoleIds) {
		this.userRoleIds = userRoleIds;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public PageResult getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}

	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

	
}
