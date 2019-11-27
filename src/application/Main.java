package application;
	
import controllers.CashierController;
import database.ProductDB;
import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import view.CashierView;
import view.ClientView;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		ClientView clientView = new ClientView();

		ProductDB model = new ProductDB("src/Files/article.txt");
		CashierController cashierController = new CashierController(model);
		CashierView cashierView = new CashierView(cashierController);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
