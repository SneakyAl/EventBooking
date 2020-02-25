/**
 * Implements a Play. All we do is provide the constructors, so we have a new
 * type.
 * 
 * @author BAO HOANG
 *
 */
public class Play extends Event {

	/**
	 * Creates a Play object with the description and price factor.
	 * 
	 * @param description the description of the play
	 * @param priceFactor the price factor for the play
	 */
	public Play(String description, double priceFactor) {
		super(description, priceFactor);
	}

	/**
	 * Creates a play with the given description and a price factor of 1.0.
	 * 
	 * @param description the description of the play
	 */
	public Play(String description) {
		super(description, 1.0);
	}

	/**
	 * Adds a ticket to the list of tickets sold for this Play object. It also
	 * adjusts the price factor.
	 * 
	 * @param ticket the Ticket object to be added
	 * @return true iff the Ticket object could be added.
	 */
	@Override
	public boolean addTicket(Ticket ticket) throws UnsupportedOperationException, NoSpaceException {
		double priceFactor = this.getPriceFactor(); // upto 60% of the capacity
		if (this.getTickets().size() < 3) {
			super.addTicket(ticket);
		} else if (this.getTickets().size() >= 3) {// is 60% or 80% of the capacity
			super.setPriceFactor(priceFactor * 1.2); // increase the priceFactor to a 1.2
			priceFactor = super.getPriceFactor();
			super.addTicket(ticket);
		}
		return true;
	}

	/**
	 * Returns a String representation.
	 * 
	 */
	@Override
	public String toString() {
		return "Play " + super.toString();
	}
}
