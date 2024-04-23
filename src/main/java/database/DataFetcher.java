package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import customer.Bill;
import customer.BillHistory;
import customer.ComplaintDetails;
import customer.Customer;

public class DataFetcher {
	public static Connection establishConnection() throws ClassNotFoundException, SQLException {
		boolean status = false;
		final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		Class.forName(driver);
		final String JDBC_URL = "jdbc:derby:C:\\Users\\bhara\\MyDB;create=true";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(JDBC_URL);
			if (connection != null) {
				System.out.println("Connection established successfully");
				status = true;
			}
		} catch (SQLException e) {
			System.out.println("Unable to establish connection");
			e.printStackTrace();
			status = false;
		}
		return connection;
	}

	public static ArrayList<String> getAcknoledgementDetails(String number) throws ClassNotFoundException, SQLException {
		boolean status = false;
		ArrayList<String> returnList = new ArrayList<String>();
		Connection connection = null;
		try {
			connection = establishConnection();
			if (connection!=null) {
				String sql = "select name,mobileNumber from CUSTOMER where consumerNumber=?";
				PreparedStatement ps = connection.prepareStatement(sql);
				Long consumerNumber = Long.parseLong(number);
				ps.setLong(1, consumerNumber);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					String name = rs.getString(1);
					long mobileNumber = rs.getLong(2);
					returnList.add(name);
					returnList.add("" + mobileNumber);
				}
				System.out.println("Details Fetched Successfully");
				status = true;
			}
		} catch (Exception e) {
			System.out.println("Error in fetching Acknowledgement details");
			e.printStackTrace();
			status = false;
		}
		return returnList;
	}

	public static String[] validateCustomerByEmail(String email, String password) throws ClassNotFoundException, SQLException {
		boolean allowLogin = false;
		Connection connection = null;
		String[] returnArr = new String[3];
		try {
			connection = establishConnection();
			if (connection!=null) {
				String sql = "select consumerNumber,password,name from CUSTOMER where email=?";
				String consumerNumber = null;
				String passwordFromDB = null;
				String consumerName = null;
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, email);
				ResultSet resultTable = ps.executeQuery();
				while (resultTable.next()) {
					consumerNumber = resultTable.getString(1);
					passwordFromDB = resultTable.getString(2);
					consumerName = resultTable.getString(3);
					
				}
				returnArr[0] = consumerNumber;
				returnArr[1] = consumerName.substring(0, consumerName.indexOf(" "));
				if (password.equals(passwordFromDB)) {
					allowLogin = true;
				}
			}
		} catch (SQLException e) {
			allowLogin = false;
			System.out.println("SQL Exception occured while validating with customer email");
			e.printStackTrace();
		} catch (Exception e) {
			allowLogin = false;
			System.out.println("Exception occured while validating with customer email");
			e.printStackTrace();
		}
		returnArr[2] = ""+allowLogin;
		return returnArr;
	}

	public static String[] validateCustomerByConsumerNumber(String consumerNumber, String password) throws ClassNotFoundException, SQLException {
		boolean allowLogin = false;
		String[] returnArr = new String[3];
		Connection connection = null;
		try {
			connection = establishConnection();
			if (connection!=null) {
				String passwordFromDB = null;
				String consumerNameFromDB = null;
				String sql = "select password,name from CUSTOMER where consumerNumber=?";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, consumerNumber);
				ResultSet resultTable = ps.executeQuery();
				while (resultTable.next()) {
					passwordFromDB = resultTable.getString(1);
					consumerNameFromDB = resultTable.getString(2);
				}
				returnArr[0] = consumerNumber;
				returnArr[1] = consumerNameFromDB.substring(0, consumerNameFromDB.indexOf(" "));
				if (password.equals(passwordFromDB)) {
					allowLogin = true;
				} else {
					allowLogin = false;
				}
				returnArr[2] = ""+allowLogin;
			}
		} catch (SQLException e) {
			allowLogin = false;
			System.out.println("SQL Exception occured while validating with customer Id");
			e.printStackTrace();
		} catch (Exception e) {
			allowLogin = false;
			System.out.println("Exception occured while validating with customer Id");
			e.printStackTrace();
		}
		return returnArr;
	}

	public static ArrayList<Bill> getBillDetails(String consumerNumber) throws ClassNotFoundException, SQLException {
		ArrayList<Bill> bills = new ArrayList<Bill>();
		boolean billDetailsFetched = false;
		Connection connection = null;
		try {
			connection = establishConnection();
			if (connection!=null) {
				boolean billTableExists = DataInjector.createBillTable();
				if (billTableExists) {
					String sql = "select billNumber, billAmount, dueAmount, billPaid from BILL where consumerNumber=?";
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setString(1, consumerNumber);
					System.out.println("Consumer number in  DataFetcher.getBillDetails"+consumerNumber);
					ResultSet resultSet = ps.executeQuery();
					while (resultSet.next()) {
						int billNumber = resultSet.getInt(1);
						int billAmount = resultSet.getInt(2);
						int dueAmount = resultSet.getInt(3);
						boolean billPaid = resultSet.getBoolean(4);
						System.out.println("----------------");
						System.out.println(billNumber);
						System.out.println(billAmount);
						System.out.println(dueAmount);
						System.out.println(billPaid);
						System.out.println("----------------");
						Bill billObject = new Bill(billNumber, billAmount, dueAmount,billPaid);
						bills.add(billObject);
					}
					billDetailsFetched = true;
				}
			}

		} catch (SQLException e) {
			System.out.println("SQL Exception occured while fetching bill details");
			e.printStackTrace();
			billDetailsFetched = false;
		} catch (Exception e) {
			System.out.println("Exception occured while fetching bill details");
			e.printStackTrace();
			billDetailsFetched = false;
		}
		System.out.println("Bill details Fetched:"+billDetailsFetched);
		return bills;
	}
	
	public static ArrayList<String> getCardDetails() {
		ArrayList<String> cardDetailsFromDB = new ArrayList<String>();
		boolean cardDetailsFetchedFromDB = false;
		try {
			Connection connection = establishConnection();
			if(connection!=null) {
				String sql = "select * from CARD";
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
					long cardNumberFromDB = resultSet.getLong(1);
					int monthFromDB = resultSet.getInt(2);
					int yearFromDB = resultSet.getInt(3);
					int cvvFromDB = resultSet.getInt(4);
					String cardHolderNameFromDB = resultSet.getString(5);
					cardDetailsFromDB.add(""+cardNumberFromDB);
					cardDetailsFromDB.add(""+monthFromDB);
					cardDetailsFromDB.add(""+yearFromDB);
					cardDetailsFromDB.add(""+cvvFromDB);
					cardDetailsFromDB.add(""+cardHolderNameFromDB);
				}
				cardDetailsFetchedFromDB = true;
			}
			
		} catch(SQLException e) {
			System.out.println("SQL Exception occured in Fetching card Details");
			e.printStackTrace();
		} catch(Exception e) {
			System.out.println("Exception occured in Fethcing card details");
			e.printStackTrace();
		}
		return cardDetailsFromDB;
	}
	
	public static boolean validateAdminDetails(String email, String password) {
		boolean allowLogin = false;
		try {
			Connection connection = establishConnection();
			if(connection!=null) {
				String sql = "select  password, activestatus from ADMIN where email=?";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1,email);
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					String passwordFromDB = resultSet.getString(1);
					boolean activeStatusFromDB = resultSet.getBoolean(2);
					if(activeStatusFromDB && password.equals(passwordFromDB)) {
						allowLogin = true;
					}
				}
				
			}
		}catch(SQLException e) {
			System.out.println("SQL Exception occured while validating admin login");
			allowLogin = false;
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("Exception occured while validation admin login");
			allowLogin = false;
			e.printStackTrace();
		}
		return allowLogin;
	}
	
	public static String getCustomerDetailsByConsumerIdForAdmin(String consumerNumber){
		Customer customer = null;
		String customerDetails = null;
		try {
			Connection connection = establishConnection();
			String sql = "select consumerNumber, gender, email, name, country, mobileNumber from CUSTOMER where consumerNumber=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, consumerNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				long consumerNumberFromDB = resultSet.getLong(1);
				String genderFromDB = resultSet.getString(2);
				String emailFromDB = resultSet.getString(3);
				String nameFromDB = resultSet.getString(4);
				String countryFromDB = resultSet.getString(5);
				long mobileNumberFromDB = resultSet.getLong(6);
				customer = new Customer(consumerNumberFromDB,genderFromDB, emailFromDB, nameFromDB, countryFromDB, mobileNumberFromDB);
				customerDetails = customer.toString();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return customerDetails;
	}
	
	public static ArrayList<String> getCustomersDetailsByEmailForAdmin(String email){
		ArrayList<String> customerDetails = new ArrayList<String>();
		try {
			Connection connection = establishConnection();
			if(connection!=null) {
				String sql ="select consumerNumber, gender, email, name, country, mobileNumber from CUSTOMER where email LIKE ?";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, email);
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					long consumerNumberFromDB = resultSet.getLong(1);
					String genderFromDB = resultSet.getString(2);
					String emailFromDB = resultSet.getString(3);
					String nameFromDB = resultSet.getString(4);
					String countryFromDB = resultSet.getString(5);
					long mobileNumberFromDB = resultSet.getLong(6);
					Customer customer = new Customer(consumerNumberFromDB,genderFromDB, emailFromDB, nameFromDB, countryFromDB, mobileNumberFromDB);
					customerDetails.add(customer.toString());
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return customerDetails;
	}
	
	public static ArrayList<BillHistory> getBillHistory(String consumerNumber){
		ArrayList<BillHistory> billHistory = new ArrayList<BillHistory>();
		try {
			Connection connection = establishConnection();
			if(connection!=null) {
				String sql = "select * from BILLHISTORY where consumerNumber=? order by paymentDate desc";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setLong(1, Long.parseLong(consumerNumber));
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					int billNumberFromDB = resultSet.getInt(1);
					long consumerNumberFromDB = resultSet.getLong(2);
					String paymentTypeFromDB = resultSet.getString(3);
					String paymentDateFromDB = ""+resultSet.getTimestamp(4);
					boolean paymentStatusFromDB = resultSet.getBoolean(5);
					BillHistory obj = new BillHistory(billNumberFromDB, consumerNumberFromDB, paymentTypeFromDB, paymentDateFromDB, paymentStatusFromDB);
					billHistory.add(obj);
					System.out.println("Bill history fetched successfully from database");
				}
				
			}
		}catch(SQLException e) {
			System.out.println("SQLException occured while fetching values for Bill history");
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Exception occured while fetching values for Bill history");
			e.printStackTrace();
		}
		return billHistory;
	}
	
	public static ArrayList<ComplaintDetails> getComplaintDetails(String consumerNumber){
		ArrayList<ComplaintDetails> complaintDetails = new ArrayList<ComplaintDetails>();
		try{
			Connection connection = establishConnection();
			if(connection!=null) {
				String sql = "select * from COMPLAINT where consumerNumber=?";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setLong(1, Long.parseLong(consumerNumber));
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					int complaintIdFromDB = resultSet.getInt(1);
					String complaintTypeFromDB = resultSet.getString(2);
					String landmarkFromDB = resultSet.getString(3);
					String categoryFromDB = resultSet.getString(4);
					long consumerNumberFromDB = resultSet.getLong(5);
					String contactPersonFromDB = resultSet.getString(6);
					String problemDescriptionFromDB = resultSet.getString(7);
					long mobileNumberFromDB = resultSet.getLong(8);
					String addressFromDB = resultSet.getString(9);
					boolean statusFromDB = resultSet.getBoolean(10);
					ComplaintDetails obj = new ComplaintDetails(complaintIdFromDB, complaintTypeFromDB, landmarkFromDB, categoryFromDB, consumerNumberFromDB, contactPersonFromDB, problemDescriptionFromDB, mobileNumberFromDB, addressFromDB, statusFromDB);
					complaintDetails.add(obj);
				}
			}
		}catch(SQLException e) {
			System.out.println("SQL Exception occured while fetching complaint details");
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("Exception occured whie fetching complaint details");
			e.printStackTrace();
		}
		return complaintDetails;
	}

}
