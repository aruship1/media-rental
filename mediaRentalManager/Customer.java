package mediaRentalManager;

import java.util.ArrayList;

/**
 * Class that defines the basic characteristics associated 
 * with customers including name, address, and plan. Implements
 * the Comparable interface to compare the titles of received objects.
 * @author Pamela
 *
 */

public class Customer implements Comparable<Customer> {
	
	private String name;
	private String address;
	private String plan;
	
	protected ArrayList<String> rented;
	protected ArrayList<String> queue;
	
	/**
	 * Constructor sets title and copiesAvailable of current object
	 * @param title
	 * @param copiesAvailable
	 */
	
	public Customer(String name, String address, String plan){
		this.name = name;
		this.address = address;
		this.plan = plan;
		rented = new ArrayList<String>();
		queue = new ArrayList<String>();
		
		if(plan.equals("LIMITED")){
			MediaRentalManager lim = new MediaRentalManager();
			lim.setLimitedPlanLimit(2);
		}
	}
	
	/**
	 * Gets the current object's name
	 * @return name
	 */
	
	public String getName(){
		return this.name;
	}
	
	/**
	 * Sets current object's name  to parameter
	 * @param name
	 */
	
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Sets current object's plan  to parameter
	 * @param plan
	 */
	
	public void setPlan(String plan){
		this.plan = plan;
	}
	
	/**
	 * Gets the current object's address
	 * @return address
	 */
	
	public String getAddress(){
		return this.address;
	}
	
	/**
	 * Sets current object's address  to parameter
	 * @param address
	 */
	
	public void setAddress(String address){
		this.address = address;
	}
	
	/**
	 * Gets the current object's plan
	 * @return plan
	 */
	
	public String getPlan(){
		return this.plan;
	}
	
	/**
	 * Format's object info into a string
	 * @return string
	 */
	
	public String toString(){
		return "Name: " + this.getName() + ", Address: " + this.address + ", Plan: " + this.plan;
	}
	
	/**
	 * Receives an object and checks position in alphabet compared to customer o
	 * @param o
	 * @return integer < 1 if current object name is lexicographically ahead
	 * of o, 0 if it is the same, integer > 1 if it is ;later in the alphabet
	 */

	@Override
	public int compareTo(Customer o) {
		return this.name.compareTo(o.name);
	}

}
