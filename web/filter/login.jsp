<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>namBookStore log in page</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="../css1/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="../dependencies/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="../css1/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="../css1/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="../css1/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="../css1/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="../css1/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="../css1/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="../css1/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="../css1/css/util.css">
	<link rel="stylesheet" type="text/css" href="../css1/css/main.css">
<!--===============================================================================================-->
</head>
<body style="background-color: #666666;">
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form validate-form" action="${pageContext.request.contextPath}/LoginController" method="POST">
					<span class="login100-form-title p-b-43">
						Login to continue
					</span>
					
					
					<div class="wrap-input100 validate-input" data-validate = "Valid username is required">
						<input class="input100" type="text" name="username">
						<span class="focus-input100"></span>
						<span class="label-input100">Username</span>
					</div>
					
					
					<div class="wrap-input100 validate-input" data-validate="Password is required">
						<input class="input100" type="password" name="password">
						<span class="focus-input100"></span>
						<span class="label-input100">Password</span>
					</div>

					
			

					<div class="container-login100-form-btn">
						<button class="login100-form-btn" type="submit">
							Login
						</button>
					</div>
					
					<div class="text-center p-t-46 p-b-20">
						<span class="txt2">
                                                    Don't have an account? Click <a class="form-recovery" href="./registrationForm.jsp">here</a> to sign up a new one.
						</span>
					</div>

					
				</form>

				<div class="login100-more" style="background-image: url('../css1/images/bg-01.jpg');">
				</div>
			</div>
		</div>
	</div>
        <%-- 
        out	JspWriter
        request	HttpServletRequest
        response	HttpServletResponse
        config	ServletConfig
        application	ServletContext
        session	HttpSession
        pageContext	PageContext
        page	Object
        exception	Throwable
        
        --%>
       
	
<!--===============================================================================================-->
	<script src="../css1/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="../css1/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="../css1/vendor/bootstrap/js/popper.js"></script>
	<script src="../css1/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="../css1/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="../css1/vendor/daterangepicker/moment.min.js"></script>
	<script src="../css1/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="../css1/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="../css1/js/main.js"></script>

</body>
</html>