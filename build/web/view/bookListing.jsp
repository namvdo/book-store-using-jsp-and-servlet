<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="db.ReviewDAO"%>
<%@page import="bean.BookBean"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../template/header.jsp" %>
<%--<sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:sqlserver://localhost:1433;databaseName=BookStore"
         user = "sa"  password = "nam123"/>

<sql:query dataSource = "${snapshot}" var = "review">
select username, isbn, review_text from Rating where isbn = ?"
<sql:param value="${book.isbn}"></sql:param>
</sql:query>--%>
<%-- get stars, for example, 1 start -> 5 votes then create a fixed star 1, using map<int, int> to get the value map.get(1) -> number ratings --%>

<% BookBean book = (BookBean) request.getAttribute("book");%>
<div class="panel panel-default">
    <div class="panel-body">
        <form action="${pageContext.request.contextPath}/CartUpdateController" method="post">

            <div class="container">
                <article class="item-pane">
                    <div class="item-preview">
                        <div class="book">
                            <img class="img-thumbnail" src="<%=book.getCoverImageFile()%>">
                        </div>
                    </div>
                    <div class="item-details">
                        <h1><%=book.getTitle()%></h1><span class="subtitle">by <%=book.getAuthors()%></span>
                        <div class="pane__section">
                            <p>
                                <%=book.getDescription()%>
                            </p>
                        </div>
                        <div class="pane__section">
                            <dl>


                                <dt>ISBN</dt>
                                <dd><%=book.getIsbn()%></dd>
                                <dt>Book rating</dt>
                                <%
                                    float rating = book.getRating();
                                    DecimalFormat df2 = new DecimalFormat("#.#");
                                    if (rating != 0) {
                                        out.println("<dd>" + df2.format(rating) + "/5</dd>");
                                    } else {
                                        out.println("<dd>No rating yet!</dd>");
                                    }
                                %>
                                <dt>Publisher </dt>
                                <dd><%=book.getPublisher()%></dd>
                                <dt>Publication date</dt>
                                <dd><%=book.getPublishDate()%></dd>
                                <dt>Available inventory</dt>
                                <dd><%=book.getInventory()%></dd>
                                <dt>Number of views </dt>
                                <dd>${book.numOfView}</dd>
                            </dl>
                        </div>
                    </div>
                </article>
            </div>


            <input type="hidden" name="isbn" value='<%=book.getIsbn()%>'/>
            <input type="hidden" name="title" value='<%=book.getTitle()%>' />
            <input type="hidden" name="quantity" value="1" />
            <%if (book.getInventory() != 0) { %>
            <button class="btn btn-primary" style="float:right; margin-top:1em" type="submit" name="add" value="Add to Cart" />Add to Cart</button>
            <% } %>
            <% if (book.getInventory() == 0) { %>
            <button disabled class="btn btn-primary" style="float:right" type="submit" name="add" value="Add to Cart" />Sold out!</button>
            <% }%>
        </form>
        <br>
        <br>
        <hr>
        <form class="col-md-6" action="${pageContext.request.contextPath}/BookRatingController" method="post" style="max-width: 330px; margin: 0 auto; padding: 30px">
            <h2>Submit a Rating</h2>
            <div class="form-group">
                <label for="rating">Rating</label>
                <select name="rating" class="form-control">
                    <option value="1">1 Star</option>
                    <option value="2">2 Star</option>
                    <option value="3">3 Star</option>
                    <option value="4">4 Star</option>
                    <option value="5">5 Star</option>
                </select>
                <input type="hidden" name="isbn" value="${book.isbn}" />
            </div>
            <div class="form-group">
                <label for="review-text">Write a review</label>
                <textarea rows="4" cols="50" name="review-text" class="form-control"></textarea>
            </div>
            <input type="submit" value="Submit Rating" />
        </form>



        <c:if test="${reviewed eq 'reviewed'}">
            <strong>You already have a review for this book. You can click right <a href="${pageContext.request.contextPath}/CustomerPageController">here</a> to see it.</strong>
        </c:if>


        <%
            Map<Integer, Integer> map = new ReviewDAO().getAllRatingAndRatingCount(book.getIsbn());
            int sum = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                sum += entry.getValue();
            }
            System.out.println(sum);
        %>


        <div class="col-md-6"  style="padding: 30px;text-align: center;">
            <h2>Number of ratings: <%=sum%></h2>
            <div class="star-group">
                <div class="row star fiveStar">

                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="star"><%=map.get(5) == null ? 0 : map.get(5)%></span>
                    <span class="percentage"><%=map.get(5) == null ? 0 : df2.format((double) map.get(5) / sum * 100)%>%</span>
                </div>
                <div class="row star fourStar">
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star "></span>
                    <span class="star"><%=map.get(4) == null ? 0 : map.get(4)%></span>
                    <span class="percentage"><%=map.get(4) == null ? 0 : df2.format((double) map.get(4) / sum * 100)%>%</span>
                </div>
                <div class="row star threeStar">
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star "></span>
                    <span class="fa fa-star "></span>
                    <span class="star"><%=map.get(3) == null ? 0 : map.get(3)%></span>
                    <span class="percentage"><%=map.get(3) == null ? 0 : df2.format((double) map.get(3) / sum * 100)%>%</span>
                </div>
                <div class="row star twoStar">
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star "></span>
                    <span class="fa fa-star "></span>
                    <span class="fa fa-star "></span>
                    <span class="star"><%=map.get(2) == null ? 0 : map.get(2)%></span>
                    <span class="percentage"><%=map.get(2) == null ? 0 : df2.format((double) map.get(2) / sum * 100)%>%</span>

                </div>
                <div class="row star oneStar">
                    <span class="fa fa-star checked"></span> 
                    <span class="fa fa-star "></span>
                    <span class="fa fa-star "></span>
                    <span class="fa fa-star "></span>
                    <span class="fa fa-star "></span>
                    <span class="star"><%=map.get(1) == null ? 0 : map.get(1)%></span>
                    <span class="percentage"><%=map.get(1) == null ? 0 : df2.format((double) map.get(1) / sum * 100)%>%</span>

                </div>
            </div>
        </div>

        <div class="template-demo col-md-12">
            <h3>How people say about this book: </h3>

            <c:forEach items="${reviews}" var="review">



                <div class="container">
                    <img class="float-left" src="https://img.icons8.com/ultraviolet/20/000000/quote-left.png">
                    <div class="row">
                        <p>${review.reviewText}</p>

                        <div class="col-sm-2"> </div>
                        <div class="col-sm-10">
                            <div class="profile">
                                <h4 class="cust-name">${review.userName}</h4>
                                <p class="cust-profession">Rated: ${review.rating}/5</p>
                            </div>
                        </div>
                    </div>
                </div>

                <hr>
            </c:forEach>
        </div>
    </div>
    <div id="piechart">

    </div>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>


    <script type="text/javascript">
        // Load google charts
        google.charts.load('current', {'packages': ['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        // Draw the chart and set the chart values
        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Task', 'Hours per Day'],

                ['One star', <%=map.get(1)%>],
                ['Two stars', <%=map.get(2)%>],
                ['Three stars', <%=map.get(3)%>],
                ['Four stars', <%=map.get(4)%>],
                ['Five stars', <%=map.get(5)%>],
            ]);

            // Optional; add a title and set the width and height of the chart
            var options = {'title': 'Rating statistic'};

            // Display the chart inside the <div> element with id="piechart"
            var chart = new google.visualization.PieChart(document.getElementById('piechart'));
            chart.draw(data, options);


        }
    </script>
    <div id="chart_div"></div>
    <script>
        google.charts.load('current', {packages: ['corechart', 'bar']});
        google.charts.setOnLoadCallback(drawBasic);

        function drawBasic() {

            var data = google.visualization.arrayToDataTable([
                ['Star', 'Number of ratings'],

                ['One star', <%=map.get(1)%>],
                ['Two stars', <%=map.get(2)%>],
                ['Three stars', <%=map.get(3)%>],
                ['Four stars', <%=map.get(4)%>],
                ['Five stars', <%=map.get(5)%>],
            ]);

            var options = {
                title: 'All rating stars for <%=book.getTitle()%>',
                bars: 'vertical',
                chartArea: {width: '50%'},
                hAxis: {
                    title: 'Total Rating stars',
                    minValue: 0,
                    format: 0
                },
                vAxis: {
                    title: '1-5 stars',
                    format: '0',
                },
               
                          


               
            };
            // change barchart to column chart
            var chart = new google.visualization.BarChart(document.getElementById('chart_div'));

            chart.draw(data, options);
        }

    </script>
    
      <!--<div id="chart_div2"></div>-->

    <script>
        google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawBasic);

