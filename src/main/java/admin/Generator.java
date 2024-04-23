package admin;

public class Generator {
	static long consumerNumberCount = 1000000000000L;
	static int billNumberCount = 10000;
	static long userIdCount = 10000000L;
	static int complaintIdCount = 10000;
	public static long getConsumerNumber() {
		return consumerNumberCount++;
	}
	public static int getbillNumber() {
		return billNumberCount++;
	}
	public static long getUserId() {
		return userIdCount++;
	}
	public static int complaintId() {
		return complaintIdCount++;
	}

}
