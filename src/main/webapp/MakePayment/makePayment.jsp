<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="makePayment_Styles3.css">
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
			
		String[] billsSelectedToPay = (String[]) request.getAttribute("billsSelectedToPay");
		System.out.println("Bills to be updated in makepayment.jsp:"+billsSelectedToPay);
		for(String i:billsSelectedToPay){
			System.out.println(i);
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


		<div class="whole-box">

	
			<div class="details-box left-box">
				<form id="card-details-form" onsubmit=" return validateCardDetails(event)"
			action="<%= request.getContextPath() %>/MakePaymentServlet"
			method="post">
				<img src="visaMasterCard.jpg" alt="Visa and Master Card Logo" height="100px" width="200px" style="margin-left: auto;margin-right: auto"/>
					<div class="card-number-box left-box-inner">
						<label for="card-number" class="label-heading">Card Number</label><br/>
						<div class="card-number-input">
							<input type="text" name="card-number" id="card-number"
								placeholder="Enter Card Number" size="16" maxlength="16">
							<img src="cardLogo.jpg" alt="Card Logo">
						</div><br/>
					</div>
					<br /> <label for="expiry-details" class="label-heading">Expiration
						Details</label><br />
					<div class="expiry-details left-box-inner">
						<div class="month">
							<!-- <label for="month" class="label-heading">Month</label><br /> -->
							<select id="month" name="month" class="select_month">
								<option value="Month" disabled selected hidden>Month</option>
								<option value=1>January</option>
								<option value=2>February</option>
								<option value=3>March</option>
								<option value=4>April</option>
								<option value=5>May</option>
								<option value=6>June</option>
								<option value=7>July</option>
								<option value=8>August</option>
								<option value=9>September</option>
								<option value=10>October</option>
								<option value=11>November</option>
								<option value=12>December</option>
							</select>
						</div>
						<div class="year">
							<!-- <label for="year" class="label-heading">Year</label><br /> -->
							<input type="text" name="year" id="year" placeholder="Year"
								maxlength="4" size="4">
						</div>
						<div class="cvv">
							<!-- <label for="cvv" class="label-heading">CVV / CVC</label><br /> -->
							<input type="text" name="cvv" id="cvv" placeholder="CVV" size="3"
								maxlength="3">
						</div>
					</div>
					<br />
					<div class="card-name left-box-inner">
						<label for="card-holder-name" class="label-heading">Card
							Holder Name</label><br /> <input type="text" name="card-holder-name"
							id="card-holder-name" placeholder="Enter Card Holder Name"><br/>
					</div><br/>
					<div class="make-and-cancel-box left-box-inner">
						<button class="payment-btn" type="submit">Make Payment</button>
						<br />
						<div style="text-align: right;">
							<a href="<%=request.getContextPath() %>/bill.jsp">Cancel</a>
						</div>
					</div>
					<%
					HttpSession sessionForBills = request.getSession();
					sessionForBills.setAttribute("billsSelectedToPay",billsSelectedToPay);
					%>
				</form>
			</div>
			<div class="right-box">
				<%
					String totalAmountPayable = (String) request.getAttribute("totalAmountPayable");
					System.out.println("Total amount in makePayment.jsp:"+totalAmountPayable);
					
				%>
				Payment Amount:<span id="payment-amount">&emsp; &emsp;<%=totalAmountPayable %></span>
			</div>
			<% 
				try{
					
				boolean areCardDetailsCorrect = (boolean) request.getAttribute("areCardDetailsCorrect");
				boolean areBillsStatusUpdated = (boolean) request.getAttribute("areBillsStatusUpdated");
			%>
			<span>Card Details:<%=areCardDetailsCorrect %></span>
			<span>Bills updated:<%=areBillsStatusUpdated %></span>
			
			<%
				}catch(Exception e){
					e.printStackTrace();
				}
			%>
		</div>
		</div>
	<script src="makePaymentScript1.js"></script>
</body>

</html>