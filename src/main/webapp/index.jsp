<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
response.sendRedirect(path+"/sys/login_toLoginUI.action");

%>

