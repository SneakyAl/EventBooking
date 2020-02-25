
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * This is the user interface for the program. It supports creating plays and
 * concerts, sell tickets for them and updating the events. It also displays the
 * tickets.
 * 
 * @author Brahma Dathan
 *
 */
public class UserInterface extends Application {

	Button createEventButton, updateEventButton, bookTicketButton;
	Events events = new Events();
	static int counter = 1;

	public static void main(String[] args) {
		Application.launch(args);
	}

	private void updateFiles() {
		// need to do this
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Events");

		HBox topLayout = new HBox(50);
		createEventButton = new Button("Create Event");
		updateEventButton = new Button("Update Event");
		bookTicketButton = new Button("Book Ticket");
		topLayout.getChildren().addAll(createEventButton, updateEventButton, bookTicketButton);
		topLayout.setAlignment(Pos.CENTER);

		VBox leftLayout = new VBox(10);
		Text leftTitle = new Text("Event Options:");
		ListView<String> eventOptions = new ListView<>();
		eventOptions.getItems().addAll("Concert", "Play", "Meeting");
		eventOptions.getSelectionModel().select("Concert");
		leftLayout.getChildren().addAll(leftTitle, eventOptions);

		VBox centerLayout = new VBox(10);
		Text centerTitle = new Text("Event List:");
		ListView<Event> eventList = new ListView<>(events.getEvents());
		centerLayout.getChildren().addAll(centerTitle, eventList);

		VBox rightLayout = new VBox(10);
		Text rightTitle = new Text("Ticket List:");
		ListView<Ticket> ticketList = new ListView<>();
		ticketList.setPrefWidth(300);
		rightLayout.getChildren().addAll(rightTitle, ticketList);
		eventList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
			@Override
			public void changed(ObservableValue<? extends Event> observable, Event oldValue, Event newValue) {
				if (newValue != null) {
					ticketList.setItems(null);
					if (!(newValue instanceof Meeting)) {
						ticketList.setItems(newValue.getTickets());
					}
				}
			}
		});

		HBox bottomLayout = new HBox();
		Text message = new Text("This is where messages are displayed \n");
		bottomLayout.getChildren().add(message);
		bottomLayout.setAlignment(Pos.CENTER);

		BorderPane mainPane = new BorderPane();
		Insets insets = new Insets(10);
		mainPane.setTop(topLayout);
		BorderPane.setMargin(topLayout, insets);
		mainPane.setLeft(leftLayout);
		mainPane.setCenter(centerLayout);
		mainPane.setRight(rightLayout);
		mainPane.setBottom(bottomLayout);

		Scene scene = new Scene(mainPane, 900, 525);
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent window) {
				updateFiles();
				System.exit(0);
			}
		});

		createEventButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.setTitle("Create Event");

				HBox descriptionLayout = new HBox(62);
				TextField descriptionField = new TextField();
				Text descriptionLabel = new Text("Description:");
				descriptionLayout.getChildren().addAll(descriptionLabel, descriptionField);

				HBox priceFactorLayout = new HBox(30);
				TextField priceFactorField = new TextField("1.0");
				Text priceFactorLabel = new Text("Price Factor/Fee:");
				priceFactorLayout.getChildren().addAll(priceFactorLabel, priceFactorField);

				HBox eventID = new HBox(125);
				Text eventIdInfo = new Text("Event ID:  " + counter);
				Button create = new Button("Create");
				eventID.getChildren().addAll(eventIdInfo, create);

				BorderPane EventLayout = new BorderPane();
				Insets insets = new Insets(10);
				EventLayout.setBottom(eventID);
				BorderPane.setMargin(eventID, insets);
				EventLayout.setTop(descriptionLayout);
				BorderPane.setMargin(descriptionLayout, insets);
				EventLayout.setCenter(priceFactorLayout);
				BorderPane.setMargin(priceFactorLayout, insets);

				Scene createEventScene = new Scene(EventLayout, 500, 150);
				primaryStage.setScene(createEventScene);

				create.setOnAction(new EventHandler<ActionEvent>() {// make sure it's a double?
					@Override
					public void handle(ActionEvent event) {
						int eventSelection = eventOptions.getSelectionModel().getSelectedIndex();
						String description = new String(descriptionField.getText());
						double priceFactor = Double.parseDouble(priceFactorField.getText());

						if (eventSelection == 0) {
							Concert selection = new Concert(description, priceFactor);
							events.add(selection);
							counter++;
							message.setText("Concert event created.\n");
						} else if (eventSelection == 1) {
							Play selection = new Play(description, priceFactor);
							events.add(selection);
							counter++;
							message.setText("Play event created.\n");
						} else if (eventSelection == 2) {
							Meeting selection = new Meeting(description, priceFactor);
							events.add(selection);
							counter++;
							message.setText("Meeting event created.\n");
						} else {
							message.setText("Please choose Concert, Play, or Meeting from the Event Options list.\n");
						}
						primaryStage.setTitle("Events");
						primaryStage.setScene(scene);
					}
				});

			}
		});

		updateEventButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Event eventToUpdate = eventList.getSelectionModel().getSelectedItem();
				if (eventToUpdate == null) {
					message.setText("Please choose an event to update.\n");
				} else {
					primaryStage.setTitle("Update Event");

					HBox descriptionLayout = new HBox(62);
					TextField descriptionField = new TextField(eventToUpdate.getDescription());
					Text descriptionLabel = new Text("Description:");
					descriptionLayout.getChildren().addAll(descriptionLabel, descriptionField);

					HBox priceFactorLayout = new HBox(30);
					TextField priceFactorField = new TextField(Double.toString(eventToUpdate.getPriceFactor()));
					Text priceFactorLabel = new Text("Price Factor/Fee:");
					priceFactorLayout.getChildren().addAll(priceFactorLabel, priceFactorField);

					HBox eventID = new HBox(125);
					Text eventIdInfo = new Text("Event ID:  " + eventToUpdate.getEventId());
					Button update = new Button("Update");
					eventID.getChildren().addAll(eventIdInfo, update);

					BorderPane EventLayout = new BorderPane();
					Insets insets = new Insets(10);
					EventLayout.setBottom(eventID);
					BorderPane.setMargin(eventID, insets);
					EventLayout.setTop(descriptionLayout);
					BorderPane.setMargin(descriptionLayout, insets);
					EventLayout.setCenter(priceFactorLayout);
					BorderPane.setMargin(priceFactorLayout, insets);

					Scene updateEventScene = new Scene(EventLayout, 500, 150);
					primaryStage.setScene(updateEventScene);

					update.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							eventToUpdate.setDescription(descriptionField.getText());
							eventToUpdate.setPriceFactor(Double.parseDouble(priceFactorField.getText()));
							eventList.setItems(null);
							eventList.setItems(events.getEvents());
							eventList.getSelectionModel().select(eventToUpdate);
							message.setText("Your event has been updated.");
							primaryStage.setTitle("Events");
							primaryStage.setScene(scene);
						}
					});
				}
			}
		});

		bookTicketButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Event ticketedEvent = eventList.getSelectionModel().getSelectedItem();
				if (ticketedEvent == null) {
					message.setText("Please choose a play or concert from the Event list.\n");
				} else {
					if (ticketedEvent instanceof Meeting) {
						message.setText("I'm sorry.  Tickets can not be purchased for meetings.\n");
					} else {
						try {
							Ticket ticket = new Ticket(ticketedEvent);
							message.setText("Your ticket has been booked.\n");
						} catch (NoSpaceException e) {
							message.setText("I'm sorry.  That event is sold out.\n");
						}
					}
				}
			}
		});

	}
}
