package itcast.core.util;

import java.util.List;

public class PageResult {
  
	//当前页码
    private int pageNum;
	 //根据分页查询结果
	private List ResultList;
	//每页中的数据个数
	private int pageSize;
	//总记录数
	private Long totalCount;
	  
	//总页数
	private int totalPageNum;

	public PageResult(int pageNum, int pageSize,
			Long totalCount, List resultList) {
		
		this.ResultList = resultList;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		if(totalCount>0){
			this.pageNum = pageNum;	
		int num=(int) (totalCount/pageSize);
		totalPageNum=totalCount%pageSize==0?num:(num+1);
		}else{
			this.pageNum=0;
			this.totalPageNum=0;			
		}
		
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public List getResultList() {
		return ResultList;
	}

	public void setResultList(List resultList) {
		ResultList = resultList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}
	
	
	
	
	
	
	
}
