package customer;
import admin.Generator;
import database.DataInjector;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Trying to put data");
		
		//Get values from form data
		long consumerNumber = Long.parseLong(request.getParameter("consumer-number"));
		int billNumber = Generator.getbillNumber();
		String gender = null;
		String title = request.getParameter("title");
		if(title.equals("Mr.")) {
			gender = "Male";
		}
		else {
			gender = "Female";
		}
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String[] wordsInName = name.split(" ");
		String nameForCookie = wordsInName[0];
		System.out.println("Name in register servlet:"+name);
		String country = request.getParameter("mobile-code");
		System.out.println("Mobile code in register servlet"+country);
		long mobileNumber = Long.parseLong(request.getParameter("mobile-number"));
		System.out.println("Mobile number in register servlet:"+mobileNumber);
		long userId = Generator.getUserId();
		String password = request.getParameter("password");
		
		//Inject values into database;
		try {
			boolean customerRegistered = DataInjector.RegisterCustomer(consumerNumber, billNumber, gender, email, name, country, mobileNumber, userId, password);
			if(customerRegistered) {
				System.out.println("Your data has been submitted successfully");
				Cookie userData = new Cookie("consumerNumber", ""+consumerNumber);
				Cookie userData2 = new Cookie("consumerName", nameForCookie);
				System.out.println("Added cookies in Register Servlet to track ConsumerNumber and consumerName");
				response.addCookie(userData);
				response.addCookie(userData2);
				response.sendRedirect("AcknowledgementServlet");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error while inserting values");
			e.printStackTrace();
		}
		
		
		
		
	}

}
