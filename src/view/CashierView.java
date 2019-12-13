package view;

import database.ProductDB;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CashierView {
	private Stage stage = new Stage();		
	private CashierMainPane borderPane;

	public CashierView(ProductDB db){
		stage.setTitle("CASHIER VIEW");
		stage.setResizable(false);		
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 750, 500);
		borderPane = new CashierMainPane(db);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();

	}

}
