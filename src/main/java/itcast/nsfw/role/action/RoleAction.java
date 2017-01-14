package itcast.nsfw.role.action;

import com.opensymphony.xwork2.ActionContext;
import itcast.core.Constant.Constant;
import itcast.core.action.BaseAction;
import itcast.core.util.PageResult;
import itcast.core.util.QueryHelper;
import itcast.nsfw.role.entity.Privilege;
import itcast.nsfw.role.entity.Role;
import itcast.nsfw.role.entity.RolePrivilege;
import itcast.nsfw.role.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleAction extends BaseAction {
	
	@Resource
	private RoleService roleService;
	private List<Role> roleList;
	private Role role;
	protected String[] selectedRow;
	public String[] rolePrivilege;
	private String strTitle;
	
	private int pageSize;
	private int pageNum;
	private PageResult pageResult;
	
	public static int default_pageSize=3;
		
	//列表页面
	public String listUI() throws Exception{
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);

        QueryHelper queryHelper=new QueryHelper(Role.class,"r");
		
	    
		//String sql="from Info i";
		//List<Object> parameters =new ArrayList<Object>();
		if(role!=null){
			
			if(StringUtils.isNotBlank(role.getName())){
				//获取查询语句
				System.out.println("==============解码前title===="+role.getName()+"=========================");
				role.setName(URLDecoder.decode(role.getName(),"utf-8"));
				queryHelper.addCondition("r.name like ?" , "%"+role.getName()+"%");							
				System.out.println("==============解码后title===="+role.getName()+"=========================");				
			}			
		}
		
		try {
		
			pageResult = roleService.getPageResult(queryHelper,default_pageSize,getPageNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			roleList = roleService.findObjects();
		
		   return "listUI";
	}
	


	@Test
	//保存新增
	public String add(){
		if(rolePrivilege!=null){
			 HashSet<RolePrivilege> set=new HashSet<RolePrivilege>();
			for(int i=0;i<rolePrivilege.length;i++){
				set.add(new RolePrivilege(new Privilege(rolePrivilege[i],role)));
			}
			role.setRolePrivilege(set);
		}
				
		if(role!=null){		
		roleService.save(role);
		}		
		return "list";
	}
	
	//跳转到编辑页面
	public String editUI(){	
		System.out.println("--------------editUI-------------------");
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		//权限回显		
	
		if (role != null && role.getRoleId() != null) {
			strTitle=role.getName();
			role = roleService.findObjectById(role.getRoleId());
			if(role.getRolePrivilege()!=null){				
			   int length=role.getRolePrivilege().size();
				 rolePrivilege= new String[length];
			     Set<RolePrivilege> rolePrivilege1=role.getRolePrivilege();
			     int i=0;
				for(RolePrivilege rp: rolePrivilege1 ){
					rolePrivilege[i++]=rp.getPrivilege().getCode();
					
				}
				
			
			}
		}
		
		return "editUI";
	}
	
	//跳转到新增页面
	public String addUI(){
		strTitle=role.getName();
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		
		return "addUI";
	}
	
	//保存编辑
	public String edit(){	
		
		if(rolePrivilege!=null){
			 HashSet<RolePrivilege> set=new HashSet<RolePrivilege>();
			for(int i=0;i<rolePrivilege.length;i++){
				set.add(new RolePrivilege(new Privilege(rolePrivilege[i],role)));			   
			}
			role.setRolePrivilege(set);
		}
		
		try {				
			roleService.update(role);			
			}catch (Exception e) {				
				e.printStackTrace();
			}

		return "list";
	}
	
	//删除
	public String delete(){	
		strTitle=role.getName();
		if(role!=null){
		roleService.delete(role.getRoleId());
	
		}
		return "list";
	}
	
	//批量删除
	public String deleteSelected(){
		strTitle=role.getName();
		if(selectedRow!=null){
			for(String id:selectedRow  ){
				roleService.delete(id);				
			}						
		}
			
		return "list";
	}
	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	public RoleService getRoleService() {
		return roleService;
	}


	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}


	public List<Role> getRoleList() {
		return roleList;
	}


	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}


	public String[] getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}


	public String[] getRolePrivilege() {
		return rolePrivilege;
	}

	
	public void setRolePrivilege(String[] rolePrivilege) {
		this.rolePrivilege = rolePrivilege;
	}

	public PageResult getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
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



	public String getStrTitle() {
		return strTitle;
	}



	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}






}
