package customer;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DataFetcher;

@WebServlet("/BillServlet")
public class BillServlet extends HttpServlet{

	
	public void doPost (HttpServletRequest request, HttpServletResponse response) {
			
			//Fetching values from cookies
			Cookie[] cookies = request.getCookies();
			String consumerNumberFromCookie = null;
			String allowLoginFromCookie = null;
			if(cookies!=null) {
				for(Cookie c: cookies) {
					String cookieName = c.getName();
					if(cookieName.equals("consumerNumber")) {
						consumerNumberFromCookie = c.getValue();
					}
					if(cookieName.equals("allowLogin")) {
						allowLoginFromCookie = c.getValue();
					}
				}
			}else {
				System.out.println("No cookies found in bill servlet");
			}
			
			System.out.println("Consumer number in Bill servlet:"+consumerNumberFromCookie);
			System.out.println("Allow login in Bill servlet:"+allowLoginFromCookie);
			try {
				ArrayList<Bill> bills = DataFetcher.getBillDetails(consumerNumberFromCookie);
				request.setAttribute("bills", bills);
				System.out.println("Bills arrayList in Bill Servlet: "+bills);
				System.out.println("Redirecting to bill.jsp");
//				RequestDispatcher requestDispatcher = request.getRequestDispatcher("bill.jsp");
//				requestDispatcher.forward(request, response);
				response.sendRedirect("bill.jsp");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
