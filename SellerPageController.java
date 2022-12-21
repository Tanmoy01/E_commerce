package main.e_commerce;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerPageController {
    @FXML
    TextField name, price, sellerid;
    @FXML
    public void AddProduct(MouseEvent e) throws SQLException {
        int productId = 1;
        ResultSet response2= main.connection.executeQuery("Select max(productID) as productID from product;");
        if(response2.next()) {
            productId = response2.getInt("productID") + 1;
        }
        String query = String.format("Insert Into product values(%s, '%s', %s, '%s')",productId, name.getText(), price.getText(),sellerid.getText());
        int response= main.connection.executeUpdate(query);
        if(response>0){
            System.out.println("New Product is Added");
        }
    }
}
