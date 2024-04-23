package customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ChoosePaymentServlet")
public class ChoosePaymentServlet extends HttpServlet{
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Fetch the billNumbers that have been selected to pay and sending to makepayment.jsp
		System.out.println("Checking values of bill numbers selected in Choose payment servlet");
		String[] billNumbers = request.getParameterValues("billsSelectedToPay");
		for(String i:billNumbers) {
			System.out.println(i);
		}
		request.setAttribute("billsSelectedToPay", billNumbers);
		
		
		//Sending the total amount to be paid to MakePayment.jsp
		String totalAmountPayable = (String) request.getParameter("totalAmount");
		request.setAttribute("totalAmountPayable", totalAmountPayable);
		System.out.println("Total amount in choosePaymentServlet:"+totalAmountPayable);
		//Sending request and response to makepayment.jsp
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("makePayment.jsp");
		requestDispatcher.forward(request, response);
	}

}
