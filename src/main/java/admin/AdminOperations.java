package admin;

import java.util.Scanner;

import database.DataFetcher;
import database.DataInjector;

public class AdminOperations {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome Admin");
		System.out.println("-----------------------------------");
		char operation = '0';
		do {
			System.out.println("1. Register as admin");
			System.out.println("2. Login as admin");
			System.out.println("3. Exit");
			System.out.println("Choose your operation:");
			operation = input.nextLine().charAt(0);
			inner:switch(operation) {
			case '1': 
				System.out.println("Initiating registration");
				System.out.println("Enter your name:");
				String nameToRegister = input.nextLine();
				System.out.println("Enter your admin email:");
				String emailToRegister = input.nextLine();
				System.out.println("Enter your password:");
				String passwordToRegister = input.nextLine();
				boolean isAdminRegistered = DataInjector.registerAdmin(nameToRegister, emailToRegister, passwordToRegister);
				if(isAdminRegistered) {
					System.out.println("You have been registered successfully. Now go to login and enter your credentials");
				}
				break inner;
				
			case '2':
				System.out.println("-----------------------------------");
				System.out.println("Logging you in... Kindly enter your credentials");
				System.out.println("Enter your emailId: ");
				String emailToLogin = input.nextLine();
				System.out.println("Enter your password: ");
				String passwordToLogin = input.nextLine();
				boolean allowLogin = DataFetcher.validateAdminDetails(emailToLogin, passwordToLogin);
				if(allowLogin) {
					System.out.println("Logging you in... Please wait");
					System.out.println("-----------------------------------");
					AdminAfterLoginOperations.afterLoginOperations();
				}
				else {
					System.out.println("Invalid Login details. Check your credentials and enter again");
					System.out.println("-----------------------------------");
				}
				break inner;
			case '3':
				System.out.println("Giving you an exit... Please wait");
				System.out.println("-----------------------------------");
				break inner;
			default:
				System.out.println("Invalid input. Enter the correct input to proceed further");
				System.out.println("If you want to exit, choose option 3");
				break inner;
			}
			
		}
		while(operation!='3');
		System.out.println("Successfully logged out. Thank you for being a valuable member");
	}
}
