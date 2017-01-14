package itcast.nsfw.role.dao;

import itcast.nsfw.role.entity.Role;
import itcast.core.dao.BaseDao;

public interface RoleDao extends BaseDao<Role> {

	void deleteRolePrivilegeByRoleId(String roleId);

          
}
