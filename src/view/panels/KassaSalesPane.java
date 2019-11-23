package view.panels;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Product;

public class KassaSalesPane extends GridPane {
    private TableView<Product> table;
    public KassaSalesPane(){
        this.table = new TableView<>();
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        TextField articleNumberInput = new TextField();
        Button addArticle = new Button("add Article");
        this.add(articleNumberInput,0,0);
        this.add(addArticle,0,1);
        TableColumn<Product,String> artikelcode = new TableColumn<>("Artikel Code");
        // the setcellValueFactory will check in the product class for getId() in this example
        artikelcode.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Product,String> description = new TableColumn<>("Name");
        description.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product,String> artikelGroup = new TableColumn<>("Artikel Group");
        artikelGroup.setCellValueFactory(new PropertyValueFactory<>("group"));

        TableColumn<Product,String> price = new TableColumn<>("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product,String> stock = new TableColumn<>("Stock");
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        table.getColumns().add(artikelcode);
        table.getColumns().add(description);
        table.getColumns().add(artikelGroup);
        table.getColumns().add(price);
        table.getColumns().add(stock);
        this.add(table,1,0);

    }
}
