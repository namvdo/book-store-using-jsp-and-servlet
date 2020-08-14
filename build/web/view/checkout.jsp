<%-- 
    Document   : checkout
    Created on : Jul 14, 2020, 4:52:23 PM
    Author     : Nam
--%>


<%@page import="bean.CartBean"%>
<%@page import="bean.LineItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../template/header.jsp" %>
<%-- if there is already a Cart bean created inside the session scope, this will not ininitiate a new object --%>
<jsp:useBean id="cart" scope="session" class="bean.CartBean"></jsp:useBean>
    <h2>Book Store Checkout Page</h2>
    
<c:if test="${not empty cart.items}">
    <table class="table table-striped">
        <thead>
        <th>Quantity</th>
        <th>Title</th>
        <th>Price</th>
        <th>Total Price</th>
        <th></th>
    </thead>
    <tbody>
        <c:forEach var="item" items="${cart.items}">
        <form action="CartUpdateController">
            <tr style="text-align: left">
                <td>
                    <input type="hidden" name="isbn" value="${item.isbn}">
                    <input type="text" name="updateQuantity" size="2" value="${item.quantity}">

                           <input type="submit" value="Update">    
                </td>
                
                <td>${item.title}</td>
                <td><fmt:formatNumber value="${item.price}" type="currency"></fmt:formatNumber></td>
                <td><fmt:formatNumber value="${item.totalPrice}" type="currency"></fmt:formatNumber></td>
                <td><input type="submit" name="remove" value="Remove"></td>
            </tr>
        </form>
                
    </c:forEach>
    
</tbody>
</table>
    
    
    <h3>Total price of all items: <fmt:formatNumber value="${cart.items.stream().map(i -> i.totalPrice).sum()}" type="currency"></fmt:formatNumber></h3>
    <form class="form-inline"  action="${pageContext.request.contextPath}/SubmitOrderController">
        <input type="submit" class="btn btn-sucess" value="Checkout"></input>
        <button class="btn btn-sucess"><a href="${pageContext.request.contextPath}/view/index.jsp">Continue Shopping</a></button>

    </form>
    
</c:if>
<c:if test="${empty cart.items}">
    <h4>Sorry, you haven't placed any order yet. Back to the home page to browse more books.</h4>
</c:if>
<%@include file="../template/footer.jsp" %>

