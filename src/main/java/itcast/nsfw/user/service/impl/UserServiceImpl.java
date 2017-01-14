package itcast.nsfw.user.service.impl;
import itcast.core.service.impl.BaseServiceImp;
import itcast.core.util.ExcelUtil;
import itcast.nsfw.role.entity.Role;
import itcast.nsfw.user.dao.UserDao;
import itcast.nsfw.user.entity.User;
import itcast.nsfw.user.entity.UserRole;
import itcast.nsfw.user.entity.UserRoleIds;
import itcast.nsfw.user.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Service("userService")
public class UserServiceImpl extends BaseServiceImp<User> implements UserService {
	
	
	private UserDao userDao;
	@Resource
	public void setUserDao(UserDao userDao) {
		super.setBaseDao(userDao);
		this.userDao = userDao;
	}

	
	@Override
	public void delete(Serializable id) {
		userDao.delete(id);
		 //清空本条数据role原数据
        userDao.deleteUserRoleIdByUserId(id);
	}

	@Override
	public void exportExcel(List<User> userList, ServletOutputStream outputStream) {
		ExcelUtil.exportUserExcel(userList, outputStream);
	}
	

	@Override
	public void importExcel(File userExcel, String userExcelFileName) {
		try {
			
			FileInputStream fileInputStream = new FileInputStream(userExcel);
			boolean is03Excel = userExcelFileName.matches("^.+\\.(?i)(xls)$");
			//1、读取工作簿
			Workbook workbook = is03Excel ? new HSSFWorkbook(fileInputStream):new XSSFWorkbook(fileInputStream);
			//2、读取工作表
			Sheet sheet = workbook.getSheetAt(0);
			//3、读取行
			if(sheet.getPhysicalNumberOfRows() > 2){
				User user = null;
				for(int k = 2; k < sheet.getPhysicalNumberOfRows(); k++){
					//4、读取单元格
					Row row = sheet.getRow(k);
					user = new User();
					//用户名
					Cell cell0 = row.getCell(0);
					user.setName(cell0.getStringCellValue());
					//帐号
					Cell cell1 = row.getCell(1);
					user.setAccount(cell1.getStringCellValue());
					//所属部门
					Cell cell2 = row.getCell(2);
					user.setDept(cell2.getStringCellValue());
					//性别
					Cell cell3 = row.getCell(3);
					user.setGender(cell3.getStringCellValue().equals("男"));
					//手机号
					String mobile = "";
					Cell cell4 = row.getCell(4);
					try {
						mobile = cell4.getStringCellValue();
					} catch (Exception e) {
						double dMobile = cell4.getNumericCellValue();
						mobile = BigDecimal.valueOf(dMobile).toString();
					}
					user.setMobile(mobile);
					
					//电子邮箱
					Cell cell5 = row.getCell(5);
					user.setEmail(cell5.getStringCellValue());
					//生日
					Cell cell6 = row.getCell(6);
					if(cell6.getDateCellValue() != null){
						user.setBirthday(cell6.getDateCellValue());
					}
					//默认用户密码为 123456
					user.setPassword("123456");
					//默认用户状态为 有效
					user.setState(User.USER_STATE_VALID);
					
					//5、保存用户
					save(user);
				}
			}
			workbook.close();
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> findUserByAccountAndId(String id, String account) {
		return userDao.findUserByAccountAndId(id, account);
	}

	@Override
	public void saveUserRole(User user, String[] userRoleIds) {
		//保存user
		userDao.save(user);
		//保存role
		if(userRoleIds!=null){
			for(String RoleId:userRoleIds){
				String userId=user.getId();
				UserRoleIds userRoleIds1=new UserRoleIds(new Role(RoleId),userId);
				UserRole userRole=new UserRole(userRoleIds1);
			    userDao.saveUserRoleIds(userRole);
			}
		}
		
		
	}

	@Override
	public void updateUserRole(User user, String[] userRoleIds) {
		        //清空本条数据role原数据
		        userDao.deleteUserRoleIdByUserId(user.getId());
		        
		        //更新user
				userDao.update(user);
				//更新role
				if(userRoleIds!=null){
					for(String RoleId:userRoleIds){
						String userId=user.getId();
						UserRoleIds userRoleIds1=new UserRoleIds(new Role(RoleId),userId);
						UserRole userRole=new UserRole(userRoleIds1);
					    userDao.saveUserRoleIds(userRole);
					}
				}
	}

	@Override
	public List<UserRole> findUserRoleIdByUserId(String id) {
		return userDao.findUserRoleIdByUserId(id);
		
	}

	@Override
	public List findUserByAccountAndPassword(String account, String password) {
		return userDao.findUserByAccountAndPassword(account,password);
		
	}
	
	

}
