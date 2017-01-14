package itcast.core.service.impl;

import java.io.Serializable;
import java.util.List;


import itcast.core.service.BaseService;
import itcast.core.util.PageResult;
import itcast.core.util.QueryHelper;
import itcast.core.dao.BaseDao;


public class BaseServiceImp<T> implements BaseService<T> {
	
     private BaseDao<T> baseDao;
 	 public void setBaseDao(BaseDao<T> dao) {
		this.baseDao = dao;
	}
     
 	@Override
	public void save(T entity) {
 		baseDao.save(entity);
		
	}

	@Override
	public void update(T entity) {
		baseDao.update(entity);
		
	}

	@Override
	public void delete(Serializable id) {
		baseDao.delete(id);
		
	}

	@Override
	public T findObjectById(Serializable id) {
		
		return baseDao.findObjectById(id);
	}

	@Override
	public List<T> findObjects() {
	
		return baseDao.findObjects();
	}
	
	@Override
	public List<T> findObjects(QueryHelper queryHelper) {
		
		return baseDao.findObjects(queryHelper);
	} 
 	
 	
 	
 	
 	
 	
	@Override
	public PageResult getPageResult(QueryHelper queryHelper, int pageSize,
									int pageNum) {

		return baseDao.getPageResult(queryHelper, pageSize, pageNum);
		
	}

	public BaseDao<T> getBaseDao() {
		return baseDao;
	}


}
