<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register Complaint</title>
<link rel="stylesheet" type="text/css" href="complaint_Styles.css">
</head>

<body>

	<%
		String conumerNumberFromCookie = null;
		String consumerNameFromCookie = null;
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(Cookie c: cookies){
				String cookieName = c.getName();
				if(cookieName.equals("consumerNumber")){
					conumerNumberFromCookie = c.getValue();
				}
				if(cookieName.equals("consumerName")){
					consumerNameFromCookie = c.getValue();
				}
			}
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

		<!-- View and Pay Bar -->
		<button class="button1" type="submit" name="button">
			File New Complaint<span> &#9654;</span>
		</button>




		<form onsubmit="return validateComplaintForm(event)" id="myForm" action="<%=request.getContextPath() %>/ComplaintServlet" method="post">
			<div class="table-container-wrapper">
				<div class="table-container">
					<table class="table" border="0">

						<tbody>
							<div class="form-group">
								<tr>
									<td><label for="complaint_type"><span
											class="required"></span>Complaint/Service Type:</label></td>
									<td><select id="complaint_type" name="complaint_type"
										required>
											<option value="" selected disabled>Select Complaint/Service Type</option>
											<option value="Street Light Related">Street Light
												Related</option>
											<option value="Billing Related">Billing Related</option>
											<option value="Meter Related">Meter Related</option>
											<option value="Voltage Related">Voltage Related</option>
											<option value="No Power Supply Related">No Power
												Supply Related</option>
											<option value="Service Wire Related">Service Wire
												Related</option>
											<!-- Add more options as needed -->
									</select></td>
							</div>
							<div class="form-group">
								<td><label for="landmark"><span class="required"></span>Landmark:</label>
								</td>
								<td><input type="text" id="landmark" name="landmark"
									placeholder="Landmark" required></td>
							</div>
							</tr>
							<tr>
								<div class="form-group">
									<td><label for="category"><span class="required"></span>Category:</label></td>
									<td><select id="category" name="category" required>
											<option value="" selected disabled>Select Category</option>
											<option value="Commercial">Commercial</option>
											<option value="Domestic">Domestic</option>

									</select></td>
								</div>
								<div class="form-group">
									<td><label for="consumer_number"><span
											class="required"></span>Consumer Number:</label></td>
									<td><input type="text" id="consumer_number"
										name="consumer_number" placeholder="Consumer Number"
										maxlength="13" required></td>
								</div>

							</tr>
							<tr>
								<div class="form-group">
									<td><label for="contact_person"><span
											class="required"></span>Contact Person:</label></td>
									<td><input type="text" id="contact_person"
										name="contact_person" placeholder="Contact Person" required></td>
									<td><label for="problem_description"><span
											class="required">*</span>Problem Description:</label></td>
									<td><textarea id="problem_description"
											name="problem_description" placeholder="Problem Description"
											required></textarea></td>
								</div>
							</tr>
							<tr>
								<div class="form-group">
									<td><label for="mobile_number"><span
											class="required">*</span>Mobile Number:</label></td>
									<td><input type="text" id="mobile_number"
										name="mobile_number" placeholder="Mobile Number"
										maxlength="10" required></td>
									<td><label for="address"><span class="required"></span>Address:</label></td>
									<td><textarea id="address" name="address"
											placeholder="Address" required></textarea></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<br>
			<div class="button-row">
				<button class="buttonc" type="reset">Cancel</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="buttons" type="submit" id="submit">Submit
					Complaint</button>
				<p id=" complaintID"></p>
			</div>
		</form>

	</div>


	<div class="hidden" id="displayCompId">
		<div class="card">
			<div class="wrapper">
				<svg class="checkmark" xmlns="http://www.w3.org/2000/svg"
					viewBox="0 0 52 52"> <circle class="checkmark__circle"
						cx="26" cy="26" r="25" fill="none" /> <path
						class="checkmark__check" fill="none"
						d="M14.1 27.2l7.1 7.2 16.7-16.8" />
            </svg>
			</div>
			<h5 class="card-title">Your complaint is registered Successfully
			</h5>
			<p class="card-text" id="compId"></p>
		</div>
		<button class="button2">OK</button>
	</div>
	<br>
	<br>
	<br>
	<br>

	<script src="complaintScript.js"></script>

</body>

</html>
