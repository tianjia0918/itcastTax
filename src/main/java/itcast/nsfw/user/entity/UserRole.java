package itcast.nsfw.user.entity;

import java.io.Serializable;

public class UserRole implements Serializable {
      private UserRoleIds userRoleIds;
      
      

	public UserRole() {
	}

	public UserRole(UserRoleIds userRoleIds) {
		this.userRoleIds = userRoleIds;
	}

	public UserRoleIds getUserRoleIds() {
		return userRoleIds;
	}

	public void setUserRoleIds(UserRoleIds userRoleIds) {
		this.userRoleIds = userRoleIds;
	}
      
      
      
}
