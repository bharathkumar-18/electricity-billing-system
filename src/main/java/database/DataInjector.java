package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class DataInjector {
	
	public static Connection establishConnection() throws ClassNotFoundException, SQLException {
		final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		Class.forName(driver);
		final String JDBC_URL = "jdbc:derby:C:\\Users\\bhara\\MyDB;create=true";
		Connection connection = null;
		
   try {
			connection = DriverManager.getConnection(JDBC_URL);
			if (connection != null) {
				System.out.println("Connection successful.");
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed. " + e);
			e.printStackTrace();
			
		}
   return connection;
	}
	
	
	public static boolean createCustomerTable(Connection connection) throws ClassNotFoundException, SQLException {
		
		 boolean status = false;
		 String sql = "create table CUSTOMER (consumerNumber bigint,billNumber int,gender varchar(6),email varchar(30),name varchar(30),country varchar(20),mobileNumber bigint,userId bigint,password varchar(20), activeStatus boolean, primary key(consumerNumber))";
				try {
					Statement statement = connection.createStatement();
					statement.execute(sql);
					System.out.println("Table created!");
					status = true;
				}catch(Exception e){
					System.out.println("Table already Exist");
					System.out.println(e.getMessage());
					if(e.getMessage().equals("Table/View 'CUSTOMER' already exists in Schema 'APP'.")) {
						status = true;
						return status;
					}
					else {
						e.printStackTrace();
						status = false;
					}
				}
		return status;
	}
	
	public static boolean RegisterCustomer(long consumerNumber, int billNumber, String gender, String email, String name, String country, long mobileNumber, long userId, String password) throws ClassNotFoundException, SQLException {
		boolean status = false;
			try {
				Connection connection = establishConnection();
				if(connection!=null) {
				createCustomerTable(connection);
				String sql = "insert into CUSTOMER (consumerNumber,billNumber,gender,email,name,country,mobileNumber,userId,password,activeStatus) Values (?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setLong(1, consumerNumber);
				ps.setInt(2, billNumber);
				ps.setString(3, gender);
				ps.setString(4, email);
				ps.setString(5, name);
				ps.setString(6, country);
				ps.setLong(7, mobileNumber);
				ps.setLong(8, userId);
				ps.setString(9, password);
				boolean activeStatus = true;
				ps.setBoolean(10, activeStatus);
				ps.execute();
				status = true;
			}
			}
			catch(SQLException e) {
				System.out.println("Error in inserting values into Customer Table");
				e.printStackTrace();
				status=false;
			}
			catch(Exception e) {
				System.out.println("Error while inserting values");
				e.printStackTrace();
				status = false;
			}
			
		return status;	
	}
	
	
	
	public static boolean createBillTable() throws ClassNotFoundException, SQLException {
		boolean status = false;
		try {
			Connection connection =  establishConnection();
			if(connection!=null) {
				String sql = "create table BILL (billNumber int, consumerNumber bigint, billAmount int, dueAmount int, billPaid boolean)";
				Statement statement = connection.createStatement();
				statement.execute(sql);
				status = true;
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			if(e.getMessage().equals("Table/View 'BILL' already exists in Schema 'APP'.")) {
				status = true;
				return status;
			}
		}catch(Exception e) {
			System.out.println("Error occured while creating bill table");
			e.printStackTrace();
			status = false;
		}
		return status;
	}
	
	public static boolean createAdminTable (Connection connection) {
		boolean status = false;
		String sql = "create table ADMIN (name varchar(30), email varchar(30), password varchar(30), activestatus boolean, primary key(email) )";
		try {
				Statement statement = connection.createStatement();
				statement.execute(sql);
				status = true;
		} catch(SQLException e) {
			System.out.println("SQL Exception occured while registering admin");
			System.out.println(e.getMessage());
			if(e.getMessage().equals("Table/View 'ADMIN' already exists in Schema 'APP'.")) {
				status = true;
				return status;
			}
		} catch(Exception e) {
			System.out.println("Exception occured while registering admin");
			e.printStackTrace();
			status = false;
		}
		return status;
	}
	
	public static boolean registerAdmin(String name, String email, String password) {
		boolean status = false;
		try {
			Connection connection = establishConnection();
			if(connection!=null) {
				boolean isAdminTableCreated = createAdminTable(connection);
				if(isAdminTableCreated) {
					String sql = "insert into ADMIN values (?,?,?,?)";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, name);
					preparedStatement.setString(2, email);
					preparedStatement.setString(3, password);
					preparedStatement.setBoolean(4, true);
					preparedStatement.execute();
					status = true;
				}
			}
		}catch(SQLException e) {
			System.out.println("SQL Exception occured while inserting values into admin table");
			status = false;
			e.printStackTrace();
		} catch(Exception e) {
			System.out.println("Exception occured while inserting values into admin table");
			status = false;
		}
		return status;
	}
	
	public static boolean createComplaintTable(Connection connection) {
		boolean status = false;
		String sql = "create table COMPLAINT (complaintId int check (complaintId between 10000 and 99999), complaintType varchar(30), landmark varchar(30), category varchar(20), consumerNumber bigint check (consumerNumber between 1000000000000 and 9999999999999), contactPerson varchar(30), problemDescription varchar(500), mobileNumber bigint, address varchar(300), complaintResolved boolean)";
		try {
			connection = establishConnection();
			if(connection!=null) {
				Statement statement = connection.createStatement();
				statement.execute(sql);
				status = true;
			}
		}catch(SQLException e) {
			System.out.println("SQL Exception occured while creating complaint table");
			System.out.println(e.getMessage());
			if(e.getMessage().equals("Table/View 'COMPLAINT' already exists in Schema 'APP'.")) {
				status = true;
				return status;
			}
		} catch(Exception e) {
			System.out.println("Exception occured while creating complaint table");
			e.printStackTrace();
			status = false;
		}
		return status;
	}
	
	public static boolean registerComplaint(int complaintId, String complaintType, String landmark, String category, long consumerNumber, String contactPerson, String problemDescription, long mobileNumber, String address) {
		boolean status = false;
		try {
			Connection connection = establishConnection();
			
			if(connection!=null) {
				boolean isComplaintTableCreated = createComplaintTable(connection);
				if(isComplaintTableCreated) {
					String sql = "insert into COMPLAINT values (?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, complaintId);
					preparedStatement.setString(2, complaintType);
					preparedStatement.setString(3, landmark);
					preparedStatement.setString(4, category);
					preparedStatement.setLong(5, consumerNumber);
					preparedStatement.setString(6, contactPerson);
					preparedStatement.setString(7, problemDescription);
					preparedStatement.setLong(8, mobileNumber);
					preparedStatement.setString(9, address);
					preparedStatement.setBoolean(10, false);
					preparedStatement.execute();
					status = true;
				}
			}
		}catch(SQLException e) {
			System.out.println("SQL Exception occured while inserting values into complaint table");
			status = false;
			e.printStackTrace();
		} catch(Exception e) {
			System.out.println("Exception occured while inserting values into complaint table");
			status = false;
		}
		return status;
	}
	
	public static boolean updateBillPaidStatusToTrue(String[] billNumbers) {
		boolean status = false;
		try {
			Connection connection = establishConnection();
			if(connection!=null) {
				boolean isBillTableCreated = createBillTable();
				if(isBillTableCreated) {
					for(String i: billNumbers) {
						String sql = "update BILL set billpaid=true where billNumber=?";
						PreparedStatement preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setInt(1, Integer.parseInt(i));
						preparedStatement.execute();
					}
					status = true;
				}
			}
		}catch(SQLException e) {
			System.out.println("SQL Exception occured while updating bill paid status");
			e.printStackTrace();
			status = false;
		}catch(Exception e) {
			System.out.println("Exception occured while updating bill status");
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	public static boolean createBillHistoryTable(Connection connection) {
		boolean status = false;
		String sql = "create table BILLHISTORY (billNumber int check (billNumber between 10000 and 99999), consumerNumber bigint check(consumerNumber between 1000000000000 and 9999999999999), paymentType varchar(20), paymentDate timestamp, status boolean)";
		try {
			connection = establishConnection();
			if(connection!=null) {
				Statement statement = connection.createStatement();
				statement.execute(sql);
				status = true;
			}
		}catch(SQLException e) {
			System.out.println("SQL Exception occured while creating billHistory table");
			System.out.println(e.getMessage());
			if(e.getMessage().equals("Table/View 'BILLHISTORY' already exists in Schema 'APP'.")) {
				status = true;
				return status;
			}
		} catch(Exception e) {
			System.out.println("Exception occured while creating billHistory table");
			e.printStackTrace();
			status = false;
		}
		return status;
	}
	
	public static boolean addPaidBillToBillHistory(String[] billNumbers, String consumerNumber) {
		boolean status = false;
		try {
			Connection connection = establishConnection();
			if(connection!=null) {
				boolean isBillHistoryTableCreated = createBillHistoryTable(connection);
				if(isBillHistoryTableCreated) {
					System.out.println("Trying to add paid bills into bills history");
					for(String i: billNumbers) {
						String sql = "insert into BILLHISTORY values (?,?,?,?,?)";
						PreparedStatement preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setInt(1, Integer.parseInt(i));
						preparedStatement.setLong(2, Long.parseLong(consumerNumber));
						preparedStatement.setString(3, "Card");
						Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
						preparedStatement.setTimestamp(4, currentTimestamp);
						preparedStatement.setBoolean(5, true);
						preparedStatement.execute();
					}
					status = true;
				}
				System.out.println("Successfully added paid bills to bill history");
			}
		}catch(SQLException e) {
			System.out.println("SQL Exception while adding bill to bill history");
			e.printStackTrace();
			status = false;
		}catch(Exception e) {
			System.out.println("Exception while adding bill to bill history");
			e.printStackTrace();
			status = false;
		}
		return status;
	}
	
	
}
