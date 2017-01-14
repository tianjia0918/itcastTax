package itcast.nsfw.user.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Controller;

import itcast.core.dao.BaseDao;
import itcast.nsfw.user.entity.User;
import itcast.nsfw.user.entity.UserRole;

public interface UserDao extends BaseDao<User> {

	/**
	 * 根据帐号和用户id查询用户
	 * @param id 用户ID
	 * @param account 用户帐号
	 * @return 用户列表
	 */
	//根据账户和id查找用户
	public List<User> findUserByAccountAndId(String id, String account);
    //保存用户的角色
	public void saveUserRoleIds(UserRole userRole);
   //根据用户id删除该用户对应的用户角色
	public void deleteUserRoleIdByUserId(Serializable id);
    //根据用户id查找用户对应的用户角色
	public List<UserRole> findUserRoleIdByUserId(String id);
	//根据账户和密码查找用户
	public List findUserByAccountAndPassword(String account, String password);




	




          
}
