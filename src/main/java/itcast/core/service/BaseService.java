package itcast.core.service;

import java.io.Serializable;
import java.util.List;

import itcast.core.util.PageResult;
import itcast.core.util.QueryHelper;


public interface BaseService<T> {
	public void save(T entity);
	//更新
	public void update(T entity);
	//根据id删除O
	public void delete(Serializable id);
	//根据id查找
	public T findObjectById(Serializable id);
	//查找列表
	public List<T> findObjects() ;
	public List<T> findObjects(QueryHelper queryHelper);
	
	public PageResult getPageResult(QueryHelper queryHelper, int pageSize, int pageNum);
}
