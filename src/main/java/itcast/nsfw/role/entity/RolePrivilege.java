package itcast.nsfw.role.entity;

import java.io.Serializable;


public class RolePrivilege implements Serializable {
    private Privilege privilege;
   
	public RolePrivilege() {
	}

	public RolePrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}


	
	
	
    
}
