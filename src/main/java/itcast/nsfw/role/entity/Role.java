package itcast.nsfw.role.entity;

import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable {
 private String roleId;
 private String state;
 private String name;
 private Set<RolePrivilege> rolePrivilege; 
	//用户状态
	public static String ROLE_STATE_VALID = "1";//有效
	public static String ROLE_STATE_INVALID = "0";//无效
	
 
public Role() {
}



public Role(String roleId) {
	super();
	this.roleId = roleId;
}



public Role(String roleId, String state, String name, Set<RolePrivilege> rolePrivilege) {
	super();
	this.roleId = roleId;
	this.state = state;
	this.name = name;
	this.rolePrivilege = rolePrivilege;
}

public String getRoleId() {
	return roleId;
}

public void setRoleId(String roleId) {
	this.roleId = roleId;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public Set<RolePrivilege> getRolePrivilege() {
	return rolePrivilege;
}

public void setRolePrivilege(Set<RolePrivilege> rolePrivilege) {
	this.rolePrivilege = rolePrivilege;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

 
   
   
}
