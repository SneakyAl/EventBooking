import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Collection of all events
 * 
 * @author Bao Hoang
 *
 */
public class Events {

	private ObservableList<Event> events = FXCollections.observableArrayList();

	Events() {
	}

	/**
	 * Adds an event to the list of events.
	 * 
	 * @param event the new event
	 */
	public void add(Event event) {
		events.add(event);
	}

	/**
	 * Getter for the events
	 * 
	 * @return getter
	 */
	public ObservableList<Event> getEvents() {
		return events;
	}

	/**
	 * Changes the event
	 * 
	 * @param index the position where the event occurs
	 * @param event the new version of the event
	 */
	public void setEvent(int index, Event event) {
		events.add(index, event);
	}

	/**
	 * Computes and returns the total number of tickets sold for the events.
	 * 
	 * @return number of tickets sold
	 */
	public double getTotalProceeds() {
		double totalProcess = 0;
		for (Event event : events) {
			totalProcess = totalProcess + event.getProceeds();
		}
		return totalProcess;
	}

	/**
	 * Generates a String representation of the object
	 */
	@Override
	public String toString() {
		return "Event: " + this.events;
	}
}
