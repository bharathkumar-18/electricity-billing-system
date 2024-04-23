<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="login_Styles.css">
    <script src="loginScript.js"></script>
   
</head>
<body>
    <div class="id" style="display: flex; justify-content:center">
        <p style="font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;" id="id"></p>
    </div>
    <form class="container" action="<%= request.getContextPath() %>/LoginServlet" method="post" onsubmit=" return validate(event)">
        <div class="custid">
            <span class="heading">
                <label for="cust_in">Customer Id</label>
            </span>
            <div class="input">
                <input class="cust_in" id="cusId" name="customerId"  type="text" placeholder="Enter Customer Id" maxlength="13">
            </div>
        </div><br>
        <span style="align-self: center;font-size:20px;font-weight:500;"><label for="cust_in">OR</label></span>
        <div class="eid">
            <span class="heading">
                <label for="e_in">Email Id</label> 
            </span>
            <div class="input">
                <input class="e_in" id="email" name="email"  type="email" placeholder="Enter Email">
            </div>
        </div><br>
        <!-- <hr style="width: 69%; border-style:solid;border-color:blue"> -->
        <div class="pwd">
            <span class="heading">
                <label for="pwd">Password</label>
            </span>
            <div class="input">
                <input class="pwd_in" id="pwd" name="password"  type="password" placeholder="Type Password"required>
            </div>
        </div>
        <div class="reg_div">
            <div class="div_button">
               <button type="submit" class="sub_button" href="#">LOGIN</button>
            </div>
            <div class="newsign_div">
                <span class="signin">Not registered? Click here to<a class="signin" href="<%=request.getContextPath()%>/register.jsp"> <span class="sign_in"> sign in</span></a></span>
            </div>
        </div>
        <%
        	try{
        		
		        	boolean allowLogin = (boolean) session.getAttribute("allowLogin");
		        	if(!allowLogin) {
		        		String msg = "Kindly check your credentials and try again";
        %>
        <p><%=msg %></p>
        <%
        	}
        	} catch(NullPointerException e){
        		System.out.println("Please submit the form");
        		//e.printStackTrace();
        	}catch(Exception e){
        		System.out.println("Exception in login.jsp");
        		e.printStackTrace();
        	}
        %>
    </form>
</body>
</html>
    