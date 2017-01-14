<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("basePath", request.getContextPath()+"/") ;
%>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>我要投诉</title>
    <script type="text/javascript" charset="utf-8" src="${basePath }js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath }js/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${basePath }js/ueditor/lang/zh-cn/zh-cn.js"></script>

    <script>
   		window.UEDITOR_HOME_URL = "${basePath}js/ueditor/";
    	var ue = UE.getEditor('editor');
    	
    	//根据部门查询该部门下的用户列表
    	function doSelectDept(){
	        var dept=$("#toCompDept option:selected").val();
	        if(dept!=""){
	           $.ajax({
	              url:"${basePath}sys/home_getUserJson.action",
	              data:{"dept":dept},
	              type:"post",
	              dataType:"json",
	              success:function(data){
	                 if(data!=""&&data!=undefined){
	                    if("success"==data.msg){
	                       var userlist=$("#toCompName");
	                       userlist.empty();
	                       $.each(data.userList,function(index,user){
	                          userlist.append("<option value='"+user.name+"'>"+user.name+"</option>");
	                       
	                       });                
	                    }else{
	                       alert("获取部门人员列表失败");
	                    }
	                 
	                 
	                 }else{
	                       alert("获取部门人员列表失败");
	                    }
	               },
	           });
	        }
	    
        }
    
	    function doSubmit(){
		    $.ajax({
			      url:"${basePath}sys/home_complainAdd.action",
			      data:$("#form").serialize(),
			      type:"post",
			      success:function (msg){  
			            if("success" == msg){  
			            //2.提示用户投诉成功  
			            alert("投诉成功!");  
			            //3.刷新父窗口  
			            window.opener.parent.location.reload(true);  
			            //4.关闭当前窗口  
			            window.close();  
			            }else{  
			                alert("投诉失败!");  
			            }  
			        }, 
		    });
            
	    }
    </script>
    
</head>
<body>
<form id="form" name="form" action="${basePath}sys/home_complainAdd.action" method="post" enctype="multipart/form-data">
    <div class="vp_d_1">
        <div style="width:1%;float:left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <div class="vp_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>工作主页</strong>&nbsp;-&nbsp;我要投诉</div></div>
    <div class="tableH2">我要投诉</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="250px">投诉标题：</td>
            <td><s:textfield name="comp.compTitle"/></td>
        </tr>
        <tr>
            <td class="tdBg">被投诉人部门：</td>
            <td><s:select id="toCompDept" name="comp.becompDept" list="#{'':'请选择','部门A':'部门A','部门B':'部门B'}" onchange="doSelectDept()"/></td>
        </tr>
        <tr>
            <td class="tdBg">被投诉人：</td>
            <td>
            	<select id="toCompName" name="comp.becompName">
            	  
            	</select>
            </td>
        </tr>
        
        <tr>
            <td class="tdBg">投诉详情：</td>
            <td><s:textarea id="editor" name="comp.compContent" cssStyle="width:90%;height:160px;" /></td>
        </tr>
        <tr>
            <td class="tdBg">是否匿名投诉：</td>
            <td><s:radio name="comp.compIsNm" list="#{'false':'非匿名投诉','true':'匿名投诉'}" value="true"/></td>
        </tr>
    
      	<s:hidden name="comp.compCompany" value="%{#session.SYS_USER.dept}"/>
	    <s:hidden name="comp.compName" value="%{#session.SYS_USER.name}"/>
	    <s:hidden name="comp.compPhone" value="%{#session.SYS_USER.mobile}"/>
       
       
    </table>

    <div class="tc mt20">
        <input type="button" class="btnB2" value="保存" onclick="doSubmit()" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:window.close()" class="btnB2" value="关闭" />
    </div>
    </div></div>
    <div style="width:1%;float:left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
    </div>
</form>
</body>
</html>