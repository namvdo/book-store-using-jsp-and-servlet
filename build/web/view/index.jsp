<%-- 
    Document   : index
    Created on : Jul 9, 2020, 1:38:53 PM
    Author     : Nam
--%>

<%@page import="db.BookDAO"%>
<%@page import="bean.BookBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../template/header.jsp" %>

<%@ page import="java.util.*, java.text.NumberFormat" %>

<head>
    <title>namBookStore!</title>
</head>
<form class='form form-button col-md-12' action='${pageContext.request.contextPath}/SortByPriceController' style="float:left">
    <h4>Sort by:</h4>
  <input class="form-check-input" type="radio" name="sortOrder" id="exampleRadios1" value="high" required <%=request.getParameter("sortOrder") != null && request.getParameter("sortOrder").equals("high")? "checked" : "" %>>
  <label class="form-check-label" for="exampleRadios1">
    Highest Price
  </label>
  <input class="form-check-input" type="radio" name="sortOrder" id="exampleRadios2" value="low" required <%=request.getParameter("sortOrder") != null && request.getParameter("sortOrder").equals("low")? "checked" : "" %>>
  <label class="form-check-label" for="exampleRadios2">
    Lowest Price
  </label>
    <input class="form-check-input" type="radio" name="sortOrder" id="exampleRadios3" value="view" required <%=request.getParameter("sortOrder") != null && request.getParameter("sortOrder").equals("view")? "checked" : "" %>>

  <label class="form-check-label" for="exampleRadios3">
    Number of views
  </label>
<!--    <select name="range" id="">
                <option value="volvo">5-8</option>
                <option value="saab">9-11</option>
                <option value="mercedes">12-15</option>
    </select>-->
    <button type="submit" class="btn btn-info">Sort</button>
    <div class="brand"><p>namBookStore</p></div>
    
</form>

<style>
   
    .col-md-12 {
        display: inline-block;
        text-align: left;
    }
    .brand {
        display: inline-block;
        font-size:2em;
        padding-left: 3em;
        font-family: monospace;
        font-weight: bold;
    }
    .brand p {
        text-align: center;
    }
    </style>

<%    NumberFormat nf = NumberFormat.getCurrencyInstance();
    BookDAO bk = new BookDAO();
    List<BookBean> books = bk.selectAllBooks();
    
    if (request.getAttribute("sortByPrice") != null) {
        books = (List<BookBean>) request.getAttribute("sortByPrice");
        
    }
    
%>

<div class="row d-flex justify-content-center">

    <%            for (BookBean book : books) {
    %>
    <div class="col-md-6" style="padding: 5px;">
        <div style="margin:3px; padding:10px; background-color: #eee;">
            <div class="row">
                <div class="col-md-4">
                    <a href='${pageContext.request.contextPath}/BookLookUpController?isbn=<%= book.getIsbn()%>' style="max-height: 130px; max-width: 110px;">
                        <img src="<%= book.getCoverImageFile()%>" alt="<%=book.getTitle()%> cover" style="max-width: inherit; max-height: inherit">
                    </a>
                </div>
                <div class="col-md-8" style="text-align: left; padding-left:10px;">
                    <h4><%=book.getTitle()%></h4><i>by <%=book.getAuthors()%></i>
                    <h5><%=nf.format(book.getPrice())%></h5>
                    <% if (request.getParameter("sortOrder") != null && request.getParameter("sortOrder").equals("view")) {
                        
                        out.println("<h5>Number of views: " + book.getNumOfView() + "</h5>");
                    }
                    %>
                    <a href='${pageContext.request.contextPath}/BookLookUpController?isbn=<%= book.getIsbn()%>' style="max-height: 130px; max-width: 110px;"><p class="text-white"> <p class="text-light"><button class="btn btn-primary">View the book</button></p>
                    </a>
                </div>
            </div>
        </div>
    </div>
                    
    <%
        }
    %>
</div>




<%@ include file="../template/footer.jsp" %>