package itcast.nsfw.role.dao.impl;


import itcast.nsfw.role.entity.Role;
import org.hibernate.Query;

import itcast.core.dao.impl.BaseDaoImpl;
import itcast.nsfw.role.dao.RoleDao;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Override
	public void deleteRolePrivilegeByRoleId(String roleId) {
	//	String sql="delete from role_roleprivilege where privilege.role.roleId=? ";
	
		Query query=getSession().createQuery("DELETE FROM RolePrivilege WHERE roleId=? ");
		query.setParameter(0, roleId);
		query.executeUpdate();
		
	}



}
