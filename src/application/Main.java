package application;
	
import controllers.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import view.CashierView;
import view.ClientView;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		Controller clientViewController = new Controller();
		ClientView clientView = new ClientView(clientViewController);
		CashierView cashierView = new CashierView(clientViewController);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
