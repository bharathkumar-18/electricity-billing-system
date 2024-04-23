package customer;

public class ComplaintDetails {
	private int complaintId;
	private String complaintType;
	private String landmark;
	private String category;
	private long consumerNumber;
	private String contactPerson;
	private String problemDescription;
	private long mobileNumber;
	private String address;
	private boolean status;
	public ComplaintDetails(int complaintId, String complaintType, String landmark, String category,
			long consumerNumber, String contactPerson, String problemDescription, long mobileNumber, String address,
			boolean status) {
		super();
		this.complaintId = complaintId;
		this.complaintType = complaintType;
		this.landmark = landmark;
		this.category = category;
		this.consumerNumber = consumerNumber;
		this.contactPerson = contactPerson;
		this.problemDescription = problemDescription;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.status = status;
	}
	public int getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}
	public String getComplaintType() {
		return complaintType;
	}
	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public long getConsumerNumber() {
		return consumerNumber;
	}
	public void setConsumerNumber(long consumerNumber) {
		this.consumerNumber = consumerNumber;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getProblemDescription() {
		return problemDescription;
	}
	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ComplaintDetails [complaintId=" + complaintId + ", complaintType=" + complaintType + ", landmark="
				+ landmark + ", category=" + category + ", consumerNumber=" + consumerNumber + ", contactPerson="
				+ contactPerson + ", problemDescription=" + problemDescription + ", mobileNumber=" + mobileNumber
				+ ", address=" + address + ", status=" + status + "]";
	}
	
}
