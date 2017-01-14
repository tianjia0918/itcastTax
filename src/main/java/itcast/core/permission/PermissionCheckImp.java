package itcast.core.permission;


import itcast.nsfw.role.entity.RolePrivilege;
import itcast.nsfw.user.entity.UserRole;
import itcast.nsfw.user.service.UserService;
import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

public class PermissionCheckImp implements PermissionCheck {
@Resource
private UserService userService;
	@Override
	public Boolean isAccessible(itcast.nsfw.user.entity.User user, String module) {
		List<UserRole> userRoles=userService.findUserRoleIdByUserId(user.getId());
		for(UserRole ur:userRoles){
			Set<RolePrivilege> rolePrivileges=ur.getUserRoleIds().getRole().getRolePrivilege();
			if(rolePrivileges!=null){
				for(RolePrivilege rp: rolePrivileges ){
					if(module.equals(rp.getPrivilege().getCode())){
						
						return true;	
					}
				}
			 }
		}
		return false;
	}

}
