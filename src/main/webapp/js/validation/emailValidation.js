 function validationEmail(){
		      var $email= $("#email").val();		  
		      var message = "";
		      var myreg = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/; 
		      if($email ==''){
		        message = "邮箱不能为空！";
		        alert(message);
		        $("#email").focus();
		      }else if(!myreg.test($email)){
		       message = "请输入有效的邮箱地址！";
		      alert(message);
		       $("#email").focus();
		      }else if(checkEmailIsExist()){
		        message = "该邮箱地址已经被注册！";
		        alert(message); 
		        $("#email").focus();
		      }
	  }	
 