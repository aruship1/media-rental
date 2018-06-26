package mediaRentalManager;

/**
 * Child class of Media that defines the basic characteristics associated 
 * with album inheriting title, and copies available and initializing artist
 *  and songs.
 * @author Pamela
 *
 */

public class Album extends Media {
	
	private String artist;
	private String songs;
	
	/**
	 * Constructor inherits title and copiesAvailable and initializes 
	 * artist and songs
	 * @param title
	 * @param copiesAvailable
	 */
	
	public Album(String title, int copiesAvailable, String artist, String songs){
		super(title, copiesAvailable);
		this.artist = artist;
		this.songs = songs;
		
	}
	
	/**
	 * Copy constructor
	 * @param alb
	 */
	
	public Album(Album alb){
		this(alb.getTitle(),alb.getCopiesAvailable(),alb.getArtist(),alb.getSongs());
	}

	/**
	 * Gets the current object's artist
	 * @return artist
	 */
	
	public String getArtist(){
		return this.artist;
	}
	
	/**
	 * Gets the current object's songs
	 * @return songs
	 */
	
	public String getSongs(){
		return this.songs;
	}
	
	/**
	 * Sets current object's artist  to parameter
	 * @param artist
	 */
	
	public void setArtist(String artist){
		this.artist = artist;
	}
	
	/**
	 * Sets current object's songs  to parameter
	 * @param songs
	 */
	
	public void setSongs(String songs){
		this.songs = songs;
	}
	
	/**
	 * Format's object info into a string
	 * @return string
	 */
	
	public String toString(){
		return "Title: " + super.getTitle() + ", Copies Available: " + super.getCopiesAvailable()+
				", Artist: " + artist + ", Songs: " + songs;
	}
	


}
