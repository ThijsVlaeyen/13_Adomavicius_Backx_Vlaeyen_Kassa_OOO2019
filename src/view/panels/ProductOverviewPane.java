package view.panels;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.IO.ProductsFromFile;
import model.Product;

import java.util.ArrayList;
import java.util.Collections;


public class ProductOverviewPane extends GridPane {
	private TableView<Product> table;
	
	
	public ProductOverviewPane() {
		table = new TableView<>();
	    this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        //artikelcode,omschrijving,artikelgroup,price,stock
        
		this.add(new Label("Products:"), 0, 0, 1, 1);
		TableColumn<Product,String> articlecode = new TableColumn<>("Article Code");
		// the setcellValueFactory will check in the product class for getId() in this example
		articlecode.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Product,String> description = new TableColumn<>("Description");
        description.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product,String> articleGroup = new TableColumn<>("Article Group");
        articleGroup.setCellValueFactory(new PropertyValueFactory<>("group"));

        TableColumn<Product,String> price = new TableColumn<>("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product,String> stock = new TableColumn<>("Stock");
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        table.getColumns().add(articlecode);
        table.getColumns().add(description);
        table.getColumns().add(articleGroup);
        table.getColumns().add(price);
        table.getColumns().add(stock);
        ProductsFromFile reader = new ProductsFromFile("src/database/article.txt");
        ArrayList<Product> list = reader.load();
        Collections.sort(list);
        table.getItems().addAll(list);
        //table.getItems().add(new Product(5,"product1",10,"idk","idk"));
        this.add(table,0,1);
		
	}


}
