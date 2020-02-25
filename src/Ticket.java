/**
 * Implements a single Ticket for any event.
 * 
 * @author Bao Hoang
 *
 */
public class Ticket {

	private int serialNumber;
	private double price;
	private Event event;
	private static int counter = 1;
	private static double PRICE = 10.0;

	public Ticket(Event event) throws NoSpaceException, UnsupportedOperationException {
		this.event = event;
		event.addTicket(this);
		this.price = Ticket.PRICE * event.getPriceFactor();
		this.serialNumber = computeSerialNumber();
	}

	/**
	 * Returns the price of the ticket
	 * 
	 * @return ticket price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Generates a String representation of the Ticket.
	 */
	@Override
	public String toString() {
		return "Ticket serialNumber = " + this.serialNumber + "Ticket Price: " + this.getPrice();
	}

	/*
	 * Creates a serial number for the ticket.
	 */
	private static int computeSerialNumber() {
		int oldCounter = counter;
		counter++;
		return oldCounter;
	}
}
