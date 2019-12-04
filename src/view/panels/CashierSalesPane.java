package view.panels;

import controllers.CashierController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Product;

import java.util.List;

public class CashierSalesPane extends GridPane {
    private TableView<Product> table;
    private Label productExistLabel;
    private Label totalAmount;
    private CashierController controller;
    private Label priceLabel;

    public CashierSalesPane(CashierController controller){
        this.controller = controller;
        controller.setView(this);
        this.table = new TableView<>();
        this.table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Button delete = new Button("delete");
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        TextField articleNumberInput = new TextField();
        Button addArticle = new Button("add Article");
        addArticle.setOnAction(e -> controller.addArticle(Integer.parseInt(articleNumberInput.getText())));
        productExistLabel = new Label("Not existing code");
        productExistLabel.setVisible(false);
        this.add(productExistLabel, 0, 2);
        this.add(articleNumberInput,0,0,1,1);
        this.add(addArticle,0,1,1,1);
        TableColumn<Product,String> articlecode = new TableColumn<>("Article Code");
        // the setcellValueFactory will check in the product class for getId() in this example
        articlecode.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Product,String> description = new TableColumn<>("Name");
        description.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product,String> articleGroup = new TableColumn<>("Article Group");
        articleGroup.setCellValueFactory(new PropertyValueFactory<>("group"));

        TableColumn<Product,String> price = new TableColumn<>("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product,String> stock = new TableColumn<>("Stock");
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        delete.setOnMouseClicked(event -> {
            if (table.getSelectionModel().getSelectedCells().size()<=0){
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Delete");
                info.setContentText("To delete items select the items you want to delete from the table view (shift select for multiple)");
                info.show();
            }else {
                Alert deleteConirmation = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete these item(s)",ButtonType.NO,ButtonType.YES);
                deleteConirmation.setTitle("Delete confirmation");
                deleteConirmation.setContentText("Are you sure you want to delete these item(s)");
                deleteConirmation.showAndWait().ifPresent(response ->{
                    if (deleteConirmation.getResult() == ButtonType.YES){
                        List<Product> items = table.getSelectionModel().getSelectedItems();
                        for (int i=0;i<items.size();i++){
                            System.out.println(items.get(i));
                            controller.removeArticle(items.get(i));
                        }
                    }
                });
            }
        });

        table.getColumns().add(articlecode);
        table.getColumns().add(description);
        table.getColumns().add(articleGroup);
        table.getColumns().add(price);
        table.getColumns().add(stock);

        this.add(table,1,0, 3,3);

        priceLabel = new Label("Total price:");
        totalAmount = new Label("€ 0");
        this.add(priceLabel, 4,0,1,1);
        this.add(totalAmount, 4, 1,1,1);
        this.add(delete,1,5,1,1);
    }

    public void setNotExistingCode(boolean value) {
        productExistLabel.setVisible(value);
    }

    public void updateTable(List<Product> list) {
        table.getItems().clear();
        table.getItems().addAll(list);
    }

    public void updateTotalAmount(double value) {
        totalAmount.setText(String.valueOf("€ " + value));
    }

    public void updateDisplay() {

    }
}
