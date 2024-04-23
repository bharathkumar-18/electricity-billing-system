package customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DataFetcher;
import database.DataInjector;

@WebServlet("/MakePaymentServlet")
public class MakePaymentServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String cardNumber = request.getParameter("card-number");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String cvv = request.getParameter("cvv");
		String cardHolderName = request.getParameter("card-holder-name");
		
		System.out.println(cardNumber);
		System.out.println(month);
		System.out.println(year);
		System.out.println(cvv);
		System.out.println(cardHolderName);
		ArrayList<String> cardDetailsFromDB = DataFetcher.getCardDetails();
		System.out.println("Card Details from DB");
		System.out.println(cardDetailsFromDB.toString());
		
		boolean areCardDetailsCorrect = false;
		if(cardDetailsFromDB.get(0).equals(cardNumber) && cardDetailsFromDB.get(1).equals(month) && cardDetailsFromDB.get(2).equals(year) 
			&& cardDetailsFromDB.get(3).equals(cvv) && cardDetailsFromDB.get(4).equals(cardHolderName))
		{
			areCardDetailsCorrect = true;
		}
		
		//Fetching consumerNumber using cookies 
				Cookie[] cookies = request.getCookies();
				String consumerNumberFromCookie = null;
				if(cookies!=null){
					for(Cookie c: cookies){
						String cookieName = c.getName();
						if(cookieName.equals("consumerNumber")){
							consumerNumberFromCookie = c.getValue();
						}
					}
					System.out.println("Consumer Number in makePayment Servlet : "+consumerNumberFromCookie);
				}
		
		if(areCardDetailsCorrect){
			HttpSession sessionForBills = request.getSession();
			String[] billsSelectedToPay = (String[]) sessionForBills.getAttribute("billsSelectedToPay");
			System.out.println("Bills selected in makePaymentServlet:" +billsSelectedToPay);
			boolean areBillsStatusUpdated = DataInjector.updateBillPaidStatusToTrue(billsSelectedToPay);
			boolean areBillsAddedToBillHistory = DataInjector.addPaidBillToBillHistory(billsSelectedToPay, consumerNumberFromCookie);
			if(areBillsStatusUpdated && areBillsAddedToBillHistory) {
				request.setAttribute("areCardDetailsCorrect", areCardDetailsCorrect);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("paymentStatus.jsp");
				requestDispatcher.forward(request, response);				
			} else {
				System.out.println("Card details are correct but bills selected are not updated");
				request.setAttribute("areCardDetailsCorrect", areCardDetailsCorrect);
				request.setAttribute("areBillsStatusUpdated", billsSelectedToPay);
			}
		} else {
			request.setAttribute("areCardDetailsCorrect", areCardDetailsCorrect);
			
		}
		
	}
}
