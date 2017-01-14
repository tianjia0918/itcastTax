package itcast.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import itcast.core.Constant.Constant;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import itcast.core.permission.PermissionCheck;
import itcast.nsfw.user.entity.User;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		HttpSession session=request.getSession();
		String uri=request.getRequestURI();

		String uriRequest=uri.replaceAll(request.getContextPath(), "");
		//是否是登陆请求
		if(uriRequest.length()>0&&!uriRequest.contains("sys/login")){
			//是否已经登陆
			if(session.getAttribute(Constant.user)!=null){
			    //是否有进入子系统的权限
				if(uriRequest.contains("/nsfw")){
					User user=(User) session.getAttribute("SYS_USER");
					//从spring容器中获取权限校验bean
					WebApplicationContext WebApplicationContext=WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
					PermissionCheck permissionCheck=(PermissionCheck) WebApplicationContext.getBean("permissionCheck");
					Boolean result=permissionCheck.isAccessible(user,"nsfw");
					if(result){
						filterChain.doFilter(request, response);
					}else{
						response.sendRedirect(request.getContextPath()+"/sys/login_noPermissionUI.action");
					}
					
				}else{
					filterChain.doFilter(request, response);
				}	
			
			}else{
				response.sendRedirect(request.getContextPath()+"/sys/login_toLoginUI.action");
			}
		}else{
			filterChain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
