package mediaRentalManager;

import java.util.ArrayList;
import java.util.Collections;

public class MediaRentalManager implements MediaRentalManagerInt {

	//fields
	private int planLimit;
	public ArrayList<Customer> customersDatabase = new ArrayList<Customer>();
	private ArrayList<Media> mediaDatabase = new ArrayList<Media>();

	//creates customer object for every new customer
	public void addCustomer(String name, String address, String plan) {
		Customer renter = new Customer(name, address, plan);
		customersDatabase.add(renter);
		if (renter.getPlan().equals("LIMITED")) {
			setLimitedPlanLimit(2);
		}
	}
	
	//creates movie object for every new movie
	public void addMovie(String title, int copiesAvailable, String rating) {
		Movie mov = new Movie(title, copiesAvailable, rating);
		mediaDatabase.add(mov);
	}

	//creates new album object for every new album
	public void addAlbum(String title, int copiesAvailable, String artist, String songs) {
		Album alb = new Album(title, copiesAvailable, artist, songs);
		mediaDatabase.add(alb);
	}

	//sets the plan limit to value parameter
	public void setLimitedPlanLimit(int value) {
		planLimit = value;
	}

	// order the list please
	public String getAllCustomersInfo() {
		StringBuffer info = new StringBuffer();

		Collections.sort(customersDatabase); //sorts customers list
		info.append("***** Customers' Information *****\n");

		for (int i = 0; i < customersDatabase.size(); i++) {//goes through all customers
			info.append(customersDatabase.get(i).toString() + "\n");
			info.append("Rented: " + customersDatabase.get(i).rented.toString() + "\n");
			info.append("Queue: " + customersDatabase.get(i).queue.toString() + "\n");
		}

		String infoString = info.toString(); //changes buffer to string to save space

		return infoString;
	}

	// adding everything twice, no commas, not sorted by title
	public String getAllMediaInfo() {
		StringBuffer info = new StringBuffer();
		Collections.sort(mediaDatabase);//sorts media
		info.append("***** Media Information *****\n");

		for (int i = 0; i < mediaDatabase.size(); i++) {//goes through all customers
			info.append(mediaDatabase.get(i).toString() + "\n");
		}

		String infoString = info.toString();

		return infoString;
	}

	//returns true if media can be added to queue
	public boolean addToQueue(String customerName, String mediaTitle) {
		boolean add = false;
		for (int i = 0; i < customersDatabase.size(); i++) { //goes through all the customers
			if (customersDatabase.get(i).getName().equals(customerName)) {//checks for the right customer
				if (customersDatabase.get(i).queue.contains(mediaTitle)) {//checks if that customer has the media
					add = false;
				} else {
					customersDatabase.get(i).queue.add(mediaTitle);
					add = true;
				}
			}
		}

		return add;

	}

	// return true if customer has a title and removes it from the queue
	public boolean removeFromQueue(String customerName, String mediaTitle) {
		boolean remove = false;
		for (int i = 0; i < customersDatabase.size(); i++) {//checks all customers
			if (customersDatabase.get(i).getName().equals(customerName)) {//checks if customer has title
				if (customersDatabase.get(i).queue.contains(mediaTitle)) {
					customersDatabase.get(i).queue.remove(mediaTitle);
					remove = true;
				} else {
					customersDatabase.get(i).queue.add(mediaTitle);
					remove = false;
				}
			}
		}

		return remove;

	}

