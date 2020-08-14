<%-- 
    Document   : signUpConfirmation
    Created on : Jul 8, 2020, 11:31:05 PM
    Author     : Nam
--%>

<%@page import="bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../template/header.jsp" %>
<%@ page import="db.UserDAO" %>


<%
	
	String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("password-repeat");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        int phone = Integer.parseInt(request.getParameter("phone"));
        System.out.println(phone);
        boolean isStaff = request.getParameter("isStaff") != null ? true : false;
        
        UserBean user = new UserBean(username, password, email, phone, firstname, lastname, isStaff);
        boolean result = new UserDAO().registerUser(user) && password.equals(passwordRepeat);
        if (result) {
            out.println("Register successfully");
            out.println("Go back to the log in page <a href='./login.jsp'>here.</a>");
        } else {
            if (!password.equals(passwordRepeat)){
                out.println("Register failed. The repeat password doesn't match. </br>");
                out.println("Click <a href='./registrationForm.jsp'>here</a> to go back the registering page.");
            } else {
            out.println("Register failed. This username is already existed. </br>");
            out.println("Click <a href='./registrationForm.jsp'>here</a> to go back the registering page.");
            }
        }
%>

<%@ include file="../template/footer.jsp" %>