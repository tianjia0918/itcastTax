package itcast.core.dao.impl;
import itcast.core.dao.BaseDao;
import itcast.core.util.PageResult;
import itcast.core.util.QueryHelper;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	Class<T> clazz;
	
	public BaseDaoImpl(){
		ParameterizedType pt =  (ParameterizedType)this.getClass().getGenericSuperclass();//BaseDaoImpl<User>
		clazz = (Class<T>)pt.getActualTypeArguments()[0];
	}

	@Override
	public void save(T entity) {
		
		getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		
		getHibernateTemplate().update(entity);
	}

	@Override
	public void delete(Serializable id) {
		getHibernateTemplate().delete(findObjectById(id));
	}

	@Override
	public T findObjectById(Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findObjects() {
		Query query = getSession().createQuery("FROM " + clazz.getSimpleName());
		return query.list();
	}

	@Override
	public List<T> findObjects(QueryHelper queryHelper) {
		Query listQuery = getSession().createQuery(queryHelper.getListQueryHql());
		List<Object> parameters=queryHelper.getParameters();
		if(parameters!=null){
			for(int i=0; i<parameters.size();i++){
				listQuery.setParameter(i, parameters.get(i));
		    }			
	     }
		
		return listQuery.list();
	}
	
	@Override
	public PageResult getPageResult(QueryHelper queryHelper, int pageSize, int pageNum) {
		Query listQuery = getSession().createQuery(queryHelper.getListQueryHql());
		List<Object> parameters=queryHelper.getParameters();
		if(parameters!=null){
			for(int i=0; i<parameters.size();i++){
				listQuery.setParameter(i, parameters.get(i));
		    }			
	     }
		if(pageNum==0){
			pageNum=1;
		}
		//设置记录起始行索引号
		listQuery.setFirstResult((pageNum-1)*pageSize);
		//设置本次查询出来的记录数的个数
		listQuery.setMaxResults(pageSize);
		List ResultList=listQuery.list();
		System.out.println(ResultList.size());
		
		Query countQuery = getSession().createQuery(queryHelper.getCountSql());
		if(parameters!=null){
			for(int i=0; i<parameters.size();i++){
				countQuery.setParameter(i, parameters.get(i));
		    }			
	     }
		
		Long totalCount=(Long) countQuery.uniqueResult();
		System.out.println(totalCount);
		
		return new PageResult(pageNum, pageSize, totalCount, ResultList);
	}
	
	
}
