/**
 * Represents a single Concert.
 * 
 * @author Bao Hoang
 *
 */
public class Concert extends Event {
	/**
	 * Creates a Concert object with a description and a price factor.
	 * 
	 * @param description the description of the concert
	 * @param priceFactor the price factor
	 */
	public Concert(String description, double priceFactor) {
		super(description, priceFactor);
	}

	/**
	 * Creates a concert with the given description and a price factor of 1.0.
	 * 
	 * @param description the description of the concert
	 */
	public Concert(String description) {
		super(description, 1.0);
	}

	/**
	 * Returns a String representation.
	 * 
	 */
	@Override
	public String toString() {
		String input = "Concert " + super.toString();
		return input;
	}
}
