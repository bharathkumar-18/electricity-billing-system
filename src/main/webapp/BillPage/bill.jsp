<%@page import="database.DataFetcher"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="customer.Bill"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="bill_Styles.css">
<title>Quick ebill</title>
</head>
<body>
	<%
		//Fetching consumerNumber using cookies 
		Cookie[] cookies = request.getCookies();
		String consumerNumberFromCookie = null;
		String consumerNameFromCookie = null;
		String allowLoginFromCookie = null;
		if(cookies!=null){
			for(Cookie c: cookies){
				String cookieName = c.getName();
				if(cookieName.equals("consumerNumber")){
					consumerNumberFromCookie = c.getValue();
				}
				if(cookieName.equals("consumerName")){
					consumerNameFromCookie = c.getValue();
				}
				if(cookieName.equals("allowLogin")){
					allowLoginFromCookie = c.getValue();
				}
			}
			System.out.println("Consumer Number in bill.jsp : "+consumerNumberFromCookie);
			System.out.println("Consumer Name in bill.jsp : "+consumerNameFromCookie);
			System.out.println("Allow login in bill.jsp : "+allowLoginFromCookie);
		}
		else{
			response.sendRedirect("login.jsp");
		}
		if(allowLoginFromCookie.equals("false")){
			
			response.sendRedirect("LogoutServlet");
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
			<a href="<%=request.getContextPath()%>/complaint.jsp">RegisterComplaint</a> 
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
				//ArrayList<Bill> bills = (ArrayList<Bill>) request.getAttribute("bills");
				ArrayList<Bill> bills = DataFetcher.getBillDetails(consumerNumberFromCookie);
				//System.out.println("Is bills empty:"+bills.isEmpty());
				int count = 0; int billsPaid = 0;
				for(Bill i: bills){
					count++;
					if(i.getBillPaid()){
						billsPaid++;
					}
				}
				if(count==billsPaid){
					System.out.println("Congratulations!. You have no bills to pay");
		%>
				<div class="status-box">
					<h1>Congratulations. You have no bills to pay. </h1>
					<h1>You can find your previous paid bills here.</h1><br/><br/>
					<a class="links" href="<%=request.getContextPath()%>/billHistory.jsp"><button type="button" class="button" value="Go To Bill History">Go To Bill History</button></a>
				</div>			
		<%			
				}else{
					System.out.println("Bills in Bill.jsp:"+bills);
		%>
		<!-- Bill Table -->
		<form action="ChoosePaymentServlet" method="post" onsubmit=" return validatePayableAmount(event)">
		<div class="table-container">
			<table class="table" border="1">
				<thead>
					<tr>
						<th>Bill No</th>
						<th>Select</th>
						<th>Bill Amount</th>
						<th>Due Amount</th>
						<th>Payable</th>
					</tr>
				</thead>
				<tbody>

		<%
					for(Bill i: bills){
						if(i.getBillPaid()==false){
							
						
				
		%>
					<tr>
						<td><%= i.getBillNumber() %></td>
						<td><input type="checkbox" onchange="calculateTotal()" name="selectBills" value=<%=i.getBillNumber()%>></td>
						<td><%= i.getBillAmount() %></td>
						<td><%= i.getDueAmount() %></td>
						<td><%= i.getBillAmount()+i.getDueAmount() %></td>
					</tr>

		<%			}
				}
		%>
					</tbody>
					</table>
				</div>
				<br> <br>
				
				<!-- Total Amount Payable -->
				
				<div style="text-align: right;">
					<h3>
						<b>Total Amount Payable: <span id="totalPayable">0.00</span>&nbsp;&nbsp;
						</b>
					</h3>
				</div>
				<br>
				<hr>
				<br> <br> <br>
				<!-- Proceed to Pay Button -->
				<button type="submit" class='btn' value="Proceed to Pay">Proceed to Pay</button>
				<br> <br>
				<input type="text" name="totalAmount" id="totalAmount" style="display:none"/>
			</form>
			</div>
		<%
				}
			}catch(Exception e){
					e.printStackTrace();
				}
        %>
				

	<script src="billScript2.js"></script>

</body>
</html>