<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="complaintStatus_Styles.css">
<title>Quick ebill</title>
</head>
<body>
	<%
		//Fetching consumerNumber using cookies 
		Cookie[] cookies = request.getCookies();
		String consumerNumberFromCookie = null;
		String consumerNameFromCookie = null;
		if(cookies!=null){
			for(Cookie c: cookies){
				String cookieName = c.getName();
				if(cookieName.equals("consumerNumber")){
					consumerNumberFromCookie = c.getValue();
				}
				if(cookieName.equals("consumerName")){
					consumerNameFromCookie = c.getValue();
				}
			}
			System.out.println("Consumer Number in complaintStatus.jsp : "+consumerNumberFromCookie);
		}
		else{
			response.sendRedirect("login.jsp");
		}
	%>
	
	<div class="body">
		<!-- Header -->
		<h1 class="h1">Quick EBill</h1>

		<!-- Navigation Bar -->
		<div class="navbar">
			<a href="<%=request.getContextPath()%>/bill.jsp">Home</a> 
			<a href="<%=request.getContextPath()%>/bill.jsp">Pay Bill</a> 
			<a href="<%=request.getContextPath()%>/billHistory.jsp">Bill History</a>
			<a href="<%=request.getContextPath()%>/complaint.jsp">Register Complaint</a> 
			<a href="<%=request.getContextPath()%>/complaintHistory.jsp">Complaint Status</a> 
			<form action="LogoutServlet" method="post">
				<button type="submit" class="logout-btn" value="logout">Logout -></button>
			</form>
		</div>
		<br>
		<div class="custname">
			<h3>Welcome <%=consumerNameFromCookie %></h3>
		</div>
		<br>
		<!-- View and Pay Bar -->
		<button class="button" type="submit" name="button">
			View Bill and Pay<span> &#9654;</span>
		</button>

		<br>
		<br> <br>
		<br>
		
		<%
			try{
			System.out.println("Getting to know if complaint is registered in complaintStatus");
			boolean isComplaintRegistered = (boolean) session.getAttribute("isComplaintRegistered");
			System.out.println(isComplaintRegistered);
			if(isComplaintRegistered) {
		%>
			<div class="status-box">
				<h1>Your complaint has been successfully registered. Click the button below to see your complaint status</h1><br/><br/>
				<a class="links" href="<%=request.getContextPath()%>/complaintHistory.jsp"><button type="button" class="button" value="Go To Complaint History">Go To Complaint History</button></a>
			</div>
		<%
			}else{
		%>	
			
			<div class="status-box">
				<h1>Error occured while registering your complaint.</h1><br/>
				<h1> Click here to register your complaint</h1><br/><br/>
				<a class="links" href="<%=request.getContextPath()%>/complaint.jsp"><button type="button" class="button" value="Go To Complaint Registration Page">Go To Complaint Registration Page</button></a>
			</div>
		<%
			}
			}catch(NullPointerException e){
				System.out.println("Value of isComplaintregistered in complaintStatus is null");
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
		%>
			
		<br> <br>

</body>
</html>
			
			
			
			
			
			
			
			
			
			
			
			