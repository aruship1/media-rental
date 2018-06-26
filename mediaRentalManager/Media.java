package mediaRentalManager;

/**
 * Parent class that defines the basic characteristics associated 
 * with media including title, and copies available. Implements
 * the Comparable interface to compare the titles of received objects.
 * @author Pamela
 *
 */

public class Media implements Comparable<Media>{

	private String title;
	private int copiesAvailable;
	
	/**
	 * Constructor sets title and copiesAvailable of current object
	 * @param title
	 * @param copiesAvailable
	 */
	
	public Media(String title, int copiesAvailable){
		this.title = title;
		this.copiesAvailable = copiesAvailable;

	}
	
	/**
	 * Copy constructor
	 * @param m
	 */
	
	public Media(Media m){
		this(m.title, m.copiesAvailable);
	}
	
	/**
	 * Gets the current object's title
	 * @return title
	 */
	
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Gets the current object's copiesAvailable
	 * @return copiesAvailable
	 */
	
	public int getCopiesAvailable() {
		return this.copiesAvailable;
	}

	/**
	 * Sets current object's title  to parameter
	 * @param title
	 */

	public void setTitle(String title) {
		this.title = title;
	} 
	
	
	/**
	 * Sets current object's copiesAvailable  to parameter
	 * @param copiesAvailable
	 */
	
	public void setCopiesAvailable(int copiesAvailable) {
		this.copiesAvailable = copiesAvailable;
	}
	
	/**
	 * Receives an object and checks position in alphabet compared to Media m
	 * @param Media m
	 * @return integer < 1 if current object title is lexicographically ahead
	 * of o, 0 if it is the same, integer > 1 if it is ;later in the alphabet
	 */

	@Override
	public int compareTo(Media o) {
		return this.title.compareTo(o.title);

	}
	
	/**
	 * Receives an object and checks if it the same or has the same
	 * features as the current object
	 * @param Object m
	 * @return false if object is null or does not have the same title
	 */
	
	public boolean equals(Object m) {
		if (m == null) {
			return false;
		} else if (this == m){
			return true;
		} else {
			return this.title.equals(((Media) m).title);
		}
	}


}
