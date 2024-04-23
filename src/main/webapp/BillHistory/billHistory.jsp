<%@page import="customer.BillHistory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.DataFetcher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="billHistory_Styles.css">
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
			System.out.println("Consumer Number in billHistory.jsp : "+consumerNumberFromCookie);
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
				ArrayList<BillHistory> billHistory = DataFetcher.getBillHistory(consumerNumberFromCookie);
				if(!billHistory.isEmpty()){
					System.out.println("Bill history is not null ");
		%>
			<!-- Bill Table -->
		
			<div class="table-container">
				<table class="table" border="1">
					<thead>
						<tr>
							<th>Bill No</th>
							<th>Consumer Number</th>
							<th>Payment Type</th>
							<th>Date</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
		<%
					for(BillHistory i: billHistory){
						System.out.println(i.toString());
		%>
						<tr>
						<td><%= i.getBillNumber() %></td>
						<td><%= i.getConsumerNumber() %></td>
						<td><%= i.getPaymentType() %></td>
						<td><%= i.getPaymentDate() %></td>
						<% String status =null;
						if(i.getStatus()) {  
							status = "Paid";
						}else{
							status  = "Not Paid";
						}
						%>
						<td><%= status %></td>
						</tr>
		<%
					}
		%>
					</tbody>
				</table>
			</div>
			<%
						
				}else{
						System.out.println("Bill history is null ");
			%>
						<div class="billHistory-empty-msg">
						<h1>You haven't paid any bills yet. Hurry up! Go and pay early to avoid late fees.</h1><br/><br/>
						<a class="links" href="<%=request.getContextPath()%>/bill.jsp"><button type="button" class="button" value="Go To Pay Bills">Go To Pay Bills</button></a>
						</div>
			
			<%	
					}
				}catch(Exception e){
					System.out.println("Exception occured while fetching bill history in billHistory.jsp");
				}
			%>

		
		<br> <br>

</body>
</html>