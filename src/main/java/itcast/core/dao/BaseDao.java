package itcast.core.dao;

import java.io.Serializable;
import java.util.List;

import itcast.core.util.PageResult;
import itcast.core.util.QueryHelper;

public interface BaseDao<T> {
	
	//新增
	public void save(T entity);
	//更新
	public void update(T entity);
	//根据id删除
	public void delete(Serializable id);
	//根据id查找
	public T findObjectById(Serializable id);
	//查找列表
	public List<T> findObjects();
 /*  //根据条件查询
	public List<T> findObjects(String sql, List<Object> parameters);*/
	
	public List<T> findObjects(QueryHelper queryHelper);
	PageResult getPageResult(QueryHelper queryHelper, int pageSize, int pageNum);
}
