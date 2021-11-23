//Elisa Masiero COMP 271
import java.util.Date;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class pizzaElisa extends Application {

	private ComboBox<String> sizes, crustOptions;
	private TextArea order;
	private Button orderit, clearit;
	private Label lb_sizes, lb_crust,lb_tops, lb_meats, lb_veggies, lb_order;
	private ObservableList<String> size =
			FXCollections.observableArrayList (
					"Small Pizza", "Medium Pizza",
					"Large Pizza", "X-Large Pizza");
	private ObservableList<String> crust =
			FXCollections.observableArrayList(
					"Hand Tossed","Brooklyn Square", "Thin & Crispy",
					"Stuffed Crust", "Flatbread");
	private String toppings = " ";
	private double cost;
	private CheckBox pepperoni, grilledChicken, italianSausage, bacon,
					onions, mushrooms,
					blackOlives, jalapeno, tomatoes;
	
	private ImageView elisapizza;
	private static double deliverycharge = 5.0;
	private static Date timeOrdered;
	
	public void start(Stage primaryStage) {
		
		VBox pane = new VBox(20);
		pane.setPadding(new Insets(30,40,40,30));
		HBox size_pane = new HBox(10);
		size_pane.setPadding(new Insets(5,5,5,5));
		HBox crust_pane = new HBox(10);
		crust_pane.setPadding(new Insets(5,5,5,5));
		HBox topping_header= new HBox(15);
		topping_header.setPadding(new Insets(10,10,10,10));
		topping_header.setStyle("-fx-border-color: lightgray");
		HBox topping_meats = new HBox(15);
		topping_meats.setPadding(new Insets(15,15,15,15));
		topping_meats.setStyle("-fx-border-color: lightgray");
		HBox topping_veggies = new HBox(15);
		topping_veggies.setPadding(new Insets(15,15,15,15));
		topping_veggies.setStyle("-fx-border-color: lightgray");
		HBox order_pane = new HBox(10);
		
	
		elisapizza = new ImageView("elisapizza.gif");
		elisapizza.setFitHeight(250);
		elisapizza.setFitWidth(550);
			
		// Sizes
		lb_sizes = new Label("Select a size");
		lb_sizes.setFont(Font.font("avenir",FontWeight.BOLD,14));
		sizes = new ComboBox(size);
		sizes.setVisibleRowCount(4);
		sizes.setValue("Large Pizza");
		size_pane.getChildren().addAll(lb_sizes, sizes);
		
		// Crust
		lb_crust = new Label("Select a crust");
		lb_crust.setFont(Font.font("avenir",FontWeight.BOLD,14));
		crustOptions = new ComboBox(crust);
		crustOptions.setVisibleRowCount(5);
		crustOptions.setValue("Hand Tossed");
		crust_pane.getChildren().addAll(lb_crust,crustOptions);
		
		//Toppings
		lb_tops = new Label("\t\t\t\t\t\t\t\t\tSelect toppings");
		lb_tops.setFont(Font.font("avenir",FontWeight.BOLD,14));
		//lb_tops.setAlignment(Pos.CENTER);
		topping_header.getChildren().add(lb_tops);
		
		lb_meats = new Label("Meats   ");
		lb_meats.setFont(Font.font("avenir",FontWeight.BOLD, 14));
		pepperoni = new CheckBox("Pepperoni");
		pepperoni.setFont(Font.font("avenir",14));
		grilledChicken = new CheckBox("Grilled Chicken");
		grilledChicken.setFont(Font.font("avenir",14));
		italianSausage = new CheckBox("Italian Sausage");
		italianSausage.setFont(Font.font("avenir",14));
		bacon = new CheckBox("Bacon");
		bacon.setFont(Font.font("avenir",14));
		
		lb_veggies = new Label("Veggies");
		lb_veggies.setFont(Font.font("avenir",FontWeight.BOLD,14));
		onions = new CheckBox("Onions");
		onions.setFont(Font.font("avenir",14));
		mushrooms = new CheckBox("Mushrooms");
		mushrooms.setFont(Font.font("avenir",14));
		blackOlives =  new CheckBox("Black Olives");
		blackOlives.setFont(Font.font("avenir",14));
		jalapeno = new CheckBox("Jalapeño Peppers");
		jalapeno.setFont(Font.font("avenir",14));
		tomatoes = new CheckBox("Tomatoes");
		tomatoes.setFont(Font.font("avenir",14));
		topping_meats.getChildren().addAll(lb_meats, pepperoni, grilledChicken, italianSausage, bacon);
		topping_veggies.getChildren().addAll(lb_veggies, onions, mushrooms, blackOlives, jalapeno,tomatoes);
		
		// Order
		lb_order = new Label("Order Summary");
		lb_order.setFont(Font.font("avenir",FontWeight.BOLD,14));
		order = new TextArea();
		order.setEditable(false);
		order.setPrefColumnCount(25);
		
		
		order_pane.getChildren().addAll(lb_order, order);
		orderit = new Button("Place Order");
		orderit.setFont(Font.font("avenir",12));
		clearit = new Button("Clear Order");
		clearit.setFont(Font.font("avenir",12));
		
		OrderHandler oh = new OrderHandler();
		orderit.setOnAction(oh);
		clearit.setOnAction(oh);
		
		pane.getChildren().addAll(elisapizza, size_pane,crust_pane,topping_header, topping_meats, topping_veggies, order_pane, orderit, clearit);
		Scene scene = new Scene(pane,800,900);
		primaryStage.setTitle("Make Your Own");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);

	}
	class OrderHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			timeOrdered = new Date();
			if (e.getSource() == orderit) { // Placing an order
				String size = sizes.getValue();
				if (size.equals("Small Pizza")){
					cost = 9;	
				}
				else if (size.equals("Medium Pizza")) {
					cost = 11.00;
				}
				else if(size.equals("Large Pizza")){
					cost = 13.00;
				}
				else if(size.equals("X-Large Pizza")) {
					cost = 15.00;
				}
				
				String crust = crustOptions.getValue();
				if (crust.equals("Hand Tossed")) {
					cost += 1.00;
				}
				if (crust.equals("Brooklyn Square")) {
					cost += 2.00;
				}
				if  (crust.equals("Thin & Crispy")) {
					cost += 0.50;
				}
				if (crust.equals("Stuffed Crust")) {
					cost += 3.00;
				}
				if (crust.equals("Flatbread")) {
					cost += 1.00;
				}

				String result = (size + " " + crust);
				toppings = "";
				
				if(pepperoni.isSelected()) {
					toppings += "Pepperoni\n";
					cost+= 0.50;
				}
				if (grilledChicken.isSelected()) {
					toppings += "Grilled Chicken\n";
					cost += 0.50;
				}
				if (italianSausage.isSelected()) {
					toppings += "Italian Sausage\n";
					cost += 0.50;
				}
				if (bacon.isSelected()) {
					toppings += "Bacon\n";
					cost += 0.50;
				}
				if (onions.isSelected()) {
					toppings += "Onions\n";
					cost += 0.50;
				}
				if (mushrooms.isSelected()) {
					toppings += "Mushrooms\n";
					cost += 0.50;
				}
				if (blackOlives.isSelected()) {
					toppings += "Black Olives\n";
					cost += 0.50;
				}
				if (jalapeno.isSelected()) {
					toppings += "Jalapeño Peppers\n";
					cost += 0.50;
				}
				if (tomatoes.isSelected()) {
					toppings += "Tomatoes\n";
					cost += 0.50;
				}
				cost+=deliverycharge;
				order.setFont(Font.font("avenir",12));
				
				String strCost = String.format("%.2f", cost);
				order.setText(timeOrdered + "\n\n"+result + "\n" + 
					toppings +"Delivery Charge + $ 5.00"+ "\n\nTotal Order $" + strCost);
				
			}
			else {// time to clear the order
				order.setText("");
				// first flavor in the list
				sizes.setValue(size.get(0));
				// should deselect all the check boxes
				pepperoni.setSelected(false); grilledChicken.setSelected(false); 
				italianSausage.setSelected(false); bacon.setSelected(false);
					onions.setSelected(false); mushrooms.setSelected(false);
					blackOlives.setSelected(false); jalapeno.setSelected(false); 
					tomatoes.setSelected(false);

	} //end e
	}//end handler
	
}
}

	


