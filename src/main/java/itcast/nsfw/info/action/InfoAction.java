package itcast.nsfw.info.action;

import com.opensymphony.xwork2.ActionContext;
import itcast.core.action.BaseAction;
import itcast.core.util.PageResult;
import itcast.core.util.QueryHelper;
import itcast.nsfw.info.entity.Info;
import itcast.nsfw.info.service.InfoService;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.sql.Timestamp;

public class InfoAction extends BaseAction{
	@Resource
	private InfoService infoService;
	private Info info;
	protected String[] selectedRow;
	private String strTitle;
	private int pageSize;
	private int pageNum;
	private PageResult pageResult;
	
	public static int default_pageSize=3;
		
	//列表页面
	public String listUI() throws Exception{
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.Info_type_map);
		
		QueryHelper queryHelper=new QueryHelper(Info.class,"i");
		
	    
		//String sql="from Info i";
		//List<Object> parameters =new ArrayList<Object>();
		if(info!=null){
			
			if(StringUtils.isNotBlank(info.getTitle())){
				//获取查询语句
				System.out.println("==============解码前title===="+info.getTitle()+"=========================");
				info.setTitle(URLDecoder.decode(info.getTitle(),"utf-8"));
				queryHelper.addCondition("i.title like ?" , "%"+info.getTitle()+"%");				
				
				System.out.println("==============解码后title===="+info.getTitle()+"=========================");				
			}			
		}
		
		try {
		
			pageResult = infoService.getPageResult(queryHelper,default_pageSize,getPageNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		  return "listUI";
	}
	
	//跳转到新增页面
	public String addUI(){
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.Info_type_map);
		Timestamp createTime = new Timestamp(System.currentTimeMillis()); 
		
		strTitle=info.getTitle();
		info.setCreateTime(createTime);
		
		return "addUI";
	}
	
	@Test
	//保存新增
	public String add(){
				
		if(info!=null){		
			infoService.save(info);
		}		
		return "list";
	}
	
	//跳转到编辑页面
	public String editUI(){	
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.Info_type_map);
	
		if (info != null && info.getInfoId() != null) {
		    strTitle=info.getTitle();
			info = infoService.findObjectById(info.getInfoId());
			//info.setCreateTime(createTime);
			
		}		
		
		return "editUI";
	}
	

	
	//保存编辑
	public String edit(){			
		try {				
			if(info != null){
			
				infoService.update(info);
			}			
			}catch (Exception e) {				
				e.printStackTrace();
			}

		return "list";
	}
	
	//删除
	public String delete(){		
		    strTitle=info.getTitle();
			infoService.delete(info.getInfoId());
		return "list";
	}
	
	//批量删除
	public String deleteSelected(){
		strTitle=info.getTitle();
		if(selectedRow!=null){
			for(String id:selectedRow  ){
				infoService.delete(id);				
			}						
		}
			
		return "list";
	}
	
    //异步更新
	public void publicInfo(){
		try {
		  if(info!=null){
				//1、更新信息状态
				Info tem = infoService.findObjectById(info.getInfoId());
				tem.setState(info.getState());
				infoService.update(tem);
								
				//2、输出更新结果
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream;			
				outputStream = response.getOutputStream();
				outputStream.write("更新状态成功".getBytes("utf-8"));
				outputStream.close();
		    }	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


	public Info getInfo() {
		return info;
	}


	public void setInfo(Info info) {
		this.info = info;
	}


	public InfoService getInfoService() {
		return infoService;
	}


	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
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

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public PageResult getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}





}


