package view;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import view.panels.CashierSalesPane;
import view.panels.ProductOverviewPane;

public class CashierMainPane extends BorderPane {
	public CashierMainPane(){
	    TabPane tabPane = new TabPane(); 	    
        Tab cashierTab = new Tab("Cashier",new CashierSalesPane());
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
}
