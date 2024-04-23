package customer;

public class Customer {
	
	
	private long consumerNumber;
	private String gender;
	private String email;
	private String name;
	private String country;
	private long mobileNumber;
	public Customer(long consumerNumber, String gender, String email, String name, String country,
			long mobileNumber) {
		super();
		this.consumerNumber = consumerNumber;
		this.gender = gender;
		this.email = email;
		this.name = name;
		this.country = country;
		this.mobileNumber = mobileNumber;
	}
	public long getConsumerNumber() {
		return consumerNumber;
	}
	public void setConsumerNumber(long consumerNumber) {
		this.consumerNumber = consumerNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Override
	public String toString() {
		return "Customer [consumerNumber=" + consumerNumber + ", gender=" + gender + ", email=" + email + ", name="
				+ name + ", country=" + country + ", mobileNumber=" + mobileNumber + "]";
	}
}
