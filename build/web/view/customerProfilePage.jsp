<%-- 
    Document   : customerProfilePage
    Created on : Jul 21, 2020, 6:34:20 PM
    Author     : Nam
--%>

<%@page import="db.LineItemDAO"%>
<%@page import="db.UserDAO"%>
<%@page import="bean.UserBean"%>
<%@page import="bean.BookBean"%>
<%@page import="db.BookDAO"%>
<%@page import="db.ReviewDAO"%>
<%@page import="bean.ReviewBean"%>
<%@ include file="../template/header.jsp" %>
<%    String username = (String) session.getAttribute("user");
    if (username == null) {
        response.sendRedirect("/filter/registrationForm.jsp");
    }
%>

<sql:setDataSource var = "snapshot" driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
         url = "jdbc:sqlserver://localhost:1433;DatabaseName=BookStore"
         user = "sa"  password = "nam123"/>
<h1>Welcome, <%=username%>.</h1>
<div role="tabpanel">
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#orders" aria-controls="home" role="tab" data-toggle="tab">Orders</a></li>
        <li role="presentation"><a href="#account-info" aria-controls="profile" role="tab" data-toggle="tab">Account Info</a></li>
        <li role="presentation"><a href="#ratings" aria-controls="messages" role="tab" data-toggle="tab">Ratings</a></li>
    </ul>

    





    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="orders">
            <h2>Order History</h2>


            <c:forEach var="rowTransaction" items="${transactions}">
                <div class="panel panel-default" style="max-width: 600px; margin:20px auto 20px auto;">
                    <div class="panel-heading">
                        <h3 class="panel-title" style="text-align: left">Order number <strong>${rowTransaction.transactionId}</strong>, Date: ${rowTransaction.purchaseDate}</h3>
                    </div>
                    <div class="panel-body">

                        <table class="table" style="text-align: left">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th>Title</th>
                                    <th>ISBN</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <sql:query dataSource="${snapshot}" var="lineItem">
                                    select b.cover_img, b.isbn, b.price, b.title, t.total_price, i.quantity from Book b inner join TransactionItem i on b.isbn = i.isbn inner join Transactions t on i.transaction_id = t.transaction_id where username='<%=username%>' and t.transaction_id = ${rowTransaction.transactionId}
                                </sql:query>    
                                <c:forEach var="rowOrder" items="${lineItem.rows}">
                                    <tr>
                                        <td><a href="./BookLookup?isbn=${rowOrder.isbn}"><img src="${rowOrder.cover_img}" style="max-width:40px; text-decoration: none"></a></td>
                                        <td>${rowOrder.title}</td>
                                        <td>${rowOrder.isbn}</td>
                                        <td>${rowOrder.quantity}</td>
                                        <td>${rowOrder.price}</td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="4" style="text-align: right;">Total</td>
                                    <td>${rowTransaction.totalPrice}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div role="tabpanel" class="tab-pane" id="account-info" >
            <div class="container" style="max-width:90%; margin: 20px auto 0px auto">

                <%-- Query user information --%>

                <div class="panel panel-default" id="account-info-static">
                    <h2 style="padding:15px">Account Information for <%=username%></h2>
                    <table class="table" style="max-width: 500px; margin-left: auto; margin-right: auto; padding:20px">
                        <tr style="text-align: left"><td>First Name</td><td>${userInfo.firstName}</td></tr>
                        <tr style="text-align: left"><td>Last Name</td><td>${userInfo.lastName}</td></tr>	
                        <tr style="text-align: left"><td>Email</td><td>${userInfo.email}</td></tr>
                        <tr style="text-align: left"><td>Phone Number</td><td>${userInfo.phone}</td></tr>
                    </table>
                    <button id="edit-info-btn" class="btn btn-primary" style="margin: 20px auto 20px auto;">Edit</button>
                </div>
                <div class="panel panel-default" id="account-info-update" style="display:none">
                    <h2 style="padding:15px">Account Information for <%=username%></h2>
                    <% UserBean userBean = new UserDAO().getUserByUserName(username);
                        System.out.println("from cus: " + userBean.getFirstName());
                    %>
                    <form action="${pageContext.request.contextPath}/UpdateUserController" method="post">
                        <table class="table" style="max-width: 500px; margin-left: auto; margin-right: auto; padding:20px">
                            <tr style="text-align: left"><td>First Name: </td><td><input type="text" name="fName" value="${userInfo.firstName}" required /></td></tr>
                            <tr style="text-align: left"><td>Last Name</td><td><input type="text" name="lName" value="<%=userBean.getLastName()%>" required /></td></tr>	
                            <tr style="text-align: left"><td>Email</td><td><input type="email" name="email" value="<%=userBean.getEmail()%>" required /></td></tr>
                            <tr style="text-align: left"><td>Phone Number</td><td><input type="tel" name="phone" value="<%=userBean.getPhone()%>" required /></td></tr>
                        </table>
                        <button id="cancel-edit-info-btn" class="btn btn-primary" style="margin: 20px auto 20px auto;">Cancel</button>
                        <input type="submit" class="btn btn-primary" value="Update" />
                    </form>


                </div>

            </div>
        </div>
        <div role="tabpanel" class="tab-pane" id="ratings">
            <div class="container" style="max-width:90%; margin: 20px auto 0px auto">
                <h2 style="padding:15px">Reviews written by <%=username%></h2>
                <% List<ReviewBean> reviews = new ReviewDAO().selectReviewsFromUser(username);
                    BookDAO bookDB = new BookDAO();
                    for (ReviewBean review : reviews) {
                        BookBean book = bookDB.getBookByISBN(review.getISBN());
                %>
                <div class="panel panel-default" style="max-width: 600px; margin:20px auto 20px auto;">
                    <div class="panel-heading">
                        <h3 class="panel-title" style="text-align: left"><%= book.getTitle()%></h3>
                    </div>
                    <div class="panel-body">
                        <p>Rating: <%= review.getRating()%></p>
                        <p>Review: <%= review.getReviewText()%>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
    </div>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $('#orders a').click(function (e) {
            e.preventDefault()
            $(this).tab('show')
        });
        $('#account-info a').click(function (e) {
            e.preventDefault()
            $(this).tab('show')
        });
        $('#ratings a').click(function (e) {
            e.preventDefault()
            $(this).tab('show')
        });
        $('#edit-info-btn').click(function (e) {
            e.preventDefault();
            $('#account-info-static').hide();
            $('#account-info-update').show();
        });
        $('#cancel-edit-info-btn').click(function (e) {
            e.preventDefault();
            $('#account-info-static').show();
            $('#account-info-update').hide();
        });
    });

</script>
<%@ include file="../template/footer.jsp" %>