	//processes customers media requests
	public String processRequests() {
		Collections.sort(customersDatabase);
		String message = "";
		
		int check = 0;

		for (int i = 0; i < customersDatabase.size(); i++) {
			ArrayList<String> copy = new ArrayList<String>();
			for(int m = 0;m< customersDatabase.get(i).queue.size();m++){
				copy.add(customersDatabase.get(i).queue.get(m));
			}
			if (customersDatabase.get(i).queue.size() > 0) {//goes through all customers
				if (customersDatabase.get(i).getPlan().equals("UNLIMITED")) { //checks plans
					String mediaTitle = "";
					mediaTitle = customersDatabase.get(i).queue.get(0);
					int size = customersDatabase.get(i).queue.size();

					for (int j = 0; j < size; j++) {//goes through cutomers whole queue
						int test = customersDatabase.get(i).queue.indexOf(copy.get(j));
						mediaTitle = copy.get(test);

						System.out.println(test);
						mediaTitle = customersDatabase.get(i).queue.get(test);
						
						System.out.println(mediaTitle);
						int spot = -1;
						for (int k = 0; k < mediaDatabase.size(); k++) {//checks if the title is there and there are enough copies
							if (mediaDatabase.get(k).getTitle().equals(mediaTitle)
									&& mediaDatabase.get(k).getCopiesAvailable() > 0) {

								spot = k;
								int copiesAv = mediaDatabase.get(k).getCopiesAvailable() - 1;
								mediaDatabase.get(k).setCopiesAvailable(copiesAv);

							}
						}

						if (spot >= 0 ) {
							System.out.println(mediaTitle+":"+customersDatabase.get(i).rented+":"+customersDatabase.get(i).queue);
							customersDatabase.get(i).rented.add(mediaTitle);
							int pos = customersDatabase.get(i).queue.indexOf(mediaTitle);
							customersDatabase.get(i).queue.remove(pos);
							message = message + "Sending " + mediaTitle + " to " + customersDatabase.get(i).getName()
									+ "\n";
							check = customersDatabase.get(i).queue.indexOf(mediaTitle);
						} else {
							check = customersDatabase.get(i).queue.indexOf(mediaTitle);
						}
					}

				} else if (customersDatabase.get(i).getPlan().equals("LIMITED")) { ///checks plan
					String mediaTitle = "";
					mediaTitle = customersDatabase.get(i).queue.get(0);
					int size = customersDatabase.get(i).queue.size();
					for (int l = 0; l < size; l++) {//checks the whole queue
						mediaTitle = customersDatabase.get(i).queue.get(0);
						
						int test = customersDatabase.get(i).queue.indexOf(copy.get(l));
						mediaTitle = copy.get(test);

						System.out.println(test);
						mediaTitle = customersDatabase.get(i).queue.get(test);
						
						System.out.println(mediaTitle);

						if (customersDatabase.get(i).rented.size() < this.planLimit) {//makes sure rented is not larger than plan limit
							int spot = -1;
							for (int k = 0; k < mediaDatabase.size(); k++) {
								if (mediaDatabase.get(k).getTitle().equals(mediaTitle)
										&& mediaDatabase.get(k).getCopiesAvailable() > 0) {
									spot = k;
									int copiesAv = mediaDatabase.get(k).getCopiesAvailable() - 1;
									mediaDatabase.get(k).setCopiesAvailable(copiesAv);
								}
							}
							if (spot >= 0) { //makes sure title and copies available is appropriate 
								customersDatabase.get(i).rented.add(mediaTitle);
								int pos = customersDatabase.get(i).queue.indexOf(mediaTitle);
								customersDatabase.get(i).queue.remove(pos);
								message = message + "Sending " + mediaTitle + " to " + customersDatabase.get(i).getName() + "\n";
								check = customersDatabase.get(i).queue.indexOf(mediaTitle);

							} else {
								check = customersDatabase.get(i).queue.indexOf(mediaTitle);
							}

						} else {
							break;
						}
					}

				}

			}
		}

		return message;

	}

	// returns true if customer has mediaTitle and it is returned
	public boolean returnMedia(String customerName, String mediaTitle) {
		boolean ret = false;
		for (int i = 0; i < customersDatabase.size(); i++) { //goes through customers
			if (customersDatabase.get(i).getName().equals(customerName)) {//checks if customer has title
				for (int j = 0; j < customersDatabase.get(i).rented.size(); j++) {
					if (customersDatabase.get(i).rented.get(j).equals(mediaTitle)) {
						for (int k = 0; k < mediaDatabase.size(); k++) {
							if (mediaDatabase.get(k).getTitle().equals(mediaTitle)) {
								int copiesAv = mediaDatabase.get(k).getCopiesAvailable() + 1;
								mediaDatabase.get(k).setCopiesAvailable(copiesAv);
							}
						}
						customersDatabase.get(i).rented.remove(j);
						ret = true;
						break;
					}
				}

			}

		}
		return ret;
	}

	//return arraylist with media titles that have any of the parameters
	public ArrayList<String> searchMedia(String title, String rating, String artist, String songs) {
		ArrayList<String> media = new ArrayList<String>();

		for (int i = 0; i < mediaDatabase.size(); i++) {
			if (mediaDatabase.get(i) instanceof Album) { //checks albums
				if (((Album) mediaDatabase.get(i)).getTitle().equals(title)
						|| ((songs != null) && (((Album) mediaDatabase.get(i)).getSongs().indexOf(songs)) >= 0)
						|| ((Album) mediaDatabase.get(i)).getArtist().equals(artist)) {
					media.add((mediaDatabase.get(i)).getTitle());

				}
			} else if (mediaDatabase.get(i) instanceof Movie) {//checks movies
				if (((Movie) mediaDatabase.get(i)).getTitle().equals(title)
						|| ((Movie) mediaDatabase.get(i)).getRating().equals(rating)) {
					media.add(((Movie) mediaDatabase.get(i)).getTitle());

				}

			}

		}

		Collections.sort(media);
		return media;
	}

}
