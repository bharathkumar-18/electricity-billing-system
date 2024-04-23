<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Customer Registration</title>
<link rel="stylesheet" href="register_Styles.css">
<link rel="stylesheet" href="progress_bar.css">
</head>

<body>
	<div class="info-box" id="info-box">
		<div class="title-div">
			<p class="main-heading">Register New User</p>
		</div>
		<br /> <br />

		<form id="registration-form" onsubmit=" return registerStatus(event)"
			action="<%= request.getContextPath() %>/RegisterServlet"
			method="post">
			<!-- Consumer Details div -->
			<div class="consumer-details">
				<h3 class="heading">Consumer Details</h3>
				<br />
				<hr />
				<br />
				<div class="items">
					<div class="left-items">
						<div class="labels-and-inputs">
							<div class="labels">
								<label for="consumer-number">Consumer Number:</label>
							</div>
							<div class="inputs">
								<input type="number" name="consumer-number" id="consumer-number"
									placeholder=" Consumer Number" min="1000000000000"
									max="9999999999999" required>
							</div>
						</div>
					</div>
					<div class="right-items">
						<div class="labels-and-inputs">
							<div class="labels">
								<label for="bill-number">Bill Number:</label>
							</div>
							<div class="inputs">
								<input type="number" name="bill-number" id="bill-number"
									placeholder=" Last 5 digits" max="5" disabled>
							</div>
						</div>
					</div>
				</div>
			</div>
			<br /> <br />

			<!-- User Details div -->
			<div class="user-details">
				<h3 class="heading">User Details</h3>
				<br />
				<hr />
				<br />

				<div class="items">
					<div class="left-items">
						<div class="labels-and-inputs">
							<div class="labels">
								<label for="title">Title:</label><br /> <label for="email">Email
									Id:</label><br /> <br />
							</div>
							<div class="inputs">
								<select name="title" id="title" required>
									<option value="select" selected disabled>Please Select</option>
									<option value="Mr.">Mr.</option>
									<option value="Mrs.">Mrs.</option>
								</select><br /> <input type="text" name="email" id="email"
									placeholder=" Email address" required><br /> <br />
							</div>
						</div>
					</div>
					<div class="right-items">
						<div class="labels-and-inputs">
							<div class="labels">
								<label for="name">Name:</label> <label for="mobile-number">Mobile
									Number:</label><br /> <br />
							</div>
							<div class="inputs">
								<input type="text" name="name" id="name"
									placeholder=" Enter your Name" required><br /> <select
									name="mobile-code" id="mobile-code" required>
									<option value="india">0091-INDIA</option>
									<option value="usa">0001-USA</option>
									<option value="uk">0044-UK</option>
									<option value="japan">0081-JAPAN</option>
								</select><br /> <input type="number" id="mobile-number"
									name="mobile-number" placeholder=" Mobile Number"
									min="1000000000" max="9999999999" required><br /> <br />
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Login Details div -->
			<div class="login-detalis">
				<h3 class="heading">Login Details</h3>
				<br />
				<hr />
				<br />

				<div class="items">
					<div class="left-items">
						<div class="labels-and-inputs">
							<div class="labels">
								<label for="userid">User Id:</label>
							</div>
							<div class="inputs">
								<input type="text" name="userid" id="userid" disabled>
							</div>
						</div>
					</div>
					<div class="right-items">
						<div class="labels-and-inputs">
							<div class="labels">
								<label for="password">Password:</label><br /> <label
									for="re-password">Confirm Password:</label>
							</div>
							<div class="inputs">
								<input type="password" name="password" id="password" required><br />
								<input type="password" name="re-password" id="re-password"
									required>
							</div>
						</div>
					</div>
				</div>
			</div>
			<br /> <br /> <br />

			<div class="buttons">
				<button type="reset" id="reset-btn">Reset</button>
				&nbsp;&nbsp;
				<button type="submit" id="register-btn">Register</button><br/><br/>
			<a href="<%=request.getContextPath() %>/login.jsp">Already Registered? Go to login page</a>
			</div>
		</form>

	</div>

	<div id="progress-bar">
		<svg width="250" height="250" viewBox="0 0 250 250"
			class="circular-progress">
            <circle class="bg"></circle>
            <circle class="fg"></circle>
        </svg>
		<br /> <br /> <span id="register-status">Registering. Please
			wait ...</span>
	</div>

	<script src="registerScript.js">
    </script>
</body>

</html>