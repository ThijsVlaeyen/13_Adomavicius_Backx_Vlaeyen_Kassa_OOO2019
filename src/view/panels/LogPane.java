package view.panels;

import controller.LogController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Log;

public class LogPane extends GridPane {
    private LogController controller;
    private TableView<Log> table;

    public LogPane(LogController controller)
    {
        this.controller = controller;
        table = new TableView<>();
        controller.setView(this);

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("Logs:"), 0, 0, 1, 1);
        TableColumn<Log,String> date = new TableColumn<>("Date");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Log,String> totalPrice = new TableColumn<>("Total Price");
        totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        TableColumn<Log,String> discount = new TableColumn<>("Discount");
        discount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        TableColumn<Log,String> finalPrice = new TableColumn<>("Final Price");
        finalPrice.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));

        table.getColumns().add(date);
        table.getColumns().add(totalPrice);
        table.getColumns().add(discount);
        table.getColumns().add(finalPrice);
        this.add(table,0,1);
    }

    public void update(Log log) {
        this.table.getItems().add(log);
        System.out.println(this.table.getItems());
    }
}
