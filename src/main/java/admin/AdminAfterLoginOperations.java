package admin;

import java.util.ArrayList;
import java.util.Scanner;

import customer.Customer;
import database.DataFetcher;

public class AdminAfterLoginOperations {
	public static void afterLoginOperations() {
		Scanner input = new Scanner(System.in);
		char operationAfterLogin = '0';
		do {
			System.out.println("Login operations:");
			System.out.println("1. Search Customer Details:");
			System.out.println("2. Register a Complaint:");
			System.out.println("3. Logout->");
			System.out.println("Choose your operation:");
			operationAfterLogin = input.nextLine().charAt(0);
			switch(operationAfterLogin) {
			case '1': 
				System.out.println("-----------------------------------");
				searchCustomer();
				break;
			case '2':
				System.out.println("Registering complaint");
				break;
			case '3':
				System.out.println("Logging you out... Please wait");
				System.out.println("-----------------------------------");
				break;
			default:
				System.out.println("Invalid input. Enter the correct input to proceed further");
				System.out.println("If you want to exit, choose option 3");
			}
			
		}while(operationAfterLogin!='3');
	}
	
	public static void searchCustomer() {
		Scanner input = new Scanner(System.in);
		System.out.println("1. Search customer by consumerID:");
		System.out.println("2. Search customers by email domain");
		System.out.println("Choose your operation:");
		char searchCustomerByType = input.nextLine().charAt(0);
		switch(searchCustomerByType) {
		case '1':
			System.out.println("Enter the consumer ID:");
			String consumerNumber = input.nextLine();
			String customerDetailsByConsumerId = DataFetcher.getCustomerDetailsByConsumerIdForAdmin(consumerNumber);
			if(customerDetailsByConsumerId!=null) {
				System.out.println(customerDetailsByConsumerId);
				System.out.println("-----------------------------------");
			}else {
				System.out.println("No customer found with the consumer Id.");
				System.out.println("-----------------------------------");
			}
			break;
		case '2':
			System.out.println("Enter the email ID domain:");
			String email = "%"+input.nextLine()+"%";
			ArrayList<String> customerDetailsByEmailId = DataFetcher.getCustomersDetailsByEmailForAdmin(email);
			if(customerDetailsByEmailId.size()!=0) {
				for(String i: customerDetailsByEmailId) {
					System.out.println(i);
				}
				System.out.println("-----------------------------------");
			}else {
				System.out.println("No customer found with the email Id.");
				System.out.println("-----------------------------------");
			}
			break;
		}
		return;
	}
}
