package application;
	
import controllers.CashierController;
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
		ClientView clientView = new ClientView();
		CashierView cashierView = new CashierView();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
