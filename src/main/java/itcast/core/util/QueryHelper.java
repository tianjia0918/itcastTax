package itcast.core.util;

import java.util.ArrayList;
import java.util.List;

public class QueryHelper {
	//from 子句
    private String fromClause="";
  //where 子句
    private String whereClause="";
  //order by 子句
    private String orderByClause="";
    
    private String CountSql="";
    
    private List<Object> parameters;
    
	public static String ORDER_BY_ASC = "ASC";//升序
	public static String ORDER_BY_DESC = "DESC";//降序

    
    public QueryHelper(Class clazz,String alias){
    	fromClause="from "+clazz.getSimpleName()+" "+alias;   	
    }
    
    public QueryHelper addCondition(String condition,Object...params){
    	if(whereClause.length()>1){
    		whereClause =" and "+condition;  		
    	}else{
    		whereClause =" where "+condition; 
    	}
    	
    	if(parameters==null){
    		parameters=new ArrayList<Object>();   		
    	}
    	
    	for(Object param:params){
    		parameters.add(param);
    	}
		return this;
    	    	
    }
    
    public QueryHelper addOrderByProperty(String property, String order){
    	if(orderByClause.length()>1){
    		orderByClause =" , "+ property+" "+order;  		
    	}else{
    		orderByClause =" ORDER BY "+ property+" "+order; 
    	}
    	
    	
		return this;
    	    	
    }
    
  //返回列表查询hql语句
  	public String getListQueryHql(){
  		return fromClause + whereClause + orderByClause;
  	}
  	
  	public List<Object> getParameters() {
  		
		return parameters;
	}



	public String getCountSql() {
		
		CountSql="select count(*)"+fromClause+whereClause;
		
		return CountSql;
		
		
	}


	
}
