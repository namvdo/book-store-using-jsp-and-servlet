<%-- 
    Document   : notEnoughQuantity
    Created on : Jul 20, 2020, 9:03:21 PM
    Author     : Nam
--%>

<%@page import="db.BookDAO"%>
<%@page import="bean.BookBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../template/header.jsp" %>

<%@ page import="java.util.*, java.text.NumberFormat" %>

<div class="jumbotron">
	<h1>Sorry, we don't want to inform you that, but...</h1>
	<p>We don't have enough inventory to serve your order.</p>
	
</div>

<%@ include file="../template/footer.jsp" %>
