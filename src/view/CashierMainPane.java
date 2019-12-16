package view;

import controller.*;
import database.ProductDB;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Product;
import view.panels.CashierSalesPane;
import view.panels.LogPane;
import view.panels.ProductOverviewPane;
import view.panels.SettingsPane;
import java.util.List;

public class CashierMainPane extends BorderPane {
    private CashierSalesPane cashiersSalesPane;

    public CashierMainPane(ProductDB db){
        TabPane tabPane = new TabPane();
        ProductOverviewController productOverviewController = new ProductOverviewController(db);

        ProductOverviewPane productOverviewPane = new ProductOverviewPane(productOverviewController);
        SettingsController settingsController = new SettingsController(db);
        SettingsPane settingsPane = new SettingsPane(settingsController);
        CashierController cashierController = new CashierController(db);
        LogController logController = new LogController (db);
        LogPane logPane = new LogPane(logController);
        cashiersSalesPane = new CashierSalesPane(cashierController);
        Tab cashierTab = new Tab("Cashier", cashiersSalesPane);
        Tab articleTab = new Tab("Article",productOverviewPane);
        Tab settingsTab = new Tab("Settings",settingsPane);
        Tab logTab = new Tab("Log", logPane);
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
        cashiersSalesPane.updateTable(list);
    }

    public void updateTotalAmount(int value) {
        cashiersSalesPane.updateTotalAmount(value);
    }
}
