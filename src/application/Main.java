package application;
	
import controllers.CashierController;
import controllers.ClientViewController;
import database.ProductDB;
import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.IO.LoadSaveProperties;
import view.CashierView;
import view.ClientView;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		ClientViewController clientViewController = new ClientViewController();
		ClientView clientView = new ClientView(clientViewController);
		CashierView cashierView = new CashierView(clientViewController);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
