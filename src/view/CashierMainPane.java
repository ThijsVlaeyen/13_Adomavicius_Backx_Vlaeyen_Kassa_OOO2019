package view;


import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import controllers.ProductOverviewController;
import controllers.SettingsController;
import database.ProductDB;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.IO.LoadSaveTextfile;
import view.panels.CashierSalesPane;
import view.panels.ProductOverviewPane;
import view.panels.SettingsPane;

public class CashierMainPane extends BorderPane {
	public CashierMainPane(){
       ProductDB db = new ProductDB();
       db.setLoadSaveStrategy(new LoadSaveTextfile());
       db.load();
	   TabPane tabPane = new TabPane();
	   Tab cashierTab = new Tab("Cashier",new CashierSalesPane());
       ProductOverviewController productOverviewController = new ProductOverviewController(db);
       SettingsController settingsController = new SettingsController(db);
	   ProductOverviewPane productOverviewPane = new ProductOverviewPane(productOverviewController);
	   Tab articleTab = new Tab("Article",productOverviewPane);
      SettingsPane settingsPane = new SettingsPane(settingsController);
      Tab settingsTab = new Tab("Settings", settingsPane);
      Tab logTab = new Tab("Log");
      tabPane.getTabs().add(cashierTab);
      tabPane.getTabs().add(articleTab);
      tabPane.getTabs().add(settingsTab);
      tabPane.getTabs().add(logTab);
      this.setCenter(tabPane);
	}
}