function drawBasic() {

      var data = new google.visualization.DataTable();
      data.addColumn('timeofday', 'Time of Day');
      data.addColumn('number', 'Motivation Level');

      data.addRows([
        [{v: [8, 0, 0], f: 'One star'}, 1],
        [{v: [9, 0, 0], f: 'Two Stars'}, 2],
        [{v: [10, 0, 0], f:'Three stars'}, 3],
        [{v: [11, 0, 0], f: 'Four stars'}, 4],
        [{v: [12, 0, 0], f: 'Five stars'}, 5],
       
      ]);

      var options = {
        title: 'Motivation Level Throughout the Day',
        hAxis: {
          title: 'Time of Day',
          format: 'h:mm a',
          viewWindow: {
            min: [7, 30, 0],
            max: [17, 30, 0]
          }
        },
        vAxis: {
          title: 'Rating (scale of 1-10)'
        }
      };

      var chart = new google.visualization.ColumnChart(
        document.getElementById('chart_div2'));

      chart.draw(data, options);
    }
        
    </script>
    <style>

        .star-group {
            padding-top: 1em;
        }
        .star {
            padding-left: 1em;
            padding-top: 1em;


        }

        .checked {
            color: orange;


        }
        .percentage {
            padding-left: 2em;
        }

        .fa-star {

            padding-left: 1em;
        }


        .template-demo img {
            float: left;
        }
        .profile {
            float: right;
        }
        .template-demo .row {
            margin-left: 2em;
            width: 90%;
            text-align:left;
        }
        html,
        body {
            font-family: Roboto, sans-serif;
            font-weight: 300;
            font-size: 16px;
        }

        body {
            background: #efefef;
        }

        .clearfix:after {
            content: "";
            display: table;
            clear: both;
        }

        .item-details {
            padding-right: 2em;
        }
        .btn-primary {
            margin-right: 1.5em;
        }

        .item-pane {
            display: flex;
            -webkit-box-align: center;

            box-sizing: border-box;
            width: 100%;
            max-width: 900px;

            background: white;
            border-radius: 4px;
        }

        .item-preview {
            margin-right: 26px;
            padding: 26px;
        }



        .pane__section {
            padding-top: 26px;
        }
        .pane__section:not(:last-child) {
            padding-bottom: 26px;
            border-bottom: 1px solid #d4d4d4;
        }

        h1,
        .item-price {
            font-family: "Roboto Slab", Roboto, sans-serif;
            font-weight: 400;
            font-size: 2em;
        }

        h1 {
            margin-bottom: 0.4em;
            margin-top: 0.4em;
        }

        .subtitle {
            font-family: "Roboto Slab", Roboto, sans-serif;
            font-weight: 300;
            font-size: 1.3em;
        }

        a {
            color: #0db5db;
        }

        .item-price__units {
            font-weight: 300;
        }

        p {
            line-height: 1.4;
        }

        .button {
            padding: 0.6em 1em;
            font-weight: 400;
            text-decoration: none;
            color: white;
            text-transform: uppercase;
            background: #80db65;
            border-radius: 3px;
        }
        .button:hover {
            background: #5ed13c;
        }

        dl {
            -webkit-columns: 2;
            -moz-columns: 2;
            columns: 2;
            line-height: 1.8em;
            margin: -0.4em 0;
        }

        dt,
        dd {
            display: inline;
        }

        dt {
            font-weight: 500;
        }
        dt:after {
            content: ":";
        }

        dd:after {
            content: "";
            display: block;
        }

        .buy-button {
            float: right;
        }

        .book{
            position: relative;
            width: 216px;
            height: 322px;
            -webkit-transform: rotateX(-15deg) rotateY(30deg);
            transform: rotateX(-15deg) rotateY(30deg);
            -webkit-transform-style: preserve-3d;
            transform-style: preserve-3d;
            -webkit-transition: -webkit-transform 0.5s;
            transition: -webkit-transform 0.5s;
            transition: transform 0.5s;
            transition: transform 0.5s, -webkit-transform 0.5s;
        }
        .book:before {
            content: "";
            position: absolute;
            right: 100%;
            top: 0;
            width: 35px;
            height: 95%;
            background: #f0c0af;
            -webkit-transform-origin: top right;
            transform-origin: top right;
            -webkit-transform: rotateY(-90deg);
            transform: rotateY(-90deg);
        }
        .book:after {
            content: "";
            position: absolute;
            bottom: 100%;
            left: 0;
            width: 100%;
            height: 35px;
            background: #f5efdc;
            -webkit-transform-origin: bottom left;
            transform-origin: bottom left;
            -webkit-transform: rotateX(90deg);
            transform: rotateX(90deg);
        }
        .book:hover {
            -webkit-transform: rotateX(-10deg) rotateY(20deg);
            transform: rotateX(-10deg) rotateY(20deg);
        }
        .book__title {
            margin-bottom: 1em;
            font-weight: 700;
        }
        .book__text {
            font-family: Roboto;
            background: #e9d1c5;
            padding: 20px;
            color: #563323;
        }


    </style>     
    <!-- <p><a href="./FirstTest">Test Servlet functionality</a></p> -->

    <%@include file="../template/footer.jsp" %>