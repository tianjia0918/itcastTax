package itcast.nsfw.complain.action;

import com.opensymphony.xwork2.ActionContext;
import itcast.core.action.BaseAction;
import itcast.core.util.PageResult;
import itcast.core.util.QueryHelper;
import itcast.nsfw.complain.entity.Complain;
import itcast.nsfw.complain.service.compService;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class compAction extends BaseAction {
	
	@Resource
	private compService compService;
	protected String[] selectedRow;
	private String strTitle;
	private PageResult pageResult;
	private Complain complain;
	private int pageNum;
	private Map<String, Object> statisticMap;
	public static int default_pageSize=3;
		
	//列表页面
	public String listUI() throws Exception{
		ActionContext.getContext().getContextMap().put("complainStateMap", Complain.complain_state_map);
		
		QueryHelper queryHelper=new QueryHelper(Complain.class,"c");
		
	    
		//String sql="from Info i";
		//List<Object> parameters =new ArrayList<Object>();
		if(complain!=null){
			
			if(StringUtils.isNotBlank(complain.getCompTitle())){
				//获取查询语句
				System.out.println("==============解码前title===="+complain.getCompTitle()+"=========================");
				complain.setCompTitle(URLDecoder.decode(complain.getCompTitle(),"utf-8"));
				queryHelper.addCondition("c.title like ?" , "%"+complain.getCompTitle()+"%");				
						
			}			
		}
		
		try {
		
			pageResult = compService.getPageResult(queryHelper,default_pageSize,getPageNum());
			/*List<Complain> resultList=pageResult.getResultList();
			for(Complain complain:resultList){
				System.out.println(complain.getState());
				
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		   return "listUI";
	}
	
	
	
	//跳转到编辑页面
	public String editUI(){	
		ActionContext.getContext().getContextMap().put("complainStateMap",  Complain.complain_state_map);
			
	
		if (complain != null && complain.getCompId()!= null) {
		    strTitle=complain.getCompTitle();

			complain = compService.findObjectById(complain.getCompId());
		}		
		
		return "editUI";
	}
	

	
	//保存编辑
	public String edit(){			
		try {				
			if(complain != null){
			
				compService.update(complain);
			}			
			}catch (Exception e) {				
				e.printStackTrace();
			}

		return "list";
	}
	
	//删除
	public String delete(){		
		    strTitle=complain.getCompTitle();
			compService.delete(complain.getCompId());
		return "list";
	}
	
	//批量删除
	public String deleteSelected(){
		strTitle=complain.getCompTitle();
		if(selectedRow!=null){
			for(String id:selectedRow  ){
				compService.delete(id);				
			}						
		}
			
		return "list";
	}
	//跳转到统计页面
	public String annualStatisticChartUI(){
		
		return "annualStatisticChartUI";
	}
	
	//统计
	public String getAnnualStatisticData(){
		//1、获取年份
		HttpServletRequest request = ServletActionContext.getRequest();
		int year = 0;
		if(request.getParameter("year") != null){
			year = Integer.valueOf(request.getParameter("year"));
		} else {
			//默认 当前年份
			year = Calendar.getInstance().get(Calendar.YEAR);
		}
		//2、获取统计年度的每个月的投诉数
		statisticMap = new HashMap<String, Object>();
		statisticMap.put("msg", "success");
		statisticMap.put("chartData", compService.getAnnualStatisticDataByYear(year));
		
		return "annualStatisticData";
	}
	
    public String dealUI(){
    	
    	ActionContext.getContext().getContextMap().put("complainStateMap",  Complain.complain_state_map);
    	
    	if (complain != null && complain.getCompId()!= null) {
		   
			complain = compService.findObjectById(complain.getCompId());
		}	
		return "dealUI";
	}

	public compService getCompService() {
		return compService;
	}

	public void setCompService(compService compService) {
		this.compService = compService;
	}
	
	public String[] getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}

	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

	public PageResult getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}

	public Complain getComplain() {
		return complain;
	}

	public void setComplain(Complain complain) {
		this.complain = complain;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	




	
}
