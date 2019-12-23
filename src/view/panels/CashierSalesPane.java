package view.panels;

import controller.CashierController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Product;

import java.util.List;
/**
@Author Rafael backx, Tomas Adomavicius
*/
public class CashierSalesPane extends GridPane {
    private TableView<Product> table;
    private Label productExistLabel;
    private Label totalAmount;
    private Label discountLabel;
    private Label discount;
    private CashierController controller;
    private Label priceLabel;
    private Button payment;

    public CashierSalesPane(CashierController controller){
        this.controller = controller;
        controller.setView(this);
        this.table = new TableView<>();
        this.table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        Button delete = new Button("delete");
        TextField articleNumberInput = new TextField();
        Button addArticle = new Button("add Article");
        int[] teller = {1};
        addArticle.setOnAction(e -> {
            try{
                controller.addArticle(Integer.parseInt(articleNumberInput.getText()));
                teller[0]++;
            } catch (NumberFormatException a){
                Alert empty = new Alert(Alert.AlertType.ERROR,"This product does not exist!" , ButtonType.OK);
                empty.show();
            }
        });
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
                info.setContentText("To delete items select the items you want to delete from the table view (shift or control select for multiple)");
                info.show();
            }else {
                Alert deleteConirmation = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete these item(s)",ButtonType.NO,ButtonType.YES);
                deleteConirmation.setTitle("Delete confirmation");
                deleteConirmation.setContentText("Are you sure you want to delete these item(s)");
                deleteConirmation.showAndWait().ifPresent(response ->{
                    if (deleteConirmation.getResult() == ButtonType.YES){
                        controller.removeArticles(table.getSelectionModel().getSelectedItems());
                    }
                });
            }
        });

        table.getColumns().add(articlecode);
        table.getColumns().add(description);
        table.getColumns().add(articleGroup);
        table.getColumns().add(price);
        table.getColumns().add(stock);

        priceLabel = new Label("Total price:");
        discountLabel = new Label("Total discount:");
        discount = new Label("€ 0.0");
        totalAmount = new Label("€ 0");


        Button addOnHold = new Button("add on hold");
        addOnHold.setOnAction(e -> {
                    try {
                        controller.addOnHold();
                    } catch (IllegalStateException exception) {
                        showAlert(exception.getMessage()); }
                });

        Button takeFromHold = new Button("take from hold");
        takeFromHold.setOnAction(e -> controller.takeFromHold());


        Button close = new Button("Close Sale");
        close.setOnAction(e->{
            controller.close();
            discountLabel.setText("discount applied");
        });

        this.add(table,0,1, 5,3);
        this.add(articleNumberInput,0,0,1,1);
        this.add(addArticle,1,0,1,1);
        this.add(addOnHold, 0, 4, 1, 1);
        this.add(takeFromHold, 0, 5, 1, 1);
        this.add(priceLabel, 2,4,1,1);
        this.add(totalAmount, 2, 5,1,1);
        this.add(discountLabel,3,4,1,1);
        this.add(discount, 3,5,1,1);
        this.add(delete,4,4,1,1);
        this.add(close,1,4,1,1);
        payment = new Button("payment");
        payment.setDisable(true);
        payment.setOnAction(e -> controller.payment());
        this.add(payment, 1, 5, 1, 1);
    }

    public void setNotExistingCode(boolean value) {
        productExistLabel.setVisible(value);
    }

    public void updateTable(List<Product> list) {
        table.getItems().clear();
        table.getItems().addAll(list);
    }

    public void updateTotalAmount(double value) {
        totalAmount.setText("€ " + value);
    }

    public void updateDiscount(double value){
        discount.setText(String.valueOf(value));
    }

    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR,message, ButtonType.CLOSE);
        alert.setTitle(message);
        alert.setContentText(message);
        alert.show();
    }

    public void updatePaymentButton(boolean b) {
        payment.setDisable(b);
    }
}
