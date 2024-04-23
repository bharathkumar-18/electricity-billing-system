package customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DataFetcher;

@WebServlet("/AcknowledgementServlet")
public class AcknowledgementServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
		//System.out.println("Consumer number in akcnowledgement serlvet:"+consumerNumber);
		try {
			
			//Fetching consumerNumber from cookie
			Cookie[] cookies = request.getCookies();
			String consumerNumberFromCookie = null;
			if(cookies!=null) {
				for(Cookie c: cookies) {
					String cookieName = c.getName();
					if(cookieName.equals("consumerNumber")) {
						consumerNumberFromCookie = c.getValue();
						System.out.println("Consumer number from cookie in acknowledgement servlet:"+ consumerNumberFromCookie);
					}
				}
			}
			ArrayList<String> returnList = DataFetcher.getAcknoledgementDetails(consumerNumberFromCookie);
			
			HttpSession session = request.getSession();
			session.setAttribute("consumerNumber", consumerNumberFromCookie);
			session.setAttribute("name", returnList.get(0));
			session.setAttribute("mobileNumber", returnList.get(1));
			response.sendRedirect("acknowledgement.jsp");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
