package itcast.nsfw.user.service;

import java.io.File;
import java.util.List;

import javax.servlet.ServletOutputStream;

import itcast.core.service.BaseService;
import itcast.nsfw.user.entity.User;
import itcast.nsfw.user.entity.UserRole;

public interface UserService extends BaseService<User> {

	
	//导出用户列表
    public void exportExcel(List<User> userList, ServletOutputStream outputStream);
	//导入用户列表
    public void importExcel(File userExcel, String userExcelFileName); 
	/**
	 * 根据帐号和用户id查询用户
	 * @param id 用户ID
	 * @param account 用户帐号
	 * @return 用户列表
	 */
	public List<User> findUserByAccountAndId(String id, String account);
	
	public void saveUserRole(User user, String[] userRoleIds);
	public void updateUserRole(User user, String[] userRoleIds);
	public List<UserRole> findUserRoleIdByUserId(String id);
	public List findUserByAccountAndPassword(String account, String password); 
          
}
