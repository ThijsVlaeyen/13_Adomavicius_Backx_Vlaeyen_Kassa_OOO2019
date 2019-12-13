package application;
	
import controllers.ClientViewController;
import database.ProductDB;
import javafx.application.Application;
import javafx.stage.Stage;
import view.CashierView;
import view.ClientView;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		ProductDB db = new ProductDB();
		ClientViewController clientViewController = new ClientViewController(db);
		CashierView cashierView = new CashierView(db);
		ClientView clientView = new ClientView(clientViewController);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
