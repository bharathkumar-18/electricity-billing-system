package customer;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DataFetcher;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String consumerNumber = request.getParameter("customerId");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("Running Login Servlet");
		boolean allowLogin = false;
		String[] returnedArr = new String[3];
		if(consumerNumber==null) {
			try {
				returnedArr = DataFetcher.validateCustomerByEmail(email, password);
				String consumerNumberFromDB = returnedArr[0];
				String consumerNameFromDB = returnedArr[1];
				System.out.println("consumer number in login servlet after fetching details:"+consumerNumberFromDB);
				System.out.println("consumer name in login servlet after fetching details:"+consumerNameFromDB);
				allowLogin = Boolean.parseBoolean(returnedArr[2]);
				System.out.println("Allow login in login servlet after fetching details:"+allowLogin);
				if(allowLogin) {
					System.out.println("Login successful. Redirecting to Bill Servlet");
//					request.setAttribute("consumerNumber", consumerNumberFromDB);
//					System.out.println("Consumer number in Login Servlet: "+consumerNumber);
					Cookie userData = new Cookie("consumerNumber",consumerNumberFromDB);
					userData.setMaxAge(60*60);
					Cookie userData2 = new Cookie("consumerName", consumerNameFromDB);
					userData2.setMaxAge(60*60);
					Cookie userData3 = new Cookie("allowLogin", Boolean.toString(allowLogin));
					userData3.setMaxAge(60*60);
					response.addCookie(userData);
					response.addCookie(userData2);
					response.addCookie(userData3);
					RequestDispatcher rd = request.getRequestDispatcher("BillServlet");
					rd.forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				allowLogin = false;
				e.printStackTrace();
			}
		}
		else {
			try {
				returnedArr = DataFetcher.validateCustomerByConsumerNumber(consumerNumber, password);
				String consumerNumberFromDB = returnedArr[0];
				String consumerNameFromDB = returnedArr[1];
				System.out.println("consumer number in login servlet after fetching details:"+consumerNumberFromDB);
				System.out.println("consumer name in login servlet after fetching details:"+consumerNameFromDB);
				allowLogin = Boolean.parseBoolean(returnedArr[2]);
				System.out.println("Allow login in login servlet after fetching details:"+allowLogin);
				if(allowLogin) {
					System.out.println("Login successful. Redirecting to Bill Servlet");
//					request.setAttribute("consumerNumber", consumerNumber);
//					System.out.println("Consumer number in Login Servlet: "+consumerNumber);
					Cookie userData = new Cookie("consumerNumber",consumerNumberFromDB);
					userData.setMaxAge(60*60);
					Cookie userData2 = new Cookie("consumerName", consumerNameFromDB);
					userData2.setMaxAge(60*60);
					Cookie userData3 = new Cookie("allowLogin", Boolean.toString(allowLogin));
					userData3.setMaxAge(60*60);
					response.addCookie(userData);
					response.addCookie(userData2);
					response.addCookie(userData3);
					RequestDispatcher rd = request.getRequestDispatcher("BillServlet");
					rd.forward(request, response);
					//response.sendRedirect("BillServlet");
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				allowLogin = false;
				e.printStackTrace();
			}
		}
		
	}
	
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String consumerNumber = request.getParameter("customerId");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("Running Login Servlet");
		boolean allowLogin = false;
		String[] returnedArr = new String[3];
		if(consumerNumber==null) {
			try {
				returnedArr = DataFetcher.validateCustomerByEmail(email, password);
				String consumerNumberFromDB = returnedArr[0];
				String consumerNameFromDB = returnedArr[1];
				System.out.println("consumer number in login servlet after fetching details:"+consumerNumberFromDB);
				System.out.println("consumer name in login servlet after fetching details:"+consumerNameFromDB);
				allowLogin = Boolean.parseBoolean(returnedArr[2]);
				System.out.println("Allow login in login servlet after fetching details:"+allowLogin);
				if(allowLogin) {
					System.out.println("Login successful. Redirecting to Bill Servlet");
//					request.setAttribute("consumerNumber", consumerNumberFromDB);
//					System.out.println("Consumer number in Login Servlet: "+consumerNumber);
					Cookie userData = new Cookie("consumerNumber",consumerNumberFromDB);
					userData.setMaxAge(60*60);
					Cookie userData2 = new Cookie("consumerName", consumerNameFromDB);
					userData2.setMaxAge(60*60);
					Cookie userData3 = new Cookie("allowLogin", Boolean.toString(allowLogin));
					userData3.setMaxAge(60*60);
					response.addCookie(userData);
					response.addCookie(userData2);
					response.addCookie(userData3);
					RequestDispatcher rd = request.getRequestDispatcher("BillServlet");
					rd.forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				allowLogin = false;
				e.printStackTrace();
			}
		}
		else {
			try {
				returnedArr = DataFetcher.validateCustomerByConsumerNumber(consumerNumber, password);
				String consumerNumberFromDB = returnedArr[0];
				String consumerNameFromDB = returnedArr[1];
				System.out.println("consumer number in login servlet after fetching details:"+consumerNumberFromDB);
				System.out.println("consumer name in login servlet after fetching details:"+consumerNameFromDB);
				allowLogin = Boolean.parseBoolean(returnedArr[2]);
				System.out.println("Allow login in login servlet after fetching details:"+allowLogin);
				if(allowLogin) {
					System.out.println("Login successful. Redirecting to Bill Servlet");
//					request.setAttribute("consumerNumber", consumerNumber);
//					System.out.println("Consumer number in Login Servlet: "+consumerNumber);
					Cookie userData = new Cookie("consumerNumber",consumerNumberFromDB);
					userData.setMaxAge(60*60);
					Cookie userData2 = new Cookie("consumerName", consumerNameFromDB);
					userData2.setMaxAge(60*60);
					Cookie userData3 = new Cookie("allowLogin", Boolean.toString(allowLogin));
					userData3.setMaxAge(60*60);
					response.addCookie(userData);
					response.addCookie(userData2);
					response.addCookie(userData3);
					RequestDispatcher rd = request.getRequestDispatcher("BillServlet");
					rd.forward(request, response);
					//response.sendRedirect("BillServlet");
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				allowLogin = false;
				e.printStackTrace();
			}
		}
		
	}
}
