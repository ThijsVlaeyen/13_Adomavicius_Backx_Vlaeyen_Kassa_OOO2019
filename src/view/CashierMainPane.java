package view;


import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import controllers.CashierController;
import controllers.ProductOverviewController;
import controllers.SettingsController;
import database.ProductDB;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.IO.LoadSaveTextfile;
import model.Product;
import view.panels.CashierSalesPane;
import view.panels.ProductOverviewPane;
import view.panels.SettingsPane;

import java.util.List;

public class CashierMainPane extends BorderPane {
    private CashierSalesPane cashiersSalesPane;

//    public CashierMainPane(CashierController controller){
//       ProductDB db = new ProductDB();
//       db.setLoadSaveStrategy(new LoadSaveTextfile());
//       db.load();
//	   TabPane tabPane = new TabPane();
//	   Tab cashierTab = new Tab("Cashier",new CashierSalesPane(controller));
//       ProductOverviewController productOverviewController = new ProductOverviewController(db);
//       SettingsController settingsController = new SettingsController(db);
//	   ProductOverviewPane productOverviewPane = new ProductOverviewPane(productOverviewController);
//	   Tab articleTab = new Tab("Article",productOverviewPane);
//      SettingsPane settingsPane = new SettingsPane(settingsController);
//      Tab settingsTab = new Tab("Settings", settingsPane);
//      Tab logTab = new Tab("Log");
//      tabPane.getTabs().add(cashierTab);
//      tabPane.getTabs().add(articleTab);
//      tabPane.getTabs().add(settingsTab);
//      tabPane.getTabs().add(logTab);
//      this.setCenter(tabPane);
//	}

    public CashierMainPane(){
        ProductDB db = new ProductDB();
        //db.load();
        TabPane tabPane = new TabPane();
        ProductOverviewController productOverviewController = new ProductOverviewController(db);
        ProductOverviewPane productOverviewPane = new ProductOverviewPane(productOverviewController);
        SettingsController settingsController = new SettingsController(db);
        SettingsPane settingsPane = new SettingsPane(settingsController);
        CashierController cashierController = new CashierController(db);
        cashiersSalesPane = new CashierSalesPane(cashierController);
        Tab cashierTab = new Tab("Cashier", cashiersSalesPane);
        Tab articleTab = new Tab("Article",productOverviewPane);
        Tab settingsTab = new Tab("Settings",settingsPane);
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
