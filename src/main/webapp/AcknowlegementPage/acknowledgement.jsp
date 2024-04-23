<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="database.DataFetcher,java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registration Successful</title>
<link rel="stylesheet" type="text/css" href="acknowledgement_Styles.css">

</head>
<body>
	<h1>Registered Successfully</h1>
	<br>
	<h2>Thank you for registering with Ebill Application</h2>
	<hr>
	<br>
	<br>
	<%
   		String consumerId = (String) session.getAttribute("consumerNumber");
   		String customerName = (String) session.getAttribute("name");
   		String customerMobileNumber = (String) session.getAttribute("mobileNumber");
   %>


	<p>
		<strong>Customer ID: <%= consumerId %></strong>
	</p>
	<br>
	<p>
		<strong>Customer Name: <%= customerName %></strong>
	</p>
	<br>
	<p>
		<strong>Mobile number: <%= customerMobileNumber %></strong>
	</p>
	<br>
	<br>
	<br>


	<div class="center">
	<form action="GoToLoginServlet" method="post">
		<button onclick="goToLoginPage()">Back to Login</button>
	</form>
	</div>
	<script src="acknowledgementScript.js"></script>
</body>
</html>