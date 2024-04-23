package customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GoToLoginServlet")
public class GoToLoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		//Delete the cookies
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {			
			for(Cookie cookie: cookies) {
				cookie.setValue("");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			System.out.println("Deleted the cookies before login");
			System.out.println("Forwarding request and response after clearing cookies to login page");
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
//			requestDispatcher.forward(request, response);
			response.sendRedirect("login.jsp");
		}else {
			response.sendRedirect("login.jsp");
		}
	}

}
