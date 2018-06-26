package mediaRentalManager;

/**
 * Child class of Media that defines the basic characteristics associated 
 * with a movie inheriting title, and copies available and initializing rating.
 * @author Pamela
 *
 */

public class Movie extends Media {

	private String rating;
	
	/**
	 * Constructor inherits title and copiesAvailable and initializes 
	 * ratings
	 * @param title
	 * @param copiesAvailable
	 * @param rating
	 */
	
	public Movie(String title, int copiesAvailable, String rating){
		super(title, copiesAvailable);
		this.rating = rating;
		
	}
	
	/**
	 * Copy constructor
	 * @param mov
	 */
	
	public Movie(Movie mov){
		this(mov.getTitle(),mov.getCopiesAvailable(),mov.getRating());
	}
	
	/**
	 * Format's object info into a string
	 * @return string
	 */

	public String toString(){
		return "Title: " + super.getTitle() + ", Copies Available: " + super.getCopiesAvailable() +
				", Rating: " + rating;
	}
	
	/**
	 * Gets the current object's rating
	 * @return rating
	 */
	
	public String getRating(){
		return this.rating;
	}
	
	/**
	 * Sets current object's rating  to parameter
	 * @param rating
	 */
	
	public void setRating(String rating){
		this.rating = rating;
	}

}
