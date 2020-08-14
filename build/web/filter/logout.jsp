<%-- 
    Document   : logout
    Created on : Jul 9, 2020, 6:16:11 PM
    Author     : Nam
--%>
<%
session.removeAttribute("user");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../template/header.jsp" %>
<%session.removeAttribute("user");
  session.removeAttribute("cart");
%>
<h1>Successfully logged out</h1>
<a href="../view/index.jsp">Home</a>

<%@ include file="../template/footer.jsp" %>
