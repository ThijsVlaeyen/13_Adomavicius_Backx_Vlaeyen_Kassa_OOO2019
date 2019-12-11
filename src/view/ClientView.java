package view;

import controllers.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Product;

import java.util.Map;

public class ClientView {
	private Stage stage = new Stage();
	private Controller controller;
	private TableView<Map.Entry<Product,Integer>> table;
	private Label priceLabel;
		
	public ClientView(Controller controller){
		this.controller = controller;
		stage.setTitle("CLIENT VIEW");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);			
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();
		setup(root);
	}

	public void update(){
		this.table.getItems().clear();
		this.table.getItems().addAll(controller.getItems().entrySet());
		this.priceLabel.setText("Total price \n€ " + controller.getTotalPrice());
	}

	public void setup(Group root){
		HBox items = new HBox();
		this.controller.setView(this);
		this.priceLabel = new Label("Total price\n€ 0.0");
		this.table = new TableView<>();

		TableColumn<Map.Entry<Product,Integer>,String> articleId = new TableColumn<>("prouct Id");
		articleId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Product, Integer>, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Product, Integer>, String> param) {
				return new SimpleStringProperty(String.valueOf(param.getValue().getKey().getId()));
			}
		});
		TableColumn<Map.Entry<Product,Integer>,String> articleName = new TableColumn<>("prouct name");
		articleName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Product, Integer>, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Product, Integer>, String> param) {
				return new SimpleStringProperty(param.getValue().getKey().getName());
			}
		});
		TableColumn<Map.Entry<Product,Integer>,String> articleGroup = new TableColumn<>("Article Group");
		articleGroup.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Product, Integer>, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Product, Integer>, String> param) {
				return new SimpleStringProperty(String.valueOf(param.getValue().getKey().getGroup()));
			}
		});
		TableColumn<Map.Entry<Product,Integer>,String> articlePrice = new TableColumn<>("Article Price");
		articlePrice.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Product, Integer>, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Product, Integer>, String> param) {
				return new SimpleStringProperty(String.valueOf(param.getValue().getKey().getPrice()));
			}
		});
		TableColumn<Map.Entry<Product,Integer>,String> amount = new TableColumn<>("amount");
		amount.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Product, Integer>, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Product, Integer>, String> param) {
				return new SimpleStringProperty(String.valueOf(param.getValue().getValue()));
			}
		});

		table.getColumns().add(articleId);
		table.getColumns().add(articleName);
		table.getColumns().add(articleGroup);
		table.getColumns().add(articlePrice);
		table.getColumns().add(amount);
		items.getChildren().add(this.table);
		items.getChildren().add(this.priceLabel);
		root.getChildren().add(items);
	}
}
