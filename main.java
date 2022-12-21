package main.e_commerce;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class main extends Application {
    public static DatabaseConnection connection;
    public static Group root;
    public static String emailId;

    @Override
    public void start(Stage primaryStage) throws Exception {
        emailId= "";
        connection = new DatabaseConnection();
        root= new Group();
        Header header= new Header();
        ProductPage productPage= new ProductPage();
        AnchorPane productPane= new AnchorPane();
        productPane.getChildren().add(productPage.products());
        productPane.setLayoutX(150);
        productPane.setLayoutY(150);
        root.getChildren().addAll(header.root, productPane);
        primaryStage.setScene(new Scene(root,500, 500));
        primaryStage.setTitle("E_Commerce");
        primaryStage.show();
        primaryStage.setOnCloseRequest(e ->{
            try {
                connection.con.close();
                System.out.println("Connection is closed successfully");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}