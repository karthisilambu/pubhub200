

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<title>Welcome</title>

</head>
<body>

	<%@ include file="../../layout/header.jsp"%>
	<div class="container-fluid">

		<div class="row">
			<div class="col-md-4">
				
				<%
				Object object = session.getAttribute("LOGGED_IN_USER");
				if ( object != null ){
					response.sendRedirect("/books");
				}
				
				
				%>

				<div class="portlet-title">
					<div class="page-header">
						<h3>Login</h3>
					</div>
				</div>
				<div class="portlet-body">
					<form action="auth/login" method="POST">
						<div class="form-group">
							<label for="userName">EmailId :</label> <input type="email"
								class="form-control" name="email" placeholder="Enter emailId"
								autofocus="autofocus" required="required" id="emailId"
								value="test@gmail.com" />
						</div>
        
						<div class="form-group">
							<label for="password">Password :</label> <input type="password"
								class="form-control" name="password"
								placeholder="Enter Password" required="required" id="password"
								value="test@123" />
						</div>
						<div class="form-group">
							<button type="submit" name="add" class="btn btn-success"
								id="addLoginBtn">SIGN IN</button>

							<a href="user/register.jsp" class='btn btn-primary'>SIGN UP</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>