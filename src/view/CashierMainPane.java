package view;


import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import view.panels.CashierSalesPane;
import view.panels.ProductOverviewPane;
import view.panels.SettingsPane;

public class CashierMainPane extends BorderPane {
	public CashierMainPane(){
	   TabPane tabPane = new TabPane();
	   Tab cashierTab = new Tab("Cashier",new CashierSalesPane());
	   ProductOverviewPane productOverviewPane = new ProductOverviewPane();
	   Tab articleTab = new Tab("Article",productOverviewPane);
      SettingsPane settingsPane = new SettingsPane();
      Tab settingsTab = new Tab("Settings", settingsPane);
      Tab logTab = new Tab("Log");
      tabPane.getTabs().add(cashierTab);
      tabPane.getTabs().add(articleTab);
      tabPane.getTabs().add(settingsTab);
      tabPane.getTabs().add(logTab);
      this.setCenter(tabPane);
	}
}
