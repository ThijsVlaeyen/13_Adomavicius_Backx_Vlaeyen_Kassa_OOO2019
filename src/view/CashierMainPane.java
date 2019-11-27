package view;


import controllers.CashierController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Product;
import view.panels.CashierSalesPane;
import view.panels.ProductOverviewPane;

import java.util.List;

public class CashierMainPane extends BorderPane {
    private Tab cashierTab;
    private CashierSalesPane cashiersSalesPane;

	public CashierMainPane(CashierController controller){
	    TabPane tabPane = new TabPane();
	    cashiersSalesPane = new CashierSalesPane(controller);
        cashierTab = new Tab("Cashier", cashiersSalesPane);
        ProductOverviewPane productOverviewPane = new ProductOverviewPane();
        Tab articleTab = new Tab("Article",productOverviewPane);
        Tab settingsTab = new Tab("Settings");
        Tab logTab = new Tab("Log");
        tabPane.getTabs().add(cashierTab);
        tabPane.getTabs().add(articleTab);
        tabPane.getTabs().add(settingsTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}

	public void setNotExistingCode(boolean value) {
	    cashiersSalesPane.setNotExistingCode(value);
    }

    public void updateScannedItemsTable(List<Product> list) {
        cashiersSalesPane.updateScannedItemsTable(list);
    }

    public void updateTotalAmount(int value) {
        cashiersSalesPane.updateTotalAmount(value);
    }
}
