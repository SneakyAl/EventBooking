import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a single event. It stores a description of the play, a unique id,
 * and the tickets sold for the play.
 * 
 * @author Bao Hoang
 *
 */
public abstract class Event {

	private String description;
	private int ticketSold, eventId;
	private double priceFactor;
	private static int counter = 1;
	private static final int CAPACITY = 5;
	protected int protectedTicketSold;

	private ObservableList<Ticket> tickets = FXCollections.observableArrayList();

	public Event(String description, double priceFactor) {
		this.description = description;
		this.priceFactor = priceFactor;
		this.eventId = computeSerialNumber();
	}

	/**
	 * Receives the description and stores that and a price factor of 1.0. Besides,
	 * it assigns a unique id to the event. The constructor also allocates the array
	 * tickets.
	 * 
	 * @param description a description of this Play
	 * 
	 */
	public Event(String description) {
		this(description, 1.0);
	}

	/**
	 * Returns the unique id of the play
	 * 
	 * @return id of the play
	 * 
	 */
	public int getEventId() {
		return eventId;
	}

	/**
	 * Returns the tickets list
	 * 
	 * @return the tickets list
	 */
	public ObservableList<Ticket> getTickets() {
		return tickets;
	}

	/**
	 * Sets the price factor for the event.
	 * 
	 * @param priceFactor the new price factor
	 */
	public void setPriceFactor(double priceFactor) {
		this.priceFactor = priceFactor;
	}

	/**
	 * Computes and returns the total proceeds for thos event.
	 * 
	 * @return total proceeds
	 */

	public double getProceeds() {
		double totalPrice = 0;
		for (Ticket tickets : tickets) {
			totalPrice = totalPrice + tickets.getPrice();
		}
		return totalPrice;
	}

	/**
	 * Compares this Play with object. Follows the semantics of the equals method in
	 * Object.
	 * 
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object == null) {
			return false;
		} else if (!(object instanceof Event)) {
			return false;
		}
		Event otherEvent = (Event) object;
		if (eventId != otherEvent.getEventId()) {
			return false;
		}
		return true;
	}

	/**
	 * Returns the description of the Play object
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the price factor
	 * 
	 * @return price factor
	 */
	public double getPriceFactor() {
		return priceFactor;
	}

	/**
	 * Setter for description
	 * 
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns a unique serial number. This is a helper method.
	 * 
	 * @return serial number
	 */
	static int computeSerialNumber() {
		int oldCounter = counter;
		counter++;
		return oldCounter;
	}

	/**
	 * Adds a ticket to the list of tickets sold for this Play object.
	 * 
	 * @param ticket the Ticket object to be added
	 * @return true iff the Ticket object could be added.
	 * @throws NoSpaceException
	 * @throws UnsupportedOperationException
	 */

	public boolean addTicket(Ticket ticket) throws UnsupportedOperationException, NoSpaceException {
		if (tickets.size() >= CAPACITY) {
			return false;
		} else {
			tickets.add(ticket);
			ticketSold++;
		}
		return true;
	}

	/**
	 * Returns a String representation of this Event object
	 */
	@Override
	public String toString() {
		String input = eventId + " " + description + " " + priceFactor + " ";
		return input;
	}

}
