<%-- 
    Document   : searchResult
    Created on : Jul 20, 2020, 7:43:20 AM
    Author     : Nam
--%>

<%@page import="java.text.NumberFormat"%>
<%@page import="bean.BookBean"%>
<%@ include file="../template/header.jsp" %>




<c:if test="${books.size() eq 0}">
    <h2>Sorry, there is no book that matched the keyword(s) </h2>

</c:if>
    <c:if test="${books.size() ne 0}">
    <h2>Relevant books matched the keyword</h2>

</c:if>
    
    
   
    <c:forEach var="book" items="${books}">
    <div class="col-md-6" style="padding: 5px;">
        <div style="margin:3px; padding:10px; background-color: #eee;">
            <div class="row">
                <div class="col-md-4">
                    <a href='${pageContext.request.contextPath}/BookLookUpController?isbn=${book.isbn}>' style="max-height: 130px; max-width: 110px;">
                        <img src="${book.coverImageFile}" alt="${book.title}" style="max-width: inherit; max-height: inherit">
                    </a>
                </div>
                <div class="col-md-8" style="text-align: left; padding-left:10px;">
                    <h4>${book.title}</h4><i>by ${book.authors}</i>
                    <h5><fmt:formatNumber value="${book.price}" type="currency"></fmt:formatNumber></h5>
                    <a href='${pageContext.request.contextPath}/BookLookUpController?isbn=${book.isbn}' style="max-height: 130px; max-width: 110px;"><p class="text-white"> <p class="text-light"><button class="btn btn-primary">View the book</button></p>
                    </a>
                </div>
            </div>
        </div>
    </div>
   
    </c:forEach>
<%@ include file="../template/footer.jsp" %>
