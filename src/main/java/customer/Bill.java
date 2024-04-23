package customer;

public class Bill {
	private int billNumber;
	private int billAmount;
	private int dueAmount;
	private boolean billPaid;
	public Bill (int billNumber, int billAmount, int dueAmount, boolean billPaid){
		this.billNumber = billNumber;
		this.billAmount = billAmount;
		this.dueAmount = dueAmount;
		this.billPaid = billPaid;
	}
	public int getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(int billNumber) {
		this.billNumber = billNumber;
	}
	public int getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(int billAmount) {
		this.billAmount = billAmount;
	}
	public int getDueAmount() {
		return dueAmount;
	}
	public void setDueAmount(int dueAmount) {
		this.dueAmount = dueAmount;
	}
	public boolean getBillPaid() {
		return billPaid;
	}
	public void setBillPaid(boolean billPaid) {
		this.billPaid = billPaid;
	}
	

}
