package main.e_commerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageController {
    @FXML
    TextField email;
    @FXML
    PasswordField password;
    @FXML
    public void login(MouseEvent e) throws SQLException, IOException {
        String query = String.format("Select * from user where emailID = '%s' AND pass='%s'", email.getText(), password.getText());
        ResultSet res= main.connection.executeQuery(query);
        if(res.next()){
            main.emailId = res.getString("emailID");
            String userType= res.getString("usertype");
            if(userType.equals("Seller")){
                AnchorPane sellerPage= FXMLLoader.load((getClass().getResource("sellerpage.fxml")));
                main.root.getChildren().add(sellerPage);
            }
            else{
                    System.out.println("we are logged in as buyer");
                    ProductPage productPage= new ProductPage();

                    Header header= new Header();
                    AnchorPane productPane= new AnchorPane();
                    productPane.getChildren().add(productPage.products());
                    productPane.setLayoutX(150);
                    productPane.setLayoutY(150);
                    main.root.getChildren().clear();
                    main.root.getChildren().addAll(header.root, productPane);

            }
            System.out.println("The user is present in the User Table ");
        }
        else{
            //System.out.println("The user is not present in the User Table ");
            Dialog<String> dialog= new Dialog<>();
            dialog.setTitle("LogIn");
            ButtonType type= new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("LogIn failed, Please check username/Password and try again");
            dialog.showAndWait();
        }
    }

}
