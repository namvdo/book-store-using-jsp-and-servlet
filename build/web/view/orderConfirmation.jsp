<%-- 
    Document   : orderConfirmation
    Created on : Jul 15, 2020, 11:16:59 PM
    Author     : Nam
--%>

<%@include file="../template/header.jsp" %>

<div class="jumbotron">
	<h1>Order Confirmation</h1>
	<p>Congratulations, your order has been placed. The confirmation number for your order is:</p>
	<p><strong><%= request.getAttribute("confirmId") %></strong>
</div>

<%@include file="../template/footer.jsp" %>
