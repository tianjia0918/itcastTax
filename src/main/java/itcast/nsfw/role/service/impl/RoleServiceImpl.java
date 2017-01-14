package itcast.nsfw.role.service.impl;

import javax.annotation.Resource;

import itcast.core.service.impl.BaseServiceImp;
import itcast.nsfw.role.entity.Role;
import itcast.nsfw.role.service.RoleService;
import org.springframework.stereotype.Service;

import itcast.nsfw.role.dao.RoleDao;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImp<Role> implements RoleService {
	
	private RoleDao roleDao;
	@Resource
	public void setRoleDao(RoleDao roleDao) {
		super.setBaseDao(roleDao);
		this.roleDao = roleDao;
	} 

	@Override
	public void update(Role role) {
		roleDao.deleteRolePrivilegeByRoleId(role.getRoleId());
		roleDao.update(role);
		
	}

	


	
	

	public RoleDao getRoleDao() {
		return roleDao;
	}




}
