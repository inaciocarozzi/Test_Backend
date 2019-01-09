package br.com.backend.rest.data;

public class TransactionData {
	private long timestamp;
	private double amount;

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "TransactionData [timestamp=" + timestamp + ", amount=" + amount + "]";
	}

}
