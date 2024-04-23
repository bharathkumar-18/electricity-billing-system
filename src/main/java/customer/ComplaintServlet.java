package customer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.Generator;
import database.DataInjector;

@WebServlet("/ComplaintServlet")
public class ComplaintServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		//Get values from complaint form
		int complaintId = Generator.complaintId();
		String complaintType = request.getParameter("complaint_type");
		String landmark = request.getParameter("landmark");
		String category = request.getParameter("category");
		long consumerNumber = Long.parseLong(request.getParameter("consumer_number"));
		String contactPerson = request.getParameter("contact_person");
		String problemDescription = request.getParameter("problem_description");
		long mobileNumber = Long.parseLong(request.getParameter("mobile_number"));
		String address = request.getParameter("address");
		
		try {
			boolean isComplaintRegistered = DataInjector.registerComplaint(complaintId, complaintType, landmark, category, consumerNumber, contactPerson, problemDescription, mobileNumber, address);
			if(isComplaintRegistered) {
				System.out.println("Complaint registered successfully");
			}
			HttpSession session = request.getSession();
			session.setAttribute("isComplaintRegistered", isComplaintRegistered);
			response.sendRedirect("complaintStatus.jsp");
		}catch(Exception e) {
			System.out.println("Exception occured while registering complaint");
			e.printStackTrace();
		}
		
	}
}
