package br.com.backend.rest.data;

public final class AppReference {
	private double sum;
	private double min = Double.MAX_VALUE;
	private double max;
	private double avg;
	private int count;

	public AppReference() {
		super();
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "AppReference [sum=" + sum + ", min=" + min + ", max=" + max + ", avg=" + avg + ", count=" + count + "]";
	}
}
