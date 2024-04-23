package customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//Delete the cookies before logout
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {			
			for( Cookie cookie: cookies) { 
				String cookieName = cookie.getName();
				if(cookieName.equals("consumerNumber") || cookieName.equals("consumerName")) {
					System.out.println("-------------------------");
					System.out.println(cookie.getName());
					System.out.println(cookie.getMaxAge());
					System.out.println(cookie.getValue());
					cookie.setValue("");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					System.out.println(cookie.getName());
					System.out.println(cookie.getMaxAge());
					System.out.println(cookie.getValue());
					System.out.println("-------------------------");
				}
				if(cookieName.equals("allowLogin")) {
					System.out.println("-------------------------");
					System.out.println(cookie.getName());
					System.out.println(cookie.getMaxAge());
					System.out.println(cookie.getValue());
					cookie.setValue("false");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					System.out.println(cookie.getName());
					System.out.println(cookie.getMaxAge());
					System.out.println(cookie.getValue());
					System.out.println("-------------------------");
				}
			}
//			Cookie userData = new Cookie("consumerNumber", "");
//			response.addCookie(userData);
//			Cookie userData2 = new Cookie("consumerName", "");
//			response.addCookie(userData2);
//			Cookie userData3 = new Cookie("allowLogin", "false");
//			response.addCookie(userData3);
//			
			System.out.println("Deleted the cookies before logout");
			//Invalidate the session
			HttpSession session = request.getSession(false);
			if(session!=null) {
				session.invalidate();
			}
			
			System.out.println("Forwarding request and response after clearing cookies to login page after logout");
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
//			requestDispatcher.forward(request, response);
			response.sendRedirect("login.jsp");
		}else {
			response.sendRedirect("login.jsp");
		}
		System.out.println("You have been successfully logged out");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//Delete the cookies before logout
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {			
			for( Cookie cookie: cookies) { 
				String cookieName = cookie.getName();
				if(cookieName.equals("consumerNumber") || cookieName.equals("consumerName") || cookieName.equals("allowLogin") ) {
					System.out.println("-------------------------");
					System.out.println(cookie.getName());
					System.out.println(cookie.getMaxAge());
					System.out.println(cookie.getValue());
					cookie.setValue("");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					System.out.println(cookie.getName());
					System.out.println(cookie.getMaxAge());
					System.out.println(cookie.getValue());
					System.out.println("-------------------------");
				}
				if(cookieName.equals("allowLogin")) {
					System.out.println("-------------------------");
					System.out.println(cookie.getName());
					System.out.println(cookie.getMaxAge());
					System.out.println(cookie.getValue());
					cookie.setValue("false");
					cookie.setMaxAge(60*60*24*365*10);
					response.addCookie(cookie);
					System.out.println(cookie.getName());
					System.out.println(cookie.getMaxAge());
					System.out.println(cookie.getValue());
					System.out.println("-------------------------");
				}
			}
//			Cookie userData = new Cookie("consumerNumber", "");
//			response.addCookie(userData);
//			Cookie userData2 = new Cookie("consumerName", "");
//			response.addCookie(userData2);
//			Cookie userData3 = new Cookie("allowLogin", "false");
//			response.addCookie(userData3);
//			
			System.out.println("Deleted the cookies before logout");
			//Invalidate the session
			HttpSession session = request.getSession(false);
			if(session!=null) {
				session.invalidate();
			}
			
			System.out.println("Forwarding request and response after clearing cookies to login page after logout");
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
//			requestDispatcher.forward(request, response);
			response.sendRedirect("login.jsp");
		}else {
			response.sendRedirect("login.jsp");
		}
		System.out.println("You have been successfully logged out");
	}
}
