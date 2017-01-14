package itcast.nsfw.user.dao.impl;

import java.io.Serializable;
import java.util.List;

import itcast.nsfw.user.dao.UserDao;
import itcast.nsfw.user.entity.UserRole;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import itcast.core.dao.impl.BaseDaoImpl;
import itcast.nsfw.user.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public List<User> findUserByAccountAndId(String id, String account) {

		String hql = "FROM User WHERE account = ?";
		if(StringUtils.isNotBlank(id)){
			hql += " AND id!=?";
		}
		Query query = getSession().createQuery(hql);
		query.setParameter(0, account);
		if(StringUtils.isNotBlank(id)){
			query.setParameter(1, id);
		}
		
		return query.list();
	}

	@Override
	public void saveUserRoleIds(UserRole userRole) {
         getHibernateTemplate().save(userRole);
		
	}

	@Override
	public void deleteUserRoleIdByUserId(Serializable id) {
		Query query = getSession().createQuery("delete from UserRole where userRoleIds.userId=?");
		query.setParameter(0, id);
		query.executeUpdate();
		
	}

	@Override
	public List<UserRole> findUserRoleIdByUserId(String id) {
		Query query = getSession().createQuery("from UserRole where userRoleIds.userId=?");
		query.setParameter(0, id);
		return query.list();
	}

	@Override
	public List findUserByAccountAndPassword(String account, String password) {
		Query query = getSession().createQuery("from User where account=? and password=?");
		query.setParameter(0, account);
		query.setParameter(1, password);
		return query.list();
	}








}
