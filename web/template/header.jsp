<%-- 
    Document   : header
    Created on : Jul 8, 2020, 11:12:22 PM
    Author     : Nam
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="bean.GenreBean"%>
<%@page import="db.GenreDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>



<link rel="shortcut icon" href="./favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href='${pageContext.request.contextPath}/view/index.jsp'>namBookStore</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <form class="navbar-form navbar-left" action="${pageContext.request.contextPath}/BookSearchController" method="get">
                <input type="text" class="form-control" name="search-query" id="search-query" size="40" placeholder="ISBN, Title, Author, Keyword...">
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Browse<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="${pageContext.request.contextPath}/BookSearchController?search-query=">All</a></li>
                            <c:forEach var="row" items="${genres}">
                            <li><a href="${pageContext.request.contextPath}/BookSearchController?search-query=${row.genreName}">${row.genreName}</a></li>
                            </c:forEach>


                    </ul>
                </li>
                <%

                    String userLoggedIn = "";
                    if (session.getAttribute("user") != null) {
                        System.out.println("heahder: Fuck " + session.getAttribute("user"));

                        userLoggedIn = (String) session.getAttribute("user");
                %>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><%=userLoggedIn%> <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="${pageContext.request.contextPath}/CustomerPageController">Account</a></li>
                        <li class="divider"></li>
                        <!-- ask teacher about the path -->
                        <li><a href="${pageContext.request.contextPath}/filter/logout.jsp">Logout</a></li>

                    </ul>
                </li>
                <li><a href="${pageContext.request.contextPath}/view/checkout.jsp">Cart</a></li>

                <% if (session.getAttribute("user").equals("admin")) { %>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Admin<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="./generateReports">View Reports</a></li>
                        <li><a href="./AddBook.jsp">Add Book</a></li>
                        <li><a href="./UserLookup">View Users</a></li>
                    </ul>
                </li>

                <%
                    }
                %>
                <%
                } else {
                    // Not logged in, show login prompt
                %>
                <li><a href="${pageContext.request.contextPath}/filter/login.jsp">Sign in/Register</a></li> 
                    <%
                        }
                    %>


            </ul>
        </div>
    </div>
</nav>
<div style="padding: 70px 15px; text-align: center;">
    <div class="container">