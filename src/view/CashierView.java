package view;

import controllers.CashierController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Product;

import java.util.List;

public class CashierView {
	private Stage stage = new Stage();		
	private CashierMainPane borderPane;

	public CashierView(CashierController controller){
		stage.setTitle("CASHIER VIEW");
		stage.setResizable(false);		
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 750, 500);
		borderPane = new CashierMainPane(controller);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();
		controller.setView(this);
	}

	public void setNotExistingCode(boolean value) {
		borderPane.setNotExistingCode(value);
	}

	public void updateScannedItemsTable(List<Product> list) {
		borderPane.updateScannedItemsTable(list);
	}

	public void updateTotalAmount(int value) {
		borderPane.updateTotalAmount(value);
	}

	public void updateDisplay() {

	}
}
