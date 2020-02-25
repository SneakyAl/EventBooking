import javafx.collections.ObservableList;

public class Meeting extends Event {
	/**
	 * Creates a meeting with a given description and fee
	 * 
	 * @param description meeting description
	 * @param fee         the fee charged
	 * 
	 * @author Bao Hoang
	 */

	private double fee;

	public Meeting(String description, double fee) {
		super(description);
		this.fee = fee;
	}

	/**
	 * To disable the price factor
	 */
	@Override
	public void setPriceFactor(double priceFactor) {
		throw new UnsupportedOperationException("Pricefactor not supported by Meeting");
	}

	/**
	 * * Not supported. Throws UnsupportedOperationException.
	 */
	@Override
	public double getProceeds() {
		return fee;
	}

	/**
	 * Not supported. Throws UnsupportedOperationException.
	 * 
	 * @return nothing
	 */
	public double getPriceFactor() {
		throw new UnsupportedOperationException("Pricefactor not supported by Meeting");
	}

	/**
	 * Not supported. Throws UnsupportedOperationException.
	 * 
	 * @param ticket the Ticket object to be added
	 * @return true iff the Ticket object could be added.
	 */
	@Override
	public boolean addTicket(Ticket ticket) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("addTicket not supported by Meeting");
	}

	/**
	 * Get tickets is not supported. Throws UnsupportedOperationException.
	 */
	@Override
	public ObservableList<Ticket> getTickets() {
		throw new UnsupportedOperationException("Pricefactor not supported by Meeting");
	}

	/**
	 * Returns the fee
	 * 
	 * @return the fee
	 */
	public double getFee() {
		return fee;
	}

	/**
	 * Returns a String representation of this Event object
	 */
	@Override
	public String toString() {
		return "Metting " + super.toString();
	}

}
