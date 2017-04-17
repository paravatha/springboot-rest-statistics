/**
 * 
 */
package org.springboot.stats.domain;

import java.io.Serializable;

/**
 * Statistics domain class
 * @author Prasad Paravatha
 */
public class Statistics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double sum;
	private double avg;
	private double max;
	private double min;
	private long count;
	
	/**
	 * @return the sum
	 */
	public double getSum() {
		return sum;
	}
	/**
	 * @return the avg
	 */
	public double getAvg() {
		return avg;
	}
	/**
	 * @return the max
	 */
	public double getMax() {
		return max;
	}
	/**
	 * @return the min
	 */
	public double getMin() {
		return min;
	}
	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}
	/**
	 * @param sum the sum to set
	 */
	public void setSum(double sum) {
		this.sum = sum;
	}
	/**
	 * @param avg the avg to set
	 */
	public void setAvg(double avg) {
		this.avg = avg;
	}
	/**
	 * @param max the max to set
	 */
	public void setMax(double max) {
		this.max = max;
	}
	/**
	 * @param min the min to set
	 */
	public void setMin(double min) {
		this.min = min;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(long count) {
		this.count = count;
	}
}
