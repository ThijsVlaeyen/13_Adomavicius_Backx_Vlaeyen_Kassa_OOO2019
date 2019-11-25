package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.CashierView;
import view.ClientView;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		CashierView cashierView = new CashierView();
		ClientView clientView = new ClientView();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
