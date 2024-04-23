package customer;

public class BillHistory {
	@Override
	public String toString() {
		return "BillHistory [billNumber=" + billNumber + ", consumerNumber=" + consumerNumber + ", paymentType="
				+ paymentType + ", paymentDate=" + paymentDate + ", status=" + status + "]";
	}
	private int billNumber;
	private long consumerNumber;
	private String paymentType;
	private String paymentDate;
	private boolean status;
	public BillHistory(int billNumber, long consumerNumber, String paymentType, String paymentDate, boolean status) {
		super();
		this.billNumber = billNumber;
		this.consumerNumber = consumerNumber;
		this.paymentType = paymentType;
		this.paymentDate = paymentDate;
		this.status = status;
	}
	public int getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(int billNumber) {
		this.billNumber = billNumber;
	}
	public long getConsumerNumber() {
		return consumerNumber;
	}
	public void setConsumerNumber(long consumerNumber) {
		this.consumerNumber = consumerNumber;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
