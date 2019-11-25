package view.panels;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.IO.LoadSaveStrategy;
import model.IO.LoadSaveStrategyFactory;
import model.IO.LoadSaveTextfile;
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

        //Implementation of factory hardcoded to use textfile
        LoadSaveStrategyFactory factory = new LoadSaveStrategyFactory();
        LoadSaveStrategy loadSaveStrategy = factory.createObject("TEXTFILE");
        loadSaveStrategy.setPath("src/Files/article.txt");
        ArrayList<Product> list = loadSaveStrategy.load();
        Collections.sort(list);
        table.getItems().addAll(list);
        //table.getItems().add(new Product(5,"product1",10,"idk","idk"));
        this.add(table,0,1);
		
	}


}
