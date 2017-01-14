<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
        <%@include file="/common/header.jsp" %>
  </head>
  
  <body>
  
     <div class="c_pate" style="margin-top: 5px;">
        
		  <s:if test="PageResult.resultList.size>0">
				<table width="100%" class="pageDown" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td align="right">
		                 	总共<s:property value='PageResult.totalCount'/>条记录，当前第<s:property value='PageResult.pageNum'/>页，共 <s:property value='PageResult.totalPageNum'/>页 &nbsp;&nbsp;
		                 	   <s:if test="PageResult.pageNum>1">
		                            <a href="javascript:doGetPage(<s:property value='PageResult.pageNum-1'/>)">上一页</a>
		                        </s:if>
		                        
		                            &nbsp;&nbsp;
		                          <s:if test="PageResult.pageNum<PageResult.totalPageNum">  
		                            <a href="javascript:doGetPage(<s:property value='PageResult.pageNum+1'/>)">下一页</a>
		                          </s:if>  
							到&nbsp;<input id="pageNum" name="pageNum" type="text" style="width: 30px;" onkeypress="if(event.keyCode == 13){doGoPage(this.value);}" min="1"
							max="" value=" <s:property value='PageResult.pageNum'/>" /> &nbsp;&nbsp;
					    </td>
					</tr>
				</table>
			</s:if>
		    <s:else> 暂无数据</s:else>   
        </div>
        <script type="text/javascript">
            function doGetPage(pageNum){
              document.getElementById("pageNum").value=pageNum;
              document.forms[0].action = path;
  		      document.forms[0].submit();
            }
        
        </script>
   
  </body>
</html>
